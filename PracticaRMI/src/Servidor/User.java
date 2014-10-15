package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public interface User extends Remote{
	
	public boolean setUser(String name) throws RemoteException;
	public boolean setPassword(String oldPassword, String newPassword1, String newPassword2) throws RemoteException;
	public void tweet(String tweet) throws RemoteException;
	public boolean directMessage(String user, String msg) throws RemoteException;
	public boolean searchUser(String user) throws RemoteException;
	public ArrayList<String> following() throws RemoteException;
	public ArrayList<String> followers() throws RemoteException;
	public boolean follow(String user) throws RemoteException;
	public Map<String,String> getTimeLine() throws RemoteException;

}