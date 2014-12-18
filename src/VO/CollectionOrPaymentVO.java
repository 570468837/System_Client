package VO;

import java.io.Serializable;
import java.util.ArrayList;

import PO.TransferListItemPO;

public class CollectionOrPaymentVO implements Serializable{
	String number ;
	String customer ;
	String typeOfCustomer ;
	String user ;
	ArrayList<TransferListItemVO> trList = new ArrayList<TransferListItemVO>();//转账列表
	double total ;
	boolean isApprovedByManager  ;
	boolean isApprovedByFinancer  ;
	public CollectionOrPaymentVO(CollectionOrPaymentVO theVO){
		this.number = theVO.getNumber() ;
		this.customer = theVO.getCustomer() ;
		this.typeOfCustomer = theVO.getTypeOfCustomer() ;
		this.user = theVO.getUser() ;
		for(TransferListItemVO theItem :theVO.getTrList()){
			TransferListItemVO item = new TransferListItemVO(theItem.getAccount(),theItem.getTransferMoney() , theItem.getRemark()) ;
			this.trList.add(item) ;
		}
		this.total = theVO.getTotal() ;
		this.isApprovedByFinancer = theVO.isApprovedByFinancer ;
		this.isApprovedByManager = theVO.isApprovedByManager ;
	}
	public CollectionOrPaymentVO(String number, String customer,
			String typeOfCustomer, String user,
			ArrayList<TransferListItemVO> trList, double total,
			boolean isApprovedByManager, boolean isApprovedByFinancer) {
		super();
		this.number = number;
		this.customer = customer;
		this.typeOfCustomer = typeOfCustomer;
		this.user = user;
		this.trList = trList;
		this.total = total;
		this.isApprovedByManager = isApprovedByManager;
		this.isApprovedByFinancer = isApprovedByFinancer;
	}
	public CollectionOrPaymentVO() {
		// TODO Auto-generated constructor stub
	}
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
	public ArrayList<TransferListItemVO> getTrList() {
		return trList;
	}
	public void setTrList(ArrayList<TransferListItemVO> trList) {
		this.trList = trList;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void add(TransferListItemVO theItem){
		trList.add(theItem) ;
	}
	public double getTotal(){
		double total = 0 ;
		if(trList != null )
		for(TransferListItemVO theItem:trList){
			if(theItem != null)
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
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public boolean isApprovedByManager() {
		return isApprovedByManager;
	}
	public void setApprovedByManager(boolean isApprovedByManager) {
		this.isApprovedByManager = isApprovedByManager;
	}
	public boolean isApprovedByFinancer() {
		return isApprovedByFinancer;
	}
	public void setApprovedByFinancer(boolean isApprovedByFinancer) {
		this.isApprovedByFinancer = isApprovedByFinancer;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}
