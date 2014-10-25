package businesslogicservice.PromotionBLService;

import ResultMessage.ResultMessage;
import VO.PromotionVO;

public class PromotionBLService_Driver {
	public void drive(PromotionBLService promotionBLService){
		ResultMessage result=promotionBLService.addPackage(new PromotionVO());
		if(result==ResultMessage.add_success)
			System.out.println("add success!");
		
	    result=promotionBLService.addGift(new PromotionVO());
	    if(result==ResultMessage.add_success)
			System.out.println("add success!");
	    
	    result=promotionBLService.addVoucher(new PromotionVO());
	    if(result==ResultMessage.add_success)
			System.out.println("add success!");
	    
	    result=promotionBLService.delete(new PromotionVO());
	    if(result==ResultMessage.delete_success)
			System.out.println("delete success!");
	}
	
	public static void main(String[] args){
		PromotionBLService promotionController=new PromotionController();
		PromotionBLService_Driver driver=new PromotionBLService_Driver();
		driver.drive(promotionController);
		
	}
}
