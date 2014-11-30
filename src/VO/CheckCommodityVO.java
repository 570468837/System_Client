package VO;

import PO.CheckCommodityPO;

/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityVO {
	public String time1;
	public String time2;
	public String[][] info = {
			{"a", "10", "20", "销售", "200"},
			{"b", "10", "20", "进货", "-200"},
			{"c", "10", "20", "赠送", "-200"},
			{"合计", "", "", "", "-200"},};
	
	
	public CheckCommodityVO(String time1, String time2) {
		this.time1 = time1;
		this.time2 = time2;
	}
	
	/**
	 * 将VO转换为PO
	 * @return 返回转换成的PO
	 */
	public CheckCommodityPO toPO() {
		CheckCommodityPO ccp = new CheckCommodityPO();
		ccp.time1 = this.time1;
		ccp.time2 = this.time2;
		ccp.info = this.info;
		
		return ccp;
	}
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(CheckCommodityPO po) {
		this.time1 = po.time1;
		this.time2 = po.time2;
		this.info = po.info;
	}

}
