package Servidor;

public class FailLoggingException extends Exception {
	
	public FailLoggingException(){
		super("Usuario o contraseña incorrectos.");
	}

}
