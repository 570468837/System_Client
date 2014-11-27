package PO;

import java.io.Serializable;

/**
 * 
 * @author hutao
 *
 */
public class GoodsClassPO implements Serializable{
	GoodsClassPO goodsClass;
	
	public GoodsClassPO() {}
	public GoodsClassPO(GoodsClassPO fatherClass) {
		this.goodsClass = fatherClass;
	}
	

}
