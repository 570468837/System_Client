package businesslogicservice.UserBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.UserVO;

public interface UserBLService {
	public ResultMessage login(UserVO vo);
    public ResultMessage add(UserVO vo);
    public ResultMessage delete(UserVO vo);
    public ResultMessage update(UserVO vo);
    public ArrayList<UserVO> find(String userName);
}
