package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Sales_TotalIntergration_Tester {

	@Test
	public void test() {
		MockGoods goods1=new MockGoods(50);
		MockGoods goods2=new MockGoods(30);
		
		SalesListItemVO salesListItem1=new SalesListItemVO(goods1,2);
		SalesListItemVO salesListItem2=new SalesListItemVO(goods2,3);
	
		SalesReceiptVO salesReceipt=new SalesReceiptVO();
		
		salesReceipt.addSalesListItem(salesListItem1);
		salesReceipt.addSalesListItem(salesListItem2);
		
		assertEquals(190, (int)salesReceipt.getPriceBefore());
	}

}
