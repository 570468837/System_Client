package VO;

import java.util.ArrayList;

import PO.PurchaseReceiptPO;

/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityVO {
	public String[][] info;
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public CheckCommodityVO(Object receipt) {
		ArrayList<Object> r = (ArrayList<Object>)receipt;
		ArrayList<PurchaseReceiptPO> purchaseReceipts = (ArrayList<PurchaseReceiptPO>)r.get(0);//进货单
		ArrayList<PurchaseReceiptPO> purchaseOutReceipts = (ArrayList<PurchaseReceiptPO>)r.get(1);//进货退货单
		
		
		
		
		
		
	}

}
