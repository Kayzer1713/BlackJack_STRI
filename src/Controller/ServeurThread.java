package Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ServeurThread extends Thread{

	private Serveur serveur;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;

	ServeurThread(Serveur serveur, Socket socketClient){
		this.serveur = serveur;
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
			envoiMessage("Connexion réussi");

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
					serveur.ajoutClient(out, message);
					break;
				case "Pierre":
					serveur.ajoutClient(out, message);
					System.out.println("AJOUT DE PIERRE");
					break;
				case "BIBI":
					serveur.ajoutClient(out, message);
					System.out.println("AJOUT DE BIBI");
					break;
				case "TITOUANT":
					serveur.ajoutClient(out, message);
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
		envoiMessage("new");
		message = attenteMessage();
		while( serveur.getListeJoueurs().containsKey(message) || message.equals("q")) {
			envoiMessage("pseudoDejaExistant");
			message = attenteMessage();
		}
		if ( !message.equals("q") ) {
			serveur.ajoutClient(out, message);
			envoiMessage("valide");
		}
	}

	private void choisirTable() {
		envoiMessage("Bienvenue dans le casino !");
		//bjr
		envoiMessage("choixTable");
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
