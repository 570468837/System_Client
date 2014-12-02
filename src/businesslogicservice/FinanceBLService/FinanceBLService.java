package businesslogicservice.FinanceBLService;


import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
import VO.PaymentVO;

public interface FinanceBLService {
	public ResultMessage addAccount(AccountVO vo);
	public ResultMessage deletAccount(AccountVO vo);
	public ResultMessage updateAccount(AccountVO vo);
	public ArrayList<AccountVO> findAccount(String keyword);
	public ResultMessage addCollection(CollectionVO vo);
	public ResultMessage addPayment(PaymentVO vo);
	public ResultMessage addCash(CashVO vo);
	public ResultMessage init();
	public ArrayList<AccountVO> show() ;
}

