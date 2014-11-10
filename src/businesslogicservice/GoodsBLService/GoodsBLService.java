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
	
	public GoodsVO getGoodsByID(int id);
	public ArrayList<GoodsVO> getGoodsVOList();
	public GoodsClassVO getGoodsClassByID(int id);
	public ArrayList<GoodsClassVO> getGoodsClassVOList();
	
	public ResultMessage addGoods(GoodsVO goodsVO);
	public ResultMessage delGoods(int id);
	public ResultMessage updGoods(GoodsVO goodsVO);
	public ArrayList<GoodsVO> searchGoods(String info);
	
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO);
	public ResultMessage delGoodsClass(int id);
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO);

}



