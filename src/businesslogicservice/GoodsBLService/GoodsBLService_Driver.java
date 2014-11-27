package businesslogicservice.GoodsBLService;

import java.util.*;

import ResultMessage.ResultMessage;
import VO.*;

/**
 * 
 * @author hutao
 *
 */
public class GoodsBLService_Driver {
	
	public void drive(GoodsBLService goodsController) {
		//GoodsVO goodsVO = goodsController.getGoodsByID(0);
		System.out.println("got good0");
		
		//ArrayList<GoodsVO> goodsVOList = goodsController.getGoodsVOList();
		System.out.println("got goodsList");
		
		//GoodsClassVO goodsClassVO = goodsController.getGoodsClassByID(0);
		System.out.println("got goodClass0");
		
		//ArrayList<GoodsClassVO> goodsClassVOList = goodsController.getGoodsClassVOList();
		System.out.println("got goodsClassList");
		
		ResultMessage resultMessage = goodsController.addGoods(new GoodsVO());
		if(resultMessage == ResultMessage.add_success) {
			System.out.println("goods add success");
		}
		else {
			System.out.println("goods add failure");
		}
		
		resultMessage = goodsController.delGoods("0");
		if(resultMessage == ResultMessage.delete_success) {
			System.out.println("goods del success");
		}
		else {
			System.out.println("goods del failure");
		}
		
		resultMessage = goodsController.updGoods(new GoodsVO());
		if(resultMessage == ResultMessage.update_success) {
			System.out.println("goods upd success");
		}
		else {
			System.out.println("goods upd failure");
		}
		
		
		ArrayList<GoodsVO> goodsVOList = goodsController.searchGoods(new String());
		if(goodsVOList.size() != 0) {
			System.out.println("have searched");
		}
		else {
			System.out.println("no match result");
		}
		
		
		resultMessage = goodsController.addGoodsClass(new GoodsClassVO());
		if(resultMessage == ResultMessage.add_success) {
			System.out.println("goodsClass add success");
		}
		else {
			System.out.println("goodsClass add failure");
		}
		
		resultMessage = goodsController.delGoodsClass(0);
		if(resultMessage == ResultMessage.delete_success) {
			System.out.println("goodsClass del success");
		}
		else {
			System.out.println("goodsClass del failure");
		}
		
		resultMessage = goodsController.updGoodsClass(new GoodsClassVO());
		if(resultMessage == ResultMessage.update_success) {
			System.out.println("goodsClass upd success");
		}
		else {
			System.out.println("goodsClass upd failure");
		}
		
	}
	
	
	public static void main(String[] args) {
		GoodsBLService_Driver g = new GoodsBLService_Driver();
		g.drive(new GoodsController());
	}
	

}
