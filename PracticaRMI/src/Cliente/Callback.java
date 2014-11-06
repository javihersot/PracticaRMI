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
	

	public void notifyMessage() throws RemoteException{
		Cliente.cliente.pantallaPrincipal.notify("Tienes un nuevo mensaje");
	}
	
	public void notifyTweet() throws RemoteException{
		Cliente.cliente.pantallaPrincipal.refresh();
	}
	
	public void notifyFollower(String seguidor) throws RemoteException{
		Cliente.cliente.pantallaPrincipal.notify(seguidor + " a comenzado a seguirte.");
	}

}
