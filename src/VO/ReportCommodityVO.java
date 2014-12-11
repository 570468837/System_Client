package VO;

import java.util.Date;

import PO.ReportCommodityPO;


/**
 * 库存报单VO
 * @author hutao
 *
 */
public class ReportCommodityVO {
	public long goodsVOId;
	public int num;
	public Date date;
	
	public ReportCommodityVO(String goodsVOId, int num) {
		this.goodsVOId = Long.parseLong(goodsVOId);
		this.num = num;
		date = new Date();
	}
	public ReportCommodityVO(ReportCommodityVO vo) {
		this.goodsVOId = vo.goodsVOId;
		this.num = vo.num;
		this.date = vo.date;
	}

	/**
	 * 将VO转换为PO
	 * @return 返回转换成的PO
	 */
	public ReportCommodityPO toPO() {
		ReportCommodityPO rcp = new ReportCommodityPO();
		rcp.goodsPOId = this.goodsVOId;
		rcp.num = this.num;
		rcp.date = this.date;
		return rcp;
		
	}
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(ReportCommodityPO po) {
		this.goodsVOId = po.goodsPOId;
		this.num = po.num;
		this.date = po.date;
	}
	
}
