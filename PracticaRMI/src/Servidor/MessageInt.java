package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInt extends Remote{
	
	public String getContent() throws RemoteException;
	public String getAutor() throws RemoteException;
	public int getRetweets () throws RemoteException;
	public int getFavs() throws RemoteException;
	

}
