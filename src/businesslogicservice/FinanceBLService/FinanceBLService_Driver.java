package businesslogicservice.FinanceBLService;

import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
import VO.PaymentVO;

public class FinanceBLService_Driver {
	public void drive(FinanceBLService financeBLService){
		financeBLService.addAccount(new AccountVO()) ;
		financeBLService.deletAccount(new AccountVO()) ;
		financeBLService.updateAccount(new AccountVO()) ;
		financeBLService.findAccount("0001") ;
		financeBLService.addCollection(new CollectionVO()) ;
		financeBLService.addPayment(new PaymentVO()) ;
		financeBLService.addCash(new CashVO()) ;
		financeBLService.init() ; 
	}
}
