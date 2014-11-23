package VO;
/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityVO {
	private String time1;
	private String time2;
	public String[][] Info = {
			{"a", "10", "20", "销售", "200"},
			{"b", "10", "20", "进货", "-200"},
			{"c", "10", "20", "赠送", "-200"},
			{"合计", "", "", "", "-200"},};
	
	
	public CheckCommodityVO(String time1, String time2) {
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public void print() {
		System.out.println("<库存查看信息>");
	}

}
