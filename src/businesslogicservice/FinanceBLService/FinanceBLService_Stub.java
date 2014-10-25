package businesslogicservice.FinanceBLService;

import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
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
	public ResultMessage updata(AccountVO vo) {
		// TODO Auto-generated method stub
		if(vo.getName().equals("0004"))
			return ResultMessage.Exist ;
		else
			return ResultMessage.Not_Exist ;
	}

	@Override
	public ResultMessage find(AccountVO vo) {
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
		return ResultMessage.Exist;
	}
	
}
