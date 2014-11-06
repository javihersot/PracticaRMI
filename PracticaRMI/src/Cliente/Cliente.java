package Cliente;

import gráfico.Logging;
import gráfico.Principal;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import Servidor.DirectMessage;
import Servidor.FailLoggingException;
import Servidor.Funciones;
import Servidor.InexistentUserException;
import Servidor.MessageInt;
import Servidor.Profile;
import Servidor.Tweet;
import Servidor.User;
import Servidor.UserImpl;

public class Cliente {

	public static Registry registroServ;
	public static Funciones funciones;
	public User user;
	public static Cliente cliente;
	public User usuarioAbierto;
	public Principal pantallaPrincipal;
	public MessageInt tweetAbierto;

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

	public void verUser(String userName) {
		try {
			funciones.verUser(userName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		cliente = new Cliente();
		Logging.main(args);
		/*
		 * try { registroServ = LocateRegistry.getRegistry(); funciones =
		 * (Funciones) registroServ.lookup("Funciones");
		 * funciones.register("ramvito", "123456789", "123456789"); User
		 * usuarioRamvito = null; funciones.register("isidoro", "123456789",
		 * "123456789"); User usuarioIsidoro = null; CallbackInterface
		 * callbackRamvito = new Callback();
		 * 
		 * if (funciones.connect("ramvito", "123456789", callbackRamvito)) {
		 * usuarioRamvito = (User) registroServ.lookup("ramvito"); }
		 * 
		 * if (funciones.connect("isidoro", "123456789", new Callback())) {
		 * usuarioIsidoro = (User) registroServ.lookup("isidoro"); }
		 * usuarioRamvito.follow("isidoro"); funciones.disconnect("ramvito");
		 * System.out.println(usuarioIsidoro.followers().toString());
		 * usuarioIsidoro.tweet(
		 * "HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
		 * ); if (funciones.connect("ramvito", "123456789", callbackRamvito)) {
		 * usuarioRamvito = (User) registroServ.lookup("ramvito"); }
		 * System.out.println(usuarioRamvito.getTimeLine());
		 * usuarioRamvito.retwittear(0); usuarioRamvito.fav(0);
		 * System.out.println(usuarioRamvito.getTimeLine());
		 * usuarioIsidoro.directMessage("ramvito", "Prueba");
		 * System.out.println(usuarioRamvito.getMessages());
		 * System.out.println(usuarioRamvito.message(0));
		 * usuarioRamvito.getProfile().setName("Víctor Rampérez");
		 * System.out.println(usuarioRamvito.getProfile().getName()); } catch
		 * (RemoteException e) { e.printStackTrace(); } catch (NotBoundException
		 * e) { e.printStackTrace(); //} catch (FailLoggingException e) { //
		 * e.printStackTrace(); //} catch (InexistentUserException e) { //
		 * e.printStackTrace(); }
		 */
	}

}
