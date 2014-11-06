package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.ws.handler.MessageContext;

public class Tweet extends UnicastRemoteObject implements MessageInt, Serializable{

	private String user;
	private String tweet;
	private int favs, retweets;

	public Tweet(String user, String tweet) throws RemoteException{
		if (tweet.length() >= 140) {
			tweet = tweet.substring(0, 137);
			tweet = tweet + "...";
		}
		this.user = user;
		this.tweet = tweet;
	}

	public String getAutor() {
		return user;
	}

	public String getContent() {
		return tweet;
	}

	public int getFavs() {
		return favs;
	}

	public void setFavs(int fav) {
		this.favs = fav + favs;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int ret) {
		this.retweets = ret + retweets;
	}

}
