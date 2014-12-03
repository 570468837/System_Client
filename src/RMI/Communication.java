package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.ScreeningConditionPO;
import ResultMessage.ResultMessage;

public interface Communication extends Remote{
	public void voidCommand(String command,Object PO) throws RemoteException;
	public ResultMessage messageCommand(String command,Object PO) throws RemoteException;
	public ArrayList<Object> findObject(String command,String keywords) throws RemoteException;
	public ArrayList<Object> showObject(String command)throws RemoteException;
	public ArrayList<Object> showObjectByPO(String command,Object PO)throws RemoteException;
	public ArrayList<Object> showReceipt(String command,ScreeningConditionPO condition) throws RemoteException ;
	public String showSalesConditionInfo(String command,String time1,String time2)throws RemoteException ;
	public ResultMessage mangeReceipt(String command,String typeOfReceipt,String number)throws RemoteException ;
	public String getReceiptNumber(String typeOfReceipt) throws RemoteException ;
}
