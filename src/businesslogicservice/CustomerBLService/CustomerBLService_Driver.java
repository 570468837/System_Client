package businesslogicservice.CustomerBLService;

import Config.Level;
import Config.Sort;
import ResultMessage.ResultMessage;
import VO.CustomerVO;

public class CustomerBLService_Driver {
	public CustomerBLService_Driver(CustomerBLService cbs){
		ResultMessage result;
		
		CustomerVO vo=new CustomerVO("001", Sort.importer, Level.fiveClassVIP, "sue","110","there","00123", "@", "qiao");
		System.out.println(cbs.addCustomer(new CustomerVO()));
//		cbs.deleteCustomer(new CustomerVO());
//		cbs.updateCustmer(new CustomerVO());
//		cbs.findCustomer("",0);		
	}
	
	
	public static void main(String [] args){
		CustomerBLService customerController=new CustomerController();
		new CustomerBLService_Driver(customerController);
	}
	
}
