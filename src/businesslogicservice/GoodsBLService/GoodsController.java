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
	private ListReload listReload;
	private final static int reloadTime = 500; //0.5秒刷新一次
	
	public GoodsController() {
		com = new Communication_Start();
		com.initial();
		listReload = new ListReload();
		listReload.start();
	}

	@Override
	public GoodsVO getGoodsByID(long id) {
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
		return goodsVOList;
	}

	@Override
	public GoodsClassVO getGoodsClassByID(long id) {
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
		//TODO
		return new ArrayList<GoodsVO>();
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
	
	class ListReload extends Thread {
		ArrayList<Object> goodsPOList;
		ArrayList<Object> goodsClassPOList;
		GoodsVO g;
		GoodsClassVO gc;
		@Override
		public void run() {
			while(true) {
				goodsVOList.clear();
				goodsClassVOList.clear();
				try {
					//TODO
					goodsPOList = Communication_Start.client.showObject("goodsListGet");
					goodsClassPOList = Communication_Start.client.showObject("goodsClassListGet");
					
				} catch (RemoteException e) {
					System.out.println("list get error");
				}
				for(int i = 0; i < goodsPOList.size(); i ++) {
					g = new GoodsVO();
					g.toVO((GoodsPO)goodsPOList.get(i));
					goodsVOList.add(g);
				}
				for(int i = 0; i < goodsClassPOList.size(); i ++) {
					gc = new GoodsClassVO();
					gc.toVO((GoodsClassPO)goodsClassPOList.get(i));
					goodsClassVOList.add(gc);
				}
				
				
				try {
					Thread.sleep(reloadTime);
				} catch (InterruptedException e) {
					System.out.println("reload error");
				}
			}
		}
		
	}
	

}
