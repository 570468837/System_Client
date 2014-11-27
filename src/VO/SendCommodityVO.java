package VO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityVO {
	public long goodsVOId;
	public String customerVOName;
	public int num;
	
	public SendCommodityVO() {}
	public SendCommodityVO(String goodsVOId, String customerVOName, int num) {
		this.goodsVOId = Long.parseLong(goodsVOId);
		this.customerVOName = customerVOName;
		this.num = num;
	}

}
