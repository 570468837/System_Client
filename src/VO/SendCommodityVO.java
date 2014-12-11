package VO;

import java.util.Date;

import PO.SendCommodityPO;


/**
 * 库存赠送单VO
 * @author hutao
 *
 */
public class SendCommodityVO {
	public long goodsVOId;
	public String customerVOName;
	public int num;
	public Date date;
	public int checked = UNCHECKED;
	
	public SendCommodityVO(String goodsVOId, String customerVOName, int num, int isChecked) {
		this.goodsVOId = Long.parseLong(goodsVOId);
		this.customerVOName = customerVOName;
		this.num = num;
		this.date = new Date();
		this.checked = isChecked;
	}


	/**
	 * 将VO转换为PO
	 * @return 返回转换成的PO
	 */
	public SendCommodityPO toPO() {
		SendCommodityPO scp = new SendCommodityPO();
		scp.goodsPOId = this.goodsVOId;
		scp.customerPOName = new String(this.customerVOName);
		scp.num = this.num;
		scp.checked = this.checked;
		scp.date = this.date;
		return scp;
	}
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(SendCommodityPO po) {
		this.goodsVOId = po.goodsPOId;
		this.customerVOName = po.customerPOName;
		this.num = po.num;
		this.checked = po.checked;
		this.date = po.date;
	}
	

	public static final int UNCHECKED = 0;
	public static final int PASS = 1;
	public static final int CANCEL= 2;
}
