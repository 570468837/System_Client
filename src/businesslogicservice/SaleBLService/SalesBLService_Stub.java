package businesslogicservice.SaleBLService;

import ResultMessage.ResultMessage;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;
import VO.SalesReceiptVO;

public class SalesBLService_Stub implements SalesBLService {

	@Override
	public ResultMessage addMember(String name) {
		// 对供货商做出修改
		System.out.println("modify customer successfully");
		return null;
	}

	@Override
	public ResultMessage addGoods(GoodsVO vo) {
		// 向仓库中添加或者减少商品
		System.out.println("modify commodity succesfully");
		return null;
	}
 
	@Override
	public long getTotal(long price, int quantity) {
		System.out.println("success");
		return 0;
	}

	@Override
	public ResultMessage creatReceipt(SalesReceiptVO purchaseReceiptVO) {
		if(purchaseReceiptVO.getSerialNumber().equals("JHD-20140101-00001")){
			return ResultMessage.create_purchasereceipt_success;
		}else{
			return ResultMessage.create_purchasereceipt_failure;
		}
	}


}
