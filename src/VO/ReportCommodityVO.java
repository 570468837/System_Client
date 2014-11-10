package VO;
/**
 * 
 * @author hutao
 *
 */
public class ReportCommodityVO extends CommodityBillVO {
	private GoodsVO goodsVO;
	private int num;
	
	public ReportCommodityVO() {}
	public ReportCommodityVO(GoodsVO goodsVO, int num) {
		this.goodsVO = goodsVO;
		this.num = num;
	}
	
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public int getNum() {
		return num;
	}
	
}
