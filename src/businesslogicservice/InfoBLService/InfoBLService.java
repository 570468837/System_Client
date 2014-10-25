package InfoBLService;

import java.util.ArrayList;

import VO.ReceiptVO;

public interface InfoBLService {
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(PurchaseReceiptVO receipt,PurchaseReceiptVO receipt2);
	public ArrayList<ReceiptVO> showSalesProcessInfo(ReceiptVO receipt,ReceiptVO receipt2) ;
	public String showSalesConditionInfo(String time1 ,String time2) ;
	public void deletReceipt(ReceiptVO receipt) ;//红冲
	public ReceiptVO deletAndUpdateReceipt(ReceiptVO receipt) ; //红冲并复制
}