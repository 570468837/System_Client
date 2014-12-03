package businesslogicservice.ApprovalBLService;

import java.util.ArrayList;

import PO.CollectionOrPaymentPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;

public interface ApprovalBLService {
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> purchases);
	public void purchaseChangeCustomer(ArrayList<PurchaseReceiptPO> purchases);
	public void salesChangeGoods(ArrayList<SalesReceiptPO> sales);
	public void salesChangeCustomer(ArrayList<SalesReceiptPO> sales);
	public void collectionOrPaymentChangeCustomer(ArrayList<CollectionOrPaymentPO> collections);
}
