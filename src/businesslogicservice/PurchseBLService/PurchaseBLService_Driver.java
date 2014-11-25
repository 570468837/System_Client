package businesslogicservice.PurchseBLService;

import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;

public class PurchaseBLService_Driver {

	public PurchaseBLService_Driver(PurchaseBLService purchaseController){
		purchaseController.updateGoods(new GoodsVO());
		purchaseController.updateCustomer(new CustomerVO());
		purchaseController.creatReceipt(new PurchaseReceiptVO());
		purchaseController.getTotal(100,1);
		
	}
	
	public static void main(String []args){
		PurchaseBLService purchaseController=new PurchaseController();
		new PurchaseBLService_Driver(purchaseController);
		
	}
}
