
public class Carte {
	
	int value;		//1 à 13, as to king
	int suit;		//1=trefle, 2=carreau, 3=coeur, 4=pique
	
	
	//constructeur
	public Carte(int value, int suit) {
		super();
		this.value = value;
		this.suit = suit;
	}

	public int getvalue() {
		return value;
	}

	public void setvalue(int value) {
		this.value = value;
	}

	public int getsuit() {
		return suit;
	}

	public void setsuit(int suit) {
		this.suit = suit;
	}
}
