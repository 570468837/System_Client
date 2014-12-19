package PO;

import java.io.Serializable;
import java.util.ArrayList;

import VO.CustomerVO;
import VO.UserVO;

public class PurchaseReceiptPO implements Serializable {
	
	private CustomerPO customerPO;
	
	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	private String serialNumber;
	private ArrayList<PurchaseListItemPO> purchaseList = new ArrayList<PurchaseListItemPO>();
	private UserPO userPO;
	private String time;
	private String comments;
	private double totalPrice;
	
	
	private boolean isApprovedByManager=false;
	private boolean isApprovedByCommodity=false;
	
	
	public PurchaseReceiptPO(){}
	
	public PurchaseReceiptPO(CustomerPO customerPO, String serialNumber,
			UserPO userPO, String time, String comments, double totalPrice) {
		this.customerPO=customerPO;
		this.serialNumber=serialNumber;
		this.userPO=userPO;
		this.time=time;
		this.comments=comments;
		this.totalPrice=totalPrice;
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



	public PurchaseReceiptPO(String serialNumber,
			ArrayList<PurchaseListItemPO> purchaseList, UserPO userPO,
			String time, String comments, double totalPrice) {
		
		this.serialNumber=serialNumber;
		this.purchaseList=purchaseList;
		this.userPO=userPO;
		this.time=time;
		this.comments=comments;
		this.totalPrice=totalPrice;
		
	}
	
	//拷贝
	public PurchaseReceiptPO(PurchaseReceiptPO po){
		this.setApprovedByCommodity(po.isApprovedByCommodity());
		this.setApprovedByManager(po.isApprovedByManager());
		this.setComments(po.getComments());
		this.setCustomerPO(po.getCustomerPO());
		this.setPurchaseList(po.getPurchaseList());
		this.setSerialNumber(po.getSerialNumber());
		this.setTime(po.getTime());
		this.setTotalPrice(po.getTotalPrice());
		this.setUserPO(po.getUserPO());
	}

	// 添加商品
	public void addPurchaseListItem(PurchaseListItemPO purchaseListItemPO) {
		this.purchaseList.add(purchaseListItemPO);
	}

	public ArrayList<PurchaseListItemPO> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(ArrayList<PurchaseListItemPO> purchasesList) {
		this.purchaseList = purchasesList;
	}

	public double getTotalPrice() {
		// 遍历所有商品以计算总价
		for (int i = 0; i < this.purchaseList.size(); i++) {
			this.totalPrice += this.purchaseList.get(i).getTotalPrice();
		}
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
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
