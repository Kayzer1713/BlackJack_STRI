package Serveur;
import java.net.*;
import java.io.*;

/** 
 * @author  Vidal Ghislain
 */
public class Server extends Object {

  /** Port par défaut */
  public final static int portEcho = 7;
    
  /**
  * @param args the command line arguments
  */
  public static void main (String args[]) {
    ServerSocket    leServeur = null;
    Socket          connexionCourante;
    InputStream     entreeSocket;
    OutputStream    sortieSocket;
    
    try {
      leServeur = new ServerSocket(portEcho);
    }
    catch (IOException ex)
    {
      // fin de connexion
      System.err.println("Impossible de cr�er un socket serveur sur ce port : "+ex);
      
      try {
        // on demande un port anonyme 
        leServeur = new ServerSocket(0);
      }
      catch (IOException ex2)
      {
        // fin de connexion
        System.err.println("Impossible de cr�er un socket serveur : "+ex);
      }
    }
     
    if
      (leServeur != null)
    {
     try {
      System.err.println("En attente de connexion sur le port : "+leServeur.getLocalPort());
      while (true) {
        connexionCourante = leServeur.accept();
        
        System.err.println("Nouvelle connexion : "+connexionCourante);
        entreeSocket = connexionCourante.getInputStream();
        sortieSocket = connexionCourante.getOutputStream();
        
        try {
          int b = 0;
          while (b != -1) {
            b = entreeSocket.read();
            sortieSocket.write(b);
          }
          System.err.println("Fin de connexion");
        }
        catch (IOException ex)
        {
          // fin de connexion
          System.err.println("Fin de connexion : "+ex);
        }
        
        connexionCourante.close();
      }
    }
    catch (Exception ex)
    {
      // erreur de connexion
      System.err.println("Une erreur est survenue : "+ex);
      ex.printStackTrace();
    }
   } 
  }

}