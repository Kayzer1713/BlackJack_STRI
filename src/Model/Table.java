package Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Controller.Casino;

public class Table extends Thread{

	/**
	 * Map contenant la liste des joueurs de la table
	 */
	private HashMap<String, Joueur> listeJoueursTable;

	/**
	 * Paquet utilisé sur la table en question
	 * @see Deck
	 */
	private Deck paquet;

	/**
	 * Mise minimal requise pour jouer sur la table
	 */
	private int miseTable;

	/**
	 * Boléen indiquant si table dynamique = 0, table permanente/statique = 1
	 */
	private boolean tablePermanente;

	/**
	 * Nombre de joueurs sur la table
	 */
	private int nbJoueurMax;

	/**
	 * Constructeur table
	 * @param dealer
	 */
	public Table(Casino casino, Joueur dealer, int miseTable, boolean tablePermanente, int nbJoueurMax) {
		this.listeJoueursTable = new HashMap<String, Joueur>();
		ajoutJoueur(dealer.getNom(), dealer);
		this.paquet = new Deck();
		this.paquet.reset();
		this.miseTable = miseTable;
		this.tablePermanente = tablePermanente;
		this.nbJoueurMax = nbJoueurMax;
	}

	/**
	 * Ajoute un joueur à la table si la table n'est pas pleine.
	 * @param s Socket du joueur
	 * @param j joueur à ajouter
	 * @see Joueur
	 * @see Socket
	 */
	public void ajoutJoueur(String pseudo, Joueur j) {
		if(listeJoueursTable.size()-1 <= nbJoueurMax){
			this.listeJoueursTable.put(pseudo, j);
		} else {
			System.out.println("Nombre de joueurs max atteint.");
			envoiMessage("maxTable", j.getOutJoueur());
		}
	}

	/**
	 * Retire un joueur de la table.
	 * @param j joueur à retirer
	 */
	public void retireJoueur(String pseudo) {
		this.listeJoueursTable.remove(pseudo);
	}

	public void distribuer(Joueur j) {
		j.ajoutCarte(this.paquet.prendreCarte());
	}

	/**
	 * Donne une carte au joueur en mettant à jour le status de la table chez tout les clients
	 */
	public void majCarte() {
		// met a jour les informations chez tout les clients qu'il y a eu un changement
	}
	public int nbJoueur() {
		return this.listeJoueursTable.size()-1;
	}

	@Override
	public String toString() {
		String description = "";
		if ( this.tablePermanente )
			description += "Permanante: ";
		else
			description += "Temporaire | ";
		description += "Mise minimum:" + miseTable + " | Nb Joueurs: " + (listeJoueursTable.size()-1) + "/" + nbJoueurMax;
		return description;
	}

	public void run() {
		// parcour de la liste des joueurs:
		Joueur jCourant;
		
		Set<String> cles = listeJoueursTable.keySet();
		Iterator<String> it = cles.iterator();
		
		// distribution premier tour
		while (it.hasNext()){
			String cle = it.next();
			jCourant = listeJoueursTable.get(cle);
			distribuer(jCourant);
		}
		
		// distribution deuxième tour
		while (it.hasNext()){
			String cle = it.next();
			jCourant = listeJoueursTable.get(cle);
			if (jCourant.isDealer() == true)
				distribuer(jCourant);
			distribuer(jCourant);
		}
	}
	
	private void envoiMessage(String msg, ObjectOutputStream out)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("Envoi>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
}





