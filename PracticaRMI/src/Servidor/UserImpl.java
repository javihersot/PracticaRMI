package Servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public class UserImpl implements User{

	public UserImpl(String userName, String password) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean setUser(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
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
	public boolean searchUser(String user) throws RemoteException {
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
		return "";
	}

}