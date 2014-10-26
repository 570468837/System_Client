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
	

	@Override
	public GoodsVO getGoodsByID(int id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsVO> getGoodsVOList() {
		
		return null;
	}

	@Override
	public GoodsClassVO getGoodsClassByID(int id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsClassVO> getGoodsClassVOList() {
		
		return null;
	}

	@Override
	public ResultMessage addGoods(GoodsVO goodsVO) {
		
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delGoods(int id) {
		
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage updGoods(GoodsVO goodsVO) {
		
		return ResultMessage.update_success;
	}

	@Override
	public ArrayList<GoodsVO> searchGoods(String info) {
		
		return new ArrayList<GoodsVO>();
	}

	@Override
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO) {
		
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delGoodsClass(int id) {
		
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO) {
		
		return ResultMessage.update_success;
	}


}
