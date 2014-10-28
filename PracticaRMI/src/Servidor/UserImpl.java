package Servidor;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserImpl extends UnicastRemoteObject implements User, Serializable {

	private String password;
	private String userName;
	private ArrayList<String> followers;
	private ArrayList<String> following;
	private Map<String, String> timeLine;
	private ArrayList<DirectMessage> mailbox;

	public UserImpl(String userName, String password) throws RemoteException {
		this.password = password;
		this.userName = userName;
		followers = new ArrayList<String>();
		following = new ArrayList<String>();
		timeLine = new HashMap<String, String>();
	}

	public String getUserName() throws RemoteException {
		return this.userName;
	}

	@Override
	public boolean setUser(String name) throws RemoteException {
		if (!Servidor.misFuncionesImpl.searchUser(name)) {
			Servidor.misFuncionesImpl.deleteUser(userName);
			this.userName = name;
			Servidor.misFuncionesImpl.addUser(this.userName, this);
			return true;
		} else {
			System.out.println("Nombre de usuario actualmente en uso.");
			return false;
		}
	}

	@Override
	public boolean setPassword(String oldPassword, String newPassword1,
			String newPassword2) throws RemoteException {
		if (!newPassword1.equals(newPassword2)) {
			System.out.println("Las contraseñas no coinciden.");
			return false;
		} else {
			this.password = newPassword1;
			return true;
		}

	}

	@Override
	public void tweet(String tweet) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void directMessage(String user, String msg) 
			throws RemoteException, InexistentUserException {
		if (!Servidor.misFuncionesImpl.following(user, this.userName)) {// No es mis followers sino los del destinatario
			throw new InexistentUserException();
		}
		DirectMessage message = new DirectMessage(this.userName, user, msg);
		if (Servidor.misFuncionesImpl.isConnected(user)){
			Servidor.misFuncionesImpl.recieveMessage(message);
		}
	}

	@Override
	public ArrayList<String> following() throws RemoteException {
		return this.following;
	}

	@Override
	public ArrayList<String> followers() throws RemoteException {
		return this.followers;
	}

	@Override
	public void follow(String user) throws RemoteException,InexistentUserException {
		if(!Servidor.misFuncionesImpl.searchUser(user)){
			throw new InexistentUserException();
		}
		this.following.add(user);
		Servidor.misFuncionesImpl.recieveFollower(user, this.userName);
	}

	@Override
	public Map<String, String> getTimeLine() throws RemoteException {
		return this.timeLine;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void putMessage(DirectMessage message){
		this.mailbox.add(message);
	}
	
	public void putFollower(String user){
		this.followers.add(user);
		System.out.println("Followers de " + this.userName +": " + followers.toString());
	}

}
