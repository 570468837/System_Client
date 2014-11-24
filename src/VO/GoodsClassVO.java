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
	 * 判断两个商品分类是否相同
	 * @return
	 */
	public boolean equal(GoodsClassVO gcv) {
		if(this.goodsClassName.equals(gcv))
			return true;
		return false;
	}

}
