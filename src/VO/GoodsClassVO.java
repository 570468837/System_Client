package VO;
/**
 * 
 * @author hutao
 *
 */
public class GoodsClassVO {
	public long fatherGoodsClassNum;
	public long Num;//商品分类编号
	public String goodsClassName;
	
	
	public GoodsClassVO() {}
	public GoodsClassVO(GoodsClassVO fatherGoodsClass, String name) {
		this.fatherGoodsClassNum = fatherGoodsClass.Num;
		this.goodsClassName = name;
	}
	public GoodsClassVO(String name) {
		this.fatherGoodsClassNum = 0;
		this.goodsClassName = name;
	}
	/**
	 * 根据编号判断两个商品分类是否相同
	 * @return
	 */
	public boolean equal(GoodsClassVO gcv) {
		if(this.Num == gcv.Num)
			return true;
		return false;
	}

}
