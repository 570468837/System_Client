package businesslogicservice.FinanceBLService;

import VO.AccountVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;

public class FinanceBLService_Driver {
	public void drive(FinanceBLService financeBLService){
		financeBLService.addAccount(new AccountVO()) ;
		financeBLService.deletAccount(new AccountVO()) ;
		financeBLService.updateAccount(new AccountVO()) ;
		financeBLService.findAccount("0001") ;
		financeBLService.addCollectionOrPaymentVO(new CollectionOrPaymentVO()) ;
		financeBLService.addCash(new CashVO()) ;
		financeBLService.init() ; 
	}
}
