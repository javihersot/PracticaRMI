package Servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserImpl implements User{
	
	private String password;
	private String userName;
	private ArrayList<String> followers;
	private ArrayList<String> following;
	private Map<String, String> timeLine;


	public UserImpl(String userName, String password) {
		this.password = password;
		this.userName = userName;
		followers = new ArrayList<String>();
		following = new ArrayList<String>();
		timeLine = new HashMap<String, String>();
	}
	
	public String getUserName(){
		return userName;
	}

	@Override
	public boolean setUser(String name) throws RemoteException {
		if (!Servidor.misFuncionesImpl.searchUser(name)){
			Servidor.misFuncionesImpl.deleteUser(userName);
			this.userName = name;
			Servidor.misFuncionesImpl.regUsers.put(this.userName,this);
			return true;
		}else{
			System.out.println("Nombre de usuario actualmente en uso.");
			return false;
		}
	}

	@Override
	public boolean setPassword(String oldPassword, String newPassword1,
			String newPassword2) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tweet(String tweet) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean directMessage(String user, String msg)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public ArrayList<String> following() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> followers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean follow(String user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> getTimeLine() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getPassword(){
		return this.password;
	}

}
