package Servidor;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import Cliente.Callback;
import Cliente.CallbackInterface;

public class FuncionesImpl extends UnicastRemoteObject implements Funciones,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779351076503953090L;

	private Map<String, UserImpl> regUsers;
	private Map<String, CallbackInterface> connected;

	public FuncionesImpl() throws RemoteException {
		this.regUsers = new HashMap<String, UserImpl>();
		this.connected = new HashMap<String, CallbackInterface>();
	}

	public UserImpl searchUser(String user) throws RemoteException {
		if (regUsers.containsKey(user))
			return regUsers.get(user);
		else
			return null;
	}

	@Override
	public int register(String userName, String password, String password2)
			throws RemoteException {
		if (searchUser(userName) != null) {
			return 1;
		} else {
			if (!password.equals(password2)) {
				return 2;
			} else {
				UserImpl usuario = new UserImpl(userName, password);
				regUsers.put(usuario.getUserName(), usuario);
				return 0;
			}
		}
	}

	@Override
	public boolean connect(String userName, String password,
			CallbackInterface callbackUsuario) throws RemoteException {
		String nombreCallback = userName + "Callback";
		if (!regUsers.containsKey(userName)
				|| !regUsers.get(userName).getPassword().equals(password)) {
			return false;
		}
		Servidor.registro.rebind(nombreCallback, callbackUsuario);
		connected.put(userName, callbackUsuario);
		Servidor.registro.rebind(userName, regUsers.get(userName));
		for (DirectMessage mensaje : regUsers.get(userName).getDirectMessages()) {
			if (!mensaje.leido()) {
				callbackUsuario.notifyMessage();
				break;
			}
		}
		return true;
	}

	@Override
	public void disconnect(String userName) throws RemoteException {
		String callback = userName + "Callback";
		try {
			Servidor.registro.unbind(callback);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		connected.remove(userName);
	}

	public void deleteUser(String userName) {
		UserImpl aux = regUsers.get(userName);
		regUsers.remove(userName);
	}

	// Metodo para añadir un usuario, previa comprobación de que se puede
	public void addUser(String userName, UserImpl user) {
		regUsers.put(userName, user);
	}

	public boolean isConnected(String user) {
		return connected.containsKey(user);
	}

	public void recieveMessage(DirectMessage message) {
		try {
			this.regUsers.get(message.getDestinatario()).putMessage(message);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			if (this.connected.containsKey(message.getDestinatario()))
				this.connected.get(message.getDestinatario()).notifyMessage();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void recieveFollower(String userFollowed, String userFollowing) {
		this.regUsers.get(userFollowed).putFollower(userFollowing);
		try {
			if (isConnected(userFollowed))
				this.connected.get(userFollowed).notifyFollower(userFollowing);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public boolean following(String user1, String user2) {
		boolean res = false;
		try {
			res = regUsers.get(user1).following().contains(user2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}

	public void twittear(Tweet tweet, ArrayList<String> followers) {
		try {
			this.connected.get(tweet.getAutor()).notifyTweet();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Iterator<String> it = followers.iterator();
		while (it.hasNext()) {
			String user = it.next();
			regUsers.get(user).putTweet(tweet);
			if (this.connected.containsKey(user)) {
				try {
					this.connected.get(user).notifyTweet();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void removeFollower(String user, String user2) {
		regUsers.get(user).putUnfollow(user2);
	}

	@Override
	public void verUser(String userName) throws RemoteException {
		try {
			Servidor.registro.rebind(userName, regUsers.get(userName));
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
