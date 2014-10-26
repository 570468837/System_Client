package businesslogicservice.CommodityBLService;

import VO.*;
import ResultMessage.ResultMessage;
/**
 * 
 * @author hutao
 *
 */
public class CommodityBLService_Stub implements CommodityBLService {
	@Override
	public CheckCommodityVO checkCommodity(String time1, String time2) {
		
		return new CheckCommodityVO(time1, time2);
	}

	@Override
	public InventoryCommodityVO inventoryCommodity() {
		
		return new InventoryCommodityVO();
	}

	@Override
	public ResultMessage addSendCommodity(SendCommodityVO sendCommodityVO) {
		
		return ResultMessage.add_success;
	}
	@Override
	public ResultMessage addReportCommodity(ReportCommodityVO reportCommodityVO) {
		
		return ResultMessage.add_success;
	}

	@Override
	public void alarmCommodity() {
		System.out.println("no alarm");
		
	}

	
	
	

}
