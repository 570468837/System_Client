package businesslogicservice.FinanceBLService;


import java.util.ArrayList;

import PO.CashPO;
import PO.CollectionOrPaymentPO;
import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;

public interface FinanceBLService {
	public ResultMessage addAccount(AccountVO vo);
	public ResultMessage deletAccount(AccountVO vo);
//	public ResultMessage updateAccount(AccountVO vo);
	public ArrayList<AccountVO> findAccount(String keyword);
	public ResultMessage addCash(CashVO vo);
	public ResultMessage init();
	public ArrayList<AccountVO> show() ;
	public String getReceiptNumber(String typeOfReceipt) ;
	ResultMessage addCollectionOrPaymentVO(CollectionOrPaymentVO vo);
	ResultMessage updateCollectionOrPayment(CollectionOrPaymentPO po);
	public ArrayList<CollectionOrPaymentVO> showCollectionOrPaymentVOs(); 
	public ArrayList<CashVO> showCashVOs() ;
	public CollectionOrPaymentPO VOToPO(CollectionOrPaymentVO vo) ;
	public CollectionOrPaymentVO POToVO(CollectionOrPaymentPO po) ;
	public CashPO VOToPO(CashVO vo) ;
	public CashVO POToVO(CashPO po) ;
 }

