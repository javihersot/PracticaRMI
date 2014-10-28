package Servidor;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

	static FuncionesImpl misFuncionesImpl;
	static Registry registro;
	

	public static void main(String[] args) {
		
		System.out.println("Arrancando servidor...");
		try {
			misFuncionesImpl = new FuncionesImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			registro.rebind("Funciones", misFuncionesImpl);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
