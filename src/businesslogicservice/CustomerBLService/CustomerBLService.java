package businesslogicservice.CustomerBLService;

import java.util.ArrayList;

import PO.CustomerPO;
import ResultMessage.ResultMessage;
import VO.CustomerVO;

public interface CustomerBLService {
	public ResultMessage addCustomer(CustomerVO vo);
	public ResultMessage deleteCustomer(CustomerVO vo);
	public ResultMessage updateCustmer(CustomerVO vo);
	public ArrayList<CustomerPO> findCustomer(String keyWord);
	public CustomerPO getCustomerPOById(String id);
}
