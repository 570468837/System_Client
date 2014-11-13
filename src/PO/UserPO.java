package PO;

import java.io.Serializable;


public class UserPO implements Serializable{
    String userName;
    String password;
    int level;
    
    public UserPO(String userName, String password, int level) {
		super();
		this.userName = userName;
		this.password = password;
		this.level = level;
	}

	public String getUserName(){
    	return this.userName;
    }
    
    public String getPassword(){
    	return this.password;
    }

	public int getLevel() {
		return level;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
