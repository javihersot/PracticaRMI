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
	private String remitente;		//Nombre suario autor del mensaje.
	private String destinatario;	// Nombre del usuario al que va dirigido el mensaje.
	private String message;			// Cuerpo del mensaje.
	private boolean leido;			// Indica si el destinatario ya ha leido este mensaje.
	
	/*
	 * Constructor que inicializa los atributos.
	 */
	public DirectMessage(String remitente, String destinatario, String message) throws RemoteException{
		this.remitente=remitente;
		this.destinatario=destinatario;
		this.message=message;
	}

	/*
	 * Método que devuelve el nombre del autor del mensaje directo.
	 */
	public String getAutor() throws RemoteException{
		return remitente;
	}

	/*
	 * Método que devuelve el nombre del usuario al que va dirigido el mensaje.
	 */
	public String getDestinatario() throws RemoteException{
		return destinatario;
	}

	/*
	 * Método que devuelve el contenido del mensaje.
	 */
	public String getContent() throws RemoteException{ 
		this.leido = true;
		return message;
	}
	
	/*
	 * Método que devuelve true si el mensaje ya ha sido leido por el usuario y false en caso contrario.
	 */
	public boolean leido()throws RemoteException{
		return this.leido;
	}

	/*
	 * Método obligatorio para implementar la interfaz.
	 */
	@Override
	public int getRetweets() throws RemoteException {
		return 0;
	}

	/*
	 * Método obligatorio para implementar la interfaz.
	 */
	@Override
	public int getFavs() throws RemoteException {
		return 0;
	}

}