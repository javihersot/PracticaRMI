package Cliente;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Callback extends UnicastRemoteObject implements CallbackInterface, Serializable{

	public Callback() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 134195810117452489L;
	

	public void notifyMessage() throws RemoteException{
		System.out.println("Tienes un nuevo mensaje.");
	}
	
	public void notifyTweet() throws RemoteException{
		System.out.println("Una persona a la que sigues a tweeteado.");
	}
	
	public void notifyFollower(String seguidor) throws RemoteException{
		System.out.println(seguidor +" Ha comenzado a seguirte.");
	}

}
