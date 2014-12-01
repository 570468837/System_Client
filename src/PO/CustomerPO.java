package PO;

import java.io.Serializable;

import Config.Level;
import Config.Sort;
import ResultMessage.ResultMessage;
import VO.UserVO;

public class CustomerPO implements Serializable{
	
	//TODO 后期需要设置默认值，防止某个属性值为空报异常
		private String number;
		private Sort sort;
		private Level level;
		private String name;
		private String phone;
		private String address;
		//邮编
		private String zipCode;
		private String mail;
		//业务员
		private String clerk;
		//应收应付和应收额度
		private double getting;
		private double pay;
		private double debt_upper_limit;
		
		
		public double getGetting() {
			return getting;
		}
		//只可以在进货管理和销售管理中修改
		public void setGetting(double getting) {
			this.getting = getting;
		}

		public double getPay() {
			return pay;
		}

		public void setPay(double pay) {
			this.pay = pay;
		}

		public double getDebt_upper_limit() {
			return debt_upper_limit;
		}

		public ResultMessage setDebt_upper_limit(double debt_upper_limit,UserPO po) {
			if(po.level==3){
				this.debt_upper_limit=debt_upper_limit;
				return ResultMessage.update_success;
			}
			else{
				System.out.println("权限过低！");
				return ResultMessage.update_failure;
			}
		}
		
		public CustomerPO(){}
		
		public CustomerPO(String number, Sort sort, Level level, String name,
				String phone, String address, String zipCode, String mail,
				String clerk) {
			this.number=number;
			this.sort=sort;
			this.level=level;
			this.name=name;
			this.phone=phone;
			this.address=address;
			this.zipCode=zipCode;
			this.mail=mail;
			this.clerk=clerk;
		}
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public Sort getSort() {
			return sort;
		}
		public void setSort(Sort sort) {
			this.sort = sort;
		}
		public Level getLevel() {
			return level;
		}
		public void setLevel(Level level) {
			this.level = level;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getClerk() {
			return clerk;
		}
		public void setClerk(String clerk) {
			this.clerk = clerk;
		}

		

}
