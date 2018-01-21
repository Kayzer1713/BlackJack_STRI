package Model;

public class Carte {
	
	/**
	 * Le numero de la carte
	 */
	int numero;
	
	/**
	 * La couleur de la carte
	 * @see Couleur
	 */
	Couleur couleur;
	
	int valeur;
	/**
	 * Constructor
	 * @param numero
	 * @param couleur
	 */
	public Carte(int numero, Couleur couleur) {
		this.numero = numero;
		this.couleur = couleur;
	}

	/**
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * @return numero
	 */
	public int getValeur() {
		return numero;
	}
	
	/**
	 * @param numero
	 */
	public void setVaeur(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * @return couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * @param couleur
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
}
