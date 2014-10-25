package InfoDataService;

import PO.ReceiptPO;

public interface InfoDataService {
	public ReceiptPO findReceipt(ReceiptPO receipt) ;
	public void deletReceipt(ReceiptPO receipt) ;
	public String getSalesCondition(String time1 ,String time2) ;
}
