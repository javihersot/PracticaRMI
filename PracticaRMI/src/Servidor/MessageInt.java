package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInt extends Remote{
	
	/*
	 * Método que muestra el contenido de un tweet o un mensaje directo.
	 */
	public String getContent() throws RemoteException;
	
	/*
	 * Método que devuelve el autor de un tweet o mensaje directo.
	 */
	public String getAutor() throws RemoteException;
	
	/*
	 * Método que devuelve el número de retweets de un tweet.
	 */
	public int getRetweets () throws RemoteException;
	
	/*
	 * Método que devuelve el número de usuarios que han marcado como favorito un tweet.
	 */
	public int getFavs() throws RemoteException;
	

}
