package Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Serveur{
	ServerSocket serveurSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Serveur(){}
	void run()
	{
		// Cr�ation du socket
		try {
			serveurSocket = new ServerSocket(9999);

			// Attente de la connexion
			System.out.println("Attente de connexion...");
			connection = serveurSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress());

			//Initialisation du flux de donn�es
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			envoiMessage("Connexion r�ussi");

			// Init nouv joueur
			envoiMessage("new");
			envoiMessage("Bonjour voulez vous jouer au BlackJack ?");

			// Boucle principale de communication
			do{
				message = attenteMessage();
				System.out.println("Re�u>" + message);
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
				serveurSocket.close();
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
		System.out.println("Attente de r�ponse du client...");
		String re�u;
		long timeout = 5000;
		long tempsActuel = System.currentTimeMillis();
		do {
			re�u = (String)in.readObject();
		} while( ( System.currentTimeMillis()-tempsActuel < timeout ) && re�u.equals("") );
		if ( re�u == null )
			throw new Exception("Timeout: le message n'as pas �tait re�us � temps ou le client ne r�pond plus...");
		return re�u;
	}
	

	public static void main(String args[])
	{
		Serveur server = new Serveur();
		while(true){
			server.run();
		}
	}
}
