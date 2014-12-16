package businesslogicservice.CommodityBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import businesslogicservice.GoodsBLService.GoodsController;
import PO.CustomerPO;
import PO.GoodsPO;
import PO.InventoryCommodityPO;
import PO.ReportCommodityPO;
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
		SendCommodityVO vo = new SendCommodityVO(null, null, 0, 0, 0);
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
	/**
	 * 给盛宇的
	 * 显示通过的赠送单
	 * @return
	 */
	public ArrayList<SendCommodityVO> showSendCommodity() {
		ArrayList<Object> o;
		try {
			o = Communication_Start.client.showObject("showSendCommodity");
		} catch (RemoteException e) {
			e.printStackTrace();
			o = null;
		}
		ArrayList<SendCommodityVO> voList = new ArrayList<SendCommodityVO>();
		Iterator<Object> iter = o.iterator();
		
		while(iter.hasNext()) {
			SendCommodityVO vo = new SendCommodityVO(null, null, 0, 0, 0);
			vo.toVO((SendCommodityPO)iter.next());
			voList.add(vo);
		}
		return voList;
	}
	/**
	 * 给盛宇的
	 * 显示库存报单
	 * @return
	 */
	public ArrayList<ReportCommodityVO> showReportCommodity() {
		ArrayList<Object> o;
		try {
			o = Communication_Start.client.showObject("showReportCommodity");
		} catch (RemoteException e) {
			e.printStackTrace();
			o = null;
		}
		ArrayList<ReportCommodityVO> voList = new ArrayList<ReportCommodityVO>();
		Iterator<Object> iter = o.iterator();
		
		while(iter.hasNext()) {
			ReportCommodityVO vo = new ReportCommodityVO(null, 0, 0);
			vo.toVO((ReportCommodityPO)iter.next());
			voList.add(vo);
		}
		return voList;
	}
	/**
	 * 给盛宇的
	 * @return 一段时间的报溢收入，默认正值
	 */
	public double reportIncome(String s1, String s2) {
		Double d;
		try {
			d = (Double)Communication_Start.client.someMethodForFinancer("reportIncome", s1, s2);
		} catch (RemoteException e) {
			d = new Double(0);
			e.printStackTrace();
		}
		return d.doubleValue();
	}
	
	/**
	 * 给盛宇的
	 * @return 一段时间的报损支出，默认负值
	 */
	public double reportOutcome(String s1, String s2) {
		Double d;
		try {
			d = (Double)Communication_Start.client.someMethodForFinancer("reportOutcome", s1, s2);
		} catch (RemoteException e) {
			d = new Double(0);
			e.printStackTrace();
		}
		return d.doubleValue();
	}
	/**
	 * 给盛宇的
	 * @return 一段时间的赠送支出，默认正值
	 */
	public double sendOutcome(String s1, String s2) {
		Double d;
		try {
			d = (Double)Communication_Start.client.someMethodForFinancer("sendOutcome", s1, s2);
		} catch (RemoteException e) {
			d = new Double(0);
			e.printStackTrace();
		}
		return d.doubleValue();
	}
	
	
	
	
	
	
	@Override
	public CheckCommodityVO checkCommodity(String time1, String time2) {
		try {
			return new CheckCommodityVO(Communication_Start.client.someMethodForFinancer("showAllPurchaseReceiptsInATime", time1, time2),
					Communication_Start.client.someMethodForFinancer("showAllSalesReceiptsInATime", time1, time2));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
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
		ArrayList<Object> customerList;
		try {
			customerList = Communication_Start.client.showObject("showCustomer");
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return ResultMessage.add_failure;
		}
		CustomerPO cp;
		boolean finded = false;
		for(Object o : customerList) {
			cp = (CustomerPO)o;
			if(cp.getName().equals(sendCommodityVO.customerVOName)) {
				finded = true;
				break;
			}
		}
		if(!finded) return ResultMessage.add_failure;
		
		try {
			return Communication_Start.client.messageCommand("commoditySend", sendCommodityVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
		
	}
	@Override
	public ResultMessage addReportCommodity(ReportCommodityVO reportCommodityVO) {
		try {
			return Communication_Start.client.messageCommand("commodityReport", reportCommodityVO.toPO());
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.add_failure;
		}
	}
	
	@Override
	public void alarmCommodity() {
		System.out.println("no alarm");
		
	}

}
