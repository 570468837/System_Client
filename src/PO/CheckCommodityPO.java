package PO;
/**
 * 
 * @author hutao
 *
 */
public class CheckCommodityPO {
	String time1;
	String time2;
	
	public CheckCommodityPO(String time1, String time2) {
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public void print() {
		System.out.println("<库存查看信息>");
	}

}
