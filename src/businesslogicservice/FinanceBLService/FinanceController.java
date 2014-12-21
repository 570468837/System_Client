package businesslogicservice.FinanceBLService;

import java.lang.reflect.Array;
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
		if(objects != null){
		for(Object theAccount:objects){
			AccountPO acc = (AccountPO) theAccount ;
			accounts.add(new AccountVO(acc.getName(),acc.getBalance())) ;
		}
		return accounts;
		}else{
			return null ;
		}
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
		CollectionOrPaymentPO collection = new CollectionOrPaymentPO(vo.getNumber(),vo.getCustomer(),vo.getTypeOfCustomer(),vo.getUser(),trList,vo.getTotal(),vo.isApprovedByManager(),vo.isApprovedByFinancer());
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
	public ArrayList<CollectionOrPaymentVO> showCollectionOrPaymentVOs() {
		// TODO Auto-generated method stub
		ArrayList<Object> objects = new ArrayList<Object>() ;
		Communication_Start com = new Communication_Start() ;
		com.initial(); 
		try {
			objects = com.client.showObject("collectionOrPaymentShow") ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<CollectionOrPaymentVO> result = new ArrayList<CollectionOrPaymentVO>() ;
		
		for(Object theObject :objects){
			CollectionOrPaymentPO theReceipt = (CollectionOrPaymentPO)theObject ;
			ArrayList<TransferListItemVO> tfItems = new ArrayList<TransferListItemVO>() ;
			if(theReceipt.getTrList()!=null)
			for(TransferListItemPO theItem : theReceipt.getTrList()){
				TransferListItemVO item = new TransferListItemVO(theItem.getAccount(), theItem.getTransferMoney(), theItem.getRemark()) ;
				tfItems.add(item) ;
				}
			result.add(new CollectionOrPaymentVO(theReceipt.getNumber(),theReceipt.getCustomer(),theReceipt.getTypeOfCustomer(),theReceipt.getUser(),tfItems,theReceipt.getTotal(),theReceipt.isApprovedByManager(),theReceipt.isApprovedByFinancer())) ;
			
		}
		return result;
	}
	public ArrayList<CollectionOrPaymentVO> showPassReceipt(){
		ArrayList<CollectionOrPaymentVO> receipts = this.showCollectionOrPaymentVOs() ;
		ArrayList<CollectionOrPaymentVO> passReceipts = new ArrayList<CollectionOrPaymentVO>() ;
		for(CollectionOrPaymentVO theReceipt:receipts){
			if(theReceipt.isApprovedByManager() && (!theReceipt.isApprovedByFinancer())){
				passReceipts.add(theReceipt) ;
			}
		}
		return passReceipts ;
	}
	public ArrayList<CollectionOrPaymentVO> showFailReceipt(){
		ArrayList<CollectionOrPaymentVO> receipts = this.showCollectionOrPaymentVOs() ;
		ArrayList<CollectionOrPaymentVO> failReceipts = new ArrayList<CollectionOrPaymentVO>() ;
		for(CollectionOrPaymentVO theReceipt : receipts){
			if(theReceipt.isApprovedByFinancer() && (!theReceipt.isApprovedByManager())){
				failReceipts.add(theReceipt) ;
			}
		}
		return failReceipts ;
	}
	@Override
	public ArrayList<CashVO> showCashVOs() {
		// TODO Auto-generated method stub
		ArrayList<Object> objects = new ArrayList<Object>() ;
		Communication_Start com = new Communication_Start() ;
		com.initial(); 
		try {
			objects = com.client.showObject("cashShow") ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<CashVO> result = new ArrayList<CashVO>() ;
		for(Object theObject : objects){
			CashPO theCash = (CashPO) theObject ;
			ArrayList<CaseListItemVO> items = new ArrayList<CaseListItemVO>() ;
			for(CaseListItemPO theItem : theCash.getCases()){
				CaseListItemVO item = new CaseListItemVO(theItem.getCasename(), theItem.getCaseMoney(), theItem.getRemark());
				items.add(item) ;
			}
			result.add(new CashVO(theCash.getNumber(),theCash.getUser(),theCash.getAccount(),items,theCash.getSum())) ;
		}
		return result ;
	}
   @Override
    public CollectionOrPaymentPO VOToPO(CollectionOrPaymentVO vo) {
	// TODO Auto-generated method stub
	ArrayList<TransferListItemPO> tfItems = new ArrayList<TransferListItemPO>() ;
	for(TransferListItemVO theItem : vo.getTrList()){
		TransferListItemPO item = new TransferListItemPO(theItem.getAccount(), theItem.getTransferMoney(), theItem.getRemark()) ;
		tfItems.add(item) ;
	}
	CollectionOrPaymentPO po = new CollectionOrPaymentPO(vo.getNumber(), vo.getCustomer(), vo.getTypeOfCustomer(), vo.getUser(), tfItems, vo.getTotal(),vo.isApprovedByManager(),vo.isApprovedByFinancer()) ; 
	return po;
}

@Override
public CollectionOrPaymentVO POToVO(CollectionOrPaymentPO po) {
	// TODO Auto-generated method stub
	ArrayList<TransferListItemVO> tfItems = new ArrayList<TransferListItemVO>() ;
	for(TransferListItemPO theItem : po.getTrList()){
		TransferListItemVO item = new TransferListItemVO(theItem.getAccount(), theItem.getTransferMoney(), theItem.getRemark()) ;
		tfItems.add(item) ;
	}
	CollectionOrPaymentVO vo = new CollectionOrPaymentVO(po.getNumber(), po.getCustomer(), po.getTypeOfCustomer(), po.getUser(), tfItems, po.getTotal(),po.isApprovedByManager(),po.isApprovedByFinancer()) ;
	return vo;
}

@Override
public ResultMessage updateCollectionOrPayment(CollectionOrPaymentPO po) {
	// TODO Auto-generated method stub
	Communication_Start com = new Communication_Start() ;
	com.initial(); 
	try {
		result = com.client.messageCommand("collectionOrPaymentUpdate", po) ;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}

@Override
public CashPO VOToPO(CashVO vo) {
	// TODO Auto-generated method stub
	ArrayList<CaseListItemPO> items = new ArrayList<CaseListItemPO>() ;
	for(CaseListItemVO theItem:vo.getCases()){
		CaseListItemPO item = new CaseListItemPO(theItem.getCasename(), theItem.getCaseMoney(),theItem.getRemark()) ;
		items.add(item) ;
	}
	CashPO po = new CashPO(vo.getNumber(), vo.getUser(), vo.getAccount(), items, vo.getSum()) ;
	return po;
}

@Override
public CashVO POToVO(CashPO po) {
	// TODO Auto-generated method stub
	ArrayList<CaseListItemVO> items = new ArrayList<CaseListItemVO>() ;
	for(CaseListItemPO theItem:po.getCases()){
		CaseListItemVO item = new CaseListItemVO(theItem.getCasename(), theItem.getCaseMoney(),theItem.getRemark()) ;
		items.add(item) ;
	}
	CashVO vo = new CashVO(po.getNumber(), po.getUser(), po.getAccount(), items, po.getSum()) ;
	return vo;
}
@Override
public ResultMessage init() {
	// TODO Auto-generated method stub
	return null;
}
public static void main(String[] args){
}

public void updateCollectionOrPayment(
		CollectionOrPaymentVO collectionOrPaymentVO) {
	// TODO Auto-generated method stub
	CollectionOrPaymentPO thePO = this.VOToPO(collectionOrPaymentVO) ;
	this.updateCollectionOrPayment(thePO) ;
	
}

}
