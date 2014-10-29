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
			registroServ.rebind("ramvitoCallback", new Callback());
			User usuarioRamvito = null;
			funciones.register("isidoro", "123456789", "123456789");
			registroServ.rebind("isidoroCallback", new Callback());
			User usuarioIsidoro = null;
			if (funciones.connect("ramvito", "123456789")) {
				usuarioRamvito = (User) registroServ.lookup("ramvito");
			}
			if (funciones.connect("isidoro", "123456789")) {
				usuarioIsidoro = (User) registroServ.lookup("isidoro");
			}
			usuarioRamvito.follow("isidoro");
			//usuarioIsidoro.follow("ramvito");
			System.out.println(usuarioIsidoro.followers().toString());
			System.out.println(usuarioRamvito.following().toString());
			usuarioIsidoro.directMessage("ramvito", "unodostrescuatrdhfhfghahsdhALHSBFlakjbkajebkeburheRUHkñljbvkjekuhrkjbrusefobugwekgbflkdjbgñkjrdhñksurhnvnhgbfhdhshnehehedccnfnfhfjgjgjgotorrtthh");
			System.out.println(usuarioRamvito.getMessages().get("isidoro").length());
			System.out.println(usuarioRamvito.getMessages().toString());
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
