package PO;

public class UserPO {
    String userName;
    String password;
    int level;
    
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
