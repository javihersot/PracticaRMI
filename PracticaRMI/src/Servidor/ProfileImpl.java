package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.ImageIcon;

public class ProfileImpl extends UnicastRemoteObject implements Profile, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2388367593206940990L;
	private String name;
	private String web;
	private String info;
	private String photo;

	public ProfileImpl() throws RemoteException {
		this.name = "";
		this.info = "";
		this.web = "";
		this.photo = "";
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	// XXX FALTA
	@Override
	public boolean setPhoto(String photo) throws RemoteException {
		//TODO
		return false;
	}

	@Override
	public void setWeb(String web) throws RemoteException {
		this.web = web;
	}

	@Override
	public void setInfo(String info) throws RemoteException {
		this.info = info;
	}
	
	public String getName() throws RemoteException{
		return this.name;
	}

}
