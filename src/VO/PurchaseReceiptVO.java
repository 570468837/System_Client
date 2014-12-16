package VO;

import java.util.ArrayList;

public class PurchaseReceiptVO {
	private CustomerVO customerVO;
	private String serialNumber;
	private UserVO userVO;
	private String time;
	private String comments;
	private double totalPrice;
	

	// 审批
	private boolean isApprovedByManager = false;
	private boolean isApprovedByCommodity = false;

	// 防止add方法list为空指针
	private ArrayList<PurchaseListItemVO> purchaseList = new ArrayList<PurchaseListItemVO>();

	
	//
	public PurchaseReceiptVO(){
		
	}
	public PurchaseReceiptVO(CustomerVO customerVO, String serialNumber,
			UserVO userVO, String time, String comments, double totalPrice) {
		this.customerVO=customerVO;
		this.serialNumber=serialNumber;
		this.userVO=userVO;
		this.time=time;
		this.comments=comments;
		this.totalPrice=totalPrice;
	}

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public boolean isApprovedByManager() {
		return isApprovedByManager;
	}

	public void setApprovedByManager(boolean isApprovedByManager) {
		this.isApprovedByManager = isApprovedByManager;
	}

	public boolean isApprovedByCommodity() {
		return isApprovedByCommodity;
	}

	public void setApprovedByCommodity(boolean isApprovedByCommodity) {
		this.isApprovedByCommodity = isApprovedByCommodity;
	}

	public void addPurchaseListItem(PurchaseListItemVO purchaseListItem) {
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

	public double getTotalPrice() {
		// 遍历所有商品以获得总价
		for (int i = 0; i < this.purchaseList.size(); i++) {
			this.totalPrice += this.purchaseList.get(i).getTotalPrice();
		}
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
