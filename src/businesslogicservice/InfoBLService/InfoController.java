package businesslogicservice.InfoBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.SaleBLService.SalesController;
import PO.CollectionOrPaymentPO;
import PO.GoodsPO;
import PO.PurchaseReceiptPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import PO.ScreeningConditionPO;
import PO.TransferListItemPO;
import PO.UserPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CollectionOrPaymentVO;
import VO.GoodsVO;
import VO.SalesListItemVO;
import VO.SalesReceiptVO;
import VO.ScreeningConditionVO;
import VO.TransferListItemVO;
import VO.UserVO;

public class InfoController implements InfoBLService{
	@Override
	public ArrayList<SalesReceiptVO> showSalesDetailsInfo(
			ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptVO> result = new ArrayList<SalesReceiptVO>() ;
		ArrayList<SalesReceiptPO> resultOfPO = new SalesController().show() ;
		int date ;
		String begin = condition.getTime1() ;
		String end = condition.getTime2() ;
		int time1 = Integer.parseInt(begin.substring(0,4)+begin.substring(5,7)+begin.substring(8)) ;
		int time2 = Integer.parseInt(end.substring(0,4)+begin.substring(5,7)+begin.substring(8));
		boolean isContain = false ;
		for(SalesReceiptPO theReceipt:resultOfPO){
		    date = Integer.parseInt(theReceipt.getSerialNumber().substring(4,12));
		    
			if(time1>=date||date>=time2) continue ;
			
			if(!theReceipt.getCustomerPO().getName().contains(condition.getCustomer())) continue ;
			
			if(!theReceipt.getRetailer().contains(condition.getUser())) continue ;
			
			if(!theReceipt.getCommodityNum().contains(condition.getRepository())) continue ;
			
			for(SalesListItemPO saleItem:theReceipt.getSalesList()){
				if(saleItem.getGoodsPO().getName().equals(condition.getNameOfGood())){
					isContain = true ;
				}
				if(isContain){
					UserVO userVO = new UserVO(theReceipt.getUserPO().getUserName(),
							theReceipt.getUserPO().getPassword(), theReceipt
									.getUserPO().getUserSort(), theReceipt.getUserPO()
									.getLevel());

					GoodsVO goodsVO;

					ArrayList<SalesListItemVO> list = new ArrayList<SalesListItemVO>();
					for (int i = 0; i < theReceipt.getSalesList().size(); i++) {
						goodsVO = new GoodsVO(theReceipt.getSalesList().get(i).getGoodsPO().getSerialNumber(),
								theReceipt.getSalesList().get(i).getGoodsPO().getName(), 
								theReceipt.getSalesList().get(i).getGoodsPO().getModel(),
								theReceipt.getSalesList().get(i).getGoodsPO().getPrice(), 
								theReceipt.getSalesList().get(i).getGoodsPO().getTotalPrice(), 
								theReceipt.getSalesList().get(i).getGoodsPO().getComment());

						list.add(new SalesListItemVO(goodsVO, theReceipt.getSalesList()
								.get(i).getQuantity()));

					}
					SalesReceiptVO vo = new SalesReceiptVO(
							theReceipt.getSerialNumber(), theReceipt.getRetailer(),
							theReceipt.getSalesman(), list, userVO,
							theReceipt.getCommodityNum(),
							theReceipt.getPriceBefore(), theReceipt.getDiscout(),
							theReceipt.getFinalprice(), theReceipt.getTime(),
							theReceipt.getComment());
					isContain = false ;
					result.add(vo) ;
				}
			}
		}
		return result ;
	}

	@Override
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		ArrayList<Object> result = new ArrayList<Object>() ;
		String beginTime = condition.getTime1().substring(0, 4)+condition.getTime1().substring(5,7)+condition.getTime1().substring(8,10);
		String endTime = condition.getTime2().substring(0, 4)+condition.getTime2().substring(5,7)+condition.getTime2().substring(8,10);
		ArrayList<Object> objects = new ArrayList<Object>();
		Communication_Start com = new Communication_Start() ;
		com.initial();
		if(condition.getTypeOfReceipt().equals("SKD")){
			try {
				objects = com.client.showObject("collectionOrPaymentShow") ;
				
				ArrayList<CollectionOrPaymentVO> receipts = new ArrayList<CollectionOrPaymentVO>() ;
				for(Object theObjecet :objects ){
					CollectionOrPaymentPO theReceipt = (CollectionOrPaymentPO)theObjecet ;
					ArrayList<TransferListItemVO> tfItems = new ArrayList<TransferListItemVO>() ;
					for(TransferListItemPO theItem : theReceipt.getTrList()){
						TransferListItemVO item = new TransferListItemVO(theItem.getAccount(), theItem.getTransferMoney(), theItem.getRemark()) ;
						tfItems.add(item) ;
					}
					CollectionOrPaymentVO oneReceipt = new CollectionOrPaymentVO(theReceipt.getNumber(), theReceipt.getCustomer(), theReceipt.getTypeOfCustomer(), theReceipt.getUser(), tfItems, theReceipt.getTotal()) ;
					receipts.add(oneReceipt) ;
				}
				for(CollectionOrPaymentVO theReceipt : receipts){
					int time = Integer.parseInt(theReceipt.getNumber().substring(4,12)) ;
					if( theReceipt.getNumber().substring(0, 3).equals("SKD") && (time>=Integer.parseInt(beginTime)&&time<=Integer.parseInt(endTime))
							&& theReceipt.getCustomer().equals(condition.getCustomer()) && theReceipt.getUser().equals(condition.getUser())){
						result.add(theReceipt) ;
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
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
	public ResultMessage deletReceipt(String typeOfReceipt,String number) {
		// TODO Auto-generated method stub
		ResultMessage result = null ;
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result = com.client.mangeReceipt("receiptDelet", typeOfReceipt,number) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}

	@Override
	public ResultMessage deletAndUpdateReceipt(String typeOfReceipt,String number) {
		// TODO Auto-generated method stub
		ResultMessage result = null ;
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result = com.client.mangeReceipt("receiptDeletAndUpdate",typeOfReceipt, number) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
}
