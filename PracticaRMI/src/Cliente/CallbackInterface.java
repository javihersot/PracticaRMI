package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackInterface extends Remote{
	
	public void notifyMessage() throws RemoteException;
	public void notifyTweet()throws RemoteException;
	public void notifyFollower(String segidor)throws RemoteException;

}
