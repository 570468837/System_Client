package PO;

import java.io.Serializable;


/**
 * 
 * @author hutao
 *
 */
public class GoodsClassPO implements Serializable{
	public long fatherGoodsClassNum;
	public long Num;
	public String goodsClassName;
	
	public GoodsClassPO() {}
	public GoodsClassPO(GoodsClassPO fatherGoodsClass, String name) {
		this.fatherGoodsClassNum = fatherGoodsClass.Num;
		this.goodsClassName = name;
	}
	public GoodsClassPO(String name) {
		this.fatherGoodsClassNum = 0;
		this.goodsClassName = name;
	}
	

}
