package Controller;

import Model.Joueur;
import Model.Table;

public class Partie {

	private Table table;
	private Joueur dealer;


	public void distribuer(Joueur j) {

	}

	public void majCarte() {
		// met a jour les informations chez tout les clients qu'il y a eu un changement
	}

	public Partie() {
		this.dealer = new Joueur("Dealer", null);
		this.table = new Table(this.dealer);

	}
}
