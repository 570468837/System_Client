package businesslogicservice.SaleBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.GoodsPO;
import PO.PurchaseListItemPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.SalesReceiptVO;
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

	@Override
	public ResultMessage creatReceipt(SalesReceiptVO salesReceiptVO) {
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

		Communication_Start com = new Communication_Start();
		com.initial();

		try {
			return com.client.messageCommand("create_sales_receipt", po);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.create_purchasereceipt_failure;

		}
	}
	
	public ArrayList<Object> show(){
		Communication_Start com = new Communication_Start();
		com.initial();

		try {
			return com.client.showObject("showSalesReceipts");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
