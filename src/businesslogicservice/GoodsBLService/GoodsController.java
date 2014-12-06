package businesslogicservice.GoodsBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.GoodsClassPO;
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
	Communication_Start com;
	
	public GoodsController() {
		com = new Communication_Start();
		com.initial();
	}

	@Override
	public GoodsVO getGoodsByID(long id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsVO> getGoodsVOList() {
		try {
			ArrayList<Object> o = com.client.showObject("goodsListGet");
			ArrayList<GoodsVO> g = new ArrayList<GoodsVO>();
			for(int i = 0; i < o.size(); i ++) {
				g.add((GoodsVO)o.get(i));
			}
			return g;
		} catch (RemoteException e) {
			return null;
		}
	}

	@Override
	public GoodsClassVO getGoodsClassByID(long id) {
		
		return null;
	}

	@Override
	public ArrayList<GoodsClassVO> getGoodsClassVOList() {
		try {
			ArrayList<Object> o = com.client.showObject("goodsClassListGet");
			ArrayList<GoodsClassVO> g = new ArrayList<GoodsClassVO>();
			for(int i = 0; i < o.size(); i ++) {
				g.add((GoodsClassVO)o.get(i));
			}
			return g;
		} catch (RemoteException e) {
			return null;
		}
	}

	@Override
	public ResultMessage addGoods(GoodsVO goodsVO) {
		try {
			return com.client.messageCommand("goodsAdd", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoods(long id) {
		GoodsVO vo = new GoodsVO();
		vo.serialNumber = Long.toString(id);
		try {
			return com.client.messageCommand("goodsDel", vo.toPO());
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoods(GoodsVO goodsVO) {
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
		try {
			return com.client.messageCommand("goodsClassAdd", goodsClassVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoodsClass(long id) {
		GoodsClassPO po = new GoodsClassPO();
		po.Num = id;
		try {
			return com.client.messageCommand("goodsClassDel", po);
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO) {
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
