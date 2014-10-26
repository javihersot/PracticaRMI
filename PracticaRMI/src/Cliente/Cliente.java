package Cliente;

import java.rmi.*;
import java.rmi.registry.*;

import Servidor.FailLoggingException;
import Servidor.Funciones;
import Servidor.Profile;
import Servidor.User;
import Servidor.UserImpl;

public class Cliente {
	
	public static void main(String [] args){
		
		Registry registroServ;
		Registry registroCliente;
		
		try {
			registroServ = LocateRegistry.getRegistry();
			registroCliente = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			
			Funciones funciones = (Funciones) registroServ.lookup("Funciones");
			funciones.register("ramvito", "123456789", "123456789");
			User usuario = null;
			if (funciones.connect("ramvito", "123456789")){
				usuario = (User) registroServ.lookup("ramvito");
				System.out.println(usuario.getUserName());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FailLoggingException e) {
			e.printStackTrace();
		}
	}

}
