package businesslogicservice.CustomerBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CollectionOrPaymentPO;
import PO.CustomerPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.CustomerVO;

public class CustomerController implements CustomerBLService {

	@Override
	public ResultMessage addCustomer(CustomerVO vo) {
		CustomerPO customerPO = new CustomerPO(vo.getNumber(), vo.getSort(),
				vo.getLevel(), vo.getName(), vo.getPhone(), vo.getAddress(),
				vo.getZipCode(), vo.getMail(), vo.getClerk());
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("addCustomer",customerPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.add_failure;
			
		}

	}

	@Override
	public ResultMessage deleteCustomer(CustomerVO vo) {
		CustomerPO customerPO = new CustomerPO(vo.getNumber(), vo.getSort(),
				vo.getLevel(), vo.getName(), vo.getPhone(), vo.getAddress(),
				vo.getZipCode(), vo.getMail(), vo.getClerk());
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("deleteCustomer",customerPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.delete_failure;
			
		}

	}

	@Override
	public ResultMessage updateCustmer(CustomerVO vo) {
		CustomerPO customerPO = new CustomerPO(vo.getNumber(), vo.getSort(),
				vo.getLevel(), vo.getName(), vo.getPhone(), vo.getAddress(),
				vo.getZipCode(), vo.getMail(), vo.getClerk());
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("customerUpdate",customerPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}

	}
	
	@Override
	public ArrayList<CustomerPO> findCustomer(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerPO getCustomerPOById(String id) {
		return null;
	}
	//收付款单修改
	public ResultMessage collectionOrPaymentChangePayOrGetting(CollectionOrPaymentPO collectionOrPaymentPO){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("collection_payment_change_pay_getting",collectionOrPaymentPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}		
		
	}

	
	//修改客户应收
	public ResultMessage purchaseChangePay(PurchaseReceiptPO receipt){
		//
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("purchase_change_pay",receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}
		
	}
	//修改客户应付
	public ResultMessage salesChangeGetting(SalesReceiptPO receipt){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			 return com.client.messageCommand("sales_change_getting",receipt);
			 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}
	}
	//返回所有客户
	public ArrayList<CustomerPO> show(){
		Communication_Start com = new Communication_Start();
		com.initial();
		ArrayList<Object> objects;
		ArrayList<CustomerPO> customers=new ArrayList<CustomerPO>();
		try {
			//遍历服务器返回的结果，逐个强制类型转换
			objects= com.client.showObject("showCustomer");
			for(int i=0;i<objects.size();i++){
				customers.add((CustomerPO)objects.get(i));
			}
			return customers;
			 
			 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	public CustomerPO toPO(CustomerVO vo){
		CustomerPO po=new CustomerPO(vo.getNumber(), vo.getSort(), vo.getLevel(), vo.getName(), vo.getPhone(), vo.getAddress(), vo.getZipCode(), vo.getMail(), vo.getClerk());
		return po;
	}
	
	public CustomerVO toVO(CustomerPO po){
		CustomerVO vo=new CustomerVO(po.getNumber(), po.getSort(), po.getLevel(), po.getName(), po.getPhone(), po.getAddress(), po.getZipCode(), po.getMail(), po.getClerk());
		return vo;
	}

}
