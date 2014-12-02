package businesslogicservice.CustomerBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
			return ResultMessage.add_customer_failure;
			
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
			return ResultMessage.delete_customer_failure;
			
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
			return ResultMessage.update_customer_failure;
			
		}

	}
	
	@Override
	public ArrayList<CustomerPO> findCustomer(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerPO getCustomerPOById(String id) {
		
	}

	
	//修改客户应收
	public ResultMessage purchaseChangeGetting(PurchaseReceiptPO receipt){
		//
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			return com.client.messageCommand("purchase_change_getting",receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}
		
	}
	//修改客户应付
	public ResultMessage salesChangePay(SalesReceiptPO receipt){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			 return com.client.messageCommand("sales_change_pay",receipt);
			 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
			
		}
	}
	//返回所有客户
	public ArrayList<Object> show(){
		Communication_Start com = new Communication_Start();
		com.initial();
		
		try {
			ArrayList<Object> result=com.client.showObject("showCustomer");
			System.out.println(result);
			 return com.client.showObject("showCustomer");
			 
			 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}

}
