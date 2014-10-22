package UserBlService;

import ResultMessage.ResultMessage;
import VO.UserVO;

public interface UserBlService {
	public ResultMessage login(UserVO vo);
    public ResultMessage add(UserVO vo);
    public ResultMessage delete(UserVO vo);
    public ResultMessage update(UserVO vo);
    public UserVO find(String userName);
}
