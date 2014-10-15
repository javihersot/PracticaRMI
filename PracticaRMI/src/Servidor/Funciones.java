package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Funciones extends Remote{
	
	public boolean register(String userName, String password, String password2) throws RemoteException;
	public boolean connect(String userName, String password)throws RemoteException;
	public void disconnect(String userName)throws RemoteException;

}