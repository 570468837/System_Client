package dataservice.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;

public class FinanceDataService_Stub implements FinanceDataService{

	@Override
	public void insertAccount(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add success") ;
	}

	@Override
	public void delet(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delet success") ;
		
	}

	@Override
	public void update(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update success") ;
	}

	@Override
	public AccountPO find(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		if(account.getName().equals("0001")){
			System.out.println("find success") ;
		}
		return new AccountPO() ;
	}

	@Override
	public void insertPayment(PaymentPO payment) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add success") ;
	}

	@Override
	public void insertCollection(CollectionPO payment) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertCash(CashPO cash) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add success") ;
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		System.out.println("exit success") ;
	}
	
}
