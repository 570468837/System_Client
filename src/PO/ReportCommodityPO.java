package PO;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author hutao
 * 
 */
public class ReportCommodityPO implements Serializable {
	public long goodsPOId;
	public int num;
	public double price;
	public Date date;
	
	public ReportCommodityPO(ReportCommodityPO po) {
		this.goodsPOId = po.goodsPOId;
		this.num = po.num;
		this.price = po.price;
		this.date = po.date;
	}
	public ReportCommodityPO() {}
	
	
}
