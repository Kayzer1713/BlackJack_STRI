package Model;

import java.net.Socket;
import java.util.Map;

public class Table {

	public Map<Joueur, Socket> listeJoueurs;
	public Deck paquet;
	public Joueur dealer;

	public Table(Deck paquet, Joueur dealer) {
		this.dealer = new Joueur("Dealer");
	}

	public void ajoutJoueur(Socket s, Joueur j) {
		this.listeJoueurs.put(j, s);
	}

	public void retireJoueur(Joueur j) {
		this.listeJoueurs.remove(j);
	}
	
}