package VO;

import static org.junit.Assert.*;

import org.junit.Test;

public class Collection_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItem listItem1=new TransferListItem(new AccountVO(),100,null);
		TransferListItem listItem2=new TransferListItem(new AccountVO(),200,null);
		
		CollectionVO collection=new CollectionVO();
		collection.add(listItem1);
		collection.add(listItem2);
		
		assertEquals(300, (int)collection.getTotal());
	}

}
