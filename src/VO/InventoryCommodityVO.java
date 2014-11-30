package VO;

import PO.InventoryCommodityPO;


/**
 * 库存盘点VO
 * @author hutao
 *
 */
public class InventoryCommodityVO {
	public String[][] icInfo = {{"1", "a1", "a2", "a3", "a4", "a5", "a6", "a7"},
			{"2", "a1", "a2", "a3", "a4", "a5", "a6", "a7"},
			{"3", "a1", "a2", "a3", "a4", "a5", "a6", "a7"},
			{"4", "a1", "a2", "a3", "a4", "a5", "a6", "a7"}};
	
	
	

	/**
	 * 将VO转换为PO
	 * @return 返回转换成的PO
	 */
	public InventoryCommodityPO toPO() {
		InventoryCommodityPO icv = new InventoryCommodityPO();
		icv.icInfo = this.icInfo;
		return icv;
	}
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(InventoryCommodityPO po) {
		this.icInfo = po.icInfo;
	}
	

}
