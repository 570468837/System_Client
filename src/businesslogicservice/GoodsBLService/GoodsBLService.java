package businesslogicservice.GoodsBLService;

import java.util.*;

import PO.*;
import ResultMessage.*;

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
	
	public ResultMessage addGoods(GoodsPO goodsPO);
	public ResultMessage delGoods(int id);
	public ResultMessage updGoods(GoodsPO goodsPO);
	public ArrayList<GoodsPO> searchGoods(String info);
	
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassPO);
	public ResultMessage delGoodsClass(int id);
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassPO);

}



