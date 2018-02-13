package Model;

import java.net.Socket;
import java.util.Map;

public class Table {

	/**
	 * Map contenant la liste des joueurs de la table
	 */
	public Map<Joueur, Socket> listeJoueurs;
	
	/**
	 * Paquet utilisé sur la table en question
	 * @see Deck
	 */
	public Deck paquet;
	
	/**
	 * Dealer ou Banque propre à une table, le dealer est un joueur particulier.
	 * @see Joueur
	 */
	public Joueur dealer;

	
	public Table(Deck paquet, Joueur dealer) {
		this.dealer = new Joueur("Dealer");
	}

	/**
	 * Ajoute un joueur à la table.
	 * @param s ?? Ghislain
	 * @param j joueur à ajouter
	 * @see Joueur
	 * @see Socket
	 */
	public void ajoutJoueur(Socket s, Joueur j) {
		this.listeJoueurs.put(j, s);
	}

	/**
	 * Retire un joueur de la table.
	 * @param j joueur à retirer
	 */
	public void retireJoueur(Joueur j) {
		this.listeJoueurs.remove(j);
	}
	
}