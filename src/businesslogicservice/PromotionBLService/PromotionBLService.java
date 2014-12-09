package businesslogicservice.PromotionBLService;

import java.util.ArrayList;

import PO.PromotionPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;
import VO.PromotionVO;

public interface PromotionBLService {
	public ResultMessage addPackage(PromotionVO vo);
	public ResultMessage addGift(PromotionVO vo);
	public ResultMessage addVoucher(PromotionVO vo);
	public ResultMessage delete(PromotionVO vo); 
	public ArrayList<PromotionVO> show();
	public ArrayList<PromotionVO> ifPackage(SalesReceiptPO receipt);
	public ArrayList<PromotionVO> ifGift(SalesReceiptPO receipt);
	public ArrayList<PromotionVO> ifVoucher(SalesReceiptPO receipt);
}
