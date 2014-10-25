package InfoBLService;
import VO.ReceiptVO;

public class InfoBLService_Driver {
	public void driver(InfoBLService infoBLService){
		infoBLService.showSalesDetailsInfo(new ReceiptVO()) ;
		infoBLService.showSalesProcessInfo(new ReceiptVO()) ;
		infoBLService.showSalesProcessInfo(new ReceiptVO()) ;
		infoBLService.deletReceipt(new ReceiptVO());
		infoBLService.deletAndUpdateReceipt(new ReceiptVO()) ;
	}
}
