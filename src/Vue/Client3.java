package Vue;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client3{

	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;
	@SuppressWarnings("unused")
	private String pseudo;

	Client3(){}

	void run()
	{
		try{
			requestSocket = new Socket("localhost", 9999);
			System.out.println("Connected to localhost in port 9999");

			// Ouverture des connections
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			try {
				message = (String)in.readObject();
				System.out.println("Reçu>" + message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Boucle principale de communication
			do{
				switch(message) {
				case "new":
					System.out.println("Bienvenue dans le casino !");
					premiereConnection();
					envoiMessage("STOP");
					break;
				case "majTable":
					System.out.println("Reçu>maj Table");
					afficheTable();
					break;
				case "hit":
					System.out.println("Reçu>tire carte");
					break;
				case "again":
					System.out.println("Reçu>nouv partie");
					break;
				default :
					System.out.println("Reçu>"+message);                    	
				}
			}while(!message.equals("STOP"));
			System.out.println(message + ": OK");
			System.out.println("Connexion fermé");

		} catch(UnknownHostException unknownHost){
			System.err.println("vous essayé de vous connecté à un hote inconnu!");
		} catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			// Fermeture de la connexion
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
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

	private String attenteMessage() {
		System.out.println("Attente de réponse du serveur...");
		String reçu = null;
		long timeout = 5000;
		long tempsActuel = System.currentTimeMillis();
		try {
			do {
				reçu = (String)in.readObject();
			}	while( ( System.currentTimeMillis()-tempsActuel < timeout ) && reçu.equals("") );
			if ( reçu == null )
				throw new Exception("Timeout: le message n'as pas était reçus à temps ou le client ne répond plus...");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reçu;
	}

	private void premiereConnection() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour veuillez saisir un pseudo :");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		envoiMessage(str);
		message = attenteMessage();
		
		while ( message.equals("pseudoDejaExistant") ) {
			System.out.println("Erreur: pseudo déjà existant veuillez essayer autre chose...");
			str = sc.nextLine();
			envoiMessage(str);
			message = attenteMessage();
		}
		
		if (message.equals("valide")) {
			System.out.println("Vous êtes maintenant connecté sour le pseudo:" + str);
			this.pseudo = str;
		} else {
			envoiMessage("STOP");
		}
		sc.close();
	}
	
	private String afficheTable() {
		System.out.println("affichage des tables");
		return "not implemented yet";
	}

	public static void main(String args[])
	{

		Client client = new Client();		
		client.run();
	}
}