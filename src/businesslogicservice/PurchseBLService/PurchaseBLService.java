package businesslogicservice.PurchseBLService;

import ResultMessage.ResultMessage;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;

public interface PurchaseBLService {
	
	public ResultMessage addMember(String name);
	public ResultMessage addGoods(GoodsVO vo);
	public long getTotal(long price,int quantity);
	public ResultMessage creatReceipt(PurchaseReceiptVO purchaseReceiptVO);
	

}
