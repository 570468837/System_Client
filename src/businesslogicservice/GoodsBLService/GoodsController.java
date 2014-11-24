package businesslogicservice.GoodsBLService;

import java.util.ArrayList;

import ResultMessage.ResultMessage;
import VO.GoodsClassVO;
import VO.GoodsVO;

/**
 * 
 * @author hutao
 *
 */

public class GoodsController implements GoodsBLService {

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
		GoodsClassVO a = new GoodsClassVO("a");
		GoodsClassVO a_a = new GoodsClassVO(a ,"a_a");
		GoodsClassVO a_b = new GoodsClassVO(a ,"a_b");
		ArrayList<GoodsClassVO> al = new ArrayList<GoodsClassVO>();
		al.add(a); al.add(a_a); al.add(a_b);
		return al;
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
