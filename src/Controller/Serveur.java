package Controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Model.Joueur;

public class Serveur {

	private HashMap<String, Joueur> listeJoueurs;

	private int nbClients;// nombre total de clients connectés

	public Serveur() {
		this.setNbClients(0);
		this.listeJoueurs = new HashMap<String, Joueur>();
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

	//** Methode : détruit le client 
	synchronized public void suprClient(String pseudo)
	{
		if (listeJoueurs.containsKey(pseudo)) // l'élément existe ...
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
		return listeJoueurs.size()-1; // on retourne le numéro du client ajouté (size-1)
	}

	//** Methode : envoie le message à tous les clients **
	synchronized public void envoie(String message,String sLast)
	{
		Set<String> cles = listeJoueurs.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			String cle = it.next();
			Joueur j = listeJoueurs.get(cle); // extraction de l'élément courant (type PrintWriter)			if (out != null) // sécurité, l'élément ne doit pas être vide
			{
				// ecriture du texte passé en paramètre (et concaténation d'une string de fin de chaine si besoin)
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

	public static void main(String args[])
	{
		Serveur serveur = new Serveur();
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
			new ServeurThread(serveur, connection).start();
			System.out.println(serveur.getNbClients());
		}
	}
}
