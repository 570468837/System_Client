package businesslogicservice.InfoBLService;
import VO.PurchaseReceiptVO;
import VO.ReceiptVO;

public class InfoBLService_Driver {
	public void driver(InfoBLService infoBLService){
		infoBLService.showSalesDetailsInfo(new PurchaseReceiptVO(),new PurchaseReceiptVO()) ;
		infoBLService.showSalesProcessInfo(new ReceiptVO(),new ReceiptVO()) ;
		infoBLService.showSalesConditionInfo("0001","0002")  ;
		infoBLService.deletReceipt(new ReceiptVO());
		infoBLService.deletAndUpdateReceipt(new ReceiptVO()) ;
	}
}
