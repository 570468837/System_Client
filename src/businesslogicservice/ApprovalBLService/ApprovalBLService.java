package businesslogicservice.ApprovalBLService;

import java.util.ArrayList;

import PO.CollectionPO;
import PO.PaymentPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;

public interface ApprovalBLService {
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> purchases);
	public void purchaseChangeCustomer(ArrayList<PurchaseReceiptPO> purchases);
	public void salesChangeGoods(ArrayList<SalesReceiptPO> sales);
	public void salesChangeCustomer(ArrayList<SalesReceiptPO> sales);
	public void collectionChangeCustomer(ArrayList<CollectionPO> collections);
	public void paymentChangeCustomer(ArrayList<PaymentPO> payments);
}
