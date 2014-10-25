package VO;

public class UserVO {
	 String userName;
	 String password;
	 
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
}
