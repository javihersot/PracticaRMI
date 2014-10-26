package Servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

	static FuncionesImpl misFuncionesImpl;
	static Registry registro;
	
	public Servidor(){
		try {
			registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Servidor serv = new Servidor();
	
		
		try {
			serv.registro.rebind("Funciones", new FuncionesImpl());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
