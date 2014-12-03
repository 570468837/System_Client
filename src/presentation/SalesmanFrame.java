package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class SalesmanFrame extends JFrame {
	

	private JLabel exitButton,customerLabel,salesLabel,purchaseLabel;
	private CustomerPanel customerPanel=new CustomerPanel(this);
	private SalesPanel salesPanel=new SalesPanel(this);
	private PurchasePanel purchasePanel=new PurchasePanel(this);
	
	public SalesmanFrame(){   //总Frame
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		getContentPane().setLayout(null);
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
		
		customerLabel = new JLabel("客户管理", JLabel.CENTER);
		customerLabel.setBackground(new Color(147, 224, 255, 255));
		customerLabel.setBounds(40, 100, 100, 50);
		customerLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				customerPanel.setVisible(true);
				salesPanel.setVisible(false);
				purchasePanel.setVisible(false);
			}
		});
		
		salesLabel = new JLabel("销售管理", JLabel.CENTER);
		salesLabel.setBackground(new Color(185, 227, 217, 255));
		salesLabel.setBounds(40, 160, 100, 50);
		salesLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				customerPanel.setVisible(false);
				salesPanel.setVisible(true);
				purchasePanel.setVisible(false);
			}
		});
		
		purchaseLabel = new JLabel("进货管理", JLabel.CENTER);
		purchaseLabel.setBounds(40, 220, 100, 50);
		purchaseLabel.setBackground(new Color(173, 137, 115, 255));
		purchaseLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				customerPanel.setVisible(false);
				salesPanel.setVisible(false);
				purchasePanel.setVisible(true);
			}
		});
		
		
		getContentPane().add(exitButton);
		getContentPane().add(customerLabel);
		getContentPane().add(salesLabel);
		getContentPane().add(purchaseLabel);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class CustomerPanel extends JPanel {      
		//private JTable goodsTable;
		public CustomerPanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));

			String[] columnTitle1={"编号","分类","级别","姓名","电话","地址","邮编","电子邮箱","业务员","应收","应付","应收额度"};
			Object[][] tableData1={
					new Object[]{"0001","供货商","VIP","胡韬","22222222","南大","210046","@","鹅","0","0","100"},
					new Object[]{"0002","进货商","VIP","小宇","33333333","南大","210046","@","鹅","0","0","100"},
					           };
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(630,400);
			tablePane1.setLocation(80, 74);
			this.add(tablePane1);
			
			JButton addButton1=new JButton("增加客户");
			addButton1.setBounds(725,150,100,30);
			this.add(addButton1);
			JButton deleteButton1=new JButton("删除客户");
			deleteButton1.setBounds(725,185,100,30);
			this.add(deleteButton1);
			JButton updateButton1=new JButton("修改客户");
			updateButton1.setBounds(725, 220, 100, 30);
			this.add(updateButton1);
			
			
			JTextField serchField=new JTextField();	
			serchField.setBounds(317,30, 200, 20);
			this.add(serchField);
			JButton serchButton=new JButton("搜索");
			serchButton.setBounds(520, 30, 40, 20);
			this.add(serchButton);


			theFrame.getContentPane().add(this);
			
			addButton1.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					new SalesmanFrameHelper("addCustomer");
				}
				
			});
		}
	}
	
	class SalesPanel extends JPanel {     //查看报表信息panel
		//private JTable goodsTable;
		public SalesPanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(185, 227, 217, 255));
			//操作日志
			String[] columnTitle1={"操作","操作人员","日期"};
			Object[][] tableData1={
					new Object[]{"创建销售单","鹅","12.11"},
					new Object[]{"创建销售退货单","鹅","12.11"},
					           };
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.LEFT);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(630,400);
			tablePane1.setLocation(80, 74);
			this.add(tablePane1);
			
			JButton creatInButton1=new JButton("创建销售单");
			creatInButton1.setBounds(725,150,100,30);
			this.add(creatInButton1);
			JButton creatOutButton1=new JButton("创建销售退货单");
			creatOutButton1.setBounds(715,185,120,30);
			this.add(creatOutButton1);
			
		
			theFrame.getContentPane().add(this);
		}
	}
	
	class PurchasePanel extends JPanel {     //指定促销策略panel
		//private JTable goodsTable;
		public PurchasePanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(173, 137, 115, 255));
			//操作日志
			String[] columnTitle1={"操作","操作人员","日期"};
			Object[][] tableData1={
					new Object[]{"创建进货单","鹅","12.11"},
					new Object[]{"创建进货退货单","鹅","12.11"},
					           };
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.LEFT);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(630,400);
			tablePane1.setLocation(80, 74);
			this.add(tablePane1);
			
			JButton creatInButton1=new JButton("创建进货单");
			creatInButton1.setBounds(725,150,100,30);
			this.add(creatInButton1);
			
			JButton creatOutButton1=new JButton("创建进货退货单");
			creatOutButton1.setBounds(715,185,120,30);
			this.add(creatOutButton1);
			
			theFrame.getContentPane().add(this);
			
			creatInButton1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new SalesmanFrameHelper("addPurchaseReceipt");
					
				}
			});
			
		}
	}
	
	class MyTableModel extends AbstractTableModel{      //表格模型
		private Object[][] tableData;
		private String[] columnTitle;
		public MyTableModel(Object[][] data,String[] title) {
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
			return tableData.length;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			
			return columnTitle.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return tableData[rowIndex][columnIndex];
		}
		
		public Class getColumnClass(int c) {  
	        return getValueAt(0, c).getClass();  
	    }
		
		 public boolean isCellEditable(int row, int col) { 
			 return true;
		 }
		
		public void setValueAt(Object value, int row, int col) {  
	        tableData[row][col] = value;  
	        fireTableCellUpdated(row, col);  
	    }	
		
	}

	class TransferFrame extends JFrame{
		
	}

	

	       
	
	
	public static void main(String[] args){
		new SalesmanFrame();
	}

}
