package Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ServeurThread extends Thread{

	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;

	ServeurThread( Socket socketClient){
		
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
			envoiMessage("new");
			envoiMessage("Bonjour voulez vous jouer au BlackJack ?");

			// Boucle principale de communication
			do{
				message = attenteMessage();
				
				System.out.println(threadId + ": Reçus>" + message);
				
				switch(message) {
				// potentiellement sortir le new
				case "Jacque":
					//envoiMessage("Bonjour " + message + " je suis votre serveur");
					break;
				case "Paul":
					//envoiMessage("Bonjour " + message + " je suis votre serveur");
					break;
				default :
					System.out.println(threadId + ": Fin de la connexion");
					break;
				}
				if (message.equals("STOP"))
					envoiMessage("STOP");

			}while(!message.equals("STOP"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	private String attenteMessage() throws Exception {
		System.out.println("Attente de réponse du client...");
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
