package businesslogicservice.SaleBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.GoodsPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.SalesListItemVO;
import VO.SalesReceiptVO;
import VO.UserVO;
import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.GoodsBLService.GoodsController;

public class SalesController implements SalesBLService {

	@Override
	public ResultMessage updateCustomer(CustomerVO vo) {
		return new CustomerController().updateCustmer(vo);
	}

	@Override
	public ResultMessage updateGoods(GoodsVO vo) {
		// 向仓库中添加或者减少商品 TODO 判断
		return new GoodsController().updGoods(vo);
	}

	@Override
	public long getTotal(long price, int quantity) {
		System.out.println("success");
		return 0;
	}
	
	public ResultMessage updateReceipt(SalesReceiptPO po){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("updateSalesReceipt", po);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
		}
		//TODO
	}

	@Override
	public ResultMessage creatReceipt(SalesReceiptVO salesReceiptVO) {
		
		SalesReceiptPO po = this.toPO(salesReceiptVO);
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
//			System.out.println(com.client.messageCommand("create_sales_receipt", po));
			return com.client.messageCommand("create_sales_receipt", po);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.add_failure;

		}
	}
	//本方法与服务器端不同，返回的时PO
	public ArrayList<SalesReceiptPO> show(){
		Communication_Start com = new Communication_Start();
		com.initial();
		ArrayList<Object> objects;
		ArrayList<SalesReceiptPO> receipts=new ArrayList<SalesReceiptPO>();
		try {
			//逐个强制类型转换
			objects= com.client.showObject("showSalesReceipts");
			for (int i = 0; i < objects.size(); i++) {
				receipts.add((SalesReceiptPO)objects.get(i));				
			}
			
			return receipts;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public double getTotalIncomeInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		Object obj;
		try {
			//逐个强制类型转换
			obj= com.client.someMethodForFinancer("showIncomeInATime", beginTime, endTime);
			double result=(Double)obj;
			
			return result;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public double getTotalCostInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		Object obj;
		try {
			//逐个强制类型转换
			obj= com.client.someMethodForFinancer("showCostInATime", beginTime, endTime);
			double result=(Double)obj;
			
			return result;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public double getTotalDisountInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		Object obj;
		try {
			//逐个强制类型转换
			obj= com.client.someMethodForFinancer("showDiscountInATime", beginTime, endTime);
			double result=(Double)obj;
			
			return result;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public double getDifferenceInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		Object obj;
		try {
			//逐个强制类型转换
			obj= com.client.someMethodForFinancer("showDifferenceInATime", beginTime, endTime);
			double result=(Double)obj;
			
			return result;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public double getDifferenceFromVocherInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		Object obj;
		try {
			obj= com.client.someMethodForFinancer("showDifferenceFromVocherInATime", beginTime, endTime);
			double result=(Double)obj;
			
			return result;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<SalesReceiptPO> getReceiptsInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		ArrayList<SalesReceiptPO> results=new ArrayList<SalesReceiptPO>();
		try {
			//逐个强制类型转换
			results= (ArrayList<SalesReceiptPO>)com.client.someMethodForFinancer("showAllSalesReceiptsInATime", beginTime, endTime);
			return results;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("发生错误");
			return null;

		}
	
	}
	
	public SalesReceiptPO toPO(SalesReceiptVO salesReceiptVO){
		UserPO userPO = new UserPO(salesReceiptVO.getUserVO().getUserName(),
				salesReceiptVO.getUserVO().getPassword(), salesReceiptVO
						.getUserVO().getUserSort(), salesReceiptVO.getUserVO()
						.getLevel());

		GoodsPO goodsPO;

		ArrayList<SalesListItemPO> list = new ArrayList<SalesListItemPO>();
		for (int i = 0; i < salesReceiptVO.getSalesList().size(); i++) {
			goodsPO = new GoodsPO(salesReceiptVO.getSalesList().get(i)
					.getGoodsVO().serialNumber, salesReceiptVO.getSalesList()
					.get(i).getGoodsVO().name, salesReceiptVO.getSalesList()
					.get(i).getGoodsVO().model, salesReceiptVO.getSalesList()
					.get(i).getGoodsVO().price, salesReceiptVO.getSalesList()
					.get(i).getGoodsVO().salePrice, salesReceiptVO.getSalesList()
					.get(i).getGoodsVO().comment);

			list.add(new SalesListItemPO(goodsPO, salesReceiptVO.getSalesList()
					.get(i).getQuantity()));

		}
		SalesReceiptPO po = new SalesReceiptPO(
				salesReceiptVO.getSerialNumber(), salesReceiptVO.getRetailer(),
				salesReceiptVO.getSalesman(), list, userPO,
				salesReceiptVO.getCommodityNum(),
				salesReceiptVO.getPriceBefore(), salesReceiptVO.getDiscout(),
				salesReceiptVO.getFinalprice(), salesReceiptVO.getTime(),
				salesReceiptVO.getComment());
		
		po.setApprovedByCommodity(salesReceiptVO.isApprovedByCommodity());
		po.setApprovedByManager(salesReceiptVO.isApprovedByManager());
		
		po.setVocher(salesReceiptVO.getVocher());
		po.setCustomerPO(new CustomerController().toPO(salesReceiptVO.getCustomerVO()));
		if(salesReceiptVO.getPromotionVO()!=null){
			po.setPromotionPO(salesReceiptVO.getPromotionVO().toPO());
		}
		
		return po;
		
	}
	
	public SalesReceiptVO toVO(SalesReceiptPO salesReceiptPO){
		UserVO userVO = new UserVO(salesReceiptPO.getUserPO().getUserName(),
				salesReceiptPO.getUserPO().getPassword(), salesReceiptPO
						.getUserPO().getUserSort(), salesReceiptPO.getUserPO()
						.getLevel());

		GoodsVO goodsVO;

		ArrayList<SalesListItemVO> list = new ArrayList<SalesListItemVO>();
		for (int i = 0; i < salesReceiptPO.getSalesList().size(); i++) {
			goodsVO = new GoodsVO(salesReceiptPO.getSalesList().get(i)
					.getGoodsPO().getSerialNumber(), salesReceiptPO.getSalesList()
					.get(i).getGoodsPO().getName(), salesReceiptPO.getSalesList()
					.get(i).getGoodsPO().getModel(), salesReceiptPO.getSalesList()
					.get(i).getGoodsPO().getPrice(), salesReceiptPO.getSalesList()
					.get(i).getGoodsPO().getSalePrice(), salesReceiptPO.getSalesList()
					.get(i).getGoodsPO().getComment());

			list.add(new SalesListItemVO(goodsVO, salesReceiptPO.getSalesList()
					.get(i).getQuantity()));

		}
		SalesReceiptVO vo = new SalesReceiptVO(
				salesReceiptPO.getSerialNumber(), salesReceiptPO.getRetailer(),
				salesReceiptPO.getSalesman(), list, userVO,
				salesReceiptPO.getCommodityNum(),
				salesReceiptPO.getPriceBefore(), salesReceiptPO.getDiscout(),
				salesReceiptPO.getFinalprice(), salesReceiptPO.getTime(),
				salesReceiptPO.getComment());
		
		vo.setCustomerVO(new CustomerController().toVO(salesReceiptPO.getCustomerPO()));
		vo.setApprovedByCommodity(salesReceiptPO.isApprovedByCommodity());
		vo.setApprovedByManager(salesReceiptPO.isApprovedByManager());
		
		vo.setVocher(salesReceiptPO.getVocher());
		
		
		return vo;
		
	}
}
