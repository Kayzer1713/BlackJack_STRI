package Model;

public class Carte {
	
	/**
	 * Le numero de la carte
	 */
	private int numero;
	
	/**
	 * La couleur de la carte
	 * @see Couleur
	 */
	private int couleur;

	/**
	 * La valeur correspondant à la carte
	 */
	@SuppressWarnings("unused")
	private int valeur;
	
	/**
	 * Constructeur Carte
	 * @param numero
	 * @param couleur
	 */
	public Carte(int couleur, int numero) {
		if ( 1 > numero || numero > 13 ) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("le numero de la carte doit être compris entre 1 et 13");
			}
		}
		if (0 > couleur || couleur > 3) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("la couleur de la carte doit être compris entre 0 et 3");
			}
		}
		
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
		if ( 1 > numero || numero > 13 ) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("le numero de la carte doit être compris entre 1 et 13");
			}
		}
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
	public int getCouleur() {
		return this.couleur;
	}
	
	/**
	 * @param couleur
	 */
	public void setCouleur(int couleur) {
		if (0 > couleur || couleur > 3) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("la couleur de la carte doit être compris entre 0 et 3");
			}
		}
		this.couleur = couleur;
	}
	
	@Override
	public String toString() {
		String msg;
		msg = Numero.values()[this.numero-1].toString() + " " + Couleur.values()[this.couleur];
		return msg;
	}
}
