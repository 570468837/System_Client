package VO;
/**
 * 
 * @author hutao
 *
 */
public class GoodsClassVO {
	public GoodsClassVO fatherGoodsClass;
	public String goodsClassName;
	public boolean isBottomGoodsClass = false;
	public long Num;//商品分类编号
	
	public GoodsClassVO() {}
	public GoodsClassVO(GoodsClassVO fatherGoodsClass, String name) {
		this.fatherGoodsClass = fatherGoodsClass;
		this.goodsClassName = name;
	}
	public GoodsClassVO(String name) {
		this.fatherGoodsClass = null;
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
	/**
	 * 根据分类名判断两个商品分类是否相同
	 * @param goodsClassName
	 * @return
	 */
	public boolean equal(String goodsClassName) {
		if(this.goodsClassName.equals(goodsClassName))
			return true;
		return false;
					
	}
	

}
