package businesslogicservice.SaleBLService;

import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.SalesReceiptVO;

public interface SalesBLService {

	public ResultMessage updateCustomer(CustomerVO vo);
	public ResultMessage updateGoods(GoodsVO vo);
	public long getTotal(long price,int quantity);
	public ResultMessage creatReceipt(SalesReceiptVO salesReceiptVO);
}
