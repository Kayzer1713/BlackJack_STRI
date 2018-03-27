package Controller;

import Model.Joueur;
import Model.Table;
/**
 * Controller Partie
 * @category Controller
 *
 */
public class Partie {
	
	/**
	 *Variable table de type Table 
	 */
	private Table table;
	/**
	 * Variable dealer de type Joueur
	 */
	private Joueur dealer;

	/**
	 * Donne une carte au joueur j
	 * @param j
	 */
	public void distribuer(Joueur j) {

	}
	/**
	 * Donne une carte au joueur en mettant à jour le status de la table chez tout les clients
	 */
	public void majCarte() {
		// met a jour les informations chez tout les clients qu'il y a eu un changement
	}
	/**
	 * Méthode de lancement d'une partie
	 */
	public Partie() {
		this.dealer = new Joueur("Dealer", null);
		this.table = new Table(this.dealer, 0, false, 0);

	}
}
