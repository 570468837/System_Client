package businesslogicservice.ApprovalBLService;

import java.lang.reflect.Array;
import java.util.ArrayList;

import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.FinanceBLService.FinanceController;
import businesslogicservice.GoodsBLService.GoodsController;
import businesslogicservice.PurchseBLService.PurchaseController;
import businesslogicservice.SaleBLService.SalesController;
import Config.PromotionSort;
import PO.CollectionOrPaymentPO;
import PO.CustomerPO;
import PO.GoodsPO;
import PO.PromotionPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import VO.CollectionOrPaymentVO;
import VO.GoodsVO;
import VO.SendCommodityVO;

public class ApprovalBLService_Controller implements ApprovalBLService{
	GoodsController gController;
	public ApprovalBLService_Controller() {
		gController=new GoodsController();
	}
	
	@Override
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
			//?????去修改单据的数据
			boolean ifIn=false;
			if(p.getSerialNumber().substring(0,3).equals("JHD"))
				ifIn=true;
			
			ArrayList<PurchaseListItemPO> items=p.getPurchaseList();
			ArrayList<GoodsVO> goodsVOs=new ArrayList<GoodsVO>(); 
			for(int i=0;i<items.size();i++){
				GoodsPO getGoodsPO=items.get(i).getGoodsPO();
				GoodsVO vo=new GoodsVO();
				vo.toVO(getGoodsPO);
				vo.commodityQuantity=items.get(i).getQuantity();
				goodsVOs.add(vo);
			}
			//遍历商品VO去修改数据
			for(int i=0;i<goodsVOs.size();i++){
				GoodsVO oneVO=goodsVOs.get(i);
				GoodsVO getGoods=gController.getGoodsByID(Long.parseLong(oneVO.serialNumber));
				
				if(ifIn){
					if(getGoods.commodityQuantity==-1)
						getGoods.commodityQuantity++;
					getGoods.commodityQuantity=getGoods.commodityQuantity+oneVO.commodityQuantity;
					getGoods.latestPrice=oneVO.price;
				}
				else
					getGoods.commodityQuantity=getGoods.commodityQuantity-oneVO.commodityQuantity;
				
				gController.updGoods(getGoods);
			}
		}
		
	}

	@Override
	public void purchaseChangeCustomer(ArrayList<PurchaseReceiptPO> purchases) {
		// TODO Auto-generated method stub
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(true);
			new CustomerController().purchaseChangePay(p);
			
			new PurchaseController().updateReceipt(p);
		}
		
	}

	@Override
	public void salesChangeGoods(ArrayList<SalesReceiptPO> sales) {
		// TODO Auto-generated method stub
		for(SalesReceiptPO s:sales){
			s.setApprovedByManager(true);
			//?????去修改单据的数据？
			
			boolean ifOut=false;
			if(s.getSerialNumber().substring(0,3).equals("XSD"))
				ifOut=true;
			
			ArrayList<SalesListItemPO> items=s.getSalesList();
			ArrayList<GoodsVO> goodsVOs=new ArrayList<GoodsVO>(); 
			for(int i=0;i<items.size();i++){
				GoodsPO getGoodsPO=items.get(i).getGoodsPO();
				GoodsVO vo=new GoodsVO();
				vo.toVO(getGoodsPO);
				vo.commodityQuantity=items.get(i).getQuantity();
				goodsVOs.add(vo);
			}
			//遍历商品
			for(int i=0;i<goodsVOs.size();i++){
				GoodsVO oneVO=goodsVOs.get(i);
				GoodsVO getGoods=gController.getGoodsByID(Long.parseLong(oneVO.serialNumber));
				System.out.println(getGoods.name);
				if(ifOut){
					getGoods.commodityQuantity=getGoods.commodityQuantity-oneVO.commodityQuantity;
					getGoods.latestSalePrice=oneVO.salePrice;
				}
				else
					getGoods.commodityQuantity=getGoods.commodityQuantity+oneVO.commodityQuantity;
				gController.updGoods(getGoods);
			}
		}
	}

	@Override
	public void salesChangeCustomer(ArrayList<SalesReceiptPO> sales) {
		// TODO Auto-generated method stub
		for(SalesReceiptPO s:sales){
			s.setApprovedByManager(true);
			new CustomerController().salesChangeGetting(s);
			
			new SalesController().updateReceipt(s);
			}
		}

	@Override
	public void collectionOrPaymentChangeCustomer(ArrayList<CollectionOrPaymentVO> receipts) {
		// TODO Auto-generated method stub
		for(CollectionOrPaymentVO c:receipts){
			c.setApprovedByManager(true);
			new FinanceController().updateCollectionOrPayment(new FinanceController().VOToPO(c));
			
		}
		
	}

	public void sendCommodityUpdate(ArrayList<SendCommodityVO> receipts){
		for(SendCommodityVO s:receipts){
			s.checked=SendCommodityVO.PASS;
		}
		new CommodityController().updUncheckedSend(receipts);
	}
	
	public void purchaseNotPassed(ArrayList<PurchaseReceiptPO> purchases){
		for(PurchaseReceiptPO p:purchases){
			p.setApprovedByManager(false);
			p.setApprovedByCommodity(true);

			new PurchaseController().updateReceipt(p);
		}
	}

	public void salesNotPassed(ArrayList<SalesReceiptPO> sales){
		for(SalesReceiptPO s:sales){
			s.setApprovedByManager(false);
			s.setApprovedByCommodity(true);
			
			new SalesController().updateReceipt(s);
		}
	}
	
	public void collectionOrPaymentNotPassed(ArrayList<CollectionOrPaymentVO> receipts){
		for(CollectionOrPaymentVO c:receipts){
			c.setApprovedByManager(false);
			c.setApprovedByFinancer(true);
			new FinanceController().updateCollectionOrPayment(new FinanceController().VOToPO(c));
		}
	}

	public void sendCommodityNotPassed(ArrayList<SendCommodityVO> receipts){
		for(SendCommodityVO s:receipts){
			s.checked=SendCommodityVO.CANCEL;
		}
		new CommodityController().updUncheckedSend(receipts);
	}

	public void addSendCommodityReceipt(ArrayList<SalesReceiptPO> sales){    //自动创建赠送单
		for(int i=0;i<sales.size();i++){
			SalesReceiptPO s=sales.get(i);
			if(s.getPromotionPO().getPromotionType()==PromotionSort.Gifts){
				PromotionPO p=s.getPromotionPO();
				new CommodityController().addSendCommodity(new SendCommodityVO(
						p.getPresents().get(0).getSerialNumber(),s.getCustomerPO().getName(), 
						1, p.getPresents().get(0).getSalePrice(), SendCommodityVO.UNCHECKED));
			}
		}
	}
}
