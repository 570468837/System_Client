package businesslogicservice.SaleBLService;

import VO.CustomerVO;
import VO.GoodsVO;
import VO.SalesReceiptVO;

public class SalesBLService_Driver {

	public SalesBLService_Driver(SalesBLService saleblservice){
		saleblservice.updateGoods(new GoodsVO());
		saleblservice.updateCustomer(new CustomerVO());
		saleblservice.creatReceipt(new SalesReceiptVO());
		saleblservice.getTotal(100,1);
		
	}
	
	public static void main(String [] args){
		
		SalesBLService salesController = new SalesController();
		new SalesBLService_Driver(salesController);
	}
}
