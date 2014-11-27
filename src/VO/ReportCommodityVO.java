package VO;
/**
 * 
 * @author hutao
 *
 */
public class ReportCommodityVO {
	public long goodsVOID;
	public int num;
	
	public ReportCommodityVO() {}
	public ReportCommodityVO(String goodsVOID, int num) {
		this.goodsVOID = Long.parseLong(goodsVOID);
		this.num = num;
	}
	
}
