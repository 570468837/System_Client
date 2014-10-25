package InfoBLService;

import java.util.ArrayList;

import VO.ReceiptVO;


public class InfoBLService_Stub implements InfoBLService {

	@Override
	public ArrayList<PurchaseReceiptVO> showSalesDetailsInfo(
			PurchaseReceiptVO receipt,PurchaseReceiptVO receipt2) {
		// TODO Auto-generated method stub
		if(receipt.getNumber.equals("0001")){
			System.out.println("success");
			return new ArrayList<PurchaseReceiptVO>() ;
		}else{
			System.out.println("fail") ;
			return new ArrayList<PurchaseReceiptVO>() ;
		}
	}

	@Override
	public ArrayList<ReceiptVO> showSalesProcessInfo(ReceiptVO receipt,ReceiptVO receipt2) {
		// TODO Auto-generated method stub
		if(receipt.getNumber().equals("0001")){
			System.out.println("success");
			return new ArrayList<ReceiptVO>() ;
		}
		else{
			System.out.println("fail") ;
			return new ArrayList<ReceiptVO>() ;
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
	public void deletReceipt(ReceiptVO receipt) {
		// TODO Auto-generated method stub
		if(receipt.getNumber().equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
	}

	@Override
	public ReceiptVO deletAndUpdateReceipt(ReceiptVO receipt) {
		// TODO Auto-generated method stub
		if(receipt.getNumber().equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return new ReceiptVO() ;
    }
}
