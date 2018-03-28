package Controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Model.Joueur;
import Model.Table;
/**
 * Model Casino
 * @category Model
 *
 */
public class Casino {
	/**
	 * Liste de nom pour instancier des dealers
	 */
	private String[] nameDealer = {"Charles de Gaulle",
			"Louis Pasteur",
			"Coluche",
			"Moli�re",
			"Bourvil",
			"Raymond Poulidor",
			"Jules Ferry",
			"Claude Fran�ois",
			"Michel Drucker",
	"Nicolas Sarkozy"};
	private HashMap<String, Joueur> listeJoueurs;
	private int nbClients;// nombre total de clients connect�s

	/**
	 * ArrayList des tables
	 */
	private ArrayList<Table> listTable ;
	/**
	 * ArrayList des Dealers
	 */
	private ArrayList<Joueur> listDealer ;
	/**
	 * Boolean d'itenification du type de table : 
	 * True : table permanente
	 * False : table dynamique
	 */
	private boolean tablePerm;
	/**
	 * Tableau des mises :
	 * 5, 10, 20, 30
	 */
	private int[] mise = {5,10,20,30};
	/**
	 * Nombre de joueur par table, ici 6.
	 */
	private int nbjoueursTable = 6;
	/**
	 * Sauvegarde de l'index de navigation au sein de la liste des dealer afin de ne pas affecter un m�me dealer � une nouvelle table
	 */
	private int indexDealerUse;	
	/**
	 * Constructeur : 
	 * Instanciation de la liste de dealer via la liste de pr�nom.
	 * Instanciation de la liste de table
	 */

	public Casino(){	
		this.nbClients=0;
		this.listeJoueurs = new HashMap<String, Joueur>();
		// boucle de cr�ation des dealers
		for(int j=0; j<=10;j++) {
			listDealer.add(new Joueur(nameDealer[j]));
		}
		// boucle de cr�ation des tables
		for(int i=0; i<=3; i++) {		
			indexDealerUse = i +indexDealerUse;
			listTable.add(new Table(this, listDealer.get(i),mise[i],true,nbjoueursTable));
		}	
	}

	/**
	 * M�thode d'ajout de table avec en param�tre un boolean qui d�finit le type de la table:
	 * True = Table permanente
	 * False = Table dynamique
	 * @param tablePerm
	 */
	public void addTable(boolean tablePerm) {
		indexDealerUse++;
		tablePerm = this.tablePerm;
		listTable.add(new Table(this, listDealer.get(indexDealerUse),mise[1],tablePerm,nbjoueursTable));
	}
	/**
	 * @return the listeJoueurs
	 */
	public HashMap<String, Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	/**
	 * @param listeJoueurs the listeJoueurs to set
	 */
	public void setListeJoueurs(HashMap<String, Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}
	
	/**
	 * @return 
	 * 
	 */
	public  ArrayList<Table> getListeTable() {
		return listTable;
	}

	//** Methode : d�truit le client 
	synchronized public void suprClient(String pseudo)
	{
		if (listeJoueurs.containsKey(pseudo)) // l'�l�ment existe ...
		{
			listeJoueurs.remove(pseudo); // ... on le supprime
			setNbClients(getNbClients() - 1); // un client en moins ! snif
		}
	}

	//** Methode : ajoute un nouveau client dans la liste **
	synchronized public int ajoutClient(ObjectOutputStream out, String pseudo)
	{
		setNbClients(getNbClients() + 1); // un client en plus ! ouaaaih
		listeJoueurs.put(pseudo, new Joueur(pseudo, out)); // on ajoute le nouveau flux de sortie au tableau
		return listeJoueurs.size()-1; // on retourne le num�ro du client ajout� (size-1)
	}

	//** Methode : envoie le message � tous les clients **
	synchronized public void envoie(String message,String sLast)
	{
		Set<String> cles = listeJoueurs.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			String cle = it.next();
			Joueur j = listeJoueurs.get(cle); // extraction de l'�l�ment courant (type PrintWriter)			if (out != null) // s�curit�, l'�l�ment ne doit pas �tre vide
			{
				// ecriture du texte pass� en param�tre (et concat�nation d'une string de fin de chaine si besoin)
				try {
					j.getOutJoueur().writeObject(message+sLast);
					j.getOutJoueur().flush(); // envoi dans le flux de sortie
				} catch (IOException e) {e.printStackTrace();}

			}
		}
	}

	public int getNbClients() {
		return nbClients;
	}

	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}

	@SuppressWarnings("resource")
	public static void main(String args[])
	{
		Casino casino = new Casino();
		ServerSocket serveurSocket = null;
		Socket connection = null;

		try {
			serveurSocket = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();

		}
		while (true) { // boucle d'attente de connexion des clients 
			try {
				System.out.println("attente de nouvelle connection...");
				connection = serveurSocket.accept();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			// nouveau thread pour le prochain client
			new ServeurThread(casino, connection).start();
			System.out.println(casino.getNbClients());
		}
	}
	
}




