package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import VO.*;
import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.GoodsBLService.GoodsController;
/**
 * 库存管理人员界面
 * @author hutao
 * 
 */
@SuppressWarnings("serial")
public class CommodityFrame extends JFrame {
	private JFrame theFrame;
	private JLabel 
	    //backgroundLabel,
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
		this.repaint();
		
		@SuppressWarnings("unused")
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	
	/**
	 * 商品管理的panel
	 */
	class GoodsPanel extends JPanel {
		private GoodsController gc = new GoodsController();
		private ArrayList<GoodsClassVO> gcvList = gc.getGoodsClassVOList();
		private ArrayList<GoodsVO> gvList = gc.getGoodsVOList();
		private ArrayList<JScrollPane> jspList = new ArrayList<JScrollPane>(); //商品类层栈
		private ArrayList<JTable> jtList = new ArrayList<JTable>(); //用来获取jtable的全局引用，和jspList联用
		
		private JLabel backToRoot; //回到根商品分类键
		private JLabel back; //退栈键
		private JFrame 
		    popFrame, //弹出选项的frame
		    inputFrame; //
		
		public GoodsPanel(JFrame theFrame) {
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(150, 255, 150, 255));
			this.setLayout(null);
			
			iniSearch();
			
			iniGoodsManager();
			
			theFrame.add(this);
		}
		/**
		 * 初始化搜索的几个组件
		 */
		private void iniSearch() {
			JLabel searchLabel = new JLabel("搜索", JLabel.CENTER);
			JTextField searchField = new JTextField("<请输入商品关键字>");
			searchField.setBounds(310, 20, 200, 25);
			new AddWordsChange(searchField, "<请输入商品关键字>");
			searchLabel.setFont(new Font("default", 1, 18));
			searchLabel.setBounds(530, 19, 60, 25);
			searchLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					//隐藏goodsmanager的组件，显示搜索的表格     
				}
		    });
			this.add(searchField);
			this.add(searchLabel);
		}
		/**
		 * 初始化商品/商品分类管理的表格
		 */
		private void iniGoodsManager() {
			jspList.clear();
			jtList.clear();
			String[] head = {"商品根分类"};
			ArrayList<GoodsClassVO> gcvBufferList = new ArrayList<GoodsClassVO>();
			Iterator<GoodsClassVO> iter = gcvList.iterator();
			GoodsClassVO gcv;
			while(iter.hasNext()) {
				gcv = iter.next();
				if(gcv.fatherGoodsClass == null) {
					gcvBufferList.add(gcv);
				}
			}
			String[][] body = new String[gcvBufferList.size()][1];
			for(int i = 0; i < gcvBufferList.size(); i ++) {
				body[i][0]  = gcvBufferList.get(i).goodsClassName;
			}
			JTable table = new JTable(body, head);
			table.setPreferredSize(new Dimension(120, body.length * 30));
			table.setAutoResizeMode(0);
			table.setRowHeight(30);
			table.setEnabled(false);
			table.setFont(new Font("default", 0, 16));
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setEnabled(false);
			table.getTableHeader().setFont(new Font("default", 1, 17));
			table.getTableHeader().setPreferredSize(new Dimension(0, 45));
			
			addListener(table);
			
			JScrollPane jsp = new JScrollPane(table);
			jsp.setBounds(25, 70, 120 + 15, 360 + 48);
			jsp.setPreferredSize(new Dimension(120, 360));
	    	jsp.setHorizontalScrollBar(null);
	    	jtList.add(table);
	    	jspList.add(jsp);
	    	
	    	
	    	backToRoot = new JLabel("返回根商品分类", JLabel.CENTER);
	    	backToRoot.setBounds(30,500, 100, 50);
	    	backToRoot.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseClicked(MouseEvent e) {
	    			while(jspList.size() != 1) {
	    				iniGoodsManager(); //重新构建，并刷新一遍
	    			}
	    		}
	    	});
	    	back = new JLabel("返回", JLabel.CENTER);
	    	back.setBounds(30, 10, 100, 50);
	    	back.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseClicked(MouseEvent e) {
	    			int size = jspList.size();
	    			if (size != 1) {
	    				jspList.remove(size - 1);
		    			jtList.remove(size - 1);
		    			JScrollPane jp;
		    			for (int i = 0; i < size - 1; i ++) {
		    				jp = jspList.get(i);
		    				jp.setLocation(jp.getX() + 100, jp.getY());
		    			}
		    			jspList.get(size - 2).setVisible(true);
	    			}
	    		}
	    	});
	    	
	    	this.add(backToRoot);
	    	this.add(back);
	    	this.add(jsp);
		}
		/**
		 * 添加点击则自动产生子分类或子商品分类的响应，内含递归
		 * @param table 要添加响应的表格
		 */
		private void addListener(JTable table) {
			//是商品分类表格
			if(table.getColumnCount() == 1) {
				table.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel add;
						if(jspList.size() == 1) {
							add = new JLabel("增加根分类", JLabel.CENTER);
						}
						else add = new JLabel("增加子分类", JLabel.CENTER);
						add.setBounds(0, 0, 120, 25);
						add.addMouseListener(new MouseAdapter() {
							JTextField input;
							JLabel submit, notice, cancel;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 70);
								notice = new JLabel("请输入要添加的商品分类名", JLabel.CENTER);
								notice.setBounds(10, 0, 200, 30);
								input = new JTextField();
								input.setBounds(10, 30, 200, 30);
								cancel = new JLabel("取消", JLabel.CENTER);
								cancel.setBounds(210, 0, 50, 30);
								cancel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
								});
								submit = new JLabel("添加", JLabel.CENTER);
								submit.setBounds(210, 30, 50, 30);
								submit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										
									}
								});
								
								inputFrame.add(cancel);
								inputFrame.add(notice);
								inputFrame.add(input);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
						JLabel upd = new JLabel("更改", JLabel.CENTER);
						upd.setBounds(0, 25, 120, 25);
						if(jspList.size() == 1) {
							upd.setEnabled(false);
						}
						else {
							upd.addMouseListener(new MouseAdapter() {
								JTextField input;
								JLabel submit, notice, cancel;
								public void mouseClicked(MouseEvent e) {
									if(inputFrame != null) inputFrame.setVisible(false);
									popFrame.dispose();
									inputFrame = new JFrame();
									inputFrame.setUndecorated(true);
									inputFrame.setLayout(null);
									inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 70);
									notice = new JLabel("请输入更改后的商品分类名", JLabel.CENTER);
									notice.setBounds(10, 0, 200, 30);
									input = new JTextField();
									input.setBounds(10, 30, 200, 30);
									cancel = new JLabel("取消", JLabel.CENTER);
									cancel.setBounds(210, 0, 50, 30);
									cancel.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
									});
									submit = new JLabel("更改", JLabel.CENTER);
									submit.setBounds(210, 30, 50, 30);
									submit.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {
											
										}
									});
									
									inputFrame.add(cancel);
									inputFrame.add(notice);
									inputFrame.add(input);
									inputFrame.add(submit);
									inputFrame.setVisible(true);
								}
							});
						}
						
						popFrame.add(add);
						popFrame.add(upd);
						popFrame.setVisible(true);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(popFrame != null) popFrame.dispose();
					}
				});
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel ext = new JLabel("展开", JLabel.CENTER);
						ext.setBounds(0, 0, 120, 25);
						ext.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {}
						});
						JLabel del = new JLabel("删除", JLabel.CENTER);
						del.setBounds(0, 25, 120, 25);
						del.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {}
						});
						popFrame.add(ext);
						popFrame.add(del);
						
						popFrame.setVisible(true);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(popFrame != null) popFrame.dispose();
					}
				});
			}
			//是商品表格
			else {
				table.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
					}
				});
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						
					}
				});
			}
			
			
		}
		
	}
	/**
	 * 库存管理的panel
	 */
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
			new AddWordsChange(time1, "<例如2014/10/10>");
			time2 = new JTextField("<例如2014/10/11>");
			time2.setBounds(580, 65, 110, 25);
			new AddWordsChange(time2, "<例如2014/10/11>");
			
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
				int //x,
				y;
				@Override
				public void mouseClicked(MouseEvent e) {
					//x = comInvenTable.columnAtPoint(e.getPoint());
					y = comInvenTable.rowAtPoint(e.getPoint());
					//System.out.println(x + "," + y);
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
			citjsp.setPreferredSize(new Dimension(825, 400));
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
				new AddWordsChange(reportName, "<商品名>");
				reportComponent[0] = reportName;
				
				JTextField reportNum = new JTextField("<商品数量>");
				reportNum.setBounds(200, 150, 100, 25);
				new AddWordsChange(reportNum, "<商品数量>");
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
				new AddWordsChange(sendCustomer, "<客户名>");
				sendCustomer.setBounds(50, 150, 100, 25);
				sendComponent[0] = sendCustomer;
				
				
				JTextField sendGood = new JTextField("<商品名>");
				new AddWordsChange(sendGood, "<商品名>");
				sendGood.setBounds(200, 150, 100, 25);
				sendComponent[1] = sendGood;
				
				JTextField sendNum = new JTextField("<赠送数量>");
				new AddWordsChange(sendNum, "<赠送数量>");
				sendNum.setBounds(350, 150, 100, 25);
				sendComponent[2] = sendNum;
				
				JTextField sendPrice = new JTextField("<商品单价>");
				new AddWordsChange(sendPrice, "<商品单价>");
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
