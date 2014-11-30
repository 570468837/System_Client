package businesslogicservice.CustomerBLService;

import Config.Level;
import Config.Sort;
import ResultMessage.ResultMessage;
import VO.CustomerVO;

public class CustomerBLService_Driver {
	public CustomerBLService_Driver(CustomerController cbs){
		ResultMessage result;
		
		CustomerVO vo=new CustomerVO("003", Sort.importer, Level.fiveClassVIP, "kue","110","there","00123", "@", "qiao");
		cbs.addCustomer(vo);
//		System.out.println(cbs.addCustomer(new CustomerVO()));
//		cbs.deleteCustomer(new CustomerVO());
//		cbs.updateCustmer(new CustomerVO());
//		cbs.findCustomer("",0);		
	}
	
	
	public static void main(String [] args){
		CustomerController customerController=new CustomerController();
		new CustomerBLService_Driver(customerController);
	}
	
}
