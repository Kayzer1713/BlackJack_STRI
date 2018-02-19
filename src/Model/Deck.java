package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Deck {

	/**
	 * Liste des cartes du deck
	 * @see Carte
	 */
	private ArrayList<Carte> listeCartes = new ArrayList<Carte>();

	/**
	 * Constructeur Deck
	 * @see reset
	 */
	public Deck() {
		this.reset();
	}

	public ArrayList<Carte> getListeCartes() {
		return this.listeCartes;
	}
	public int getTaille() {
		return this.listeCartes.size();
	}

	/**
	 * Remet le deck à neuf.
	 */
	public void reset() {

		// génération des cartes:
		// Pour chaque couleur
		for (int i = 0; i <= 3; i++) {
			// Pour chaque carte:
			for (int y = 1; y <= 13; y++) {
				this.listeCartes.add(new Carte(i, y));
			}	
		}	
	}

	public HashMap<Integer, ArrayList<Integer>> triDeck() {
		HashMap<Integer,ArrayList<Integer>> deckTrie = new HashMap<Integer,ArrayList<Integer>>();
		deckTrie.put(0, new ArrayList<Integer>());
		deckTrie.put(1, new ArrayList<Integer>());
		deckTrie.put(2, new ArrayList<Integer>());
		deckTrie.put(3, new ArrayList<Integer>());
		Carte carte;

		// parcour de la liste des cartes 
		for( int i = 0; i < this.listeCartes.size(); i++) {
			carte = this.listeCartes.get(i);
			deckTrie.get(carte.getCouleur()).add(carte.getNumero());
		}
		// Trie des liste de nombres
		for (int i = 0; i <= 3; i++) {
			Collections.sort(deckTrie.get(i));
		}
		return deckTrie;
	}

	/**
	 * Mélange le deck.
	 */
	public void melange() {
		Collections.shuffle(this.listeCartes);
	}

	/**
	 * Prend une carte du deck pour la distribuer
	 */
	public Carte prendreCarte() {
		Carte c = this.listeCartes.get(0);
		this.getListeCartes().remove(0);
		return c;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String msg = "";
		int num;
		HashMap<Integer,ArrayList<Integer>> deckTrie = triDeck();
		// parcours de la multimap:
		msg += ("Contenu du deck: \n");
		for(int i = 0; i <= 3; i++) {
			msg+= "Couleur: " + Couleur.values()[i] + "\n";
			for( int y = 0; y < deckTrie.get(i).size(); y++ ) {
				num = deckTrie.get(i).get(y);
				msg += String.valueOf(num);
				if (num != 13) {
					msg += ",";
				}
			}
			msg += "\n";
		}
		return msg;
	}

}
