package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public interface User extends Remote{
	
	public boolean setUser(String name) throws RemoteException;
	public boolean setPassword(String oldPassword, String newPassword1, String newPassword2) throws RemoteException;
	public void tweet(String tweet,int ret, int favs) throws RemoteException;
	public void directMessage(String user, String msg) throws RemoteException,InexistentUserException;
	public ArrayList<String> following() throws RemoteException;
	public ArrayList<String> followers() throws RemoteException;
	public void follow(String user) throws RemoteException, InexistentUserException;
	public void unfollow(String user) throws RemoteException, InexistentUserException;
	public String getTimeLine() throws RemoteException;
	public String getMessages () throws RemoteException;
	public ArrayList<DirectMessage> getDirectMessages() throws RemoteException;
	public String message(int index)throws RemoteException;
	public void retwittear (int index,boolean ret) throws RemoteException;
	public void fav(int index, boolean fav) throws RemoteException;
	public ArrayList<Tweet> getTweets() throws RemoteException;
	public ArrayList<Tweet> getMisTweets() throws RemoteException;
	public Profile getProfile() throws RemoteException;
	public String getUserName() throws RemoteException;
}
