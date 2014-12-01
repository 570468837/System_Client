package PO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityPO {
	public long goodsPOId;
	public String customerPOName;
	public int num;
	public boolean checked;
	
	
	public SendCommodityPO() {}
	public SendCommodityPO(long goodsPOId, String customerPOName, int num) {
		this.goodsPOId = goodsPOId;
		this.customerPOName = customerPOName;
		this.num = num;
	}

}
