package VO;

import java.util.ArrayList;

public class PurchaseReceiptVO  {

	private String serialNumber;
	private UserVO userVO;
	private String time;
	private String comments;
	private long totalPrice;
	
	
	private ArrayList<PurchaseListItemVO> purchaseList;
	
	public void addPurchaseListItem(PurchaseListItemVO purchaseListItem){
		this.purchaseList.add(purchaseListItem);
	}
	
	
	public ArrayList<PurchaseListItemVO> getPurchaseList() {
		return purchaseList;
	}
	public void setPurchaseList(ArrayList<PurchaseListItemVO> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
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
	public long getTotalPrice() {
		//遍历所有商品以获得总价
		for(int i=0;i<this.purchaseList.size();i++){
			this.totalPrice+=this.purchaseList.get(i).getTotalPrice();
		}
		return totalPrice;
	}


	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	 	
}

