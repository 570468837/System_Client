package businesslogicservice.GoodsBLService;

import java.util.*;
import VO.*;
import ResultMessage.*;

/**
 * 
 * @author hutao
 *
 */

public interface GoodsBLService {
	
	public GoodsVO getGoodsByID(long id);
	public GoodsVO getGoodsByInfo(String name, String model);
	public ArrayList<GoodsVO> getGoodsVOList();
	public GoodsClassVO getGoodsClassByID(long id);
	public GoodsClassVO getGoodsClassByInfo(String name);
	public ArrayList<GoodsClassVO> getGoodsClassVOList();
	
	public ResultMessage addGoods(GoodsVO goodsVO);
	public ResultMessage delGoods(long id);
	public ResultMessage updGoods(GoodsVO goodsVO);
	public ArrayList<GoodsVO> searchGoods(String info);
	
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO);
	public ResultMessage delGoodsClass(long id);
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO);

}



