
public class Carte {
	
	int valeur;		//1 à 13, as to king
	int couleur;		//1=trefle, 2=carreau, 3=coeur, 4=pique
	
	
	//constructeur
	public Carte(int valeur, int couleur) {
		super();
		this.valeur = valeur;
		this.couleur = couleur;
	}

	public int getValue() {
		return valeur;
	}

	public void setValue(int valeur) {
		this.valeur = valeur;
	}

	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
}
