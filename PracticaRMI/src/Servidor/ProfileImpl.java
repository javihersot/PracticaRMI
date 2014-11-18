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
	private String name;													//Nombre real del usuario
	private String web;														//Dirección web del usuario
	private String info;													//Información personal del usuario
	private String photo;													//Ruta donde se encuentra la foto del usuario

	/*
	 * Constructor que inicializa los atributos con valores por defecto.
	 */
	public ProfileImpl(String name) throws RemoteException {
		this.name = name;
		this.info = "Añadir información personal";
		this.web = "http://...";
		this.photo = "/home/victor/Escritorio/FI.upm/Quinto Semenstre/Middleware/Práctica/PracticaRMI/bin/gráfico/Default.png";
	}

	
	/*
	 * Método que cambia el nombre del usuario (su nombre real, no el nombre de usuario).
	 */
	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	/*
	 * Método apra cambiar la foto de perfil. Necesario introducir la ruta exacta a la foto.
	 */
	@Override
	public void setPhoto(String photo) throws RemoteException {
		this.photo = photo;
	}

	/*
	 * Método para editar la dirección web.
	 */
	@Override
	public void setWeb(String web) throws RemoteException {
		this.web = web;
	}

	/*
	 * Método para cambiar la información personal del perfil de usuario.
	 */
	@Override
	public void setInfo(String info) throws RemoteException {
		this.info = info;
	}
	
	/*
	 * Método que devuelve el nombre (real) del perfil de usuario.
	 */
	public String getName() throws RemoteException{
		return this.name;
	}
	
	/*
	 * Método que devuelve la información asociada al perfil de usuario.
	 */
	public String getInfo(){
		return this.info;
	}
	
	/*
	 * Método que devuelve la dirección web asociada a la cuenta de usuario.
	 */
	public String getWeb(){
		return this.web;
	}
	
	/*
	 * Método que devuelve la foto asociada al perfil de usuario.
	 */
	public String getPhoto(){
		return this.photo;
	}

}
