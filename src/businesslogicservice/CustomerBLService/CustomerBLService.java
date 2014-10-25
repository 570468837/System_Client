package businesslogicservice.CustomerBLService;

import ResultMessage.ResultMessage;
import VO.CustomerVO;

public interface CustomerBLService {
	public ResultMessage addCustomer(CustomerVO vo);
	public ResultMessage deleteCustomer(CustomerVO vo);
	public ResultMessage updateCustmer(CustomerVO vo);
	public CustomerVO findCustomer(String keyWord,int id);
}
