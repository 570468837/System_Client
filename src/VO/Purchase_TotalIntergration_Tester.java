package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Purchase_TotalIntergration_Tester {

	@Test
	public void test() {
		MockGoods goods1=new MockGoods(50);
		MockGoods goods2=new MockGoods(30);
		
		PurchaseListItemVO purchaseListItem1=new PurchaseListItemVO(goods1,2);
		PurchaseListItemVO purchaseListItem2=new PurchaseListItemVO(goods2,3);
	
		PurchaseReceiptVO purchaseReceipt=new PurchaseReceiptVO();
		
		purchaseReceipt.addPurchaseListItem(purchaseListItem1);
		purchaseReceipt.addPurchaseListItem(purchaseListItem2);
		
		assertEquals(190, (int)purchaseReceipt.getTotalPrice());

	}

}
