package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Cliente.Callback;
import Cliente.CallbackInterface;


public interface Funciones extends Remote{
	
	/*
	 * Método para registrarse en el sistema.
	 */
	public int register(String userName, String password, String password2) throws RemoteException;
	
	/*
	 * Método para conectarse al sistema.
	 */
	public boolean connect(String userName, String password, CallbackInterface callback) throws RemoteException;
	
	/*
	 * Método para buscar a un usuario y poder acceder a su perfil.
	 */
	public UserImpl searchUser(String user) throws RemoteException;
	
	/*
	 * Método para desconectarse del sistema.
	 */
	public void disconnect(String userName)throws RemoteException;
	
	/*
	 * Método auxiliar para poder ver el perfil de otros usuarios.
	 */
	public void verUser(String userName) throws RemoteException;
}
