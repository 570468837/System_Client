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
	public double price;
	public Date date;
	
	public ReportCommodityVO(String goodsVOId, double price, int num) {
		try {
			this.goodsVOId = Long.parseLong(goodsVOId);
		}
		catch(Exception e) {
			this.goodsVOId = 0;
		}
		
		this.num = num;
		this.price = price;
		date = new Date();
	}
	public ReportCommodityVO(ReportCommodityVO vo) {
		this.goodsVOId = vo.goodsVOId;
		this.num = vo.num;
		this.price = vo.price;
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
		rcp.price = this.price;
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
		this.price = po.price;
		this.date = po.date;
	}
	public static void main(String[] args){
		ReportCommodityVO theVO = new ReportCommodityVO("123", 23, 322) ;
		System.out.print(theVO.date) ;
	}
}
