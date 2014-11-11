package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.PurchaseReceiptVO;


public class InfoBLService_Stub implements InfoBLService {

	@Override
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(PurchaseReceiptVO theReceipt1,PurchaseReceiptVO theReceipt2) {
		PurchaseReceiptVO receipt1 = theReceipt1;
		PurchaseReceiptVO receipt2 = theReceipt2;
		// TODO Auto-generated method stub
		if(receipt1.getSerialNumber().equals("0001")){
			System.out.println("success");
			return new ArrayList<PurchaseReceiptVO>() ;
		}else{
			System.out.println("fail") ;
			return new ArrayList<PurchaseReceiptVO>() ;
		}
	}

	@Override
	public ResultMessage showSalesProcessInfo(String label) {
		// TODO Auto-generated method stub
		if(label.equals("0001")){
			System.out.println("success");
			return ResultMessage.Exist ;
		}
		else{
			System.out.println("fail") ;
			return ResultMessage.Not_Exist ;
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
	public void deletReceipt(String label) {
		// TODO Auto-generated method stub
		if(label.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
	}

	@Override
	public void deletAndUpdateReceipt(String label) {
		// TODO Auto-generated method stub
		if(label.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
//		return new ReceiptVO() ;
    }
}
