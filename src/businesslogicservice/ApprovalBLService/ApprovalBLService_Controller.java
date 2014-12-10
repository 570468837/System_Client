package businesslogicservice.ApprovalBLService;

import java.lang.reflect.Array;
import java.util.ArrayList;

import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.FinanceBLService.FinanceController;
import businesslogicservice.GoodsBLService.GoodsController;
import businesslogicservice.PurchseBLService.PurchaseController;
import PO.CollectionOrPaymentPO;
import PO.CustomerPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import VO.CollectionOrPaymentVO;

public class ApprovalBLService_Controller implements ApprovalBLService{
	@Override
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
			ArrayList<PurchaseListItemPO> items=p.getPurchaseList();
			for(int i=0;i<items.size();i++){
				//To be continued
			}
		}
		
	}

	@Override
	public void purchaseChangeCustomer(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
			new CustomerController().purchaseChangePay(p);
			
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
			new CustomerController().salesChangeGetting(s);
			}
		}

	@Override
	public void collectionOrPaymentChangeCustomer(ArrayList<CollectionOrPaymentVO> receipts) {
		// TODO Auto-generated method stub
		ArrayList<CollectionOrPaymentPO> pos=new ArrayList<CollectionOrPaymentPO>();
		for(CollectionOrPaymentVO c:receipts){
			c.setApprovedByManager(true);
			pos.add(new FinanceController().VOToPO(c));
		}
		
	}
}
