package businesslogicservice.PromotionBLService;

import java.rmi.RemoteException;

import PO.PromotionPO;
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
			result=com.client.messageCommand("PromotionAddPackage", po);
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
			result=com.client.messageCommand("PromotionAddGift", po);
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
			result=com.client.messageCommand("PromotionAddVoucher", po);
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
			result=com.client.messageCommand("PromotionDelete", po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


}
