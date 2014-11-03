package Cliente;

import java.rmi.*;
import java.rmi.registry.*;

import Servidor.DirectMessage;
import Servidor.FailLoggingException;
import Servidor.Funciones;
import Servidor.InexistentUserException;
import Servidor.Profile;
import Servidor.User;
import Servidor.UserImpl;

public class Cliente {

	public static void main(String[] args) {

		Registry registroServ;

		try {
			registroServ = LocateRegistry.getRegistry();
			Funciones funciones = (Funciones) registroServ.lookup("Funciones");
			funciones.register("ramvito", "123456789", "123456789");
			User usuarioRamvito = null;
			funciones.register("isidoro", "123456789", "123456789");
			User usuarioIsidoro = null;
			CallbackInterface callbackRamvito = new Callback();

			if (funciones.connect("ramvito", "123456789", callbackRamvito)) {
				usuarioRamvito = (User) registroServ.lookup("ramvito");
			}

			if (funciones.connect("isidoro", "123456789", new Callback())) {
				usuarioIsidoro = (User) registroServ.lookup("isidoro");
			}
			usuarioRamvito.follow("isidoro");
			funciones.disconnect("ramvito");
			System.out.println(usuarioIsidoro.followers().toString());
			usuarioIsidoro.tweet("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			if (funciones.connect("ramvito", "123456789", callbackRamvito)) {
				usuarioRamvito = (User) registroServ.lookup("ramvito");
			}
			System.out.println(usuarioRamvito.getTimeLine());
			usuarioRamvito.retwittear(0);
			usuarioRamvito.fav(0);
			System.out.println(usuarioRamvito.getTimeLine());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FailLoggingException e) {
			e.printStackTrace();
		} catch (InexistentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
