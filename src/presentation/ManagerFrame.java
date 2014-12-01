package presentation;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.ApprovalBLService.ApprovalBLService_Controller;
import businesslogicservice.PromotionBLService.PromotionController;
import Config.Level;
import Config.PromotionSort;
import PO.GoodsPO;
import VO.PromotionVO;


public class ManagerFrame extends JFrame{
	private JLabel backgroundLabel,exitButton,crLabel,infoLabel,promotionLabel;
	private CheckReceiptPanel crPanel=new CheckReceiptPanel(this);
	private InfoPanel infoPanel=new InfoPanel(this);
	private PromotionPanel proPanel=new PromotionPanel(this);
	
	public ManagerFrame(){   //总Frame
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
		
		crLabel = new JLabel("审批单据", JLabel.CENTER);
		crLabel.setBounds(40, 100, 100, 50);
		crLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(true);
				infoPanel.setVisible(false);
				proPanel.setVisible(false);
			}
		});
		
		infoLabel = new JLabel("查看报表", JLabel.CENTER);
		infoLabel.setBounds(40, 160, 100, 50);
		infoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(false);
				infoPanel.setVisible(true);
				proPanel.setVisible(false);
			}
		});
		
		promotionLabel = new JLabel("制定促销策略", JLabel.CENTER);
		promotionLabel.setBounds(40, 220, 100, 50);
		promotionLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(false);
				infoPanel.setVisible(false);
				proPanel.setVisible(true);
			}
		});
		
		
		this.add(exitButton);
		this.add(crLabel);
		this.add(infoLabel);
		this.add(promotionLabel);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class CheckReceiptPanel extends JPanel {      //审批报表panel
		//private JTable goodsTable;
		JPanel panel1,panel2,panel3,panel4;
		public CheckReceiptPanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));
			 //选择性标签
			JLabel Receipt1 = new JLabel("进货单和进货退货单",JLabel.CENTER);
			Receipt1.setBounds(130, 23, 120, 50);
			this.add(Receipt1);
			
			JLabel Receipt2 = new JLabel("销售单和销售退货单",JLabel.CENTER);
			Receipt2.setBounds(290, 23, 120, 50);
			this.add(Receipt2);
			
			JLabel Receipt3 = new JLabel("收款单和付款单",JLabel.CENTER);
			Receipt3.setBounds(440, 23, 120, 50);
			this.add(Receipt3);
			
			JLabel Receipt4 =new JLabel("库存赠送单",JLabel.CENTER);
			Receipt4.setBounds(565, 23, 120, 50);
			this.add(Receipt4);
			
			panel1=new JPanel();
			panel1.setLayout(null);
			panel1.setBounds(37, 63, 767, 438);
			panel1.setBackground(new Color(179, 197,135));
			this.add(panel1);
			
			panel2=new JPanel();
			panel2.setLayout(null);
			panel2.setBounds(37, 63, 767, 438);
			panel2.setBackground(new Color(140, 197,135));
			this.add(panel2);
			
			panel3=new JPanel();
			panel3.setLayout(null);
			panel3.setBounds(37, 63, 767, 438);
			panel3.setBackground(new Color(174, 221,129));
			this.add(panel3);
			
			panel4=new JPanel();
			panel4.setLayout(null);
			panel4.setBounds(37, 63, 767, 438);
			panel4.setBackground(new Color(150, 100,110));
			this.add(panel4);
			
			Receipt1.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(true);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(false);
				}
			});
			
			Receipt2.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					panel4.setVisible(false);
				}
			});
			
			Receipt3.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					panel4.setVisible(false);
				}
			});
			
			Receipt4.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(true);
				}
			});
			// 表一
			table1Refresh();
			
			
			// 表二
			table2Refresh();
			
		
			// 表三
			table3Refresh();
			
			//表四
			table4Refresh();
			
			theFrame.add(this);
		}
		public void table1Refresh(){
			String[] columnTitle1={"单据编号","供应商","仓库","操作员","入库商品列表","备注",
					"总额合计","审批通过"};
					
			ArrayList<Object> oneData=new ArrayList<Object>();
			ArrayList<ArrayList<Object>> tableData1=new ArrayList<ArrayList<Object>>();
			Object[] ex=new Object[]{"JH123","胡韬","1号","高杨","暂无","点击查看",1000,new Boolean(false)};
			Object[] exo=new Object[]{"JH124","小宇","2号","高杨","暂无","点击查看",2000,new Boolean(false)};
			for(int i=0;i<ex.length;i++){
				oneData.add(ex[i]);
			}
			tableData1.add(oneData);
			
			oneData=new ArrayList<Object>();
			for(int i=0;i<exo.length;i++){
				oneData.add(exo[i]);
			}
			tableData1.add(oneData);
			
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(630,400);
			tablePane1.setLocation(50, 10);
			panel1.add(tablePane1);
			
			JButton allButton1=new JButton("全选");
			allButton1.setBounds(685,150,73,30);
			panel1.add(allButton1);
			JButton allNotButton1=new JButton("全不选");
			allNotButton1.setBounds(685,185,73,30);
			panel1.add(allNotButton1);
			JButton doneButton1=new JButton("审批");
			doneButton1.setBounds(685, 379, 73, 30);
			panel1.add(doneButton1);
			
			allButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table1.getRowCount();i++)
							table1.setValueAt(true, i, 7);
					}
			});
			
			allNotButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table1.getRowCount();i++)
							table1.setValueAt(false, i, 7);
						}
			});   
			
			doneButton1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<ArrayList<Object>> isApproved=new ArrayList<ArrayList<Object>>();
					for(int i=0;i<tableData1.size();i++){
						if((Boolean)tableData1.get(i).get(7)==true)
							isApproved.add(tableData1.get(i));
					}
					new ApprovalBLService_Controller().salesChangeCustomer(null);
					
					
				}
			});
		}
	
		public void table2Refresh(){
			String[] columnTitle2={"单据编号","客户","业务员","操作员","仓库","出货商品清单","折让前总额",
					"折让","使用代金券金额","折让后总额","备注","审批通过"};
			ArrayList<Object> oneData=new ArrayList<Object>();
			ArrayList<ArrayList<Object>> tableData2=new ArrayList<ArrayList<Object>>();
			Object[] ex=new Object[]{"TH123","胡韬","姚锰舟","高杨","0011","点击查看",1000,20,50,980,"无",new Boolean(false)};
			Object[] exo=new Object[]{"TH124","小宇","姚锰舟","高杨","0011","点击查看",2000,40,100,1960,"无",new Boolean(false)};
			
			for(int i=0;i<ex.length;i++){
				oneData.add(ex[i]);
			}
			tableData2.add(oneData);
			
			oneData=new ArrayList<Object>();
			for(int i=0;i<exo.length;i++){
				oneData.add(exo[i]);
			}
			tableData2.add(oneData);
			
			JTable table2=new JTable(new MyTableModel(tableData2,columnTitle2));
			table2.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table2.setDefaultRenderer(Object.class, render);
		    
		    
			JScrollPane tablePane2=new JScrollPane(table2);
			tablePane2.setSize(630,400);
			tablePane2.setLocation(50, 10);
			panel2.add(tablePane2);
				
			JButton allButton2=new JButton("全选");
			allButton2.setBounds(685,150,73,30);
			panel2.add(allButton2);
			JButton allNotButton2=new JButton("全不选");
			allNotButton2.setBounds(685,185,73,30);
			panel2.add(allNotButton2);
			JButton doneButton2=new JButton("审批");
			doneButton2.setBounds(685, 379, 73, 30);
			panel2.add(doneButton2);
			
			allButton2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table2.getRowCount();i++)
							table2.setValueAt(true, i, 11);
					}
			});
			
			allNotButton2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
						for(int i=0;i<table2.getRowCount();i++)
							table2.setValueAt(false, i, 11);
						}
			});   
		}
		
		public void table3Refresh(){
			String[] columnTitle3={"单据类型","单据编号","客户","操作员","转账列表","总额汇总","审批通过"};
			
			ArrayList<Object> oneData=new ArrayList<Object>();
			ArrayList<ArrayList<Object>> tableData3=new ArrayList<ArrayList<Object>>();
			
			Object[] ex={"收款单","SKD123","东芝","小宇","点击查看","2333",new Boolean(false)};
			for(int i=0;i<ex.length;i++){
				oneData.add(ex[i]);
			}
			
			tableData3.add(oneData);
			
			JTable table3=new JTable(new MyTableModel(tableData3,columnTitle3));
			table3.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table3.setDefaultRenderer(Object.class, render);
		    
		    
			JScrollPane tablePane3=new JScrollPane(table3);
			tablePane3.setSize(630,400);
			tablePane3.setLocation(50, 10);
			panel3.add(tablePane3);
			
			JButton allButton3=new JButton("全选");
			allButton3.setBounds(685,150,73,30);
			panel3.add(allButton3);
			JButton allNotButton3=new JButton("全不选");
			allNotButton3.setBounds(685,185,73,30);
			panel3.add(allNotButton3);
			JButton doneButton3=new JButton("审批");
			doneButton3.setBounds(685, 379, 73, 30);
			panel3.add(doneButton3);
			
			allButton3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table3.getRowCount();i++)
							table3.setValueAt(true, i, 6);
					}
			});
			
			allNotButton3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table3.getRowCount();i++)
							table3.setValueAt(false, i, 6);
						}
			});         
		}
		
		public void table4Refresh(){
			String[] columnTitle4={"客户","商品编号","商品名称","商品数量","审批通过"};
			
			ArrayList<Object> oneData=new ArrayList<Object>();
			ArrayList<ArrayList<Object>> tableData4=new ArrayList<ArrayList<Object>>();
			
			Object[] ex={"东芝","DZ-123","冬至日光灯",1,new Boolean(false)};
			for(int i=0;i<ex.length;i++){
				oneData.add(ex[i]);
			}
			
			tableData4.add(oneData);
			
			JTable table4=new JTable(new MyTableModel(tableData4,columnTitle4));
			table4.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table4.setDefaultRenderer(Object.class, render);
		    
		    
			JScrollPane tablePane4=new JScrollPane(table4);
			tablePane4.setSize(630,400);
			tablePane4.setLocation(50, 10);
			panel4.add(tablePane4);
			
			JButton allButton4=new JButton("全选");
			allButton4.setBounds(685,150,73,30);
			panel4.add(allButton4);
			JButton allNotButton4=new JButton("全不选");
			allNotButton4.setBounds(685,185,73,30);
			panel4.add(allNotButton4);
			JButton doneButton4=new JButton("审批");
			doneButton4.setBounds(685, 379, 73, 30);
			panel4.add(doneButton4);
			
			allButton4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table4.getRowCount();i++)
							table4.setValueAt(true, i, 4);
					}
			});
			
			allNotButton4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table4.getRowCount();i++)
							table4.setValueAt(false, i, 4);
						}
			});         
		}
	}  
	
	class InfoPanel extends JPanel {     //查看报表信息panel
		//private JTable goodsTable;
		public InfoPanel(JFrame theFrame) {
			this.setVisible(false);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(185, 227, 217, 255));
			
			theFrame.add(this);
		}
	}
	
	class PromotionPanel extends JPanel {     //制定促销策略panel
		private JTable table;
		private JScrollPane tablePane; 
		public PromotionPanel(JFrame theFrame) {
			this.setVisible(false);
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(173, 137, 115, 255));
			
			JLabel add1=new JLabel("添加特价包策略");
			add1.setBounds(80,40,100,20);
			this.add(add1);
			
			add1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new ManagerFrameHelper("package");
				}
			});
			
			JLabel add2=new JLabel("添加达总价送赠品策略");
			add2.setBounds(200,40,130,20);
			this.add(add2);
			
			add2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new ManagerFrameHelper("gifts");
				}
			});
			
			JLabel add3=new JLabel("添加达总价送代金券策略");
			add3.setBounds(360,40,150,20);
			this.add(add3);
			
			add3.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new ManagerFrameHelper("voucher");
				}
			});
			
			JLabel delete=new JLabel("删除策略");
			delete.setBounds(520,40,130,20);
			this.add(delete);
			
			delete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new ManagerFrameHelper("deletePromotion");
				}
			});
			
			JLabel refresh=new JLabel("刷新列表");
			refresh.setBounds(727,40,130,20);
			this.add(refresh);
			
			refresh.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					tableRefresh();
				}
			});
			
			tableRefresh();
		
			theFrame.add(this);
		}
		
		public ArrayList<ArrayList<Object>> getTableData(){
			ArrayList<PromotionVO> promotions=new ArrayList<PromotionVO>(new PromotionController().show());
			ArrayList<ArrayList<Object>> tableData=new ArrayList<ArrayList<Object>>();
			for(int i=0;i<promotions.size();i++){
				ArrayList<Object> datas=new ArrayList<Object>();
				datas.add((PromotionSort)promotions.get(i).getPromotionType());
				datas.add((String)promotions.get(i).getPromotionId());
				datas.add((ArrayList<GoodsPO>)promotions.get(i).getPromotionGoods());
				datas.add((double)promotions.get(i).getLeastPrice());
				datas.add((double)promotions.get(i).getOffPrice());
				datas.add((ArrayList<GoodsPO>)promotions.get(i).getPresents());
				datas.add((int)promotions.get(i).getVoucher());
				datas.add((String)promotions.get(i).getStartTime());
				datas.add((String)promotions.get(i).getEndTime());
				datas.add((Level)promotions.get(i).getCustomer());
				switch ((PromotionSort)datas.get(0)) {
				case Package:
					datas.set(3, 0);  //leastPrice
					datas.set(5, "无");   //presents
					datas.set(6, 0);   //voucher
					break;
				case Gifts:
					datas.set(2, "无");
					datas.set(4, 0);
					datas.set(6,0);
					break;
				case Voucher:
					datas.set(2, "无");
					datas.set(4, 0);
					datas.set(5, "无");
					break;
				}
				tableData.add(datas);
			} 
			return tableData;
		}
		
		public  void tableRefresh(){
			String[] columnTitle={"策略类型","策略编号","组合商品降价","优惠需达金额","降价金额","赠品列表",
					"赠送代金券金额","起始时间","终止时间","客户最低等级"};
			ArrayList<ArrayList<Object>> tableData=getTableData();
			table=new JTable(new MyTableModel(tableData,columnTitle));
			table.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		    
		    table.getModel().addTableModelListener(new TableModelListener(){     //检测是否有内容更改
		    	public void tableChanged(TableModelEvent e) {     //进行的操作
		    	}
		    });
		    table.repaint();
		    
		    if(tablePane!=null)
		    	tablePane.setVisible(false);    //重要！防止表格出现错位
		    tablePane=new JScrollPane(table);
		    tablePane.setSize(700,400);
			tablePane.setLocation(80, 74);
			tablePane.repaint();
			this.add(tablePane);
		}
	}
	
	class MyTableModel extends AbstractTableModel{      //表格模型
		private ArrayList<ArrayList<Object>> tableData;
		private String[] columnTitle;
		public MyTableModel(ArrayList<ArrayList<Object>> data,String[] title) {
			tableData=data;
			columnTitle=title;
		}
		 
		public String getColumnName(int col)
	     {
	          return columnTitle[col];
	     }
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return tableData.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			
			return columnTitle.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return tableData.get(rowIndex).get(columnIndex);
		}
		
		public Class getColumnClass(int c) {  
	        return getValueAt(0, c).getClass();  
	    }
		
		 public boolean isCellEditable(int row, int col) { 
			 return true;
		 }
		
		public void setValueAt(Object value, int row, int col) {  
	        tableData.get(row).set(col,  value);
	        fireTableCellUpdated(row, col);  
	    }	
	}

	
	class TransferFrame extends JFrame{
		
	}

	public static void main(String[] args){
		new ManagerFrame();
	}
}

