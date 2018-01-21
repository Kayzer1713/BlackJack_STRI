package Model;

import java.util.ArrayList;

public class Joueur {
	
	/**
	 * Liste des cartes en main du joueur
	 */
	public ArrayList<Carte> main = null;
	
	/**
	 * Bool�en signifiant 
	 */
	public Boolean horsJeu;
	public String nom;

	public Joueur(String n) {
		this.nom = n;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Carte> getMain() {
		return main;
	}

	public void ajoutCarte(Carte c) {
		this.main.add(c);
	}

	public Boolean getHorsJeu() {
		return horsJeu;
	}

	public void setHorsJeu(Boolean horsJeu) {
		this.horsJeu = horsJeu;
	}

	public void reset() {
	}

	public void valeurMain() {
	}

	public void afficheMain() {
	}

}
