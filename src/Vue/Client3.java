package Vue;

import java.io.*;
import java.net.*;
/**
 * 
 * Vue Client 3
 *
 */
public class Client3{
	/**
	 * Composition du client 3:
	 * Socket requestSocket;
	 * ObjectOutputStream out;
	 * ObjectInputStream in;
	 * String message;
	 */
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	Client3(){}

	void run()
	{
		/**
		 * Connection du client au serveur
		 */
		try{
			requestSocket = new Socket("localhost", 9999);
			System.out.println("Connected to localhost in port 9999");

			/**
			 * Ouverture des connections
			 */
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			try {
				message = (String)in.readObject();
				System.out.println("Re�u>" + message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			/**
			 * Boucle principale de communication
			 */
			do{
				try{
					message = (String)in.readObject();
					switch(message) {
					// potentiellement sortir le new
					case "new":
						System.out.println("re�u>nouv joueur");
						envoiMessage("Pierre");
						envoiMessage("STOP");
						break;
					case "majTable":
						System.out.println("Re�u>maj Table");
						afficheTable();
						break;
					case "hit":
						System.out.println("Re�u>tire carte");
						break;
					case "again":
						System.out.println("Re�u>nouv partie");
						break;
					default :
						System.out.println("Re�u>"+message);                    	
					}
				}
				catch(ClassNotFoundException classNot){
					System.err.println("Format de donn�es inconnue!");
				}
			}while(!message.equals("STOP"));
			System.out.println(message + ": OK");
			System.out.println("Connexion ferm�");

		} catch(UnknownHostException unknownHost){
			System.err.println("vous essay� de vous connect� � un hote inconnu!");
		} catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			/**
			 * Fermeture de la connexion
			 */
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
	 * M�thode qui permet d'envoyer un message
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
	/**
	 * Retourne le statut mis � jour de la table
	 * @return 
	 */
	private String afficheTable() {
		System.out.println("affichage des tables");
		return "not implemented yet";
	}
	
	public static void main(String args[])
	{
		Client3 client = new Client3();
		client.run();
	}
}