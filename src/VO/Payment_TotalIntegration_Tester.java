package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Payment_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItemVO transferlist1=new TransferListItemVO(null, 200, null);
		TransferListItemVO transferlist2=new TransferListItemVO(null, 300, null);
		
		PaymentVO pay=new PaymentVO();
		pay.add(transferlist1);
		pay.add(transferlist2);
		
		assertEquals(500,(int)pay.getTotal());
		
	}

}
