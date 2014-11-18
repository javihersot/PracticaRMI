package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.ws.handler.MessageContext;

public class Tweet extends UnicastRemoteObject implements MessageInt, Serializable{

	private String user;			// Nombre del usuario autor del tweet.
	private String tweet;			// Cuerpo del tweet.
	private int favs, retweets;	// Número de veces que se ha retwitteado o marcado como favorito un tweet.

	
	/*
	 * Constructor que inicializa los atributos. Si un tweet tiene más de 140 caractéres se trunca a 137 y se añade ...
	 */
	public Tweet(String user, String tweet) throws RemoteException{
		if (tweet.length() >= 140) {
			tweet = tweet.substring(0, 137);
			tweet = tweet + "...";
		}
		this.user = user;
		this.tweet = tweet;
	}

	/*
	 * Método que devuelve el autor de un tweet.
	 */
	public String getAutor() {
		return user;
	}

	/*
	 * Métdo que devuelve el contenido de un tweet.
	 */
	public String getContent() {
		return tweet;
	}

	/*
	 * Método que devuelve el número de usuarios que han marcado el tweet como favorito.
	 */
	public int getFavs() {
		return favs;
	}

	/*
	 * Método que cambia el número de favoritos del tweet.
	 */
	public void setFavs(int fav) {
		this.favs = fav + favs;
	}

	/*
	 * Método que devuelve el número de veces que se ha retwitteado un tweet.
	 */
	public int getRetweets() {
		return retweets;
	}

	/*
	 * Metodo que cambia el número de veces que se ha retwitteado el tweet.
	 */
	public void setRetweets(int ret) {
		this.retweets = ret + retweets;
	}

}
