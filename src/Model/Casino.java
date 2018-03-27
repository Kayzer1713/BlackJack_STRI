package Model;

import java.util.ArrayList;
/**
 * Model Casino
 * @category Model
 *
 */
public class Casino {
	/**
	 * Liste de nom pour instancier des dealers
	 */
	private String[] nameDealer = {"Charles de Gaulle","Louis Pasteur","Coluche","Molière","Bourvil","Raymond Poulidor","Jules Ferry","Claude François","Michel Drucker","Nicolas Sarkozy"};
	/**
	 * ArrayList des tables
	 */
	private ArrayList<Table> listTable ;
	/**
	 * ArrayList des Dealers
	 */
	private ArrayList<Joueur> listDealer ;
	/**
	 * Boolean d'itenification du type de table : 
	 * True : table permanente
	 * False : table dynamique
	 */
	private boolean tablePerm;
	/**
	 * Tableau des mises :
	 * 5, 10, 20, 30
	 */
	private int[] mise = {5,10,20,30};
	/**
	 * Nombre de joueur par table, ici 6.
	 */
	private int nbjoueursTable = 6;
	/**
	 * Sauvegarde de l'index de navigation au sein de la liste des dealer afin de ne pas affecter un même dealer à une nouvelle table
	 */
	private int indexDealerUse;
	
	/**
	 * Constructeur : 
	 * Instanciation de la liste de dealer via la liste de prénom.
	 * Instanciation de la liste de table
	 */
	Casino(){	
		for(int j=0; j<=10;j++) {
			listDealer.add(new Joueur(nameDealer[j],null));
		}
		for(int i=0; i<=3; i++) {		
			indexDealerUse = i +indexDealerUse;
			listTable.add(new Table(listDealer.get(i),mise[i],true,nbjoueursTable));
		}	
	}
	/**
	 * Méthode d'ajout de table avec en paramètre un boolean qui définit le type de la table:
	 * True = Table permanente
	 * False = Table dynamique
	 * @param tablePerm
	 */
	public void addTable(boolean tablePerm) {
		indexDealerUse++;
		tablePerm = this.tablePerm;
		listTable.add(new Table(listDealer.get(indexDealerUse),mise[1],tablePerm,nbjoueursTable));
	}

}