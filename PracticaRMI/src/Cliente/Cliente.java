package Cliente;

import gráfico.Logging;
import gráfico.Principal;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import Servidor.DirectMessage;
import Servidor.Funciones;
import Servidor.MessageInt;
import Servidor.Profile;
import Servidor.Tweet;
import Servidor.User;
import Servidor.UserImpl;

public class Cliente {

	public static Registry registroServ;				//Registro del servidor
	public static Funciones funciones;					//Objeto de la clase Funciones para conectarse, desconectarse...
	public User user;									//Implementacion del usuario
	public static Cliente cliente;						
	public User usuarioAbierto;							//Perfil del usuario que se quiera ver
	public Principal pantallaPrincipal;					//Objeto para poder hacer saltar ventanas con las notificaciones del callback
	public MessageInt tweetAbierto;						//Objeto que contiene la referencia al tweet que se tenga abierto

	/*
	 * Constructor para inicializar los atributos tales como el registro del servidor, coger el objeto funciones de dicho registro...
	 */
	public Cliente() {
		try {
			registroServ = LocateRegistry.getRegistry();
			this.funciones = (Funciones) registroServ.lookup("Funciones");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		this.usuarioAbierto = null;
		this.pantallaPrincipal = null;
		this.tweetAbierto = null;
	}

	/*
	 * Método auxiliar para poder saber si se puede conectar un usuario, y en caso contrario poderlo notificar.
	 */
	public boolean connect(String userName, String password) {
		boolean res = false;
		try {
			res = funciones.connect(userName, password, new Callback());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (res) {
			try {
				user = (User) registroServ.lookup(userName);
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	/*
	 * Método auxiliar para poder acceder al perfil de otros usuarios.
	 */
	public void verUser(String userName) {
		try {
			funciones.verUser(userName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		/*
		 Cambios a realizar para ejecutar en dos máquinas:
		 	Cambiar la IP de codebase tanto en Cliente como en Servidor, en los ficheros policy de Servidor y Cliente
		 	Seleccionar en el cliente la ruta con la foto por defecto
		
		System.setProperty("java.security.policy","file:///home/victor/Escritorio/FI.upm/Quinto Semenstre/Middleware/Práctica/PracticaRMI/src/Cliente/practica.policy");
		System.setProperty("java.rmi.server.codebase", "http://192.168.2.105/practica.jar");
		System.setProperty("java.rmi.server.useCodebaseOnly","false");
		if (System.getSecurityManager() == null)
			   System.setSecurityManager(new RMISecurityManager());*/
		

		cliente = new Cliente();
		Logging.main(args);
	}

}
