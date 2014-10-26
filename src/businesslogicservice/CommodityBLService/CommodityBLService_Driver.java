package businesslogicservice.CommodityBLService;

import ResultMessage.ResultMessage;
import VO.*;

/**
 * 
 * @author hutao
 *
 */

public class CommodityBLService_Driver {
	public void drive(CommodityController commodityController) {
		CheckCommodityVO checkCommodityResult = commodityController.checkCommodity(new String(), new String());
	    checkCommodityResult.print();
	    
	    InventoryCommodityVO inventoryCommodityResult = commodityController.inventoryCommodity();
	    inventoryCommodityResult.print();
	    
	    ResultMessage resultMessage = commodityController.sendCommodity(new SendCommodityVO());
	    if(resultMessage == ResultMessage.add_sendCommodity_success) {
	    	System.out.println("add_sendCommodity_success");
	    }
	    else {
	    	System.out.println("add_sendCommodity_failure");
	    }
	    
	    resultMessage = commodityController.reportCommodity(new ReportCommodityVO());
	    if(resultMessage == ResultMessage.add_reportCommodity_success) {
	    	System.out.println("add_reportCommodity_success");
	    }
	    else {
	    	System.out.println("add_reportCommodity_failure");
	    }
	    
	    commodityController.alarmCommodity();
	    
	}
	
	
	
	
	
	
	
	

}
