package VO;



public class CollectionVO {
	//�տ
		String number ;
		CustomerVO customer ;//�ͻ�
		UserVO user ;//����Ա
		TransferListItem tfList = null ;//ת���б�
		double sum ;//�ܽ��
		public CollectionVO(){
			number = null ;
			customer = null ;
			user = null ;
			tfList = null ;
			sum = 0 ;
		}
		public CollectionVO(String theNumber,CustomerVO theCustomer,UserVO theUser,TransferListItem theTfList, double theSum){
			number = theNumber ; 
			customer = theCustomer ; 
			user = theUser ; 
			tfList = theTfList ; 
			sum = theSum ;
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
		public TransferListItem getTfList() {
			return tfList;
		}
		public void setTfList(TransferListItem tfList) {
			this.tfList = tfList;
		}
		public double getSum() {
			return sum;
		}
		public void setSum(double sum) {
			this.sum = sum;
		}
		

}
