package VO;
/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityVO {
	String time1;
	String time2;
	
	public CheckCommodityVO(String time1, String time2) {
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public void print() {
		System.out.println("<库存查看信息>");
	}

}
