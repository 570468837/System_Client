package businesslogicservice.PromotionBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import Config.PromotionSort;
import PO.PromotionPO;
import PO.SalesReceiptPO;
import RMI.Communication_Start;
import ResultMessage.ResultMessage;
import VO.PromotionVO;

public class PromotionController implements PromotionBLService {
	ResultMessage result=null;
	@Override
	public ResultMessage addPackage(PromotionVO vo) {
		// TODO Auto-generated method stub
		PromotionPO po=new PromotionPO(vo.getPromotionType(),vo.getPromotionId(), vo.getPromotionGoods(), 
				0, vo.getOffPrice(),null, 0, 
				vo.getStartTime(), vo.getEndTime(), vo.getCustomer());
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("promotionAddPackage", po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage addGift(PromotionVO vo) {
		// TODO Auto-generated method stub
		PromotionPO po=new PromotionPO(vo.getPromotionType(),vo.getPromotionId(), null, 
				vo.getLeastPrice(), 0,vo.getPresents(), 0, 
				vo.getStartTime(), vo.getEndTime(), vo.getCustomer());
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("promotionAddGift", po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage addVoucher(PromotionVO vo) {
		// TODO Auto-generated method stub
		PromotionPO po=new PromotionPO(vo.getPromotionType(),vo.getPromotionId(), null, 
				vo.getLeastPrice(), 0,null, vo.getVoucher(), 
				vo.getStartTime(), vo.getEndTime(), vo.getCustomer());
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("promotionAddVoucher", po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultMessage delete(PromotionVO vo) {
		// TODO Auto-generated method stub
		PromotionPO po=new PromotionPO(null, vo.getPromotionId(), null, 0, 0, null, 0, null,null, null);
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			result=com.client.messageCommand("promotionDelete", po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PromotionVO> show() {
		ArrayList<Object> objs=new ArrayList<Object>();
		ArrayList<PromotionVO> promotions=new ArrayList<PromotionVO>();
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			objs=com.client.showObject("promotionShow");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object obj:objs){
			PromotionPO po=(PromotionPO)obj;
			PromotionVO vo=new PromotionVO(po.getPromotionType(), po.getPromotionId(), 
					po.getPromotionGoods(), po.getLeastPrice(), po.getOffPrice(), po.getPresents(), 
					po.getVoucher(), po.getStartTime(), po.getEndTime(), po.getCustomer());
			switch(vo.getPromotionType()){
			case Package:vo.setLeastPrice(0);vo.setPresents(null);vo.setVoucher(0); break;
			case Gifts: vo.setPromotionGoods(null); vo.setOffPrice(0);vo.setVoucher(0); break;
			case Voucher:vo.setPromotionGoods(null); vo.setOffPrice(0); vo.setPresents(null);break;
			}
			promotions.add(vo);
		}
		return promotions;
	}

	public ArrayList<PromotionPO> ifPackage(SalesReceiptPO receipt){
		ArrayList<Object> getObjs=new ArrayList<Object>();
		ArrayList<PromotionPO> promotions=new ArrayList<PromotionPO>();
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			getObjs=com.client.showObjectByPO("promotionIfPackage", receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object o:getObjs){
			promotions.add((PromotionPO) o);
		}
		
		return promotions;
	}

	@Override
	public ArrayList<PromotionPO> ifGift(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		ArrayList<Object> getObjs=new ArrayList<Object>();
		ArrayList<PromotionPO> promotions=new ArrayList<PromotionPO>();
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			getObjs=com.client.showObjectByPO("promotionIfGift", receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object o:getObjs){
			promotions.add((PromotionPO) o);
		}
		return promotions;
	}

	@Override
	public ArrayList<PromotionPO> ifVoucher(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		ArrayList<Object> getObjs=new ArrayList<Object>();
		ArrayList<PromotionPO> promotions=new ArrayList<PromotionPO>();
		Communication_Start com=new Communication_Start();
		com.initial();
		try {
			getObjs=com.client.showObjectByPO("promotionIfVoucher", receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object o:getObjs){
			promotions.add((PromotionPO) o);
		}
		return promotions;
	}

}
