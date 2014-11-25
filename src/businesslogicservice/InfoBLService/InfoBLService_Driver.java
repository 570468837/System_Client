package businesslogicservice.InfoBLService;
import VO.PurchaseReceiptVO;
import VO.ScreeningConditionVO;

public class InfoBLService_Driver {
	public void driver(InfoBLService infoBLService){
		infoBLService.showSalesDetailsInfo(new ScreeningConditionVO("0001",null,"SalesDetailsInfo",null,null,null, null)) ;
		infoBLService.showSalesProcessInfo(new ScreeningConditionVO("0001",null,null,null,null,null, null)) ;
		infoBLService.showSalesConditionInfo("0001","0002")  ;
		infoBLService.deletReceipt(null,"0001");
		infoBLService.deletAndUpdateReceipt(null,"0001") ;
	} 
}
