package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

import Cliente.CallbackInterface;

public class DirectMessage extends UnicastRemoteObject implements MessageInt, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8029403149381091397L;
	private String remitente;
	private String destinatario;
	private String message;
	private boolean leido;
	
	public DirectMessage(String remitente, String destinatario, String message) throws RemoteException{
		this.remitente=remitente;
		this.destinatario=destinatario;
		this.message=message;
	}

	public String getAutor() throws RemoteException{
		return remitente;
	}

	public String getDestinatario() throws RemoteException{
		return destinatario;
	}

	public String getContent() throws RemoteException{ 
		this.leido = true;
		return message;
	}
	
	public boolean leido()throws RemoteException{
		return this.leido;
	}

	@Override
	public int getRetweets() throws RemoteException {
		return 0;
	}

	@Override
	public int getFavs() throws RemoteException {
		return 0;
	}

}