package businesslogicservice.CustomerBLService;

import java.rmi.RemoteException;

import Config.Level;
import Config.Sort;
import PO.CustomerPO;
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
			return com.client.messageCommand("customerAdd",customerPO);
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
			return com.client.messageCommand("customerDelete",customerPO);
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
	public CustomerVO findCustomer(String keyWord, int id) {
		System.out.println("查找客户成功");
		return null;
	}

}
