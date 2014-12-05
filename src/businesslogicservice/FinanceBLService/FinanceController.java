package businesslogicservice.FinanceBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import PO.AccountPO;
import PO.CaseListItemPO;
import PO.CashPO;
import PO.CollectionOrPaymentPO;
import PO.TransferListItemPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CaseListItemVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.TransferListItemVO;

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
			result = com.client.messageCommand("accountDelete", account);
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
			result = com.client.messageCommand("accountDelete", account);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<AccountVO> findAccount(String keyword) {
		// TODO Auto-generated method stub
		ArrayList<Object> objects = new ArrayList<Object>() ;
		ArrayList<AccountVO> accounts = new ArrayList<AccountVO>() ;
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			objects = com.client.findObject("accountFind", keyword) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object theAccount:objects){
			AccountPO acc = (AccountPO) theAccount ;
			accounts.add(new AccountVO(acc.getName(),acc.getBalance())) ;
		}
		return accounts;
	}
	@Override
	public ArrayList<AccountVO> show() {
		// TODO Auto-generated method stub
		ArrayList<Object> objects = new ArrayList<Object>() ;
		ArrayList<AccountVO> accounts = new ArrayList<AccountVO>() ;
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			objects = com.client.showObject("accountShow") ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object theAccount:objects){
			AccountPO acc = (AccountPO) theAccount ;
			accounts.add(new AccountVO(acc.getName(),acc.getBalance())) ;
		}
		return accounts;
	}

	@Override
	public ResultMessage addCollectionOrPaymentVO(CollectionOrPaymentVO vo) {
		// TODO Auto-generated method stub
		ArrayList<TransferListItemPO> trList = new ArrayList<TransferListItemPO>();
		for(TransferListItemVO theItem:vo.getTrList()){
			trList.add(new TransferListItemPO(theItem.getAccount(),theItem.getTransferMoney(),theItem.getRemark())) ;
		}
		CollectionOrPaymentPO collection = new CollectionOrPaymentPO(vo.getNumber(),vo.getCustomer(),vo.getTypeOfCustomer(),vo.getUser(),trList,vo.getTotal());
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			result = com.client.messageCommand("collectionOrPaymentAdd", collection) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage addCash(CashVO vo) {
		// TODO Auto-generated method stub
		ArrayList<CaseListItemPO> caseList = new ArrayList<CaseListItemPO>();
		for(CaseListItemVO theCase:vo.getCases()){
			caseList.add(new CaseListItemPO(theCase.getCasename(),theCase.getCaseMoney(),theCase.getRemark()));
		}
		CashPO cash = new CashPO(vo.getNumber(),vo.getAccount(),vo.getUser(),caseList,vo.getTotal()) ;
		Communication_Start com = new Communication_Start() ;
		com.initial(); 
		try {
			result = com.client.messageCommand("cashAdd", cash) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String getReceiptNumber(String typeOfReceipt) {
		// TODO Auto-generated method stub
		Communication_Start com = new Communication_Start() ;
		com.initial(); 
		String receiptNumber = "" ;
		try {
			receiptNumber =  com.client.getReceiptNumber(typeOfReceipt) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiptNumber;
	}
	@Override
	public ResultMessage init() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
