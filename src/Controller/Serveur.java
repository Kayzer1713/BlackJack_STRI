package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Model.Joueur;

public class Serveur {

	private HashMap<String, Joueur> listeJoueurs;
	private int nbClients = 0; // nombre total de clients connect�s

	//** Methode : d�truit le client 
	synchronized public void suprClient(String pseudo)
	{
		if (listeJoueurs.containsKey(pseudo)) // l'�l�ment existe ...
		{
			listeJoueurs.remove(pseudo); // ... on le supprime
			nbClients--; // un client en moins ! snif
		}
	}

	//** Methode : ajoute un nouveau client dans la liste **
	synchronized public int ajoutClient(PrintWriter out, String pseudo)
	{
		nbClients++; // un client en plus ! ouaaaih
		listeJoueurs.put(pseudo, new Joueur(pseudo, out)); // on ajoute le nouveau flux de sortie au tableau
		return listeJoueurs.size()-1; // on retourne le num�ro du client ajout� (size-1)
	}

	/**

	//** Methode : envoie le message � tous les clients **
	synchronized public void sendAll(String message,String sLast)
	{
		PrintWriter out; // declaration d'une variable permettant l'envoi de texte vers le client
		for (int i = 0; i < listeJoueurs.size(); i++) // parcours de la table des connect�s
		{
			out = (PrintWriter) listeJoueurs.elementAt(i); // extraction de l'�l�ment courant (type PrintWriter)
			if (out != null) // s�curit�, l'�l�ment ne doit pas �tre vide
			{
				// ecriture du texte pass� en param�tre (et concat�nation d'une string de fin de chaine si besoin)
				out.print(message+sLast);
				out.flush(); // envoi dans le flux de sortie
			}
		}
	}

	 **/
	@SuppressWarnings("resource")
	public static void main(String args[])
	{
		Serveur serveur = new Serveur();
		ServerSocket serveurSocket = null;
		Socket connection = null;

		try {
			serveurSocket = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();

		}
		while (true) { // boucle d'attente de connexion des clients 
			try {
				System.out.println("attente de nouvelle connection...");
				connection = serveurSocket.accept();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			// nouveau thread pour le prochain client
			new ServeurThread(connection).start();
		}
	}
}
