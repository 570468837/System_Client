package PO;

public class PurchaseReceiptPO {
	
		private String serialNumber;
		private GoodsPO goodsPO;
		private UserPO userPO;
		private String time;
		private String comments;
		
		
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public GoodsPO getGoodsPO() {
			return goodsPO;
		}
		public void setGoodsPO(GoodsPO goodsPO) {
			this.goodsPO = goodsPO;
		}
		public UserPO getUserPO() {
			return userPO;
		}
		public void setUserPO(UserPO userPO) {
			this.userPO = userPO;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
			
	
}
