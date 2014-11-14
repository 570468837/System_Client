package VO;

public class UserVO {
	 String userName;
	 String password;
	 int level;
	 
	 public UserVO(String userName,String password,int level){
		 this.userName=userName;
		 this.password=password;
		 this.level=0;
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
