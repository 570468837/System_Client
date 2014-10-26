package businesslogicservice.CommodityBLService;

import PO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public interface CommodityBLService {
	
	
	
	public CheckCommodityPO checkCommodity(String time1, String time2);
	public InventoryCommodityPO inventoryCommodity();
	public ResultMessage sendCommodity(SendCommodityPO sendCommodityPO);
	public ResultMessage reportCommodity(ReportCommodityPO reportCommodityPO);
	
	public void alarmCommodity();
	
	

}
