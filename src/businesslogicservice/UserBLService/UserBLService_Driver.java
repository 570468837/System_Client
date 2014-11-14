package businesslogicservice.UserBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.UserVO;

public class UserBLService_Driver {
	public void drive(UserBLService userBLService){
		ResultMessage result;
		
		result=userBLService.login(new UserVO("0001","0001", 0));
		if(result==ResultMessage.login_success)
			System.out.println("login!");
		
		result=userBLService.add(new UserVO("0002","0002",0));
		if(result==ResultMessage.add_success)
			System.out.println("add!");
		
		result=userBLService.delete(new UserVO("0003","0003",0));
		if(result==ResultMessage.delete_success)
			System.out.println("delete!");
		
		result=userBLService.update(new UserVO("0004","0004",0));
		if(result==ResultMessage.update_success)
			System.out.println("update!");
		
		ArrayList<UserVO> uservo=userBLService.find("0005");
		for(UserVO vo:uservo)
			System.out.println(vo.getUserName()); 
	}  

	public static void main(String[] args){
		UserBLService userBLController=new UserController();
		UserBLService_Driver driver=new UserBLService_Driver();
		driver.drive(userBLController);

	}
}
