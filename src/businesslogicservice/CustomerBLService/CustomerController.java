package businesslogicservice.CustomerBLService;

import Config.Level;
import Config.Sort;
import ResultMessage.ResultMessage;
import VO.CustomerVO;

public class CustomerController implements CustomerBLService {

	@Override
	public ResultMessage addCustomer(CustomerVO vo) {
		if (vo.getAddress().equals("nju") && vo.getClerk().equals("kmn")
				&& vo.getLevel() == Level.firstClass
				&& vo.getMail().equals("123@qq.com")
				&& vo.getName().equals("kmno") && vo.getPhone().equals("123")
				&& vo.getSort() == Sort.importer
				&& vo.getZipCode().equals("123")) {
			return ResultMessage.add_customer_success;
		} else {
			return ResultMessage.add_customer_failure;

		}

	}

	@Override
	public ResultMessage deleteCustomer(CustomerVO vo) {
		if (vo.getAddress().equals("nju") && vo.getClerk().equals("kmn")
				&& vo.getLevel() == Level.firstClass
				&& vo.getMail().equals("123@qq.com")
				&& vo.getName().equals("kmno") && vo.getPhone().equals("123")
				&& vo.getSort() == Sort.importer
				&& vo.getZipCode().equals("123")) {
			return ResultMessage.delete_customer_success;
		} else {
			return ResultMessage.delete_customer_failure;

		}

	}

	@Override
	public ResultMessage updateCustmer(CustomerVO vo) {
		if (vo.getAddress().equals("nju") && vo.getClerk().equals("kmn")
				&& vo.getLevel() == Level.firstClass
				&& vo.getMail().equals("123@qq.com")
				&& vo.getName().equals("kmno") && vo.getPhone().equals("123")
				&& vo.getSort() == Sort.importer
				&& vo.getZipCode().equals("123")) {
			return ResultMessage.update_customer_success;
		} else {
			return ResultMessage.update_customer_failure;

		}

	}

	@Override
	public CustomerVO findCustomer(String keyWord, int id) {
		System.out.println("查找客户成功");
		return null;
	}


}
