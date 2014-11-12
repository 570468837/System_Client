package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Payment_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItem transferlist1=new TransferListItem(null, 200, null);
		TransferListItem transferlist2=new TransferListItem(null, 300, null);
		
		PaymentVO pay=new PaymentVO();
		pay.add(transferlist1);
		pay.add(transferlist2);
		
		assertEquals(500,(int)pay.getTotal());
		
	}

}
