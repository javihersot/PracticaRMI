package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public interface User extends Remote{
	
	/*
	 * Método para cambiar el nombre de usuario.
	 */
	public boolean setUser(String name) throws RemoteException;
	
	/*
	 * Método para cambiar la contraseña de un usuario.
	 */
	public boolean setPassword(String oldPassword, String newPassword1, String newPassword2) throws RemoteException;
	 
	 /*
	  * Método para enviar un tweet.
	  */
	public void tweet(String tweet,int ret, int favs) throws RemoteException;
	
	/*
	 * Método para enviar un mensaje directo a un usuario.
	 */
	public void directMessage(String user, String msg) throws RemoteException;
	
	/*
	 * Método que devuelve los usuarios a los que se está siguiendo.
	 */
	public ArrayList<String> following() throws RemoteException;
	
	/*
	 * Método que devuelve los usuarios que te siguen.
	 */
	public ArrayList<String> followers() throws RemoteException;
	
	/*
	 * Método para seguir a otro usuario.
	 */
	public void follow(String user) throws RemoteException;
	
	/*
	 * Método para dejar de seguir a un usuario.
	 */
	public void unfollow(String user) throws RemoteException;
	
	/*
	 * Método que devuelve el timeline.
	 */
	public String getTimeLine() throws RemoteException;
	
	/*
	 * Método que devuelve los mensajes directos en formato String.
	 */
	public String getMessages () throws RemoteException;
	
	/*
	 * Método que devuelve todos los mensajes directos recibidos como objetos DirectMessage.
	 */
	public ArrayList<DirectMessage> getDirectMessages() throws RemoteException;
	
	/*
	 * Método para retwittear a tus seguidores un tweet.
	 */
	public void retwittear (int index,boolean ret) throws RemoteException;
	
	/*
	 * Método para marcar como favorito un determinado tweet.
	 */
	public void fav(int index, boolean fav) throws RemoteException;
	
	/*
	 * Método que devuelve todos los tweets de tus seguidores como objetos tweet.
	 */
	public ArrayList<Tweet> getTweets() throws RemoteException;
	
	/*
	 * Método que devuelve todos los tweets que ha enviado el usuario.
	 */
	public ArrayList<Tweet> getMisTweets() throws RemoteException;
	
	/*
	 * Método que devuelve el perfil del usuario.
	 */
	public Profile getProfile() throws RemoteException;
	
	/*
	 * Método que devulve el nombre de usuario.
	 */
	public String getUserName() throws RemoteException;
}
