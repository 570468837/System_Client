package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Collection_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItemVO listItem1=new TransferListItemVO("0001",100,null);
		TransferListItemVO listItem2=new TransferListItemVO("0002",200,null);
		
		CollectionVO collection=new CollectionVO();
		collection.add(listItem1);
		collection.add(listItem2);
		
		assertEquals(300, (int)collection.getTotal());
	}

}
