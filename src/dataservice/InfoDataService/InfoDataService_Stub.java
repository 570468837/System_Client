package InfoDataService;

import java.rmi.RemoteException;

import PO.ReceiptPO;

public class InfoDataService_Stub implements InfoDataService {

	public ReceiptPO findReceipt(ReceiptPO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		if(receipt.getNumber().equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return new ReceiptPO() ;
	}

	public void deletReceipt(ReceiptPO receipt) {
		// TODO Auto-generated method stub
		if(receipt.getNumber().equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
	}

	public String getSalesCondition(String time1, String time2) {
		// TODO Auto-generated method stub
		if(time1.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return null ;
	}
	

}
