package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import PO.GoodsPO;
import VO.GoodsClassVO;
import VO.GoodsVO;
import VO.ReportCommodityVO;
import VO.SendCommodityVO;
import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.GoodsBLService.GoodsController;

/**
 * 添加报单和添加赠送单的可操作界面
 * @author hutao
 *
 */
class SendFrame extends JFrame {
	JFrame frame;
	CommodityController cc;
	GoodsController gc;
	JTextField 
	    sendCustomer,
	    sendName,
	    sendModel,
	    sendNum;
	JLabel send;
	public SendFrame(SendCommodityVO theVO) {
		super("赠送单制定");
		this.setSize(250, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame = this;
		
		cc = new CommodityController();
		gc = new GoodsController();
		
		sendCustomer = new JTextField(theVO.customerVOName);
		sendCustomer.setBounds(10, 10, 100, 25);
//		new AddWordsChange(sendCustomer, "<客户名>");
		
		GoodsVO theGood = gc.getGoodsByID(theVO.goodsVOId) ;
		sendName = new JTextField(theGood.name);
		sendName.setBounds(10, 35, 100, 25);
//		new AddWordsChange(sendName, "<商品名>");
		
		sendModel = new JTextField(theGood.model);
		sendModel.setBounds(110, 10, 100, 25);
//		new AddWordsChange(sendModel, "<商品型号>");
		
		sendNum = new JTextField(theVO.num);
		sendNum.setBounds(110, 35, 100, 25);
//		new AddWordsChange(sendNum, "<赠送数量>");
		
		send = new JLabel("赠送", JLabel.CENTER);
		send.setBounds(210, 10, 60, 25);
		send.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = 0;
				try {
					num = Integer.parseInt(sendNum.getText());
				}catch(Exception exc) {
					frame.setTitle("赠送单输入有误");
				}
				if (num > 0) {
					GoodsVO vo;
					if(cc.addSendCommodity(new SendCommodityVO(
							(vo = gc.getGoodsByInfo(sendName.getText(), sendModel.getText())).serialNumber,
							sendCustomer.getText(),
							num,
							vo.price,
							SendCommodityVO.PASS)) == ResultMessage.ResultMessage.add_success) {
						frame.setTitle("赠送单添加成功");
					}
					else {
						frame.setTitle("赠送单添加失败");
					}
				}
				sendCustomer.setText("");
				sendName.setText("");
				sendModel.setText("");
				sendNum.setText("");
			}
		});
		
		this.add(sendCustomer);
		this.add(sendName);
		this.add(sendNum);
		this.add(sendModel);
		this.add(send);
		this.setVisible(true);
	}
	

}

class ReportFrame extends JFrame {
	JFrame frame;
	CommodityController cc;
	GoodsController gc;
	
	JTextField 
	    reportName,
	    reportModel,
	    reportNum;
	JComboBox<String>
	    reportType;
	JLabel 
	    report;
	
	public ReportFrame(ReportCommodityVO theVO) {
		super("库存报单制定");
		this.setSize(250, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame = this;
		
		cc = new CommodityController();
		gc = new GoodsController();
		
		GoodsVO theGood = gc.getGoodsByID(theVO.goodsVOId) ;
		reportName = new JTextField(theGood.name);
		reportName.setBounds(10, 10, 100, 25);
//		new AddWordsChange(reportName, "<商品名>");
		
		reportModel = new JTextField(theGood.model);
		reportModel.setBounds(10, 35, 100, 25);
//		new AddWordsChange(reportModel, "<商品型号>");
		
		reportNum = new JTextField(theVO.num);
		reportNum.setBounds(110, 10, 100, 25);
//		new AddWordsChange(reportNum, "<商品数量>");
		
		reportType = new JComboBox<String>(
				new String[] {"<单据类型>", "报溢单", "报损单"});
		reportType.setBounds(110, 35, 100, 25);
		
		report = new JLabel("添加", JLabel.CENTER);
		report.setBounds(210, 10, 60, 25);
		report.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = 0;
				try {
					num = Integer.parseInt(reportNum.getText());
				}
				catch(Exception exc) {
					frame.setTitle("输入有误");
				} 
				if (num > 0 && reportType.getSelectedIndex() != 0) {
					if(reportType.getSelectedIndex() == 2)
						num = -num;
					
					GoodsVO vo;
					if (cc.addReportCommodity(new ReportCommodityVO(
							(vo = gc.getGoodsByInfo(reportName.getText(), reportModel.getText())).serialNumber,
							vo.price,
							num)) == ResultMessage.ResultMessage.add_success) {
						frame.setTitle("库存报单添加成功");
					}
					else {
						frame.setTitle("库存报单添加失败");
					}
					
				}
				reportName.setText("");
				reportModel.setText("");
				reportNum.setText("");
				reportType.setSelectedIndex(0);
			}
			
		});
		
		this.add(reportType);
		this.add(reportNum);
		this.add(reportModel);
		this.add(reportName);
		this.add(report);
		this.setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
//		new ReportFrame();
//		new SendFrame();
	}
}





