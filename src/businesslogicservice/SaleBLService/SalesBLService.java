package businesslogicservice.SaleBLService;

import ResultMessage.ResultMessage;
import VO.GoodsVO;
import VO.SalesReceiptVO;

public interface SalesBLService {

	public ResultMessage addMember(String name);
	public ResultMessage addGoods(GoodsVO vo);
	public long getTotal(long price,int quantity);
	public ResultMessage creatReceipt(SalesReceiptVO salesReceiptVO);
}
