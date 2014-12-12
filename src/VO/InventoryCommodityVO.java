package VO;

import java.util.Iterator;

import PO.InventoryCommodityPO;


/**
 * 库存盘点VO
 * @author hutao
 *
 */
public class InventoryCommodityVO {
	public String[][] icInfo;
	
	
	
	/**
	 * 将PO转换为VO
	 * @param po 待转换的PO
	 */
	public void toVO(InventoryCommodityPO po) {
		try {
			icInfo = new String[po.icInfo.size()][((String[])po.icInfo.get(0)).length];
		}catch(Exception e) {
			icInfo = new String[0][0];
		}
		
		Iterator<Object> iter = po.icInfo.iterator();
		String[] s;
		int i = 0;
		while(iter.hasNext()) {
			s = (String[])iter.next(); 
			for (int j = 0; j < 8; j ++) {
				icInfo[i][j] = s[j];
			}
			
			i ++;
		}
		
		
	}
	

}
