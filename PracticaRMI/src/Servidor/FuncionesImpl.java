package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class FuncionesImpl extends UnicastRemoteObject implements Funciones,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779351076503953090L;

	private Map<String, UserImpl> regUsers;
	private ArrayList<String> connected;

	public FuncionesImpl() throws RemoteException {
		this.regUsers = new HashMap<String, UserImpl>();
		this.connected = new ArrayList<String>();
	}
	
	public boolean searchUser(String user) throws RemoteException{
		return regUsers.containsKey(user);
	}

	@Override
	public boolean register(String userName, String password, String password2)
			throws RemoteException {
		if (searchUser(userName)) {
			System.out.println("Nombre de usuario ya en uso.");
			return false;
		} else {
			if (!password.equals(password2)) {
				System.out
						.println("Las contraseñas introducidas no coinciden.");
				return false;
			} else {
				UserImpl usuario = new UserImpl(userName, password);
				regUsers.put(usuario.getUserName(),usuario);
				return true;
			}
		}
	}

	@Override
	public UserImpl connect(String userName, String password)
			throws RemoteException, FailLoggingException {
		if (!regUsers.containsKey(userName)
				|| !regUsers.get(userName).getPassword().equals(password)) {
			throw new FailLoggingException();
		}
		connected.add(userName);
		return regUsers.get(userName);
	}

	@Override
	public void disconnect(String userName) throws RemoteException {
		connected.remove(userName);
	}
	
	public void deleteUser(String userName){
		UserImpl aux = regUsers.get(userName);
		regUsers.remove(userName);
	}
	
	public FuncionesImpl thisOb(){
		return this;
	}

}
