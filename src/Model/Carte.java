package Model;

public class Carte {
	
	int numero;		//1 à 13, as to king
	Couleur couleur;		//1=trefle, 2=carreau, 3=coeur, 4=pique
	
	
	public Carte(int numero, Couleur couleur) {
		this.numero = numero;
		this.couleur = couleur;
	}

	public int getValue() {
		return numero;
	}

	public void setValue(int numero) {
		this.numero = numero;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
}
