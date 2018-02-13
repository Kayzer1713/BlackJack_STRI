package Model;

import java.util.ArrayList;

public class Joueur {
	
	/**
	 * Liste des cartes en main du joueur
	 */
	public ArrayList<Carte> main = null;
	
	/**
<<<<<<< HEAD
	 * Booléen signifiant si le joueur est éliminé ou non
=======
	 * Booléen permettant de savoir si le joueur est toujours en jeu
>>>>>>> Ajout des commentaire javadoc v1
	 */
	public Boolean horsJeu;
	
	/**
	 * pseudo/nom du joueur
	 */
	public String nom;

	/**
	 * Constructeur défault
	 * @param nom du joueur
	 */
	public Joueur(String n) {
		this.nom = n;
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
		return val;
	}
	
	/**
	 * @return un string décrivant la main du joueur
	 */
	public String afficheMain() {
		
		return null;
	}

}