package dataservice.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;

public interface FinanceDataService {
	public void insertAccount(AccountPO account) throws RemoteException;
	public void delet(AccountPO account) throws RemoteException;
	public void update(AccountPO account) throws RemoteException;
	public void find(AccountPO accout) throws RemoteException;
	public void insertPayment(PaymentPO payment) throws RemoteException ;
	public void insertCollection(CollectionPO payment) throws RemoteException ;
	public void insertCash(CashPO cash) throws RemoteException ;
	public void finish() ;
}
