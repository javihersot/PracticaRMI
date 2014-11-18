package Servidor;

import java.rmi.AccessException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

	static FuncionesImpl misFuncionesImpl;
	static Registry registro;
	

	public static void main(String[] args) {
		
		/*
		 * Para arrancar servidor apache:
		 * /etc/init.d/apache2 start
		 * path /var/www/
		 * 
		 */
		
		
		//Para ejecutar en remoto:
		/*System.setProperty("java.rmi.server.hostname", "192.168.2.105");
		System.setProperty("java.rmi.server.codebase", "http://192.168.2.105/practica.jar");
		System.setProperty("java.security.policy","file:///home/victor/Escritorio/FI.upm/Quinto Semenstre/Middleware/Práctica/PracticaRMI/src/Servidor/practica.policy");
		if (System.getSecurityManager() == null)
			   System.setSecurityManager(new RMISecurityManager());*/
		
		
		
		
		
		System.out.println("Arrancando servidor...");
		try {
			misFuncionesImpl = new FuncionesImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			System.out.println("Registro OK");
			registro.rebind("Funciones", misFuncionesImpl);
			System.out.println("Listo para usar!");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
