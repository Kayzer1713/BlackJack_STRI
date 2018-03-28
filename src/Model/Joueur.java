package Model;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Joueur {

	/**
	 * 	
	 */
	private boolean dealer;

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
	 * Nombre de jetons du joueurs, 100 par défaut.
	 */
	private int jetons;

	private ObjectOutputStream out;

	public Joueur(String pseudo) {
		this.nom = pseudo;
		this.dealer = true;
	}

	/**
	 * Constructeur défault
	 * @param nom du joueur
	 */
	public Joueur(String pseudo, ObjectOutputStream out) {
		this.nom = pseudo;
		this.setOutJoueur(out);
		this.jetons = 100;
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

	public ObjectOutputStream getOutJoueur() {
		return out;
	}

	public void setOutJoueur(ObjectOutputStream out) {
		this.out = out;	
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
	 * Affiche la main du joueur
	 */
	public void afficheMain() {
		System.out.println(main);
	}

	/**
	 * Gain des jetons du joueur
	 */
	public void ajoutJetons(int mise){
		this.jetons = this.jetons + mise;
	}

	/**
	 * Perte des jetons du joueur
	 */
	public void retireJetons(int mise){
		this.jetons = this.jetons - mise;
	}

}