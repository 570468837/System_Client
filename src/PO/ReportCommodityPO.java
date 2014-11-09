package PO;
/**
 * 
 * @author hutao
 *
 */
public class ReportCommodityPO {
	private GoodsPO goodsPO;
	private int num;
	
	public ReportCommodityPO() {}
	public ReportCommodityPO(GoodsPO goodsPO, int num) {
		this.goodsPO = goodsPO;
		this.num = num;
	}
	
	public GoodsPO getGoodsPO() {
		return goodsPO;
	}
	public int getNum() {
		return num;
	}
	
}
