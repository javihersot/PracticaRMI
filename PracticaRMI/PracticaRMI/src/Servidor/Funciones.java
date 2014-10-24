package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Funciones extends Remote{
	
	public boolean register(String userName, String password, String password2) throws RemoteException;
	public UserImpl connect(String userName, String password) throws  FailLoggingException, RemoteException;
	public boolean searchUser(String user) throws RemoteException;
	public void disconnect(String userName)throws RemoteException;

}
