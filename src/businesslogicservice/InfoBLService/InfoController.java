package businesslogicservice.InfoBLService;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.PurchseBLService.PurchaseController;
import businesslogicservice.SaleBLService.SalesController;
import PO.CaseListItemPO;
import PO.CashPO;
import PO.CollectionOrPaymentPO;
import PO.PurchaseReceiptPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import PO.TransferListItemPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CaseListItemVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.GoodsVO;
import VO.ReportCommodityVO;
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
		    
		    if(!theReceipt.getSerialNumber().substring(0,3).equals("XSD")) continue ;
		    
			if(time1>=date||date>=time2) continue ;
			
			if(!theReceipt.getCustomerPO().getName().contains(condition.getCustomer())) continue ;
			
			if(!theReceipt.getRetailer().contains(condition.getRetailer())) continue ;
			
			if(!theReceipt.getCommodityNum().contains(condition.getRepository())) continue ;
			
			for(SalesListItemPO saleItem:theReceipt.getSalesList()){//判断该销售单是否含有查看的商品
				if(saleItem.getGoodsPO().getName().equals(condition.getNameOfGood())){
					isContain = true ;
					break ;
				}
			}
				if(isContain){//PO转VO
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
		
		if(condition.getTypeOfReceipt().equals("SKD")){//收款单
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
							&& theReceipt.getCustomer().equals(condition.getCustomer())){
						result.add(theReceipt) ;
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(condition.getTypeOfReceipt().equals("FKD")){//付款单
			try {
				objects = com.client.showObject("collectionOrPaymentShow");
				ArrayList<CollectionOrPaymentVO> receipts = new ArrayList<CollectionOrPaymentVO>() ;
				for(Object theObjecet :objects ){
					CollectionOrPaymentPO theReceipt = (CollectionOrPaymentPO)theObjecet ;
					receipts.add(this.POToVO(theReceipt)) ;
				}
				for(CollectionOrPaymentVO theReceipt : receipts){
					int time = Integer.parseInt(theReceipt.getNumber().substring(4,12)) ;
					if( theReceipt.getNumber().substring(0, 3).equals("FKD") && (time>=Integer.parseInt(beginTime)&&time<=Integer.parseInt(endTime))
							&& theReceipt.getCustomer().equals(condition.getCustomer())){
						result.add(theReceipt) ;
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(condition.getTypeOfReceipt().equals("XJFYD")){//现金费用单
			try {
				ArrayList<CashVO> receipts = new ArrayList<CashVO>() ; 
				objects = com.client.showObject("cashShow");
				for(Object theObject:objects){
					CashPO theReceipt = (CashPO)theObject ;
					receipts.add(POToVO(theReceipt)) ;
				}
				for(CashVO theReceipt : receipts){
					int time = Integer.parseInt(theReceipt.getNumber().substring(6,14)) ;
					if((time>=Integer.parseInt(beginTime)&&time<=Integer.parseInt(endTime))){
						result.add(theReceipt) ;
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(condition.getTypeOfReceipt().equals("XSD")){//销售单，返回的是VO
			ArrayList<SalesReceiptVO> receipts = this.showSalesDetailsInfo(condition) ;
			result = new ArrayList<>(receipts) ;
		}
		if(condition.getTypeOfReceipt().equals("XSTHD")){//销售退货单，返回的是PO
			ArrayList<SalesReceiptPO> receipts = new SalesController().show() ;
			int time1 = Integer.parseInt(beginTime) ;
			int time2 = Integer.parseInt(endTime);
			for(SalesReceiptPO theReceipt:receipts){
				int date  = Integer.parseInt(theReceipt.getSerialNumber().substring(4,12));
			    
			    if(!theReceipt.getSerialNumber().substring(0,5).equals("XSTHD")) continue ;
			    
				if(time1>=date||date>=time2) continue ;
				
				if(!theReceipt.getCustomerPO().getName().contains(condition.getCustomer())) continue ;
				
				if(!theReceipt.getRetailer().contains(condition.getRetailer())) continue ;
				
				if(!theReceipt.getCommodityNum().contains(condition.getRepository())) continue ;
				
				result.add(theReceipt) ;
			}
		}
		if(condition.getTypeOfReceipt().equals("JHD")){//进货单
			ArrayList<PurchaseReceiptPO> receipts = new PurchaseController().show() ;
			for(PurchaseReceiptPO thePO : receipts){
				int date = Integer.parseInt(thePO.getSerialNumber().substring(4, 12)) ;
				if(thePO.getSerialNumber().substring(0,3).equals("JHD") && (date>=Integer.parseInt(beginTime)&&date<=Integer.parseInt(endTime)) && thePO.getCustomerPO().getName().equals(condition.getCustomer()) && condition.getRepository().equals("仓库一")){
					result.add(thePO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("JHTHD")){//进货退货单
			ArrayList<PurchaseReceiptPO>receipts = new PurchaseController().show() ;
			for(PurchaseReceiptPO thePO :receipts){
				int date = Integer.parseInt(thePO.getSerialNumber().substring(6,14)) ;
				if(thePO.getSerialNumber().substring(0,3).equals("JHTHD") && (date>=Integer.parseInt(beginTime)&&date<=Integer.parseInt(endTime)) && thePO.getCustomerPO().getName().equals(condition.getCustomer()) && condition.getRepository().equals("仓库一")){
					result.add(thePO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("BYD")){//报溢单
			ArrayList<ReportCommodityVO> receipts = new CommodityController().showReportCommodity() ;
			for(ReportCommodityVO theVO : receipts){
				String time = new String(new SimpleDateFormat("yyyyMMdd").format(theVO.date)) ;
				if(theVO.num>0 && (Integer.parseInt(time)>=Integer.parseInt(beginTime) && Integer.parseInt(time)<=Integer.parseInt(endTime))){
					result.add(theVO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("BSD")){
			ArrayList<ReportCommodityVO> receipts = new CommodityController().showReportCommodity() ;
			for(ReportCommodityVO theVO : receipts){
				String time = new String(new SimpleDateFormat("yyyyMMdd").format(theVO.date)) ;
				if(theVO.num<0 && (Integer.parseInt(time)>=Integer.parseInt(beginTime) && Integer.parseInt(time)<=Integer.parseInt(endTime))){
					result.add(theVO) ;
				}
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
	@Override
	public CollectionOrPaymentVO POToVO(CollectionOrPaymentPO po) {
		// TODO Auto-generated method stub
		ArrayList<TransferListItemVO> tfItems = new ArrayList<TransferListItemVO>() ;
		for(TransferListItemPO theItem : po.getTrList()){
			TransferListItemVO item = new TransferListItemVO(theItem.getAccount(), theItem.getTransferMoney(), theItem.getRemark()) ;
			tfItems.add(item) ;
		}
		CollectionOrPaymentVO vo = new CollectionOrPaymentVO(po.getNumber(), po.getCustomer(), po.getTypeOfCustomer(), po.getUser(), tfItems, po.getTotal(),po.isApprovedByManager(),po.isApprovedByFinancer()) ;
		return vo;
	}
	@Override
	public CashVO POToVO(CashPO po) {
		// TODO Auto-generated method stub
		ArrayList<CaseListItemVO> items = new ArrayList<CaseListItemVO>() ;
		for(CaseListItemPO theItem:po.getCases()){
			CaseListItemVO item = new CaseListItemVO(theItem.getCasename(), theItem.getCaseMoney(),theItem.getRemark()) ;
			items.add(item) ;
		}
		CashVO vo = new CashVO(po.getNumber(), po.getUser(), po.getAccount(), items, po.getSum()) ;
		return vo;
	}
}
