package Servidor;

public class DirectMessage {
	
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
		return message;
	}

}