package PO;

import java.util.Date;

/**
 * 
 * @author hutao
 *
 */
public class SendCommodityPO {
	public long goodsPOId;
	public String customerPOName;
	public int num;
	public int checked;
	public Date date;
	

	public static final int UNCHECKED = 0;
	public static final int PASS = 1;
	public static final int CANCEL= 2;

}
