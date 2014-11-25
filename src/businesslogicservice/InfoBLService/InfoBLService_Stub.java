package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.PurchaseReceiptVO;
import VO.ScreeningConditionVO;


public class InfoBLService_Stub implements InfoBLService {

	@Override
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		if(condition.getTime1().equals("0001")){
			System.out.println("success");
			return new ArrayList<PurchaseReceiptVO>() ;
		}else{
			System.out.println("fail") ;
			return new ArrayList<PurchaseReceiptVO>() ;
		}
	}

	@Override
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		if(condition.getTime1().equals("0001")){
			System.out.println("success");
			return null ;
		}
		else{
			System.out.println("fail") ;
			return null ;
		}
	}

	@Override
	public String showSalesConditionInfo(String time1, String time2) {
		// TODO Auto-generated method stub
		if(time1.equals("0001")){
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return null ;
	}

	@Override
	public ResultMessage deletReceipt(String typeOfReceipt,String number) {
		// TODO Auto-generated method stub
		if(number.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return null ;
	}

	@Override
	public ResultMessage deletAndUpdateReceipt(String typeOfReceipt,String number) {
		// TODO Auto-generated method stub
		if(number.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return null ;
//		return new ReceiptVO() ;
    }
}
