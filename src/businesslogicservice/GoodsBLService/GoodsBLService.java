package businesslogicservice.GoodsBLService;

import java.util.*;

import PO.*;

/**
 * 
 * @author hutao
 *
 */

public interface GoodsBLService {
	
	public GoodsPO getGoodsByID(int id);
	public ArrayList<GoodsPO> getGoodsPOList();
	public GoodsClassPO getGoodsClassByID(int id);
	public ArrayList<GoodsClassPO> getGoodsClassPOList();
	
	
	
	
	

}



