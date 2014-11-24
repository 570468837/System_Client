package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import VO.*;
import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.GoodsBLService.GoodsController;
/**
 * 
 * @author hutao
 *
 */
@SuppressWarnings("serial")
public class CommodityFrame extends JFrame {
	private JFrame theFrame;
	private JLabel 
	    backgroundLabel,
	    exitButton,
	    commodityLabel,
	    goodsLabel,
	    alarmLabel;
	private GoodsPanel goodsPanel;
	
	private CommodityPanel commodityPanel;
	private JComponent[] 
			reportComponent,
			sendComponent;
	
	
	public CommodityFrame() {
		super();
		
		theFrame = this;
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		this.setLayout(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*background label*/
		backgroundLabel = new JLabel();
		
		
		
		exitButton = new JLabel("X", JLabel.CENTER);
		exitButton.setBounds(950, 0, 50, 50);
		exitButton.setFont(new Font("default", 1, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
	    });
		
		goodsPanel = new GoodsPanel(this);
		goodsPanel.setVisible(true);
		commodityPanel = new CommodityPanel(this);
		commodityPanel.setVisible(false);
		
		

		goodsLabel = new JLabel("Goods", JLabel.CENTER);
		goodsLabel.setBounds(40, 100, 100, 50);
		goodsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goodsPanel.setVisible(true);
				commodityPanel.setVisible(false);
			}
		});
		
		
		
		commodityLabel = new JLabel("Commodity", JLabel.CENTER);
		
		commodityLabel.setBounds(40, 160, 100, 50);
		commodityLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				commodityPanel.setVisible(true);
				goodsPanel.setVisible(false);
			}
		});
		
		
		alarmLabel = new JLabel("alarming", JLabel.CENTER);
		alarmLabel.setBounds(40, 470, 100, 50);
		alarmLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				AlarmFrame a = new AlarmFrame(theFrame);
				theFrame.setEnabled(false);
			}
		});
		
		
		
		this.add(goodsLabel);
		this.add(commodityLabel);
		this.add(alarmLabel);
		this.add(exitButton);
		
		@SuppressWarnings("unused")
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	
	
	
	class GoodsPanel extends JPanel {
		private GoodsController gc = new GoodsController();
		private ArrayList<JScrollPane> jspList = new ArrayList<JScrollPane>();
		private JScrollPane jsp;
		private JTable goodsTable;
		private JLabel searchLabel = new JLabel("搜索", JLabel.CENTER);
		private JTextField searchField = new JTextField("<请输入商品关键字>");
		
		public GoodsPanel(JFrame theFrame) {
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(150, 255, 150, 255));
			this.setLayout(null);
			
			/*
			 * 搜索区
			 */
			searchField.setBounds(310, 20, 200, 25);
			AddWordsChange.change(searchField, "<请输入商品关键字>");
			searchLabel.setFont(new Font("default", 1, 18));
			searchLabel.setBounds(530, 19, 60, 25);
			searchLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					
				}
		    });
			/*
			 * 操作区
			 */
			ArrayList<GoodsClassVO> gcvList = gc.getGoodsClassVOList();
			ArrayList<GoodsVO> gvList = gc.getGoodsVOList();
			
			
			
			
			
			
			
			this.add(searchField);
			this.add(searchLabel);
			theFrame.add(this);
		}
		
		
		
	}
	
	class CommodityPanel extends JPanel {
		private JScrollPane cctjsp, citjsp;
		private JTable comCheckTable, comInvenTable;
		private CommodityController cc = new CommodityController();
		private JLabel
		    comCheckLabel,
		    comInvenLabel,
		    sendLabel,
		    reportLabel,
		    exportLabel;
		private JTextField
		    time1,
		    time2;
		    
		
		public CommodityPanel(JFrame theFrame) {
			super();
			
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(170, 255, 170, 255));
			this.setLayout(null);
			
			
			comInvenLabel = new JLabel("库存盘点", JLabel.CENTER);
			comInvenLabel.setBounds(0, 50, 100, 50);
			comInvenLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					comInven();
				}
			});
			
			sendLabel = new JLabel("制定赠送单", JLabel.CENTER);
			sendLabel.setBounds(100, 50, 100, 50);
			sendLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					send();
				}
			});
			
			reportLabel = new JLabel("制定报单", JLabel.CENTER);
			reportLabel.setBounds(200, 50, 100, 50);
			reportLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					report();
				}
			});
			
			time1 = new JTextField("<例如2014/10/10>");
			time1.setBounds(450, 65, 110, 25);
			AddWordsChange.change(time1, "<例如2014/10/10>");
			time2 = new JTextField("<例如2014/10/11>");
			time2.setBounds(580, 65, 110, 25);
			AddWordsChange.change(time2, "<例如2014/10/11>");
			
			comCheckLabel = new JLabel("库存查看", JLabel.CENTER);
			comCheckLabel.setBounds(700, 50, 100, 50);
			comCheckLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					comCheck();
				}
			});
			
			
			/*
			 * 初始化显示库存盘点界面
			 */
			String[] citHead = {"", "名称", "型号", "库存数量", "库存均价", "批次", "批号", "出厂日期"};
			String[][] citInfo = cc.inventoryCommodity().icInfo;
			comInvenTable = new JTable(citInfo, citHead);
			comInvenTable.setAutoResizeMode(0);
			comInvenTable.setRowHeight(25);
			comInvenTable.setPreferredSize(new Dimension(825, citInfo.length * 25));
			comInvenTable.setEnabled(false);
			comInvenTable.setFont(new Font("default", 0, 16));
			comInvenTable.getTableHeader().setReorderingAllowed(false);
			comInvenTable.getTableHeader().setEnabled(false);
			comInvenTable.getTableHeader().setFont(new Font("default", 1, 17));
			comInvenTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
			comInvenTable.getColumnModel().getColumn(0).setPreferredWidth(30);
			
			comInvenTable.addMouseListener(new MouseAdapter() {
				JFrame popFrame;
				JLabel quickSendLabel;
				JLabel quickReportLabel;
				int x, y;
				@Override
				public void mouseClicked(MouseEvent e) {
					x = comInvenTable.columnAtPoint(e.getPoint());
					y = comInvenTable.rowAtPoint(e.getPoint());
					System.out.println(x + "," + y);
					popFrame = new JFrame();
					popFrame.setUndecorated(true);
					popFrame.setLayout(null);
					popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
					quickSendLabel = new JLabel("赠送该商品？", JLabel.CENTER);
					quickSendLabel.setBounds(0, 0, 100, 25);
					quickSendLabel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							popFrame.dispose();
							send();
							JTextField jtf = (JTextField) sendComponent[1];
							jtf.setText((String) comInvenTable.getValueAt(y, 1));
							jtf = (JTextField) sendComponent[3];
							jtf.setText((String) comInvenTable.getValueAt(y, 4));
						}
					});
					quickReportLabel = new JLabel("报损/溢该商品？", JLabel.CENTER);
					quickReportLabel.setBounds(0, 25, 100, 25);
					quickReportLabel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							popFrame.dispose();
							report();
							JTextField jtf = (JTextField) reportComponent[0];
							jtf.setText((String) comInvenTable.getValueAt(y, 1));
						}
					});
					popFrame.add(quickReportLabel);
					popFrame.add(quickSendLabel);
					popFrame.setVisible(true);
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					if (popFrame != null) {
						popFrame.dispose();
					}
				}
			});
			
			
			citjsp = new JScrollPane(comInvenTable);
			citjsp.setHorizontalScrollBar(null);
			citjsp.setBounds(5, 100, 825, 400);
			/*
			 * 导出excel键
			 */
			exportLabel = new JLabel("export");
			exportLabel.setBounds(700, 500, 100, 50);
			exportLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {}
			});
			
			this.add(time1);
			this.add(time2);
			this.add(comCheckLabel);
			this.add(comInvenLabel);
			this.add(sendLabel);
			this.add(reportLabel);
			this.add(citjsp);
			this.add(exportLabel);
			theFrame.add(this);
			
			
		}
		/**
		 * 按下库存盘点时调用的方法
		 */
		private void comInven() {
			citjsp.setVisible(true);
			exportLabel.setVisible(true);
			if(cctjsp != null) {
				cctjsp.setVisible(false);
			}
			if(reportComponent != null) {
            	for(int i = 0; i < reportComponent.length; i ++) {
            		reportComponent[i].setVisible(false);
            	}
			}
			if(sendComponent != null) {
				for(int i = 0; i < sendComponent.length; i ++) {
            		sendComponent[i].setVisible(false);
            	}
			}
		}
		/**
		 * 按下库存查看时调用的方法
		 */
		private boolean comCheck() {
			String[][] cctInfo = cc.checkCommodity(time1.getText(), time2.getText()).Info;
			if(cctInfo == null) {
				return false;
			}
			
			citjsp.setVisible(false);
			exportLabel.setVisible(false);
            if(reportComponent != null) {
            	for(int i = 0; i < reportComponent.length; i ++) {
            		reportComponent[i].setVisible(false);
            	}
			}
            if(sendComponent != null) {
				for(int i = 0; i < sendComponent.length; i ++) {
            		sendComponent[i].setVisible(false);
            	}
			}
			
			String[] cctHead = {"商品", "数量", "单价", "出入类型", "出入合计"};
			comCheckTable = new JTable(cctInfo, cctHead);
			comCheckTable.setAutoResizeMode(0);
			comCheckTable.setRowHeight(25);
			comCheckTable.setPreferredSize(new Dimension(825, cctInfo.length * 25));
			comCheckTable.setEnabled(false);
			comCheckTable.setFont(new Font("default", 0, 16));
			comCheckTable.getTableHeader().setReorderingAllowed(false);
			comCheckTable.getTableHeader().setEnabled(false);
			comCheckTable.getTableHeader().setFont(new Font("default", 1, 17));
			comCheckTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
			cctjsp = new JScrollPane(comCheckTable);
			cctjsp.setHorizontalScrollBar(null);
			cctjsp.setBounds(5, 100, 825, 440);
			this.add(cctjsp);
			return true;
		}
		/**
		 * 按下制定库存报单时调用的方法
		 */
		private void report() {
			citjsp.setVisible(false);
			exportLabel.setVisible(false);
			if(cctjsp != null) {
				cctjsp.setVisible(false);
			}
			if(sendComponent != null) {
				for(int i = 0; i < sendComponent.length; i ++) {
            		sendComponent[i].setVisible(false);
            	}
			}
			
			
			if(reportComponent == null) {
				reportComponent = new JComponent[4];
				
				JTextField reportName = new JTextField("<商品名>");
				reportName.setBounds(50, 150, 100, 25);
				AddWordsChange.change(reportName, "<商品名>");
				reportComponent[0] = reportName;
				
				JTextField reportNum = new JTextField("<商品数量>");
				reportNum.setBounds(200, 150, 100, 25);
				AddWordsChange.change(reportNum, "<商品数量>");
				reportComponent[1] = reportNum;
				
				JComboBox<String> reportType = new JComboBox<String>(
						new String[] {"<单据类型>", "报溢单", "报损单"});
				reportType.setBounds(350, 150, 100, 25);
				reportComponent[2] = reportType;
				
				
				JLabel report = new JLabel("添加", JLabel.CENTER);
				report.setBounds(500, 150, 50, 25);
				report.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//添加报单
					}
				});
				reportComponent[3] = report;
				
				this.add(reportComponent[0]);
				this.add(reportComponent[1]);
				this.add(reportComponent[2]);
				this.add(reportComponent[3]);
				this.repaint();
			}
			else {
				for(int i = 0; i < reportComponent.length; i ++) {
            		reportComponent[i].setVisible(true);
            	}
			}
			
		}
		/**
		 * 按下制定库存赠送单时调用的方法，在库存盘点时也可以通过点击表格选择制定库存赠送单调用此方法
		 */
		private void send() {
			citjsp.setVisible(false);
			exportLabel.setVisible(false);
			if(cctjsp != null) {
				cctjsp.setVisible(false);
			}
			if(reportComponent != null) {
            	for(int i = 0; i < reportComponent.length; i ++) {
            		reportComponent[i].setVisible(false);
            	}
			}
			
			if(sendComponent == null) {
				sendComponent = new JComponent[5];
				
				JTextField sendCustomer = new JTextField("<客户名>");
				AddWordsChange.change(sendCustomer, "<客户名>");
				sendCustomer.setBounds(50, 150, 100, 25);
				sendComponent[0] = sendCustomer;
				
				
				JTextField sendGood = new JTextField("<商品名>");
				AddWordsChange.change(sendGood, "<商品名>");
				sendGood.setBounds(200, 150, 100, 25);
				sendComponent[1] = sendGood;
				
				JTextField sendNum = new JTextField("<赠送数量>");
				AddWordsChange.change(sendNum, "<赠送数量>");
				sendNum.setBounds(350, 150, 100, 25);
				sendComponent[2] = sendNum;
				
				JTextField sendPrice = new JTextField("<商品单价>");
				AddWordsChange.change(sendPrice, "<商品单价>");
				sendPrice.setBounds(500, 150, 100, 25);
				sendComponent[3] = sendPrice;
				
				
				JLabel send = new JLabel("赠送", JLabel.CENTER);
				send.setBounds(650, 150, 50, 25);
				send.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
				sendComponent[4] = send;
				
				this.add(sendComponent[0]);
				this.add(sendComponent[1]);
				this.add(sendComponent[2]);
				this.add(sendComponent[3]);
				this.add(sendComponent[4]);
				this.repaint();
				
			}
			else {
				for(int i = 0; i < sendComponent.length; i ++) {
            		sendComponent[i].setVisible(true);
            	}
			}
			
		}
		
	}
	/**
	 * 报警单区域，包括报警单和总经理的回执
	 */
	class AlarmFrame extends JFrame {
		JLabel exitLabel;
		JFrame alarmFrame, comFrame;
		public AlarmFrame(JFrame theFrame) {
			alarmFrame = this;
			comFrame = theFrame;
			this.setSize(400, 300);
			this.setLocationRelativeTo(theFrame);
			this.setLayout(null);
			this.setUndecorated(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			exitLabel = new JLabel("X", JLabel.CENTER);
			exitLabel.setBounds(350, 0, 50, 50);
			exitLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					comFrame.setVisible(true);
					alarmFrame.dispose();
					comFrame.setEnabled(true);
					
				}
			});
			
			
			this.add(exitLabel);
			
			@SuppressWarnings("unused")
			MoveOfFrame m = new MoveOfFrame(this);
			this.setVisible(true);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CommodityFrame c = new CommodityFrame();
	}
	

}
