package businesslogicservice.PromotionBLService;

import ResultMessage.ResultMessage;
import VO.PromotionVO;

public interface PromotionBLService {
	public ResultMessage addPackage(PromotionVO vo);
	public ResultMessage addGift(PromotionVO vo);
	public ResultMessage addVoucher(PromotionVO vo);
	public ResultMessage delete(PromotionVO vo); 
}
