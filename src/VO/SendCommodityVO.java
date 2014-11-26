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
	public SendCommodityVO(long goodsVOId, String customerVOName, int num) {
		this.goodsVOId = goodsVOId;
		this.customerVOName = customerVOName;
		this.num = num;
	}

}
