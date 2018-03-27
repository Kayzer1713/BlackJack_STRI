package Model;

import java.net.Socket;
import java.util.HashMap;

public class Table {

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
	private int nombreJoueurs;
	
	/**
	 * Constructeur table
	 * @param dealer
	 */
	public Table(Joueur dealer, int miseTable, boolean tablePermanente, int nombreJoueurs) {
		ajoutJoueur(dealer.getNom(), dealer);
		this.paquet = new Deck();
		this.paquet.reset();
		this.miseTable = miseTable;
		this.tablePermanente = tablePermanente;
		
	}

	/**
	 * Ajoute un joueur à la table si la table n'est pas pleine.
	 * @param s Socket du joueur
	 * @param j joueur à ajouter
	 * @see Joueur
	 * @see Socket
	 */
	public void ajoutJoueur(String pseudo, Joueur j) {
		if(listeJoueursTable.size()-1 <= nombreJoueurs){
			this.listeJoueursTable.put(pseudo, j);
		} else {
			System.out.println("Nombre de joueurs max atteint.");
		}
	}

	/**
	 * Retire un joueur de la table.
	 * @param j joueur à retirer
	 */
	public void retireJoueur(String pseudo) {
		this.listeJoueursTable.remove(pseudo);
	}
	
	public int nbJoueur() {
		return this.listeJoueursTable.size()-1;
	}
	
}