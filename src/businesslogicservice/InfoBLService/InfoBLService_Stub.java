package businesslogicservice.InfoBLService;

import java.util.ArrayList;

import PO.CashPO;
import PO.CollectionOrPaymentPO;
import ResultMessage.ResultMessage;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.PurchaseReceiptVO;
import VO.SalesReceiptVO;
import VO.ScreeningConditionVO;


public class InfoBLService_Stub implements InfoBLService {

	@Override
	public ArrayList<SalesReceiptVO> showSalesDetailsInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		if(condition.getTime1().equals("0001")){
			System.out.println("success");
			return new ArrayList<SalesReceiptVO>() ;
		}else{
			System.out.println("fail") ;
			return null ;
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
	public double[] showSalesConditionInfo(String time1, String time2) {
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

	@Override
	public CollectionOrPaymentVO POToVO(CollectionOrPaymentPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CashVO POToVO(CashPO po) {
		// TODO Auto-generated method stub
		return null;
	}
}
