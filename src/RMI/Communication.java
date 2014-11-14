package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ResultMessage.ResultMessage;

public interface Communication extends Remote{
	public void voidCommand(String command,Object PO)throws RemoteException;
	public ResultMessage messageCommand(String command,Object PO) throws RemoteException;
	public ArrayList<Object> getObject(String command, Object PO)throws RemoteException;
}
