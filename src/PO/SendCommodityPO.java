package PO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityPO {
	private GoodsPO goodsPO;
	private CustomerPO customerPO;
	private int num;
	
	public SendCommodityPO() {}
	public SendCommodityPO(GoodsPO goodsPO, CustomerPO customerPO, int num) {
		this.goodsPO = goodsPO;
		this.customerPO = customerPO;
		this.num = num;
	}
	
	public GoodsPO getGoodsPO() {
		return goodsPO;
	}
	public CustomerPO getCustomerPO() {
		return customerPO;
	}
	public int getNum() {
		return num;
	}

}
