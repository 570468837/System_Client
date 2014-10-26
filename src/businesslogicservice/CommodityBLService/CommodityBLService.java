package businesslogicservice.CommodityBLService;

import VO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public interface CommodityBLService {
	
	
	
	public CheckCommodityVO checkCommodity(String time1, String time2);
	public InventoryCommodityVO inventoryCommodity();
	public ResultMessage sendCommodity(SendCommodityVO sendCommodityVO);
	public ResultMessage reportCommodity(ReportCommodityVO reportCommodityVO);
	
	public void alarmCommodity();
	
	

}
