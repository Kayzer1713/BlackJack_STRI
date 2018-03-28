package Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.omg.CORBA.Environment;

import Model.Joueur;
import Model.Table;
public class ServeurThread extends Thread{

	private Casino casino;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;
	private String pseudo;

	ServeurThread(Casino casino, Socket socketClient){
		this.casino = casino;
		this.connection = socketClient;
		this.out = null;
		this.in = null;
		this.message = null;
	}

	public void run()
	{

		try {
			long threadId = Thread.currentThread().getId();
			// Attente de la connexion
			System.out.println("Connection received from " + connection.getInetAddress());

			//Initialisation du flux de données
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());

			// Init nouv joueur
			creationJoueur();
			// Init partie
			choisirTable();

			// Boucle principale de jeux
			do{
				message = attenteMessage();
				System.out.println(threadId + ": Reçus>" + message);

				switch(message) {
				case "Jacque":
					break;
				case "Paul":
					System.out.println("AJOUT DE PAUL");
					casino.ajoutClient(out, message);
					break;
				case "Pierre":
					casino.ajoutClient(out, message);
					System.out.println("AJOUT DE PIERRE");
					break;
				case "BIBI":
					casino.ajoutClient(out, message);
					System.out.println("AJOUT DE BIBI");
					break;
				case "TITOUANT":
					casino.ajoutClient(out, message);
					System.out.println("AJOUT DE TITOUANT");
					break;
				default :
					System.out.println(threadId + ": Fin de la connexion");
					break;
				}
				if (message.equals("STOP"))
					envoiMessage("STOP");

			}while(!message.equals("STOP"));


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// exception de timeout et autre
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally{
			// Fermeture de la connexion
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void creationJoueur() throws Exception {
		envoiMessage("Connexion réussi");
		envoiMessage("new");
		message = attenteMessage();
		while( casino.getListeJoueurs().containsKey(message) || message.equals("q")) {
			envoiMessage("pseudoDejaExistant");
			message = attenteMessage();
		}
		if ( (!message.equals("q")) || (!message.equals("STOP")) ) {
			pseudo = message;
			casino.ajoutClient(out, pseudo);
			envoiMessage("valide");
		}
	}

	private void choisirTable() {
		try {
			envoiMessage("choixTable");
			// on envoit l'affichage complet des tables
			message = casino.afficheTables() + "\n" + (casino.nbTable()+1) + ": Temporaire: Nouvelle table"; 
			envoiMessage(message);
			int numTable = Integer.valueOf(attenteMessage());
			
			while ( numTable < 0 && numTable > casino.nbTable()+1 ) {
				envoiMessage("Veuillez saisir un numéro valide entre 0 et " + casino.nbTable()+1);
				numTable = Integer.valueOf(attenteMessage());
			}
			if (numTable == casino.nbTable()+1) {
				// choix des pamètres:
				envoiMessage("miseTable");
				int miseTable = Integer.valueOf(attenteMessage());
				envoiMessage("nbJoueurMax");
				int nbJoueurMax = Integer.valueOf(attenteMessage());
				
				casino.ajoutTable(new Table(casino, new Joueur(casino.nameDealer[casino.indexDealerUse]), miseTable, false, nbJoueurMax));
				envoiMessage(casino.afficheTables());
				
			} else {
				casino.getListeTable().get(numTable).ajoutJoueur(pseudo, casino.getListeJoueurs().get(pseudo));	
			}
			envoiMessage("valide");
			
		} catch (Exception e) {e.printStackTrace();}
	}

	/**
	 * 
	 * @param msg
	 */
	private void envoiMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("Envoi>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	private String attenteMessage() throws Exception {
		System.out.println("Attente de réponse du client...");
		System.out.println();
		String reçu;
		long timeout = 5000;
		long tempsActuel = System.currentTimeMillis();
		do {
			reçu = (String)in.readObject();
		} while( ( System.currentTimeMillis()-tempsActuel < timeout ) && reçu.equals("") );
		if ( reçu == null )
			throw new Exception("Timeout: le message n'as pas était reçus à temps ou le client ne répond plus...");
		return reçu;
	}
}