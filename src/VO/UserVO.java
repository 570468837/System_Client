package VO;

public class UserVO {
	 String userName;
	 String password;
	 int level;
	 
	 public UserVO(){ }
	 
	 public UserVO(String userName,String password){
		 this.userName=userName;
		 this.password=password;
	 }
	    
	    public String getUserName(){
	    	return this.userName;
	    }
	    
	    public String getPassword(){
	    	return this.password;
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

		public int getLevel() {
			return level;
		}
}
