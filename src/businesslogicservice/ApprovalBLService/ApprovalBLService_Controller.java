package businesslogicservice.ApprovalBLService;

import java.util.ArrayList;

import PO.CollectionOrPaymentPO;
import PO.CustomerPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;

public class ApprovalBLService_Controller implements ApprovalBLService{
	@Override
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
		}
		
	}

	@Override
	public void purchaseChangeCustomer(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
		}
		
	}

	@Override
	public void salesChangeGoods(ArrayList<SalesReceiptPO> sales) {
		// TODO Auto-generated method stub
		for(SalesReceiptPO s:sales){
			s.setApprovedByManager(true);
		}
	}

	@Override
	public void salesChangeCustomer(ArrayList<SalesReceiptPO> sales) {
		// TODO Auto-generated method stub
		for(SalesReceiptPO s:sales){
			s.setApprovedByManager(true);
		}
	}

	@Override
	public void collectionOrPaymentChangeCustomer(ArrayList<CollectionOrPaymentPO> collections) {
		// TODO Auto-generated method stub
		for(CollectionOrPaymentPO c:collections){
			c.setProvedByManege(true);
		}
	}
}
