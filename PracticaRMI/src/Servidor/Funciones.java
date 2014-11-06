package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Cliente.Callback;
import Cliente.CallbackInterface;


public interface Funciones extends Remote{
	
	public int register(String userName, String password, String password2) throws RemoteException;
	public boolean connect(String userName, String password, CallbackInterface callback) throws RemoteException;
	public UserImpl searchUser(String user) throws RemoteException;
	public void disconnect(String userName)throws RemoteException;
	public void verUser(String userName) throws RemoteException;
}
