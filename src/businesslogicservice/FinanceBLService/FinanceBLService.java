package businesslogicservice.FinanceBLService;


import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;

public interface FinanceBLService {
	public ResultMessage addAccount(AccountVO vo);
	public ResultMessage deletAccount(AccountVO vo);
	public ResultMessage updateAccount(AccountVO vo);
	public ArrayList<AccountVO> findAccount(String keyword);
	public ResultMessage addCash(CashVO vo);
	public ResultMessage init();
	public ArrayList<AccountVO> show() ;
	public String getReceiptNumber(String typeOfReceipt) ;
	ResultMessage addCollectionOrPaymentVO(CollectionOrPaymentVO vo);
	public ArrayList<CollectionOrPaymentVO> showCollectionOrPaymentVOs(); 
	public ArrayList<CashVO> showCashVOs() ;
 }

