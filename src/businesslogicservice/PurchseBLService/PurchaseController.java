package businesslogicservice.PurchseBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.GoodsPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.UserPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;
import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.GoodsBLService.GoodsController;

public class PurchaseController implements PurchaseBLService {

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
	public ResultMessage creatReceipt(PurchaseReceiptVO purchaseReceiptVO) {
		
		PurchaseReceiptPO po =this.toPO(purchaseReceiptVO);
		Communication_Start com = new Communication_Start();
		com.initial();

		try {
			return com.client.messageCommand("create_purchase_receipt", po);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.add_failure;

		}
	}
	
	public ArrayList<PurchaseReceiptPO> show(){
		Communication_Start com = new Communication_Start();
		com.initial();
		ArrayList<Object> objects;
		ArrayList<PurchaseReceiptPO> receipts=new ArrayList<PurchaseReceiptPO>();
		try {
			//逐个强制类型转换
			objects= com.client.showObject("showPurchaseReceipts");
			for (int i = 0; i < objects.size(); i++) {
				receipts.add((PurchaseReceiptPO)objects.get(i));				
			}
			
			return receipts;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查无此人！！");
			return null;

		}
		
	}
	
	public ArrayList<PurchaseReceiptPO> getReceiptsInATime(String beginTime,String endTime){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		ArrayList<PurchaseReceiptPO> results=new ArrayList<PurchaseReceiptPO>();
		try {
			//逐个强制类型转换
			results= (ArrayList<PurchaseReceiptPO>)com.client.someMethodForFinancer("showAllPurchaseReceiptsInATime", beginTime, endTime);
			return results;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("发生错误");
			return null;

		}
	
	}
	
	public PurchaseReceiptPO toPO(PurchaseReceiptVO purchaseReceiptVO){
		UserPO userPO = new UserPO(purchaseReceiptVO.getUserVO().getUserName(),
				purchaseReceiptVO.getUserVO().getPassword(), purchaseReceiptVO
						.getUserVO().getUserSort(), purchaseReceiptVO
						.getUserVO().getLevel());

		GoodsPO goodsPO;

		ArrayList<PurchaseListItemPO> list = new ArrayList<PurchaseListItemPO>();
		for (int i = 0; i < purchaseReceiptVO.getPurchaseList().size(); i++) {
			goodsPO = new GoodsPO(
					purchaseReceiptVO.getPurchaseList().get(i).getGoodsVO().serialNumber,
					purchaseReceiptVO.getPurchaseList().get(i).getGoodsVO().name,
					purchaseReceiptVO.getPurchaseList().get(i).getGoodsVO().model,
					purchaseReceiptVO.getPurchaseList().get(i).getGoodsVO().price,
					purchaseReceiptVO.getPurchaseList().get(i).getGoodsVO().comment);
			
			list.add(new PurchaseListItemPO(goodsPO, purchaseReceiptVO
					.getPurchaseList().get(i).getQuantity()));

		}
		PurchaseReceiptPO po = new PurchaseReceiptPO(
				purchaseReceiptVO.getSerialNumber(), list, userPO,
				purchaseReceiptVO.getTime(), purchaseReceiptVO.getComments(),
				purchaseReceiptVO.getTotalPrice());
		
		po.setApprovedByCommodity(purchaseReceiptVO.isApprovedByCommodity());
		po.setApprovedByManager(purchaseReceiptVO.isApprovedByManager());
		
		return po;
		
		
	}
}
