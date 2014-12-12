package businesslogicservice.CommodityBLService;

import ResultMessage.ResultMessage;
import VO.*;

/**
 * 
 * @author hutao
 *
 */

public class CommodityBLService_Driver {
	public void drive(CommodityBLService commodityController) {
		CheckCommodityVO checkCommodityResult = commodityController.checkCommodity(new String(), new String());
	    
	    
	    InventoryCommodityVO inventoryCommodityResult = commodityController.inventoryCommodity();
	   
	    
	    ResultMessage resultMessage = commodityController.addSendCommodity(new SendCommodityVO(null, null, 0, 0, 0));
	    if(resultMessage == ResultMessage.add_success) {
	    	System.out.println("add_sendCommodity_success");
	    }
	    else {
	    	System.out.println("add_sendCommodity_failure");
	    }
	    
	    resultMessage = commodityController.addReportCommodity(new ReportCommodityVO(null));
	    if(resultMessage == ResultMessage.add_success) {
	    	System.out.println("add_reportCommodity_success");
	    }
	    else {
	    	System.out.println("add_reportCommodity_failure");
	    }
	    
	    commodityController.alarmCommodity();
	    
	}
	
	
	public static void main(String[] args) {
		CommodityBLService_Driver c = new CommodityBLService_Driver();
		c.drive(new CommodityController());
	}
	
	
	
	
	

}
