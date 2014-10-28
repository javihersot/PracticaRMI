package Servidor;

public class InexistentUserException extends Exception {
	
	public InexistentUserException(){
		super("Usuario incorrecto.");
	}

}