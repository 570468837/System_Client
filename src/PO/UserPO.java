package PO;

import java.io.Serializable;

import Config.UserSort;


public class UserPO implements Serializable{
    String userName;
    String password;
    UserSort userSort;
    int level;
    
   public UserPO(String userName, String password, UserSort userSort, int level) {
		this.userName = userName;
		this.password = password;
		this.userSort = userSort;
		this.level = level;
	}

	public String getUserName(){
    	return this.userName;
    }
    
    public String getPassword(){
    	return this.password;
    }

	public UserSort getUserSort() {
		return userSort;
	}

	public void setUserSort(UserSort userSort) {
		this.userSort = userSort;
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
