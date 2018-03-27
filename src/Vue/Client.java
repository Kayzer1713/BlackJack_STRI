package Vue;

import java.io.*;
import java.net.*;

public class Client{

	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	Client(){}

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
				System.out.println("Re�u>" + message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Boucle principale de communication
			do{
				try{
					message = (String)in.readObject();
					switch(message) {
					// potentiellement sortir le new
					case "new":
						System.out.println("re�u>nouv joueur");
						envoiMessage("Jacque");
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