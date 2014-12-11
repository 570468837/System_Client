package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

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
	public SendFrame() {
		super("赠送单制定");
		this.setBounds(0, 0, 300, 110);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame = this;
		
		cc = new CommodityController();
		gc = new GoodsController();
		
		sendCustomer = new JTextField("<客户名>");
		sendCustomer.setBounds(10, 10, 100, 25);
		new AddWordsChange(sendCustomer, "<客户名>");
		
		sendName = new JTextField("<商品名>");
		sendName.setBounds(10, 35, 100, 25);
		new AddWordsChange(sendName, "<商品名>");
		
		sendModel = new JTextField("<商品型号>");
		sendModel.setBounds(110, 10, 100, 25);
		new AddWordsChange(sendModel, "<商品型号>");
		
		sendNum = new JTextField("<赠送数量>");
		sendNum.setBounds(110, 35, 100, 25);
		new AddWordsChange(sendNum, "<赠送数量>");
		
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
	
	public ReportFrame() {
		super("库存报单制定");
		this.setBounds(0, 0, 300, 110);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame = this;
		
		cc = new CommodityController();
		gc = new GoodsController();
		
		reportName = new JTextField("<商品名>");
		reportName.setBounds(10, 10, 100, 25);
		new AddWordsChange(reportName, "<商品名>");
		
		reportModel = new JTextField("<商品型号>");
		reportModel.setBounds(10, 35, 100, 25);
		new AddWordsChange(reportModel, "<商品型号>");
		
		reportNum = new JTextField("<商品数量>");
		reportNum.setBounds(110, 10, 100, 25);
		new AddWordsChange(reportNum, "<商品数量>");
		
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
		new ReportFrame();
		//new SendFrame();
	}
}





