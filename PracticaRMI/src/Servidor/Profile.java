package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Profile extends Remote{
	
	/*
	 * Método que cambia el nombre del usuario (su nombre real, no el nombre de usuario).
	 */
	public void setName(String name) throws RemoteException;
	
	/*
	 * Método apra cambiar la foto de perfil. Necesario introducir la ruta exacta a la foto.
	 */
	public void setPhoto(String photo) throws RemoteException;
	
	/*
	 * Método para editar la dirección web.
	 */
	public void setWeb(String web) throws RemoteException;
	
	/*
	 * Método para cambiar la información personal del perfil de usuario.
	 */
	public void setInfo(String info) throws RemoteException;
	
	/*
	 * Método que devuelve la información asociada al perfil de usuario.
	 */
	public String getInfo() throws RemoteException;
	
	/*
	 * Método que devuelve la dirección web asociada a la cuenta de usuario.
	 */
	public String getWeb() throws RemoteException;
	
	/*
	 * Método que devuelve el nombre (real) del perfil de usuario.
	 */
	public String getName() throws RemoteException;
	
	/*
	 * Método que devuelve la foto asociada al perfil de usuario.
	 */
	public String getPhoto() throws RemoteException;
}
