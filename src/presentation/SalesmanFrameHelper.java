package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Config.Level;
import Config.Sort;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseReceiptVO;
import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.GoodsBLService.GoodsController;
import businesslogicservice.PurchseBLService.PurchaseController;

public class SalesmanFrameHelper {

	public SalesmanFrameHelper(String command) {
		switch (command) {
		case "addCustomer":
			new AddCustomerFrame();
			break;
		case "addPurchaseReceipt":
			new AddPurchaseReceiptFrame();
			break;
		case "addSalesReceipt":
			new AddSalesReceiptFrame();
			break;
		}

	}

	class AddCustomerFrame extends JFrame {
		private JButton confirmButton;
		private JButton cancelButton;
		

		private JLabel serialnumLabel;
		private JTextField serialnum;
		private JLabel classLabel;
		private JComboBox classes;
		private JLabel levelLabel;
		private JComboBox level;
		private JLabel nameLabel;
		private JTextField name;
		private JLabel phoneLabel;
		private JTextField phone;
		private JLabel adressLabel;
		private JTextField adress;
		private JLabel zipcodeLabel;
		private JTextField zipcode;
		private JLabel emailLabel;
		private JTextField email;
		private JLabel clerkLabel;
		private JTextField clerk;
		
		private JLabel payLabel,gettingLabel,degreeLabel;
		private JLabel pay,getting,degree;

		public AddCustomerFrame() {
			this.setTitle("增加客户");
			this.setVisible(true);
			setBounds(100, 100, 556, 475);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			payLabel=new JLabel("应付：");
			payLabel.setBounds(400, 40, 100, 20);
			getContentPane().add(payLabel);
			
			pay=new JLabel("0",JLabel.CENTER);
			pay.setBounds(450, 40, 100, 20);
			getContentPane().add(pay);
			
			gettingLabel=new JLabel("应收：");
			gettingLabel.setBounds(400, 80, 100, 20);
			getContentPane().add(gettingLabel);
			
			getting=new JLabel("0",JLabel.CENTER);
			getting.setBounds(450, 80, 100, 20);
			getContentPane().add(getting);
			
			degreeLabel=new JLabel("应收额度：");
			degreeLabel.setBounds(400, 120, 100, 20);
			getContentPane().add(degreeLabel);
			
			degree=new JLabel("0",JLabel.CENTER);
			degree.setBounds(450, 120, 100, 20);
			getContentPane().add(degree);

			serialnumLabel = new JLabel("客户编号");
			serialnumLabel.setBounds(140, 40, 100, 20);
			serialnumLabel.setBackground(Color.BLACK);
			getContentPane().add(serialnumLabel);

			classLabel = new JLabel("分类");
			classLabel.setBounds(140, 80, 100, 20);
			getContentPane().add(classLabel);

			levelLabel = new JLabel("级别");
			levelLabel.setBounds(140, 120, 100, 20);
			getContentPane().add(levelLabel);

			nameLabel = new JLabel("姓名");
			nameLabel.setBounds(140, 160, 100, 20);
			getContentPane().add(nameLabel);

			phoneLabel = new JLabel("电话");
			phoneLabel.setBounds(140, 200, 100, 20);
			getContentPane().add(phoneLabel);

			adressLabel = new JLabel("地址");
			adressLabel.setBounds(140, 240, 100, 20);
			getContentPane().add(adressLabel);

			zipcodeLabel = new JLabel("邮编");
			zipcodeLabel.setBounds(140, 280, 100, 20);
			getContentPane().add(zipcodeLabel);

			emailLabel = new JLabel("电子邮箱");
			emailLabel.setBounds(140, 320, 100, 20);
			getContentPane().add(emailLabel);

			clerkLabel = new JLabel("业务员");
			clerkLabel.setBounds(140, 360, 100, 20);
			getContentPane().add(clerkLabel);

			confirmButton = new JButton("确认");
			confirmButton.setBounds(147, 394, 88, 30);
			getContentPane().add(confirmButton);

			cancelButton = new JButton("取消");
			cancelButton.setBounds(296, 394, 88, 30);
			getContentPane().add(cancelButton);

			serialnum = new JTextField();
			serialnum.setBounds(240, 40, 100, 20);
			serialnum.setColumns(10);
			getContentPane().add(serialnum);

			classes = new JComboBox(new String[] { "进货商", "销售商" });
			classes.setBounds(240, 80, 100, 21);
			getContentPane().add(classes);

			level = new JComboBox(new String[] { "一级", "二级", "三级", "四级",
					"五级VIP" });
			level.setBounds(240, 120, 100, 21);
			getContentPane().add(level);

			name = new JTextField();
			name.setBounds(240, 160, 100, 20);
			name.setColumns(10);
			getContentPane().add(name);

			phone = new JTextField();
			phone.setBounds(240, 200, 150, 20);
			phone.setColumns(10);
			getContentPane().add(phone);

			adress = new JTextField();
			adress.setBounds(240, 240, 150, 20);
			adress.setColumns(10);
			getContentPane().add(adress);

			zipcode = new JTextField();
			zipcode.setBounds(240, 280, 100, 20);
			zipcode.setColumns(10);
			getContentPane().add(zipcode);

			email = new JTextField();
			email.setBounds(240, 320, 150, 20);
			email.setColumns(10);
			getContentPane().add(email);

			clerk = new JTextField();
			clerk.setBounds(240, 360, 100, 20);
			clerk.setColumns(10);
			getContentPane().add(clerk);

			confirmButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CustomerVO vo = new CustomerVO(serialnum.getText(),
							getCustomerSort(classes.getSelectedItem()),
							getCustomerLevel(level.getSelectedItem()), name
									.getText(), phone.getText(), adress
									.getText(), zipcode.getText(), email
									.getText(), clerk.getText());
					
					ResultMessage result = new CustomerController().addCustomer(vo);
					
					if(result==ResultMessage.add_customer_success){
						dispose();
					}else{
						new warningDialog("已经存在该客户！");
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
				
			});

			this.repaint();
		}

	}

	class AddPurchaseReceiptFrame extends JFrame {
		private JLabel serialNumberLabel,customerLabel,userLabel,timeLabel,commentLabel,totalPriceLabel,commodityLabel;
		private JComboBox commodity;
		private JTextField serialNumber,customer,user,time,totalPrice;
		private JTextArea comment;
		private JButton cancelButton,confirmButton,addItemButton;
		private ArrayList<GoodsVO> goodsList;
		
		public AddPurchaseReceiptFrame(){
			this.setTitle("创建进货单");
			this.setVisible(true);
			setBounds(100, 100, 556, 475);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			goodsList=new ArrayList<GoodsVO>();
			
			serialNumberLabel=new JLabel("单据编号");
			serialNumberLabel.setBounds(20,20,80,20);
			getContentPane().add(serialNumberLabel);
			//自动填充
			serialNumber=new JTextField("JHD-YYMMDD-XXXXX",JTextField.CENTER);
			serialNumber.setBounds(100, 20, 170, 20);
			getContentPane().add(serialNumber);
			
			customerLabel=new JLabel("供货商");
			customerLabel.setBounds(20, 60, 100, 20);
			getContentPane().add(customerLabel);
			
			customer=new JTextField();
			customer.setBounds(100, 60, 100, 20);
			getContentPane().add(customer);
			
			userLabel=new JLabel("操作员");
			userLabel.setBounds(210, 60, 100, 20);
			getContentPane().add(userLabel);
			//自动填充
			user=new JTextField();
			user.setBounds(270, 60, 100, 20);
			getContentPane().add(user);
			
			timeLabel=new JLabel("创建时间");
			timeLabel.setBounds(20, 100, 100, 20);
			getContentPane().add(timeLabel);
			//自动添加
			time=new JTextField();
			time.setBounds(100, 100, 100, 20);
			getContentPane().add(time);
			
			commodityLabel=new JLabel("仓库");
			commodityLabel.setBounds(210, 100, 100, 20);
			getContentPane().add(commodityLabel);
			
			commodity=new JComboBox(new String[]{"唯一的仓库"});
			commodity.setBounds(270, 100, 100, 20);
			getContentPane().add(commodity);
			
			totalPriceLabel=new JLabel("总价");
			totalPriceLabel.setBounds(400, 100, 100, 20);
			getContentPane().add(totalPriceLabel);
			//自动填充
			totalPrice=new JTextField();
			totalPrice.setBounds(440, 100, 100, 20);
			getContentPane().add(totalPrice);
			
			commentLabel=new JLabel("备注");
			commentLabel.setBounds(20, 140, 100, 20);
			getContentPane().add(commentLabel);
			
			comment=new JTextArea(50, 50);
			comment.setBounds(100,140, 440, 40);
			getContentPane().add(comment);
			
			addItemButton=new JButton("添加商品");
			addItemButton.setBounds(10, 200, 80, 20);
			getContentPane().add(addItemButton);
			
			addItemButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddItemFrame();					
				}
			});
			
			String[] columnTitle1={"商品编号","商品名称","商品数量"};
			Object[][] tableData1={
					new Object[]{"ASD","胡韬牌电灯泡","100"},
					new Object[]{"DSA","盛宇牌浴霸","100"},
					           };
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.LEFT);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(440,200);
			tablePane1.setLocation(100, 200);
			getContentPane().add(tablePane1);
			
			confirmButton = new JButton("确认");
			confirmButton.setBounds(147, 410, 88, 30);
			getContentPane().add(confirmButton);

			cancelButton = new JButton("取消");
			cancelButton.setBounds(296, 410, 88, 30);
			getContentPane().add(cancelButton);
			
			
			confirmButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PurchaseReceiptVO receipt=new PurchaseReceiptVO(serialNumber.getText(), purchaseList, userPO, time.getText(), comment.getText(), totalPrice.getText());
					
					ResultMessage result=new PurchaseController().creatReceipt(receipt);
					
					if(result==ResultMessage.create_purchasereceipt_success){
						dispose();
					}else{
						new warningDialog("保存失败!");
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();				
				}
				
			});

			this.repaint();

		}
		
		/**
		 * @author gaoyang
		 *默认添加的商品在仓库中可以找到
		 */
		class AddItemFrame extends JFrame{
			private JLabel goodsSerialNumberLabel,goodsQuantityLabel;
			private JTextField goodsSerialNumber,goodsQuantity;
			private JButton confirmButton,cancelButton;
			
			public AddItemFrame(){
				this.setTitle("添加商品");
				this.setVisible(true);
				setBounds(100, 100, 250, 100);
				this.setLocationRelativeTo(null);
				getContentPane().setLayout(null);
				
				
				goodsSerialNumberLabel=new JLabel("商品编号");
				goodsSerialNumberLabel.setBounds(20, 20, 100,20);
				getContentPane().add(goodsSerialNumberLabel);
				
				goodsSerialNumber=new JTextField();
				goodsSerialNumber.setBounds(120, 20, 100, 20);
				getContentPane().add(goodsSerialNumber);
				
				goodsQuantityLabel=new JLabel("商品数量");
				goodsQuantityLabel.setBounds(20, 60, 100,20);
				getContentPane().add(goodsQuantityLabel);
				
				goodsQuantity=new JTextField();
				goodsQuantity.setBounds(120, 60, 100, 20);
				getContentPane().add(goodsQuantity);
				
				confirmButton = new JButton("确认");
				confirmButton.setBounds(20, 100, 100, 20);
				getContentPane().add(confirmButton);

				cancelButton = new JButton("取消");
				cancelButton.setBounds(120, 100, 100, 20);
				getContentPane().add(cancelButton);
				
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						GoodsVO good=new GoodsController().getGoodsByID(goodsSerialNumber.getText());
						//在这里应当向frame添加商品列表中的商品
						
						
						if(result==ResultMessage.add_success){
							dispose();
						}else{
							new warningDialog("不存在此商品，添加失败!");
						}
					}
				});
				
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();				
					}
					
				});
				
				
			}
			
		}
		
	}
	

	class AddSalesReceiptFrame extends JFrame {
	}
	

	

	class warningDialog extends JDialog {
		public warningDialog(String warnings) {
			this.setSize(284, 158);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			this.setVisible(true);
			this.setModal(true);

			JLabel warningLabel = new JLabel(warnings, JLabel.CENTER);
			warningLabel.setBounds(50, 28, 200, 50);
			warningLabel.setFont(new Font("宋体", Font.BOLD, 14));

			this.add(warningLabel);
		}
	}

	public int getYearNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public int getMonthNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	public int getDateNow() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	public String standardHM() {
		Calendar c = Calendar.getInstance();
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(c.get(Calendar.MINUTE));
		if (hour.length() == 1) {
			hour = "0" + hour;
		}
		if (minute.length() == 1) {
			minute = "0" + minute;
		}
		return hour + minute;
	}

	public Level getCustomerLevel(Object selected) {
		Level customer = null;
		switch ((String) selected) {
		case "一级":
			customer = Level.firstClass;
			break;
		case "二级":
			customer = Level.secondClass;
			break;
		case "三级":
			customer = Level.thirdClass;
			break;
		case "四级":
			customer = Level.forthClass;
			break;
		case "五级VIP":
			customer = Level.fiveClassVIP;
			break;
		}
		return customer;
	}

	public Sort getCustomerSort(Object selected) {
		Sort customer = null;
		switch ((String) selected) {
		case "进货商":
			customer = Sort.importer;
			break;
		case "销售商":
			customer = Sort.retailer;
			break;

		}

		return customer;

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

	public static void main(String[] args) {
		new SalesmanFrameHelper("addPurchaseReceipt");

	}
}
