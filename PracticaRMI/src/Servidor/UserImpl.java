package Servidor;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserImpl extends UnicastRemoteObject implements User, Serializable {

	private String password;						//Contraseña del usuario
	private String userName;						//Nombre de usuario
	private ArrayList<String> followers;			//Usuarios a los que te siguen
	private ArrayList<String> following;			//Usuarios a los que se estña siguiendo
	private ArrayList<Tweet> timeLine;				//Tweets de los usuarios a los que se sigue
	private ArrayList<Tweet> misTweets;				//Tweets del usuario
	private ArrayList<DirectMessage> mailbox;		//Mensajes directos recibidos por el usuario
	private Profile perfil;							//Perfil asociado a la cuenta de usuario

	/*
	 * Constructor que inicializa los atributos del usuario.
	 */
	public UserImpl(String userName, String password) throws RemoteException {
		this.password = password;
		this.userName = userName;
		followers = new ArrayList<String>();
		following = new ArrayList<String>();
		timeLine = new ArrayList<Tweet>();
		mailbox = new ArrayList<DirectMessage>();
		perfil = new ProfileImpl(this.userName);
		misTweets= new ArrayList<Tweet>();
	}

	/*
	 * Método que devuelve el nombre de usuario.
	 */
	public String getUserName() throws RemoteException {
		return this.userName;
	}

	/*
	 *Método que cambia el nombre de usuario de la cuenta.
	 */
	@Override
	public boolean setUser(String name) throws RemoteException {
		if (Servidor.misFuncionesImpl.searchUser(name) == null) {
			Servidor.misFuncionesImpl.deleteUser(userName);
			this.userName = name;
			Servidor.misFuncionesImpl.addUser(this.userName, this);
			return true;
		} else {
			System.out.println("Nombre de usuario actualmente en uso.");
			return false;
		}
	}

	/*
	 * Método que cambia la contraseña de la cuenta de usuario.
	 */
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

	/*
	 * Método que publica un tweet. 
	 */
	@Override
	public void tweet(String tweet,int ret,int favs) throws RemoteException {
		Tweet tweetReal = new Tweet(this.userName, tweet);
		tweetReal.setRetweets(ret);
		tweetReal.setFavs(favs);
		misTweets.add(tweetReal);
		timeLine.add(tweetReal);
		Servidor.misFuncionesImpl.twittear(tweetReal,this.followers);
	}

	
	/*
	 * Método que envia un mensaje directo al usuario indicado. Dicho usuario debe seguirte.
	 */
	@Override
	public void directMessage(String user, String msg) 
			throws RemoteException{
		DirectMessage message = new DirectMessage(this.userName, user, msg);
		Servidor.misFuncionesImpl.recieveMessage(message);
	}

	/*
	 * Método que devuelve los usuarios a los que se sigue actualmente.
	 */
	@Override
	public ArrayList<String> following() throws RemoteException {
		return this.following;
	}

	/*
	 * Método que devuelve los usuarios que actualmente te siguen.
	 */
	@Override
	public ArrayList<String> followers() throws RemoteException {
		return this.followers;
	}

	/*
	 * Método para seguir a un determinado usuario.
	 */
	@Override
	public void follow(String user) throws RemoteException{
		if(following.contains(user)){
			return;
		}
		this.following.add(user);
		Servidor.misFuncionesImpl.recieveFollower(user, this.userName);
	}

	/*
	 * Método que imprime todos los tweets del timeline (los de los usuarios a los que sigues más los tuyos), ordenados de manera cronológica.
	 */
	@Override
	public String getTimeLine() throws RemoteException {// Del reves
		String res = "";
		Iterator<Tweet> it = timeLine.iterator();
		int cont = 0;
		while(it.hasNext()){
			Tweet tweet = it.next();
		   res += "[" + ++cont + "]" + tweet.getAutor() + " twitteo: " + tweet.getContent() +  "[Retweets: " + tweet.getRetweets() + " ] [Favs: " + tweet.getFavs() + " ]" + "\n";
		}
		return res;
	}
	
	
	/*
	 * Método que imprime todos los mensajes del mailbox.
	 */
	@Override
	public String getMessages() throws RemoteException {
		String res = "Tus mensajes directos: " + "\n";
		for (DirectMessage msg : mailbox) {
		    res += msg.getAutor() +"\n";
		}
		return res;
	}
	
	
	/*
	 * Método para dejar de seguir a otro usuario.
	 */
	@Override
	public void unfollow(String user) throws RemoteException{
		if(!this.following.contains(user)){
			return;
		}
		this.following.remove(user);
		Servidor.misFuncionesImpl.removeFollower(user,this.userName);
	}
	
	/*
	 * Método para obtener los mensajes directos del mailbox.
	 */
	public ArrayList<DirectMessage> getDirectMessages (){
		return this.mailbox;
	}
	
	@Override
	public void retwittear(int index,boolean ret) throws RemoteException {
		Tweet tweet = timeLine.get(index);
		if (ret)
			tweet.setRetweets(1);
		else
			tweet.setRetweets(-1);
		this.tweet(tweet.getContent(),tweet.getRetweets(),tweet.getFavs());
	}

	/*
	 * Método para marcar como favorito un tweet.
	 */
	@Override
	public void fav(int index,boolean fav) throws RemoteException {
		Tweet tweet = timeLine.get(index);
		if(fav)
			tweet.setFavs(1);
		else
			tweet.setFavs(-1);
		this.tweet(tweet.getContent(),tweet.getRetweets(),tweet.getFavs());
	}
	
	/*
	 * Método que devuelve todos los tweets (objeto Tweet) del timeline.
	 */
	public ArrayList<Tweet> getTweets() throws RemoteException{
		return this.timeLine;
	}
	
	/*
	 * Método que devuelve el objeto perfil asociado a la cuenta de usuario.
	 */
	public Profile getProfile() throws RemoteException{
		return this.perfil;
	}

	/*
	 * Método que devuelve todos los tweets del usuario.
	 */
	@Override
	public ArrayList<Tweet> getMisTweets() throws RemoteException {
		return this.misTweets;
	}
	
	
	//////////////////////////////////////////////MÉTODOS AUXILIARES//////////////////////////////////////////

	/*
	 * Método auxiliar para obtener la contraseña actual de la cuenta de usuario.
	 */
	public String getPassword() {
		return this.password;
	}
	
	/*
	 * Método auxiliar que añade al mailbox un mensaje directo de otro usuario.
	 */
	public void putMessage(DirectMessage message){
			this.mailbox.add(message);
	}
	
	/*
	 * Método auxiliar que añade un usuario que te sigue.
	 */
	public void putFollower(String user){
		this.followers.add(user);
	}

	
	/*
	 * Método auxiliar que imprime el mensaje indicado del mailbox.
	 */
	public String message(int index)throws RemoteException{
		String res = "";
		try {
			return mailbox.get(index).getContent();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * Método auxiliar que añade al timeline un tweet publicado por un usuario al que se sigue.
	 */
	public void putTweet(Tweet tweet){
		this.timeLine.add(tweet);
	}
	
	/*
	 * Método auxiliar para eliminar a un usuario al se está siguiendo.
	 */
	public void putUnfollow(String user){
		this.followers.remove(user);
	}	
	

}
