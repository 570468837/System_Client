package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.PurchaseReceiptVO;
import VO.SalesReceiptVO;
import VO.ScreeningConditionVO;

public interface InfoBLService {
	public ArrayList<SalesReceiptVO> showSalesDetailsInfo(ScreeningConditionVO condition);
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) ;
	public String showSalesConditionInfo(String time1 ,String time2) ;
	public ResultMessage deletReceipt(String typeOfReceipt,String number) ;//红冲
	public ResultMessage deletAndUpdateReceipt(String typeOfReceipt,String number) ; //红冲并复制
}