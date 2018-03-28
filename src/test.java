import Model.Joueur;
import Model.Table;

public class test {

	public static void main(String[] args) {
		Table t = new Table(new Joueur("dealer1"),25,true,4);
		System.out.println(t);
	}
}