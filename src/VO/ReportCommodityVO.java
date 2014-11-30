package VO;

import PO.ReportCommodityPO;


/**
 * 库存报单VO
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
	

	/**
	 * 将VO转换为PO
	 * @return 返回转换成的PO
	 */
	public ReportCommodityPO toPO() {
		ReportCommodityPO rcp = new ReportCommodityPO();
		rcp.goodsVOID = this.goodsVOID;
		rcp.num = this.num;
		return rcp;
		
	}
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(ReportCommodityPO po) {
		this.goodsVOID = po.goodsVOID;
		this.num = po.num;
	}
	
}
