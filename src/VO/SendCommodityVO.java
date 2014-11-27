package VO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityVO {
	public String goodsVOId;
	public String customerVOName;
	public int num;
	
	public SendCommodityVO() {}
	public SendCommodityVO(String goodsVOId, String customerVOName, int num) {
		this.goodsVOId = goodsVOId;
		this.customerVOName = customerVOName;
		this.num = num;
	}

}
