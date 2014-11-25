package businesslogicservice.InfoBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import PO.ScreeningConditionPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.PurchaseReceiptVO;
import VO.ScreeningConditionVO;

public class InfoController implements InfoBLService{
	@Override
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(
			ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		ArrayList<PurchaseReceiptVO> result = new ArrayList<PurchaseReceiptVO>() ;
		ArrayList<Object> result1 = new ArrayList<Object>() ;
		ScreeningConditionPO theCondition = new ScreeningConditionPO(condition.getTime1(),condition.getTime2(),
				condition.getTypeOfReceipt(),condition.getNameOfGood(),condition.getCustomer(),condition.getUser(),condition.getRepository());
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result1 = com.client.showReceipt("showSalesDetailsInfo", theCondition) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object receipt:result1){
			PurchaseReceiptPO purchaseReceipt = (PurchaseReceiptPO)receipt ;
//			result.add(new PurchaseReceiptVO(purchaseReceipt.getSerialNumber(),purchaseReceipt.getTime())) ;
		}
		return result;
	}

	@Override
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		ArrayList<Object> result1 = new ArrayList<Object>();
		ScreeningConditionPO theCondition = new ScreeningConditionPO(condition.getTime1(),condition.getTime2(),condition.getTypeOfReceipt(),condition.getNameOfGood(),
				condition.getCustomer(),condition.getUser(),condition.getRepository());
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result1 = com.client.showReceipt("showSalesProcessInfo", theCondition) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String showSalesConditionInfo(String time1, String time2) {
		// TODO Auto-generated method stub
		String result = "" ;
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			result = com.client.showSalesConditionInfo("showSalesConditionIndo", time1, time2) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage deletReceipt(String number) {
		// TODO Auto-generated method stub
		return null ;
	}

	@Override
	public ResultMessage deletAndUpdateReceipt(String label) {
		// TODO Auto-generated method stub
		return null ;
	}

}
