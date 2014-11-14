package businesslogicservice.UserBLService;

import java.rmi.RemoteException;

import PO.UserPO;
import RMI.Communication;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.UserVO;

public class UserController implements UserBLService  {
	ResultMessage result=null;
	@Override
	public ResultMessage login(UserVO vo) {
		// TODO Auto-generated method stub
		UserPO userpo=new UserPO(vo.getUserName(), vo.getPassword(), vo.getLevel());
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("userLogin", userpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		UserPO userpo=new UserPO(vo.getUserName(), vo.getPassword(), vo.getLevel());
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("userAdd", userpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.getUserName().equals("0003")){
			return ResultMessage.Exist;
		}
		else{
			return ResultMessage.Not_Exist;
		}
	}

	@Override
	public ResultMessage update(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.getUserName().equals("0004")){
			return ResultMessage.Exist;
		}
		else{
			return ResultMessage.Not_Exist;
		}
	}

	@Override
	public UserVO find(String userName) {
		// TODO Auto-generated method stub
		return new UserVO(userName, userName, 0);
	}

}
