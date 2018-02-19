package Model;

import java.net.Socket;
import java.util.HashMap;

public class Table {

	/**
	 * Map contenant la liste des joueurs de la table
	 */
	private HashMap<String, Joueur> listeJoueurs;
	
	/**
	 * Paquet utilis� sur la table en question
	 * @see Deck
	 */
	private Deck paquet;
	
	public Table(Joueur dealer) {
		ajoutJoueur(dealer.getNom(), dealer);
		this.paquet = new Deck();
		this.paquet.reset();
	}

	/**
	 * Ajoute un joueur � la table.
	 * @param s Socket du joueur
	 * @param j joueur � ajouter
	 * @see Joueur
	 * @see Socket
	 */
	public void ajoutJoueur(String pseudo, Joueur j) {
		this.listeJoueurs.put(pseudo, j);
	}

	/**
	 * Retire un joueur de la table.
	 * @param j joueur � retirer
	 */
	public void retireJoueur(String pseudo) {
		this.listeJoueurs.remove(pseudo);
	}
	
	public int nbJoueur() {
		return this.listeJoueurs.size()-1;
	}
	
}