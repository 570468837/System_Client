package businesslogicservice.InfoBLService;
import VO.PurchaseReceiptVO;

public class InfoBLService_Driver {
	public void driver(InfoBLService infoBLService){
		infoBLService.showSalesDetailsInfo(new PurchaseReceiptVO(),new PurchaseReceiptVO()) ;
		infoBLService.showSalesProcessInfo("0001") ;
		infoBLService.showSalesConditionInfo("0001","0002")  ;
		infoBLService.deletReceipt("0001");
		infoBLService.deletAndUpdateReceipt("0001") ;
	} 
}
