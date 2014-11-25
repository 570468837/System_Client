package businesslogicservice.PurchseBLService;

import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;

public interface PurchaseBLService {
	
	public ResultMessage updateCustomer(CustomerVO vo);
	public ResultMessage updateGoods(GoodsVO vo);
	public long getTotal(long price,int quantity);
	public ResultMessage creatReceipt(PurchaseReceiptVO purchaseReceiptVO);
	

}
