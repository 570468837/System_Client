package businesslogicservice.CommodityBLService;

import java.rmi.RemoteException;

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
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("commoditySend", sendCommodityVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
		
	}
	@Override
	public ResultMessage addReportCommodity(ReportCommodityVO reportCommodityVO) {
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("commodityReport", reportCommodityVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}
	
	@Override
	public void alarmCommodity() {
		System.out.println("no alarm");
		
	}

}
