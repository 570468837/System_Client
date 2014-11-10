package businesslogicservice.SaleBLService;

import VO.GoodsVO;
import VO.SalesReceiptVO;

public class SalesBLService_Driver {

	public SalesBLService_Driver(SalesBLService saleblservice){
		saleblservice.addGoods(new GoodsVO());
		saleblservice.addMember("123");
		saleblservice.creatReceipt(new SalesReceiptVO());
		saleblservice.getTotal(100,1);
		
	}
	
	public static void main(String [] args){
		
		SalesBLService salesController = new SalesController();
		new SalesBLService_Driver(salesController);
	}
}
