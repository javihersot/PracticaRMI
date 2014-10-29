package Servidor;

import java.rmi.server.RemoteObject;

public class DirectMessage extends RemoteObject{
	
	private String remitente;
	private String destinatario;
	private String message;
	
	public DirectMessage(String remitente, String destinatario, String message) {
		this.remitente=remitente;
		this.destinatario=destinatario;
		this.message=message;
	}

	public String getRemitente() {
		return remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getMessage() {
		if(message.length() >= 140){
			message = message.substring(0, 137);
			message = message + "...";
			System.out.println(message.length());
		}
		return message;
	}

}