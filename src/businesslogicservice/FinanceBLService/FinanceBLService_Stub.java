package businesslogicservice.FinanceBLService;

import businesslogicservice.CustomerBLService.CustomerBLService_Stub;
import businesslogicservice.GoodsBLService.GoodsBLService_Stub;
import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PaymentVO;



public class FinanceBLService_Stub implements FinanceBLService {
	
	@Override
	public ResultMessage addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		if(vo.getName().equals("0002"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage deletAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		if(vo.getName().equals("0003"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage updateAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		if(vo.getName().equals("0004"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage findAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		if(vo.getName().equals("0005"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage addCollection(CollectionVO vo) {
		// TODO Auto-generated method stub
		if(vo.getNumber().equals("0002"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage addPayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		if(vo.getNumber().equals("0002"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage addCash(CashVO vo) {
		// TODO Auto-generated method stub
		if(vo.getNumber().equals("0002"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage init() {
		// TODO Auto-generated method stub
		GoodsBLService_Stub g = new GoodsBLService_Stub() ;
		GoodsVO good = new GoodsVO() ;
		g.addGoods(good) ;
		
		CustomerBLService_Stub c = new CustomerBLService_Stub();
		CustomerVO customer = new CustomerVO();
		c.addCustomer(customer) ;
		
		this.addAccount(new AccountVO()) ;
		
		return ResultMessage.Exist;
	}
	
}
