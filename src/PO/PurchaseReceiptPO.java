package PO;

import java.util.ArrayList;

public class PurchaseReceiptPO {
	
		private String serialNumber;
		private ArrayList<PurchaseListItemPO> purchaseList;		
		private UserPO userPO;
		private String time;
		private String comments;
		private double totalPrice;
		
		//添加商品
		public void addPurchaseListItem(PurchaseListItemPO purchaseListItemPO){
			this.purchaseList.add(purchaseListItemPO);			
		}
		
		

		public ArrayList<PurchaseListItemPO> getPurchaseList() {
			return purchaseList;
		}
		public void setPurchaseList(ArrayList<PurchaseListItemPO> purchasesList) {
			this.purchaseList = purchasesList;
		}
		public double getTotalPrice() {
			//遍历所有商品以计算总价
			for(int i=0;i<this.purchaseList.size();i++){
					this.totalPrice+=this.purchaseList.get(i).getTotalPrice();				
			}
			return this.totalPrice;
		}
		public void setTotalPrice(long totalPrice) {
			this.totalPrice = totalPrice;
		}
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
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

