package businesslogicservice.GoodsBLService;

import java.util.*;
import VO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class GoodsBLService_Stub implements GoodsBLService {
	
	
	public GoodsVO getGoodsByID(int id) {
		return new GoodsVO();
	}
	public ArrayList<GoodsVO> getGoodsVOList() {
		return null;
	}
	public GoodsClassVO getGoodsClassByID(int id) {
		return null;
	}
	public ArrayList<GoodsClassVO> getGoodsClassVOList() {
		return null;
	}
	
	public ResultMessage addGoods(GoodsVO goodsVO) {
		return null;
	}
	public ResultMessage delGoods(int id) {
		return null;
	}
	public ResultMessage updGoods(GoodsVO goodsVO) {
		return null;
	}
	public ArrayList<GoodsVO> searchGoods(String info) {
		return null;
	}
	
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO) {
		return null;
	}
	public ResultMessage delGoodsClass(int id) {
		return null;
	}
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO) {
		return null;
	}
	
	

}
