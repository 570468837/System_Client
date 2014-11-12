package VO;

import java.util.ArrayList;

public class CollectionVO {
		String number ;
		CustomerVO customer ;
		UserVO user ;
		ArrayList<TransferListItem> trList = new ArrayList<TransferListItem>();//转账列表
		double total ;
		public CollectionVO(){
			number = null ;
			customer = null ;
			user = null ;
			total = 0 ;
		}
		public CollectionVO(String theNumber,CustomerVO theCustomer,UserVO theUser, double theSum){
			number = theNumber ; 
			customer = theCustomer ; 
			user = theUser ; 
			total = theSum ;
		}
		public void add(TransferListItem theItem){
			trList.add(theItem) ;
		}
		public double getTotal(){
			double total = 0 ;
			for(TransferListItem theItem:trList){
				total += theItem.getTransferMoney() ;
			}
			return total ;
		}
		public String getNumber() {
			return number;
		}
		
		public void setNumber(String number) {
			this.number = number;
		}
		public CustomerVO getCustomer() {
			return customer;
		}
		public void setCustomer(CustomerVO customer) {
			this.customer = customer;
		}
		public UserVO getUser() {
			return user;
		}
		public void setUser(UserVO user) {
			this.user = user;
		}
		

}
