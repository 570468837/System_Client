package VO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityVO {
	private GoodsVO goodsVO;
	private CustomerVO customerVO;
	private int num;
	
	public SendCommodityVO() {}
	public SendCommodityVO(GoodsVO goodsVO, CustomerVO customerVO, int num) {
		this.goodsVO = goodsVO;
		this.customerVO = customerVO;
		this.num = num;
	}
	
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public int getNum() {
		return num;
	}

}
