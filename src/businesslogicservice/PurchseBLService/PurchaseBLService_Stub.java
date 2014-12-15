package businesslogicservice.PurchseBLService;

import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;

public class PurchaseBLService_Stub implements PurchaseBLService {

	@Override
	public ResultMessage updateCustomer(CustomerVO vo) {
		// 对供货商做出修改
		System.out.println("modify customer successfully");
		return null;
	}

	@Override
	public ResultMessage updateGoods(GoodsVO vo) {
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
	public ResultMessage creatReceipt(PurchaseReceiptVO purchaseReceiptVO) {
		if(purchaseReceiptVO.getSerialNumber().equals("JHD-20140101-00001")){
			return ResultMessage.add_success;
		}else{
			return ResultMessage.add_failure;
		}
	}

}
