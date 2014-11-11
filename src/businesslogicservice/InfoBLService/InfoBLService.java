package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.PurchaseReceiptVO;

public interface InfoBLService {
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(PurchaseReceiptVO receipt,PurchaseReceiptVO receipt2);
	public ResultMessage showSalesProcessInfo(String label) ;
	public String showSalesConditionInfo(String time1 ,String time2) ;
	public void deletReceipt(String label) ;//绾㈠啿
	public void deletAndUpdateReceipt(String label) ; //绾㈠啿骞跺鍒�
}