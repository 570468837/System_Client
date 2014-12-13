package VO;

import java.util.ArrayList;

import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;

/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityVO {
	public String[][] info;
	
	
	@SuppressWarnings({ "unchecked" })
	public CheckCommodityVO(Object receipt) {
		
		ArrayList<Object> r = (ArrayList<Object>)receipt;
		ArrayList<PurchaseReceiptPO> purchaseReceipts = (ArrayList<PurchaseReceiptPO>)r.get(0);//进货单
		ArrayList<PurchaseReceiptPO> purchaseOutReceipts = (ArrayList<PurchaseReceiptPO>)r.get(1);//进货退货单
		ArrayList<PurchaseListItemPO> plPOList;
		PurchaseListItemPO plPO;
		
		//TODO
		ArrayList<SalesReceiptPO> salesReceipts = (ArrayList<SalesReceiptPO>)r.get(2);
		ArrayList<SalesReceiptPO> salesOutReceipts = (ArrayList<SalesReceiptPO>)r.get(3);
		ArrayList<SalesListItemPO> slPOList;
		SalesListItemPO slPO;
		
		ArrayList<String[]> infoList = new ArrayList<String[]>();
		double sum = 0;
		
		for (int i = 0; i < purchaseReceipts.size(); i ++) {
			plPOList = purchaseReceipts.get(i).getPurchaseList();
			for (int j = 0; j < plPOList.size(); j ++) {
				plPO = plPOList.get(j);
				String[] s = new String[5];
				s[0] = plPO.getGoodsPO().getName() + "/" + plPO.getGoodsPO().getModel(); //商品
				s[1] = "" + plPO.getQuantity(); //数量
				s[2] = "" + plPO.getTotalPrice() / plPO.getQuantity(); //单价
				s[3] = "进货"; //类型
				s[4] = "-" + plPO.getTotalPrice(); //合计
				infoList.add(s);
				sum -= plPO.getTotalPrice();
			}
		}
		for (int i = 0; i < purchaseOutReceipts.size(); i ++) {
			plPOList = purchaseOutReceipts.get(i).getPurchaseList();
			for (int j = 0; j < plPOList.size(); j ++) {
				plPO = plPOList.get(j);
				String[] s = new String[5];
				s[0] = plPO.getGoodsPO().getName() + "/" + plPO.getGoodsPO().getModel(); //商品
				s[1] = "" + plPO.getQuantity(); //数量
				s[2] = "" + plPO.getTotalPrice() / plPO.getQuantity(); //单价
				s[3] = "进货退货"; //类型
				s[4] = "" + plPO.getTotalPrice(); //合计
				infoList.add(s);
				sum += plPO.getTotalPrice();
			}
		}
		
		for (int i = 0; i < salesReceipts.size(); i ++) {
			slPOList = salesReceipts.get(i).getSalesList();
			for (int j = 0; j < slPOList.size(); j ++) {
				slPO = slPOList.get(j);
				String[] s = new String[5];
				s[0] = slPO.getGoodsPO().getName() + "/" + slPO.getGoodsPO().getModel(); //商品
				s[1] = "" + slPO.getQuantity(); //数量
				s[2] = "" + slPO.getTotalPrice() / slPO.getQuantity(); //单价
				s[3] = "销售"; //类型
				s[4] = "" + slPO.getTotalPrice(); //合计
				infoList.add(s);
				sum += slPO.getTotalPrice();
			}
		}
		
		for (int i = 0; i < salesOutReceipts.size(); i ++) {
			slPOList = salesOutReceipts.get(i).getSalesList();
			for (int j = 0; j < slPOList.size(); j ++) {
				slPO = slPOList.get(j);
				String[] s = new String[5];
				s[0] = slPO.getGoodsPO().getName() + "/" + slPO.getGoodsPO().getModel(); //商品
				s[1] = "" + slPO.getQuantity(); //数量
				s[2] = "" + slPO.getTotalPrice() / slPO.getQuantity(); //单价
				s[3] = "销售退货"; //类型
				s[4] = "" + slPO.getTotalPrice(); //合计
				infoList.add(s);
				sum -= slPO.getTotalPrice();
			}
		}
		
		
		
		info = new String[infoList.size() + 1][5];
		info[0][0] = "/"; info[0][1] = "/"; info[0][2] = "/"; info[0][3] = "/"; info[0][4] = "" + sum;
		
		String[] s;
		for (int i = 0; i < infoList.size(); i ++) {
			s = infoList.get(i);
			info[i + 1][0] = s[0];
			info[i + 1][1] = s[1];
			info[i + 1][2] = s[2];
			info[i + 1][3] = s[3];
			info[i + 1][4] = s[4];
		}
		
		
		
		if (receipt == null) info = null;
	}

}
