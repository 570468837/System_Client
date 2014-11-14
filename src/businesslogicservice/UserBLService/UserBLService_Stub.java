package businesslogicservice.UserBLService;

import java.util.ArrayList;

import PO.UserPO;
import ResultMessage.ResultMessage;
import VO.UserVO;

public class UserBLService_Stub implements UserBLService{

	@Override
	public ResultMessage login(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.getUserName().equals("0001")&&vo.getPassword().equals("0001")){
			return ResultMessage.login_success;
		}
		else {
			return ResultMessage.login_failure;
		}
	}

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.getUserName().equals("0002")){
			return ResultMessage.Exist;
		}
		else{
			return ResultMessage.Not_Exist;
		}
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
	public ArrayList<UserVO> find(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
