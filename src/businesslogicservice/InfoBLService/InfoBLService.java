package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import PO.CashPO;
import PO.CollectionOrPaymentPO;
import ResultMessage.ResultMessage;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.PurchaseReceiptVO;
import VO.SalesReceiptVO;
import VO.ScreeningConditionVO;

public interface InfoBLService {
	public ArrayList<SalesReceiptVO> showSalesDetailsInfo(ScreeningConditionVO condition);
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) ;
	public double[] showSalesConditionInfo(String time1 ,String time2) ;
	public ResultMessage deletReceipt(String typeOfReceipt,String number) ;//红冲
	public ResultMessage deletAndUpdateReceipt(String typeOfReceipt,String number) ; //红冲并复制
	CollectionOrPaymentVO POToVO(CollectionOrPaymentPO po);
	CashVO POToVO(CashPO po);
}