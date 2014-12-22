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
import VO.ReportCommodityVO;
import VO.ScreeningConditionVO;
import VO.SendCommodityVO;
import VO.TransferListItemVO;

public class InfoController implements InfoBLService{
	@Override
	public ArrayList<SalesReceiptPO> showSalesDetailsInfo(
			ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
//		ArrayList<SalesReceiptVO> result = new ArrayList<SalesReceiptVO>() ;
		ArrayList<SalesReceiptPO> result = new ArrayList<SalesReceiptPO>() ;
		ArrayList<SalesReceiptPO> resultOfPO = new SalesController().show() ;
		int date ;
		String begin = condition.getTime1() ;
		String end = condition.getTime2() ;
		int time1 = Integer.parseInt(begin.replaceAll("/", "")) ;
		int time2 = Integer.parseInt(end.replaceAll("/", ""));
		boolean isContain = false ;
		for(SalesReceiptPO theReceipt:resultOfPO){
		    
		    if(theReceipt.isApprovedByCommodity()&&theReceipt.isApprovedByManager()){}
		    else continue ;
		    
		    if(!theReceipt.getSerialNumber().substring(0,3).equals("XSD")) continue ;
		    
		    date = Integer.parseInt(theReceipt.getSerialNumber().substring(4,12));
			if(time1>=date||date>=time2) continue ;
			
			if(!theReceipt.getCustomerPO().getName().contains(condition.getCustomer())) continue ;
			
			if(!theReceipt.getRetailer().contains(condition.getRetailer())) continue ;
			
			if(!theReceipt.getCommodityNum().contains(condition.getRepository())) continue ;
			
			if(!theReceipt.isApprovedByManager()) continue ;
			
			for(SalesListItemPO saleItem:theReceipt.getSalesList()){//判断该销售单是否含有查看的商品
				if(saleItem.getGoodsPO().getName().equals(condition.getNameOfGood())){
					isContain = true ;
					break ;
				}
			}
			if(isContain){
				result.add(theReceipt) ;
			}
		}
		return result ;
	}

	@Override
	public ArrayList<Object> showSalesProcessInfo(ScreeningConditionVO condition) {
		// TODO Auto-generated method stub
		ArrayList<Object> result = new ArrayList<Object>() ;
		String beginTime = condition.getTime1().replaceAll("/", "") ;
		String endTime = condition.getTime2().replace("/", "") ;
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
					CollectionOrPaymentVO oneReceipt = new CollectionOrPaymentVO(theReceipt.getNumber(), theReceipt.getCustomer(), theReceipt.getTypeOfCustomer(), theReceipt.getUser(), tfItems, theReceipt.getTotal(),theReceipt.isApprovedByManager(),theReceipt.isApprovedByFinancer()) ;
					receipts.add(oneReceipt) ;
				}
				for(CollectionOrPaymentVO theReceipt : receipts){
					
					int time = Integer.parseInt(theReceipt.getNumber().substring(4,12)) ;
					if( theReceipt.getNumber().substring(0, 3).equals("SKD") && (time>=Integer.parseInt(beginTime)&&time<=Integer.parseInt(endTime))
							&& theReceipt.getCustomer().contains(condition.getCustomer()) &&theReceipt.isApprovedByManager()){
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
							&& theReceipt.getCustomer().contains(condition.getCustomer()) &&theReceipt.isApprovedByManager()){
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
			ArrayList<SalesReceiptPO> receipts = this.showSalesDetailsInfo(condition) ;
			result = new ArrayList<>(receipts) ;
		}
		if(condition.getTypeOfReceipt().equals("XSTHD")){//销售退货单，返回的是PO
			ArrayList<SalesReceiptPO> receipts = new SalesController().show() ;
			int time1 = Integer.parseInt(beginTime) ;
			int time2 = Integer.parseInt(endTime);
			for(SalesReceiptPO theReceipt:receipts){
			    
				if(theReceipt.isApprovedByManager()){}
				else continue ;
				
			    if(!theReceipt.getSerialNumber().substring(0,5).equals("XSTHD")) continue ;
			    int date  = Integer.parseInt(theReceipt.getSerialNumber().substring(4,12));
			    
				if(time1>=date||date>=time2) continue ;
				
				if(!theReceipt.getCustomerPO().getName().contains(condition.getCustomer())) continue ;
				
				if(!theReceipt.getRetailer().contains(condition.getRetailer())) continue ;
				
				if(!theReceipt.getCommodityNum().contains(condition.getRepository())) continue ;
				
				result.add(theReceipt) ;
			}
		}
		if(condition.getTypeOfReceipt().equals("JHD")){//进货单,返回PO
			String storage = "仓库一";
			ArrayList<PurchaseReceiptPO> receipts = new PurchaseController().show() ;
			for(PurchaseReceiptPO thePO : receipts){
				if(!thePO.getSerialNumber().substring(0,3).equals("JHD") )
					continue ;
				int date = Integer.parseInt(thePO.getSerialNumber().substring(4, 12)) ;
				if( (date>=Integer.parseInt(beginTime)&&date<=Integer.parseInt(endTime)) 
						&& thePO.getCustomerPO().getName().contains(condition.getCustomer()) && storage.contains(condition.getRepository()) &&thePO.isApprovedByManager()){
					result.add(thePO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("JHTHD")){//进货退货单，返回PO
			String storage = "仓库一";
			ArrayList<PurchaseReceiptPO>receipts = new PurchaseController().show() ;
			for(PurchaseReceiptPO thePO :receipts){
				if(thePO.getSerialNumber().substring(0,3).equals("JHTHD")){
					;
				}else{
					continue ;
				}
				int date = Integer.parseInt(thePO.getSerialNumber().substring(6,14)) ;
				if( (date>=Integer.parseInt(beginTime)&&date<=Integer.parseInt(endTime)) 
						&& thePO.getCustomerPO().getName().contains(condition.getCustomer()) && storage.contains(condition.getRepository())  &&thePO.isApprovedByManager()){
					result.add(thePO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("BYD")){//报溢单，返回VO
			ArrayList<ReportCommodityVO> receipts = new CommodityController().showReportCommodity() ;
			for(ReportCommodityVO theVO : receipts){
				String time = new String(new SimpleDateFormat("yyyyMMdd").format(theVO.date)) ;
				System.out.println("cacacacaca");
				if(theVO.num>0 && (Integer.parseInt(time)>=Integer.parseInt(beginTime) && Integer.parseInt(time)<=Integer.parseInt(endTime))){
					result.add(theVO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("BSD")){//报损单，返回VO
			ArrayList<ReportCommodityVO> receipts = new CommodityController().showReportCommodity() ;
			for(ReportCommodityVO theVO : receipts){
				String time = new String(new SimpleDateFormat("yyyyMMdd").format(theVO.date)) ;
				if(theVO.num<0 && (Integer.parseInt(time)>=Integer.parseInt(beginTime) && Integer.parseInt(time)<=Integer.parseInt(endTime))){
					result.add(theVO) ;
				}
			}
		}
		if(condition.getTypeOfReceipt().equals("ZSD")){//赠送单，返回VO
			ArrayList<SendCommodityVO> receipts = new CommodityController().showSendCommodity() ;
			for(SendCommodityVO theVO :receipts){
				int time = Integer.parseInt(new String(new SimpleDateFormat("yyyyMMdd").format(theVO.date))) ;
				if((time<=Integer.parseInt(endTime)&&time>=Integer.parseInt(beginTime)) && theVO.customerVOName.contains(condition.getCustomer())&&(theVO.checked==1)){
					result.add(theVO) ;
				}
			}
		}
		return result;
	}

	@Override
	public double[] showSalesConditionInfo(String time1, String time2) {
		// TODO Auto-generated method stub
		String time3 = time1.replaceAll("/", "-") ;
		String time4 = time2.replaceAll("/", "-") ;
		double[] result = new double[9] ;
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			result[0] = (double) com.client.someMethodForFinancer("showDiscountInATime", time3, time4) ;//折让
			result[1] = (double) com.client.someMethodForFinancer("showIncomeInATime", time3, time4);//销售收入
			result[2] = (double) com.client.someMethodForFinancer("reportIncome", time1, time2) ;//报溢收入
			result[3] = (double) com.client.someMethodForFinancer("showDiffCostInATime", time3, time4) ;//成本调价
			result[4] = (double) com.client.someMethodForFinancer("showDifferenceInATime", time3, time4) ;//进退货差价
			result[5] = (double) com.client.someMethodForFinancer("showDifferenceFromVocherInATime", time3, time4) ;//代金券
			result[6] = (double) com.client.someMethodForFinancer("showCostInATime",time3, time4) ;//销售支出
			result[7] = (double) com.client.someMethodForFinancer("reportOutcome", time1, time2) ;//报损支出
			result[7] = -result[7] ;
			result[8] = (double) com.client.someMethodForFinancer("sendOutcome", time1, time2) ;//赠送支出
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
