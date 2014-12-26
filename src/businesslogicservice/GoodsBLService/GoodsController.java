package businesslogicservice.GoodsBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import PO.GoodsClassPO;
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
	private ArrayList<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
	private ArrayList<GoodsClassVO> goodsClassVOList = new ArrayList<GoodsClassVO>();
	private Iterator<GoodsVO> gIter;
	private Iterator<GoodsClassVO> gcIter;
	
	private Communication_Start com;
	
	public GoodsController() {
		com = new Communication_Start();
		com.initial();
		readGoodsList();
		readGoodsClassList();
	}

	@Override
	public GoodsVO getGoodsByID(long id) {
		readGoodsList();
		gIter = goodsVOList.iterator();
		GoodsVO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.serialNumber.equals(Long.toString(id)))
				return new GoodsVO(g);
		}
		return null;
	}

	@Override
	public ArrayList<GoodsVO> getGoodsVOList() {
		readGoodsList();
		return goodsVOList;
	}

	@Override
	public GoodsClassVO getGoodsClassByID(long id) {
		readGoodsClassList();
		gcIter = goodsClassVOList.iterator();
		GoodsClassVO gc;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.Num == id)
				return new GoodsClassVO(gc);
		}
		System.out.println("goodsClass not found");
		return null;
	}

	@Override
	public ArrayList<GoodsClassVO> getGoodsClassVOList() {
		readGoodsClassList();
		return goodsClassVOList;
	}

	@Override
	public ResultMessage addGoods(GoodsVO goodsVO) {
		try {
			return Communication_Start.client.messageCommand("goodsAdd", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoods(long id) {
		GoodsVO vo = new GoodsVO();
		vo.serialNumber = Long.toString(id);
		try {
			return Communication_Start.client.messageCommand("goodsDel", vo.toPO());
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoods(GoodsVO goodsVO) {
		try {
			return Communication_Start.client.messageCommand("goodsUpd", goodsVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.update_failure;
		}
	}

	@Override
	public ArrayList<GoodsVO> searchGoods(String info) {
		ArrayList<Object> gp;
		try {
			gp = Communication_Start.client.findObject("findGoods", info);
		} catch (RemoteException e) {
			e.printStackTrace();
			gp = new ArrayList<Object>();
		}
		ArrayList<GoodsVO> gv = new ArrayList<GoodsVO>();
		Iterator<Object> it = gp.iterator();
		GoodsVO g;
		while(it.hasNext()) {
			g = new GoodsVO();
			g.toVO((GoodsPO)it.next());
			gv.add(g);
		}
		
		return gv;
	}

	@Override
	public ResultMessage addGoodsClass(GoodsClassVO goodsClassVO) {
		try {
			return Communication_Start.client.messageCommand("goodsClassAdd", goodsClassVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.add_failure;
		}
	}

	@Override
	public ResultMessage delGoodsClass(long id) {
		GoodsClassPO po = new GoodsClassPO();
		po.Num = id;
		try {
			return Communication_Start.client.messageCommand("goodsClassDel", po);
		} catch (RemoteException e) {
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage updGoodsClass(GoodsClassVO goodsClassVO) {
		try {
			return Communication_Start.client.messageCommand("goodsClassUpd", goodsClassVO.toPO());
		} catch (RemoteException e) {
			return ResultMessage.update_failure;
		}
	}

	@Override
	public GoodsVO getGoodsByInfo(String name, String model) {
		readGoodsList();
		gIter = goodsVOList.iterator();
		GoodsVO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.name.equals(name) && g.model.equals(model))
				return new GoodsVO(g);
		}
		System.out.println("goods not found");
		return null;
	}

	@Override
	public GoodsClassVO getGoodsClassByInfo(String name) {
		readGoodsClassList();
		gcIter = goodsClassVOList.iterator();
		GoodsClassVO gc;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.goodsClassName.equals(name))
				return new GoodsClassVO(gc);
		}
		System.out.println("goodsClass not found");
		return null;
	}
	
	
	private void readGoodsList() {
		ArrayList<Object> goodsPOList;
		GoodsVO g;
		goodsVOList.clear();
		try {
			goodsPOList = Communication_Start.client.showObject("goodsListGet");
			
		} catch (RemoteException e) {
			System.out.println("list get error");
			goodsPOList = null;
		}
		for(int i = 0; i < goodsPOList.size(); i ++) {
			g = new GoodsVO();
			g.toVO((GoodsPO)goodsPOList.get(i));
			goodsVOList.add(g);
		}
	}
	
	private void readGoodsClassList() {
		ArrayList<Object> goodsClassPOList;
		GoodsClassVO gc;
		goodsClassVOList.clear();
		try {
			goodsClassPOList = Communication_Start.client.showObject("goodsClassListGet");
			
		} catch (RemoteException e) {
			System.out.println("list get error");
			goodsClassPOList = null;
		}
		for(int i = 0; i < goodsClassPOList.size(); i ++) {
			gc = new GoodsClassVO();
			gc.toVO((GoodsClassPO)goodsClassPOList.get(i));
			goodsClassVOList.add(gc);
		}
	}
	
	
	
	public static void main(String[] args) {
		GoodsController gc = new GoodsController();
		System.out.println(gc.getGoodsByID(1000000001).name);
	}

}
