package businesslogicservice.ApprovalBLService;

import java.util.ArrayList;

import PO.CollectionPO;
import PO.CustomerPO;
import PO.PaymentPO;
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
	public void collectionChangeCustomer(ArrayList<CollectionPO> collections) {
		// TODO Auto-generated method stub
		for(CollectionPO c:collections){
			c.setProvedByManege(true);
		}
	}

	@Override
	public void paymentChangeCustomer(ArrayList<PaymentPO> payments) {
		// TODO Auto-generated method stub
		for(PaymentPO p:payments){
			p.setProvedByManege(true);
		}
	}
}
