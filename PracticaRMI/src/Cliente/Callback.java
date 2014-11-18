package Cliente;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Servidor.Tweet;

public class Callback extends UnicastRemoteObject implements CallbackInterface, Serializable{

	public Callback() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 134195810117452489L;
	
	/*
	 * Método que notifica al cliente de que le ha llegado un nuevo mensaje directo a su mailbox.
	 */
	public void notifyMessage() throws RemoteException{
		Cliente.cliente.pantallaPrincipal.notify("Tienes un nuevo mensaje");
	}
	
	/*
	 * Mensaje que notifica al cliente de que tiene un nuevo tweet en su time line y se lo muestra.
	 */
	public void notifyTweet() throws RemoteException{
		Cliente.cliente.pantallaPrincipal.refresh();
	}
	
	/*
	 * Método que notifica al cliente de que otro usuario ha comenzado a seguirle.
	 */
	public void notifyFollower(String seguidor) throws RemoteException{
		Cliente.cliente.pantallaPrincipal.notify(seguidor + " a comenzado a seguirte.");
	}

}
