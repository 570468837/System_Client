package businesslogicservice.CommodityBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.InventoryCommodityPO;
import PO.SendCommodityPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.*;

/**
 * 
 * @author hutao
 *
 */

public class CommodityController implements CommodityBLService {
	
	public CommodityController() {
		Communication_Start com = new Communication_Start();
		com.initial();
	}
	
	
	/**
	 * 给姚锰舟用的show方法
	 * @return 待审批的赠送单
	 */
	public ArrayList<SendCommodityVO> showUncheckedSend() {
		ArrayList<Object> send = null;
		try {
			send = Communication_Start.client.showObject("uncheckedSendShow");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<SendCommodityVO> sv = new ArrayList<SendCommodityVO>();
		SendCommodityVO vo = new SendCommodityVO();
		for(Object po : send) {
			vo.toVO((SendCommodityPO)po);
			sv.add(vo);
		}
		return sv;
	}
	/**
	 * 给姚锰舟用的upd方法
	 * @param voList 审批后的vo们
	 * @return
	 */
	public ResultMessage updUncheckedSend(ArrayList<SendCommodityVO> voList) {
		ArrayList<SendCommodityPO> poList = new ArrayList<SendCommodityPO>();
		for(SendCommodityVO vo : voList) {
			poList.add(vo.toPO());
		}
		try {
			return Communication_Start.client.messageCommand("updUncheckedSend", poList);
		} catch (RemoteException e) {
			return ResultMessage.update_failure;
		}
	}
	
	
	
	
	@Override
	public CheckCommodityVO checkCommodity(String time1, String time2) {
		
		Communication_Start com = new Communication_Start();
		com.initial();
		
		
		
		return new CheckCommodityVO(time1, time2);
	}

	@Override
	public InventoryCommodityVO inventoryCommodity() {
		InventoryCommodityVO vo = new InventoryCommodityVO();
		InventoryCommodityPO po = new InventoryCommodityPO();
		try {
			po.icInfo = Communication_Start.client.showObject("inventoryCommodity");
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		vo.toVO(po);
		return vo;
		
	}

	@Override
	public ResultMessage addSendCommodity(SendCommodityVO sendCommodityVO) {
		
		try {
			return Communication_Start.client.messageCommand("commoditySend", sendCommodityVO.toPO());
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
