package Model;

import java.net.Socket;
import java.util.ArrayList;

public class Joueur {

	/**
	 * Liste des cartes en main du joueur
	 */
	private ArrayList<Carte> main;

	/**
	 * Booléen permettant de savoir si le joueur est toujours en jeu
	 */
	private Boolean horsJeu;

	/**
	 * pseudo/nom du joueur
	 */
	private String nom;

	/**
	 * Constructeur défault
	 * @param nom du joueur
	 */

	private Socket socketJoueur;

	public Joueur(String n, Socket s) {
		this.nom = n;
		this.setSocketJoueur(s);
	}

	/**
	 * @return nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom du joueur
	 */
	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public Socket getSocketJoueur() {
		return socketJoueur;
	}

	public void setSocketJoueur(Socket socketJoueur) {
		this.socketJoueur = socketJoueur;
	}


	/**
	 * 
	 * @return liste des cartes du joueur en main
	 */
	public ArrayList<Carte> getMain() {
		return main;
	}

	/**
	 * @param Carte
	 */
	public void ajoutCarte(Carte c) {
		this.main.add(c);
	}

	/**
	 * @return si le joueur est encore en jeu
	 */
	public Boolean getHorsJeu() {
		return horsJeu;
	}

	/**
	 * @param horsJeu
	 */
	public void setHorsJeu(Boolean horsJeu) {
		this.horsJeu = horsJeu;
	}

	/**
	 * remet à zéro la main du joueur
	 */
	public void reset() {
		this.main.clear();
	}

	/**
	 * @return	valeur de la main du joueur
	 */
	public int valeurMain() {
		int val = 0;
		for( int i = 0; i < main.size(); i++) {
			val += main.get(i).getValeur();
		}
		return val;
	}

	/**
	 * @return un string décrivant la main du joueur
	 */
	public void afficheMain() {
		System.out.println(main);
	}

}