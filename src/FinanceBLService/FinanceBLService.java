package FinanceBLService;


import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
import VO.PaymentVO;

public interface FinanceBLService {
	public ResultMessage addAccount(AccountVO vo);
	public ResultMessage deletAccount(AccountVO vo);
	public ResultMessage updata(AccountVO vo);
	public ResultMessage find(AccountVO vo);
	public ResultMessage addCollection(CollectionVO vo);
	public ResultMessage addPayment(PaymentVO vo);
	public ResultMessage addCash(CashVO vo);
	public ResultMessage init();
}

