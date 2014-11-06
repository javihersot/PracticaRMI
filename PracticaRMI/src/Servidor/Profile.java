package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Profile extends Remote{
	
	public void setName(String name) throws RemoteException;
	public void setPhoto(String photo) throws RemoteException;
	public void setWeb(String web) throws RemoteException;
	public void setInfo(String info) throws RemoteException;
	public String getInfo() throws RemoteException;
	public String getWeb() throws RemoteException;
	public String getName() throws RemoteException;
	public String getPhoto() throws RemoteException;
}
