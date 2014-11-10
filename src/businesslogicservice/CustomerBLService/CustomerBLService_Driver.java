package businesslogicservice.CustomerBLService;

import VO.CustomerVO;

public class CustomerBLService_Driver {
	public CustomerBLService_Driver(CustomerBLService cbs){
		cbs.addCustomer(new CustomerVO());
		cbs.deleteCustomer(new CustomerVO());
		cbs.updateCustmer(new CustomerVO());
		cbs.findCustomer("",0);		
	}
	
	
	public static void main(String [] args){
		CustomerBLService customerController=new CustomerController();
		new CustomerBLService_Driver(customerController);
	}
	
}
