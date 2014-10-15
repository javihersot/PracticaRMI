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

	@Override
	public boolean register(String userName, String password, String password2)
			throws RemoteException {
		if (regUsers.containsKey(userName)){
			System.out.println("Nombre de usuario ya en uso.");
			return false;
		}
		else {
			if (!password.equals(password2)){
				System.out.println("Las contraseñas introducidas no coinciden.");
				return false;
			}
			else {
				regUsers.put(userName, new UserImpl(userName, password));
				return true;
			}
		}
	}

	@Override
	public boolean connect(String userName, String password)
			throws RemoteException {
		if(!regUsers.containsKey(userName) 
				|| !regUsers.get(userName).getPassword().equals(password)){
			System.out.println("Usuario o contraseña incorrectos.");
			return false;
		}else{
			connected.add(userName);
			return true;
		}
	}


	@Override
	public void disconnect(String userName) throws RemoteException {
		connected.remove(userName);
	}

}
