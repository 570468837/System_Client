package businesslogicservice.FinanceBLService;

import static org.junit.Assert.*;

import org.junit.Test;
import VO.AccountVO;
import ResultMessage.ResultMessage;
public class FinanceBLService_StubTest {
	private FinanceBLService_Stub fs = new FinanceBLService_Stub() ;
	private AccountVO account = new AccountVO("0002",0);
	@Test
	public void testAddAccount() {
		assertEquals(fs.addAccount(account),ResultMessage.Exist);
	}

	@Test
	public void testDeletAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCollection() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCash() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

}
