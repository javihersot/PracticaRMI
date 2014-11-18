package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Servidor.Tweet;

public interface CallbackInterface extends Remote{
	
	/*
	 * Método que notifica al cliente de que le ha llegado un nuevo mensaje directo a su mailbox.
	 */
	public void notifyMessage() throws RemoteException;
	
	/*
	 * Mensaje que notifica al cliente de que tiene un nuevo tweet en su time line y se lo muestra.
	 */
	public void notifyTweet()throws RemoteException;
	
	/*
	 * Método que notifica al cliente de que otro usuario ha comenzado a seguirle.
	 */
	public void notifyFollower(String segidor)throws RemoteException;

}
