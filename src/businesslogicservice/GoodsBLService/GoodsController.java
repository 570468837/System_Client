package businesslogicservice.GoodsBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.GoodsPO;
import RMI.Communication_Start;
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
	public GoodsVO getGoodsByID(long id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsVO> getGoodsVOList() {
		
		return null;
	}

	@Override
	public GoodsClassVO getGoodsClassByID(long id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsClassVO> getGoodsClassVOList() {
		GoodsClassVO a = new GoodsClassVO("a");
		GoodsClassVO a_a = new GoodsClassVO(a ,"a_a");
		GoodsClassVO a_b = new GoodsClassVO(a ,"a_b");
		ArrayList<GoodsClassVO> al = new ArrayList<GoodsClassVO>();
		for (int i = 0; i < 18; i ++) {al.add(a);}
		for (int i = 0; i < 8; i ++) {al.add(a_a);}
		al.add(a_b);
		return al;
	}

	@Override
	public ResultMessage addGoods(GoodsVO goodsVO) {
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("goodsAdd", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoods(long id) {
		Communication_Start com = new Communication_Start();
		com.initial();
		GoodsPO po = new GoodsPO();
		po.getSerialNumber() = id;
		try {
			return com.client.messageCommand("goodsDel", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoods(GoodsVO goodsVO) {
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("goodsUpd", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.update_failure;
		}
	}

	@Override
	public ArrayList<GoodsVO> searchGoods(String info) {
		
		return new ArrayList<GoodsVO>();
	}

	@Override
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO) {
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("goodsClassAdd", goodsClassVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoodsClass(long id) {
		Communication_Start com = new Communication_Start();
		com.initial();
		GoodsClassPO po = new GoodsClassPO();
		po.id = id;
		try {
			return com.client.messageCommand("goodsClassDel", po);
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO) {
		Communication_Start com = new Communication_Start();
		com.initial();
		try {
			return com.client.messageCommand("goodsClassUpd", goodsClassVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.update_failure;
		}
	}

	@Override
	public GoodsVO getGoodsByInfo(String name, String model) {
		return null;
	}

	@Override
	public GoodsClassVO getGoodsClassByInfo(String name) {
		return null;
	}

}
