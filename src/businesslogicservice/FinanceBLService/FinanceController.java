package businesslogicservice.FinanceBLService;

import java.rmi.RemoteException;

import PO.AccountPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CashVO;
import VO.CollectionVO;
import VO.PaymentVO;

public class FinanceController implements FinanceBLService{
	ResultMessage result = null ;
	@Override
	public ResultMessage addAccount(AccountVO vo) {
		AccountPO account = new AccountPO(vo.getName(),vo.getBalance());
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			result = com.client.messageCommand("accountAdd", account) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage deletAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		AccountPO account = new AccountPO(vo.getName(),vo.getBalance());
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result = com.client.messageCommand("accountDelete", vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage updateAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		AccountPO account = new AccountPO(vo.getName(),vo.getBalance());
		Communication_Start com = new Communication_Start() ;
		com.initial();
		try {
			result = com.client.messageCommand("accountDelete", vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage findAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addCollection(CollectionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addPayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addCash(CashVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		ResultMessage result = null ;
		AccountVO account  = new AccountVO("0001",100);
		FinanceController controller = new FinanceController() ;
		result = controller.addAccount(account) ;
		System.out.println(result);
	}
}
