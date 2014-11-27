package businesslogicservice.PromotionBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.PromotionVO;

public class PromotionBLService_Stub implements PromotionBLService{

	@Override
	public ResultMessage addPackage(PromotionVO vo) {
		// TODO Auto-generated method stub
		
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addGift(PromotionVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addVoucher(PromotionVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delete(PromotionVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.delete_success;
	}

	@Override
	public ArrayList<PromotionVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

}
