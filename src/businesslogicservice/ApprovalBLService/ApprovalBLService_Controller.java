package businesslogicservice.ApprovalBLService;

import java.util.ArrayList;

import PO.CustomerPO;

public class ApprovalBLService_Controller implements ApprovalBLService{
	@Override
	public void changeCommodity(ArrayList<ArrayList<Object>> objs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeCustomer(ArrayList<ArrayList<Object>> objs) {
		// TODO Auto-generated method stub
		ArrayList<CustomerPO> customers=new ArrayList<CustomerPO>();
		for(int i=0;i<objs.size();i++){
			ArrayList<Object> oneObj=objs.get(i);
			CustomerPO po=new CustomerPO();
			po.setName((String)oneObj.get(1));
			System.out.println(po.getName());
			//Customer里为什么没有应收应付？
		}
		
	}
}
