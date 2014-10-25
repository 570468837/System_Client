package InfoDataService;

import PO.ReceiptPO;

public class InfoDataService_Driver {
	public void driver(InfoDataService infoDataService){
		ReceiptPO receipt = new ReceiptPO() ;
		receipt.setNumber("0001");
		infoDataService.findReceipt(receipt) ;
		infoDataService.deletReceipt(receipt); 
		infoDataService.getSalesCondition("0001", "0002") ;
	}
}
