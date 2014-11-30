package businesslogicservice.CommodityBLService;

import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.*;

/**
 * 
 * @author hutao
 *
 */

public class CommodityController implements CommodityBLService {

	@Override
	public CheckCommodityVO checkCommodity(String time1, String time2) {
		
		Communication_Start com = new Communication_Start();
		com.initial();
		
		
		
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
