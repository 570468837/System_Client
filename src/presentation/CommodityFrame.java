package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import Config.UserSort;
import ResultMessage.ResultMessage;
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
	private GoodsController gc = new GoodsController();
	private CommodityController cc = new CommodityController();
	
	public CommodityFrame(UserVO uservo) {
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
				commodityPanel.setVisible(false);
				commodityPanel = new CommodityPanel(theFrame);
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
		private JPanel goodsPanel = this;
		private ArrayList<GoodsClassVO> gcvList = gc.getGoodsClassVOList();
		private ArrayList<GoodsVO> gvList = gc.getGoodsVOList();
		private ArrayList<JScrollPane> jspList = new ArrayList<JScrollPane>(); //商品类层栈
		private ArrayList<JTable> jtList = new ArrayList<JTable>(); //用来获取jtable的全局引用，和jspList联用
		
		
		private JLabel backToRoot; //回到根商品分类键
		private JLabel back; //退栈键
		private JFrame 
		    popFrame, //弹出选项的frame
		    inputFrame; //
		private JLabel infoBoard;
		
		public GoodsPanel(JFrame theFrame) {
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(150, 255, 150, 255));
			this.setLayout(null);
			
			iniSearch();
			
			iniGoodsManager();
			
			infoBoard = new JLabel();
			infoBoard.setBounds(200, 500, 500, 50);
			infoBoard.setVisible(false);
			this.add(infoBoard);
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
					jspList.get(jspList.size() - 1).setVisible(false);
					String[] head = {"编号", "名称", "型号", "库存数量", "进价", "零售价", "最近进价", "最近零售价"};
					ArrayList<GoodsVO> searchResult = gc.searchGoods(searchField.getText());
					String[][] body = new String[searchResult.size()][8];
					Iterator<GoodsVO> iter = searchResult.iterator();
					GoodsVO gv;
					int i = 0;
					while(iter.hasNext()) {
						gv = iter.next();
						body[i][0] = gv.serialNumber;
						body[i][1] = gv.name;
						body[i][2] = gv.model;
						body[i][3] = Integer.toString(gv.commodityQuantity);
						body[i][4] = Double.toString(gv.price);
						body[i][5] = Double.toString(gv.salePrice);
						body[i][6] = Double.toString(gv.latestPrice);
						body[i][7] = Double.toString(gv.latestSalePrice);
						i ++;
					}
					JTable searchTable = new JTable(body, head);
					searchTable.setAutoResizeMode(0);
					searchTable.setRowHeight(25);
					searchTable.setPreferredSize(new Dimension(825, body.length * 25));
					searchTable.setEnabled(false);
					searchTable.setFont(new Font("default", 0, 16));
					searchTable.getTableHeader().setReorderingAllowed(false);
					searchTable.getTableHeader().setEnabled(false);
					searchTable.getTableHeader().setFont(new Font("default", 1, 17));
					searchTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
					searchTable.getColumnModel().getColumn(0).setPreferredWidth(30);
					JScrollPane jsp = new JScrollPane(searchTable);
					jsp.setHorizontalScrollBar(null);
					jsp.setBounds(5, 100, 825, 400);
					jsp.setPreferredSize(new Dimension(825, 400));
					
					jspList.add(jsp);
					jtList.add(searchTable);
					goodsPanel.add(jsp);
				}
		    });
			this.add(searchField);
			this.add(searchLabel);
		}
		/**
		 * 初始化商品/商品分类管理的表格
		 */
		private void iniGoodsManager() {
			while(jspList.size() != 0) jspList.remove(0).setVisible(false);
			jtList.clear();
			
			String[] head = {"商品根分类"};
			ArrayList<GoodsClassVO> gcvBufferList = new ArrayList<GoodsClassVO>();
			Iterator<GoodsClassVO> iter = gcvList.iterator();
			GoodsClassVO gcv;
			while(iter.hasNext()) {
				gcv = iter.next();
				if(gcv.fatherGoodsClassNum == 0) {
					gcvBufferList.add(gcv);
				}
			}
			String[][] body = new String[gcvBufferList.size()][1];
			for(int i = 0; i < gcvBufferList.size(); i ++) {
				body[i][0]  = gcvBufferList.get(i).goodsClassName;
			}
			JTable table = new JTable(body, head);
			table.setPreferredSize(new Dimension(180, body.length * 30));
			table.setAutoResizeMode(0);
			table.setRowHeight(30);
			table.setEnabled(false);
			table.setFont(new Font("default", 0, 16));
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setEnabled(false);
			table.getTableHeader().setFont(new Font("default", 1, 17));
			table.getTableHeader().setPreferredSize(new Dimension(0, 45));
			
			JScrollPane jsp = new JScrollPane(table);
			jsp.setBounds(25, 70, 180, 360 + 48);
			jsp.setPreferredSize(new Dimension(180, 360));
	    	jsp.setHorizontalScrollBar(null);
	    	jtList.add(table);
	    	jspList.add(jsp);
	    	addListener(table);
	    	
	    	backToRoot = new JLabel("返回根商品分类/刷新", JLabel.CENTER);
	    	backToRoot.setBounds(30, 500, 150, 50);
	    	backToRoot.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseClicked(MouseEvent e) {
	    			iniGoodsManager(); //重新构建
    				infoBoard.setVisible(false);
	    		}
	    	});
	    	back = new JLabel("返回", JLabel.CENTER);
	    	back.setBounds(30, 10, 100, 50);
	    	back.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseClicked(MouseEvent e) {
	    			infoBoard.setVisible(false);
	    			int size = jspList.size();
	    			if (size != 1) {
	    				jspList.remove(size - 1).setVisible(false);
		    			jtList.remove(size - 1);
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
			if(table.getColumnCount() == 1 && (table.getRowCount() != 0 || (table.getRowCount() == 0 && jspList.size() == 1))) {
				System.out.println("是商品分类表格");
				table.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel add;
						if(jspList.size() == 1) {
							add = new JLabel("添加根分类", JLabel.CENTER);
						}
						else add = new JLabel("添加子分类", JLabel.CENTER);
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
										GoodsClassVO classToBeAdded;
										if(jspList.size() == 1) {
											classToBeAdded = new GoodsClassVO(input.getText());
										}
										else {
											classToBeAdded = new GoodsClassVO(
													gc.getGoodsClassByInfo(jtList.get(jtList.size() - 1).getColumnName(0)),
													input.getText());
										}
										if(gc.addGoodsClass(classToBeAdded) == ResultMessage.add_success) {
											infoBoard.setText("添加成功");
											iniGoodsManager();
										}
										else 
											infoBoard.setText("添加失败");
										inputFrame.dispose();
										infoBoard.setVisible(true);
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
											inputFrame.dispose();
											GoodsClassVO toBeUpd = gc.getGoodsClassByInfo(jtList.get(jtList.size() - 1).getColumnName(0));
											
											GoodsClassVO upded = new GoodsClassVO();
											upded.fatherGoodsClassNum = toBeUpd.fatherGoodsClassNum;
											upded.Num = toBeUpd.Num;
											upded.goodsClassName = input.getText();
											if(gc.updGoodsClass(upded) == ResultMessage.update_success) {
												infoBoard.setText("更改成功");
												iniGoodsManager();
											}
											else 
												infoBoard.setText("更改失败");
											infoBoard.setVisible(true);
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
					String className;
					JLabel ext, del;
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						
						className = (String)table.getValueAt(table.rowAtPoint(e.getPoint()), table.columnAtPoint(e.getPoint()));
						
						ext = new JLabel("展开", JLabel.CENTER);
						ext.setBounds(0, 0, 120, 25);
						ext.addMouseListener(new MouseAdapter() {
							ArrayList<GoodsClassVO> classBufferList;
							ArrayList<GoodsVO> bufferList;
							JTable extTable;
							JScrollPane extJsp;
							public void mouseClicked(MouseEvent e) {
								popFrame.dispose();
								jspList.get(jspList.size() - 1).setVisible(false);
								GoodsClassVO father = gc.getGoodsClassByInfo(className);
								classBufferList = new ArrayList<GoodsClassVO>();
								Iterator<GoodsClassVO> classIter = gcvList.iterator();
								GoodsClassVO gcv;
								while(classIter.hasNext()) {
									gcv = classIter.next();
									if(father.Num == gcv.fatherGoodsClassNum) 
										classBufferList.add(gcv);
								}
								if(classBufferList.size() != 0) {
									//子目录下还是分类
									System.out.println("子目录下还是分类");
									String[] head = {className};
									String[][] body = new String[classBufferList.size()][1];
									for(int i = 0; i < classBufferList.size(); i ++) {
										body[i][0] = classBufferList.get(i).goodsClassName;
									}
									extTable = new JTable(body, head);
									extTable.setPreferredSize(new Dimension(180, body.length * 30));
									extTable.setAutoResizeMode(0);
									extTable.setRowHeight(30);
									extTable.setEnabled(false);
									extTable.setFont(new Font("default", 0, 16));
									extTable.getTableHeader().setReorderingAllowed(false);
									extTable.getTableHeader().setEnabled(false);
									extTable.getTableHeader().setFont(new Font("default", 1, 17));
									extTable.getTableHeader().setPreferredSize(new Dimension(0, 45));
									extJsp = new JScrollPane(extTable);
									extJsp.setBounds(25, 70, 180, 360 + 48);
									extJsp.setPreferredSize(new Dimension(180, 360));
									extJsp.setHorizontalScrollBar(null);
							    	jtList.add(extTable);
							    	jspList.add(extJsp);
							    	goodsPanel.add(extJsp);
							    	addListener(extTable);
								}
								else {
									bufferList = new ArrayList<GoodsVO>();
									Iterator<GoodsVO> iter = gvList.iterator();
									GoodsVO gv;
									long classNum = gc.getGoodsClassByInfo(className).Num;
									while(iter.hasNext()) {
										gv = iter.next();
										if(gv.goodsClassNum == classNum) 
											bufferList.add(gv);
									}
									if(bufferList.size() != 0) {
										//子目录下是商品
										System.out.println("子目录下是商品");
										String[] head = {"编号", className, "型号", "库存数量", "进价", "零售价", "最近进价", "最近售价"};
										String[][] body = new String[bufferList.size()][8];
										Iterator<GoodsVO> bufferIter = bufferList.iterator();
										GoodsVO g;
										int i = 0;
										while(bufferIter.hasNext()) {
											g = bufferIter.next();
											body[i][0] = g.serialNumber;
											body[i][1] = g.name;
											body[i][2] = g.model;
											body[i][3] = Integer.toString(g.commodityQuantity);
											body[i][4] = Double.toString(g.price);
											body[i][5] = Double.toString(g.salePrice);
											body[i][6] = Double.toString(g.latestPrice);
											body[i][7] = Double.toString(g.latestSalePrice);
											i ++;
										}
										extTable = new JTable(body, head);
										extTable.setPreferredSize(new Dimension(780, body.length * 30));
										extTable.setAutoResizeMode(0);
										extTable.setRowHeight(30);
										extTable.setEnabled(false);
										extTable.setFont(new Font("default", 0, 11));
										extTable.getTableHeader().setReorderingAllowed(false);
										extTable.getTableHeader().setEnabled(false);
										extTable.getTableHeader().setFont(new Font("default", 1, 15));
										extTable.getTableHeader().setPreferredSize(new Dimension(0, 45));
										extJsp = new JScrollPane(extTable);
										extJsp.setBounds(25, 70, 780, 360 + 48);
										extJsp.setPreferredSize(new Dimension(780, 360));
										extJsp.setHorizontalScrollBar(null);
								    	jtList.add(extTable);
								    	jspList.add(extJsp);
								    	goodsPanel.add(extJsp);
										addListener(extTable);
									}
									else {
										//子目录下为空
										System.out.println("子目录下为空");
										String[] head = {className};
										String[][] body = new String[0][0];
										extTable = new JTable(body, head);
										extTable.setPreferredSize(new Dimension(180, 0));
										extTable.setAutoResizeMode(0);
										extTable.getTableHeader().setReorderingAllowed(false);
										extTable.getTableHeader().setEnabled(false);
										extTable.getTableHeader().setFont(new Font("default", 1, 17));
										extTable.getTableHeader().setPreferredSize(new Dimension(0, 45));
										extJsp = new JScrollPane(extTable);
										extJsp.setBounds(25, 70, 180, 360 + 48);
										extJsp.setPreferredSize(new Dimension(180, 360));
										extJsp.setHorizontalScrollBar(null);
								    	jtList.add(extTable);
								    	jspList.add(extJsp);
										goodsPanel.add(extJsp);
										addListener(extTable);
									}
								}
								
								
								
							}
						});
						del = new JLabel("删除", JLabel.CENTER);
						del.setBounds(0, 25, 120, 25);
						
						del.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								popFrame.dispose();
								if(gc.delGoodsClass(gc.getGoodsClassByInfo(className).Num) == ResultMessage.delete_success) {
									infoBoard.setText("删除成功");
									iniGoodsManager();
								}
								else 
									infoBoard.setText("删除失败");
								infoBoard.setVisible(true);
							}
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
			//是空的商品分类表格
			else if(table.getColumnCount() == 1 && table.getRowCount() == 0 && jspList.size() != 1) {
				System.out.println("是空的商品分类表格");
				table.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 75);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel addClass = new JLabel("添加商品分类");
						addClass.setBounds(0, 0, 120, 25);
						addClass.addMouseListener(new MouseAdapter() {
							JTextField input;
							JLabel notice, submit, cancel;
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
										GoodsClassVO classToBeAdded;
										classToBeAdded = new GoodsClassVO(
												gc.getGoodsClassByInfo(jtList.get(jtList.size() - 1).getColumnName(0)),
												input.getText());
										//System.out.println(classToBeAdded.fatherGoodsClassNum);
										if(gc.addGoodsClass(classToBeAdded) == ResultMessage.add_success) {
											infoBoard.setText("添加成功");
											iniGoodsManager();
										}
										else 
											infoBoard.setText("添加失败");
										inputFrame.dispose();
										infoBoard.setVisible(true);
									}
								});
								
								inputFrame.add(cancel);
								inputFrame.add(notice);
								inputFrame.add(input);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
								
							}
						});
						
						JLabel add = new JLabel("添加商品");
						add.setBounds(0, 25, 120, 25);
						add.addMouseListener(new MouseAdapter() {
							JTextField j1, j2, j3, j4;
							JLabel submit, cancel;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 60);
								j1 = new JTextField("<商品名>");
								j1.setBounds(0, 0, 100, 30);
								new AddWordsChange(j1, "<商品名>");
								j2 = new JTextField("<型号>");
								j2.setBounds(100, 0, 100, 30);
								new AddWordsChange(j2, "<型号>");
								j3 = new JTextField("<默认进价>");
								j3.setBounds(0, 30, 100, 30);
								new AddWordsChange(j3, "<默认进价>");
								j4 = new JTextField("<默认零售价>");
								j4.setBounds(100, 30, 100, 30);
								new AddWordsChange(j4, "<默认零售价>");
								cancel = new JLabel("取消");
								cancel.setBounds(200, 0, 100, 30);
								cancel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
								});
								submit = new JLabel("添加");
								submit.setBounds(200, 30, 100, 30);
								submit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										GoodsVO gv = new GoodsVO();
										boolean noProblem = true;
										gv.name = j1.getText();
										gv.model = j2.getText();
										gv.goodsClassNum = gc.getGoodsClassByInfo(table.getColumnName(0)).Num;
										try {
										    gv.price = Double.parseDouble(j3.getText());
										    gv.salePrice = Double.parseDouble(j4.getText());
										} catch(Exception exception) {
											infoBoard.setText("输入有误");
											infoBoard.setVisible(true);
											noProblem = false;
										}
										if(noProblem) {
											if(gc.addGoods(gv) == ResultMessage.add_success) {
												infoBoard.setText("添加成功");
												iniGoodsManager();
											}
											else 
												infoBoard.setText("添加失败");
										}
										infoBoard.setVisible(true);
										inputFrame.dispose();
									}
								});
								inputFrame.add(j1);
								inputFrame.add(j2);
								inputFrame.add(j3);
								inputFrame.add(j4);
								inputFrame.add(cancel);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
						
						JLabel upd = new JLabel("更改商品分类信息");
						upd.setBounds(0, 50, 120, 25);
						upd.addMouseListener(new MouseAdapter() {
							JTextField input;
							JLabel notice, submit, cancel;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 70);
								notice = new JLabel("请输入要更改的商品分类名", JLabel.CENTER);
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
										GoodsClassVO classToBeAdded;
										classToBeAdded = new GoodsClassVO(gc.getGoodsClassByInfo(jtList.get(jtList.size() - 1).getColumnName(0)));
										classToBeAdded.goodsClassName = input.getText();
										if(gc.updGoodsClass(classToBeAdded) == ResultMessage.update_success) {
											infoBoard.setText("更改成功");
											iniGoodsManager();
										}
										else 
											infoBoard.setText("更改失败");
										inputFrame.dispose();
										infoBoard.setVisible(true);
									}
								});
								
								inputFrame.add(cancel);
								inputFrame.add(notice);
								inputFrame.add(input);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
						popFrame.add(addClass);
						popFrame.add(add);
						popFrame.add(upd);
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
				System.out.println("是商品表格");
				table.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel add = new JLabel("添加商品");
						add.setBounds(0, 0, 120, 25);
						add.addMouseListener(new MouseAdapter() {
							JTextField j1, j2, j3, j4;
							JLabel submit, cancel;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 60);
								j1 = new JTextField("<商品名>");
								j1.setBounds(0, 0, 100, 30);
								new AddWordsChange(j1, "<商品名>");
								j2 = new JTextField("<型号>");
								j2.setBounds(100, 0, 100, 30);
								new AddWordsChange(j2, "<型号>");
								j3 = new JTextField("<默认进价>");
								j3.setBounds(0, 30, 100, 30);
								new AddWordsChange(j3, "<默认进价>");
								j4 = new JTextField("<默认零售价>");
								j4.setBounds(100, 30, 100, 30);
								new AddWordsChange(j4, "<默认零售价>");
								cancel = new JLabel("取消");
								cancel.setBounds(200, 0, 100, 30);
								cancel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
								});
								submit = new JLabel("添加");
								submit.setBounds(200, 30, 100, 30);
								submit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										inputFrame.dispose();
										GoodsVO gv = new GoodsVO();
										boolean noProblem = true;
										gv.name = j1.getText();
										gv.model = j2.getText();
										gv.goodsClassNum = gc.getGoodsClassByInfo(table.getColumnName(1)).Num;
										try {
										    gv.price = Double.parseDouble(j3.getText());
										    gv.salePrice = Double.parseDouble(j4.getText());
										} catch(Exception exception) {
											infoBoard.setText("输入有误");
											infoBoard.setVisible(true);
											noProblem = false;
										}
										if(noProblem) {
											if(gc.addGoods(gv) == ResultMessage.add_success) {
												infoBoard.setText("添加成功");
												iniGoodsManager();
											}
											else 
												infoBoard.setText("添加失败");
										}
										infoBoard.setVisible(true);
										
									}
								});
								inputFrame.add(j1);
								inputFrame.add(j2);
								inputFrame.add(j3);
								inputFrame.add(j4);
								inputFrame.add(cancel);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
						JLabel upd = new JLabel("更改商品分类信息");
						upd.setBounds(0, 25, 120, 25);
						upd.addMouseListener(new MouseAdapter() {
							JTextField input;
							JLabel notice, submit, cancel;
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
										GoodsClassVO toBeUpd = gc.getGoodsClassByInfo(jtList.get(jtList.size() - 1).getColumnName(1));
										GoodsClassVO upded = new GoodsClassVO();
										upded.fatherGoodsClassNum = toBeUpd.fatherGoodsClassNum;
										upded.Num = toBeUpd.Num;
										upded.goodsClassName = input.getText();
										if(gc.updGoodsClass(upded) == ResultMessage.update_success) {
											infoBoard.setText("更改成功");
											iniGoodsManager();
										}
										else 
											infoBoard.setText("更改失败");
										infoBoard.setVisible(true);
										inputFrame.dispose();
									}
								});
								
								inputFrame.add(cancel);
								inputFrame.add(notice);
								inputFrame.add(input);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
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
					int eventY;
					JTable t;
					@Override
					public void mouseClicked(MouseEvent e) {
						eventY = jtList.get(jtList.size() - 1).rowAtPoint(e.getPoint());
						t = jtList.get(jtList.size() - 1);
						popFrame = new JFrame();
						popFrame.setBounds(e.getXOnScreen() - 5, e.getYOnScreen() - 5, 120, 50);
						popFrame.setUndecorated(true);
						popFrame.setLayout(null);
						JLabel del = new JLabel("删除该商品");
						del.setBounds(0, 0, 120, 25);
						del.addMouseListener(new MouseAdapter() {
							JLabel submit, cancel, notice;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 200, 50);
								notice = new JLabel("确认删除", JLabel.CENTER);
								notice.setBounds(0, 0, 200, 25);
								submit = new JLabel("是", JLabel.CENTER);
								submit.setBounds(0, 25, 100, 25);
								submit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										if(gc.delGoods(Long.parseLong((String)t.getValueAt(eventY, 0))) == ResultMessage.delete_success) {
											infoBoard.setText("删除成功");
											iniGoodsManager();
										}
										else {
											infoBoard.setText("删除失败");
										}
										infoBoard.setVisible(true);
										inputFrame.dispose();
									}
								});
								cancel = new JLabel("否", JLabel.CENTER);
								cancel.setBounds(100, 25, 100, 25);
								cancel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
								});
								inputFrame.add(notice);
								inputFrame.add(cancel);
								inputFrame.add(submit);
								inputFrame.setVisible(true);
							}
						});
						JLabel upd = new JLabel("更改该商品");
						upd.setBounds(0, 25, 120, 25);
						upd.addMouseListener(new MouseAdapter() {
							JTextField j1, j2, j3, j4;
							JLabel cancel, submit;
							public void mouseClicked(MouseEvent e) {
								if(inputFrame != null) inputFrame.setVisible(false);
								popFrame.dispose();
								inputFrame = new JFrame();
								inputFrame.setUndecorated(true);
								inputFrame.setLayout(null);
								inputFrame.setBounds(e.getXOnScreen() - 30, e.getYOnScreen() - 30, 300, 50);
								j1 = new JTextField((String)t.getValueAt(eventY, 1));
								j1.setBounds(0, 0, 100, 25);
								new AddWordsChange(j1, "<商品名>");
								j2 = new JTextField((String)t.getValueAt(eventY, 2));
								j2.setBounds(100, 0, 100, 25);
								new AddWordsChange(j2, "<型号>");
								j3 = new JTextField((String)t.getValueAt(eventY, 4));
								j3.setBounds(0, 25, 100, 25);
								new AddWordsChange(j3, "<进价>");
								j4 = new JTextField((String)t.getValueAt(eventY, 5));
								j4.setBounds(100, 25, 100, 25);
								new AddWordsChange(j4, "<售价>");
								cancel = new JLabel("取消");
								cancel.setBounds(200, 0, 50, 25);
								cancel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {inputFrame.dispose();}
								});
								submit = new JLabel("更改");
								submit.setBounds(200, 25, 50, 25);
								submit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										GoodsVO g = new GoodsVO();
										g.serialNumber = (String)t.getValueAt(eventY, 0);
										g.name = j1.getText();
										g.model = j2.getText();
										try {
											g.price = Double.parseDouble(j3.getText());
											g.salePrice = Double.parseDouble(j4.getText());
										}catch (Exception exc) {
											g.price = -1;
										}
										if(g.price <= 0) {
											infoBoard.setText("填写有误");
										}
										else {
											if(gc.updGoods(g) == ResultMessage.update_success) {
												infoBoard.setText("更改成功");
												iniGoodsManager();
											}
											else
												infoBoard.setText("更改失败");
										}
										infoBoard.setVisible(true);
										inputFrame.dispose();
									}
								});
								inputFrame.add(cancel);
								inputFrame.add(submit);
								inputFrame.add(j1);
								inputFrame.add(j2);
								inputFrame.add(j3);
								inputFrame.add(j4);
								inputFrame.setVisible(true);
								
							}
						});
						popFrame.add(del);
						popFrame.add(upd);
						popFrame.setVisible(true);
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(popFrame != null) popFrame.dispose();
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
		
		private JLabel
		    comCheckLabel,
		    comInvenLabel,
		    sendLabel,
		    reportLabel,
		    exportLabel;
		private JTextField
		    time1,
		    time2;
		private JLabel infoBoard;
		
		
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
			comInvenTable.setFont(new Font("default", 0, 12));
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
							jtf = (JTextField) sendComponent[2];
							jtf.setText((String) comInvenTable.getValueAt(y, 2));
							jtf = (JTextField) sendComponent[4];
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
							jtf = (JTextField) reportComponent[1];
							jtf.setText((String) comInvenTable.getValueAt(y, 2));
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
				public void mouseClicked(MouseEvent event) {
					export(comInvenTable);
				}
			});
			
			infoBoard = new JLabel("", JLabel.LEFT);
			infoBoard.setBounds(50, 400, 700, 50);
			
			this.add(time1);
			this.add(time2);
			this.add(comCheckLabel);
			this.add(comInvenLabel);
			this.add(sendLabel);
			this.add(reportLabel);
			this.add(citjsp);
			this.add(exportLabel);
			this.add(infoBoard);
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
			infoBoard.setVisible(false);
		}
		/**
		 * 按下库存查看时调用的方法
		 */
		private boolean comCheck() {
			String[][] cctInfo;
			try {
				cctInfo = cc.checkCommodity(time1.getText(), time2.getText()).info;
			}
			catch (Exception e) {
				cctInfo = null;
			}
			
			
			
			if(cctInfo == null) {
				cctInfo = new String[0][0];
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
            infoBoard.setVisible(false);
			
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
				reportComponent = new JComponent[5];
				
				JTextField reportName = new JTextField("<商品名>");
				reportName.setBounds(50, 150, 100, 25);
				new AddWordsChange(reportName, "<商品名>");
				reportComponent[0] = reportName;
				
				JTextField reportModel = new JTextField("<商品型号>");
				reportModel.setBounds(200, 150, 100, 25);
				new AddWordsChange(reportModel, "<商品型号>");
				reportComponent[1] = reportModel;
				
				JTextField reportNum = new JTextField("<商品数量>");
				reportNum.setBounds(350, 150, 100, 25);
				new AddWordsChange(reportNum, "<商品数量>");
				reportComponent[2] = reportNum;
				
				JComboBox<String> reportType = new JComboBox<String>(
						new String[] {"<单据类型>", "报溢单", "报损单"});
				reportType.setBounds(500, 150, 100, 25);
				reportComponent[3] = reportType;
				
				
				JLabel report = new JLabel("添加", JLabel.CENTER);
				report.setBounds(650, 150, 50, 25);
				report.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//添加报单
						int num = 0;
						switch((String)((JComboBox<String>) reportComponent[3]).getSelectedItem()) {
						case "报溢单": 
							try {
								num = Integer.parseInt(((JTextField)reportComponent[2]).getText());
							}catch(Exception exception){
								num = 0;
							}
							if (num < 0) num = 0;
							break;
						case "报损单":
							try {
								num = - Integer.parseInt(((JTextField)reportComponent[2]).getText());
							}catch(Exception exception){
								num = 0;
							}
							if (num > 0) num = 0;
							break;
						default:
							num = 0;
						}
						if(num == 0) {
							infoBoard.setText("填写有误");
						}
						else {
							GoodsVO vo;
							if(cc.addReportCommodity(
									new ReportCommodityVO(
											(vo = gc.getGoodsByInfo(
													((JTextField)reportComponent[0]).getText(), 
													((JTextField)reportComponent[1]).getText())).serialNumber,
											vo.price,
											num))
									== ResultMessage.add_success)
								infoBoard.setText("添加成功");
							else infoBoard.setText("添加失败");
						}
						infoBoard.setVisible(true);
					}
				});
				reportComponent[4] = report;
				
				this.add(reportComponent[0]);
				this.add(reportComponent[1]);
				this.add(reportComponent[2]);
				this.add(reportComponent[3]);
				this.add(reportComponent[4]);
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
				sendComponent = new JComponent[6];
				
				JTextField sendCustomer = new JTextField("<客户名>");
				new AddWordsChange(sendCustomer, "<客户名>");
				sendCustomer.setBounds(50, 150, 100, 25);
				sendComponent[0] = sendCustomer;
				
				
				JTextField sendName = new JTextField("<商品名>");
				new AddWordsChange(sendName, "<商品名>");
				sendName.setBounds(200, 150, 100, 25);
				sendComponent[1] = sendName;
				
				JTextField sendModel = new JTextField("<商品型号>");
				new AddWordsChange(sendModel, "<商品型号>");
				sendModel.setBounds(350, 150, 100, 25);
				sendComponent[2] = sendModel;
				
				JTextField sendNum = new JTextField("<赠送数量>");
				new AddWordsChange(sendNum, "<赠送数量>");
				sendNum.setBounds(500, 150, 100, 25);
				sendComponent[3] = sendNum;
				
				JTextField sendPrice = new JTextField("<商品单价>");
				new AddWordsChange(sendPrice, "<商品单价>");
				sendPrice.setBounds(650, 150, 100, 25);
				sendPrice.setEditable(false);
				sendComponent[4] = sendPrice;
				
				
				JLabel send = new JLabel("赠送", JLabel.CENTER);
				send.setBounds(750, 150, 50, 25);
				send.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int num = 0;
						try{
							num = Integer.parseInt(((JTextField) sendComponent[3]).getText());
						}
						catch(Exception excption) {
							infoBoard.setText("填写有误");
						}
						if(num <= 0) {
							infoBoard.setText("填写有误");
						}
						else {
							GoodsVO vo;
							if(cc.addSendCommodity(new SendCommodityVO(
									(vo = gc.getGoodsByInfo(
											((JTextField)sendComponent[1]).getText(),
											((JTextField)sendComponent[2]).getText())).serialNumber,
									((JTextField)sendComponent[0]).getText(),
									num,
									vo.price,
									SendCommodityVO.PASS)) == ResultMessage.add_success)
								infoBoard.setText("添加成功");
							else infoBoard.setText("添加失败");
						}
						infoBoard.setVisible(true);
					}
				});
				sendComponent[5] = send;
				
				this.add(sendComponent[0]);
				this.add(sendComponent[1]);
				this.add(sendComponent[2]);
				this.add(sendComponent[3]);
				this.add(sendComponent[4]);
				this.add(sendComponent[5]);
				this.repaint();
				
			}
			else {
				for(int i = 0; i < sendComponent.length; i ++) {
            		sendComponent[i].setVisible(true);
            	}
			}
			
		}
		/**
		 * 导出excel文件的方法
		 * @param comInvenTable
		 */
		private void export(JTable comInvenTable) {
			String url = "C:/Users/Administrator/Desktop";
			
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
		CommodityFrame c = new CommodityFrame(new UserVO("hutao","123", UserSort.Commodity, 1));
	}
	

}
