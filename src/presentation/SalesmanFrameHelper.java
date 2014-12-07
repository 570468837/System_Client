package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

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
import javax.swing.table.DefaultTableModel;

import Config.Level;
import Config.Sort;
import PO.CustomerPO;
import PO.PurchaseReceiptPO;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import VO.GoodsVO;
import VO.PurchaseListItemVO;
import VO.PurchaseReceiptVO;
import VO.SalesListItemVO;
import VO.SalesReceiptVO;
import VO.UserVO;
import businesslogicservice.CustomerBLService.CustomerController;
import businesslogicservice.GoodsBLService.GoodsController;
import businesslogicservice.PurchseBLService.PurchaseController;
import businesslogicservice.SaleBLService.SalesController;

public class SalesmanFrameHelper {
	public static final int TYPE_PURCHASE=1;
	public static final int TYPE_PURCHASE_BACK=-1;

	public static final int TYPE_SALES=2;
	public static final int TYPE_SALES_BACK=-2;
	
	public static final int ADD_CUSTOMER=11;
	public static final int DELETE_CUSTOMER=12;
	public static final int UPDATE_CUSTOMER=13;


	public static final int ADD_PURCHASERECEIPT=21;
	public static final int ADD_PURCHASERECEIPT_BACK=22;

	public static final int ADD_SALESRECEIPT=31;
	public static final int ADD_SALESRECEIPT_BACK=33;

	
	public ArrayList<GoodsVO> goodsList=new ArrayList<GoodsVO>();

	
	
	public UserVO userVO;
	
	public int yearNow=this.getYearNow();
	public int monthNow=this.getMonthNow();
	public int dayNow=this.getDateNow();

	public SalesmanFrameHelper(String command,UserVO vo) {
		this.userVO=vo;
		
		switch (command) {
		case "addCustomer":
			new AddCustomerFrame();
			break;
			
		case "deleteCustomer":
			new DeleteCustomerFrame();
			break;
			
		case "updateCustomer":
			new UpdateCustomerFrame();
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
	
	 class DeleteCustomerFrame extends JFrame{
		 private JLabel serialNumberLabel;
		 private JTextField serialNumber;
		 private JButton confirmButton,cancelButton;
		 
		public DeleteCustomerFrame(){
			this.setTitle("删除客户");
			this.setVisible(true);
			setBounds(100, 100, 250, 150);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			
			serialNumberLabel=new JLabel("商品编号");
			serialNumberLabel.setBounds(20, 20, 100,20);
			getContentPane().add(serialNumberLabel);
			
			serialNumber=new JTextField();
			serialNumber.setBounds(120, 20, 100, 20);
			getContentPane().add(serialNumber);
			
		
			
			confirmButton = new JButton("确认");
			confirmButton.setBounds(40, 60, 80, 20);
			getContentPane().add(confirmButton);

			cancelButton = new JButton("取消");
			cancelButton.setBounds(130, 60, 80, 20);
			getContentPane().add(cancelButton);
			
			confirmButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CustomerPO customerPO=new CustomerController().getCustomerPOById(serialNumber.getText());
					if(customerPO!=null){
						new CustomerController().deleteCustomer(new CustomerController().toVO(customerPO));
						dispose();
					}else{
						new warningDialog("查无此人！！");
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();					
				}
			});
			
			
		}
		
	}
	 
	 class UpdateCustomerFrame extends JFrame{

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

			public UpdateCustomerFrame() {
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

				serialnum = new JTextField("");
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

				
				//监听编号
				Thread listener=new  Thread(new Runnable() {
					
					@Override
					public void run() {
						while(true){
						if(!serialnum.getText().equals("")){
							CustomerPO customerPO=new CustomerController().getCustomerPOById(serialnum.getText());
								if(customerPO==null){
									new warningDialog("查无此人");
									
								}else{
									classes.setSelectedItem(customerPO.getClass());
									level.setSelectedItem(customerPO.getLevel());
									name.setText(customerPO.getName());
									phone.setText(customerPO.getPhone());
									adress.setText(customerPO.getAddress());
									zipcode.setText(customerPO.getZipCode());
									email.setText(customerPO.getMail());
									clerk.setText(customerPO.getClerk());
									
									break;
								}														
							}						
						}
					}
				});
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						CustomerVO vo = new CustomerVO(serialnum.getText(),
								getCustomerSort(classes.getSelectedItem()),
								getCustomerLevel(level.getSelectedItem()), name
										.getText(), phone.getText(), adress
										.getText(), zipcode.getText(), email
										.getText(), clerk.getText());
						
						ResultMessage result = new CustomerController().updateCustmer(vo);
						
						if(result==ResultMessage.add_customer_success){
							listener.stop();
							dispose();
						}else{
							new warningDialog("已经存在该客户！");
						}
					}
				});
				
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						listener.stop();
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
		//用来保存商品列表
		ArrayList<PurchaseListItemVO> listItems=new ArrayList<PurchaseListItemVO>();
		
		
		private JTable table1=new JTable();//商品列表
		private DefaultTableModel model = new DefaultTableModel();//表格模型
		private Vector tableColName=new Vector();
		private Vector tableData=new Vector();
		private Vector tableRows=new Vector();
		
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
			
			
			serialNumber=new JTextField(setSerialNumber(1),JTextField.CENTER);
			serialNumber.setEditable(false);
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
			user.setText(userVO.getUserName());
			getContentPane().add(user);
			
			timeLabel=new JLabel("创建时间");
			timeLabel.setBounds(20, 100, 100, 20);
			getContentPane().add(timeLabel);
			//自动添加
			time=new JTextField(yearNow+"-"+monthNow+"-"+dayNow);
			time.setEditable(false);
			time.setBounds(100, 100, 100, 20);
			getContentPane().add(time);
			
			commodityLabel=new JLabel("仓库");
			commodityLabel.setBounds(210, 100, 100, 20);
			getContentPane().add(commodityLabel);
			
			commodity=new JComboBox(new String[]{"仓库1"});
			commodity.setBounds(270, 100, 100, 20);
			getContentPane().add(commodity);
			
			totalPriceLabel=new JLabel("总价");
			totalPriceLabel.setBounds(400, 100, 100, 20);
			getContentPane().add(totalPriceLabel);
			//TODO 自动填充
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
			
//			String[] columnTitle1={"商品编号","商品名称","商品数量","商品总价"};
//			Object[][] tableData1={
//					new Object[]{"TEST","TEST","100","0"},
//					new Object[]{"TEST2","TEST2","100","0"},
//					           };
			tableColName.add("商品编号");
			tableColName.add("商品名称");
			tableColName.add("商品数量");
			tableColName.add("商品总价");
			
			tableRows.add("Test");
			tableRows.add("Test");
			tableRows.add("100");
			tableRows.add("100");
			
			tableData.add(tableRows);
			
			table1.setModel(model);
			
			model.setDataVector(tableData, tableColName);
			
//			table1=new JTable(new MyTableModel(tableData1,columnTitle1));
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
					//有点复杂
					PurchaseReceiptVO receipt=new PurchaseReceiptVO(new CustomerController().toVO(new CustomerController().getCustomerPOById(customer.getText())), serialNumber.getText(), userVO, time.getText(), comment.getText(), new Double(0).parseDouble(totalPrice.getText()));
					

					ResultMessage result=new PurchaseController().creatReceipt(receipt);
					
					//刷新外部表格
					
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
				setBounds(100, 100, 250, 150);
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
				confirmButton.setBounds(40, 100, 80, 20);
				getContentPane().add(confirmButton);

				cancelButton = new JButton("取消");
				cancelButton.setBounds(130, 100, 80, 20);
				getContentPane().add(cancelButton);
				
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//既然胡韬不改ID为string，这里只好强制转换
						GoodsVO good=new GoodsController().getGoodsByID(new Long(0).parseLong(goodsSerialNumber.getText()));
//						GoodsVO good=new GoodsVO("001", "hutao", "hutao", 100, 100, "a");//测试
						//在这里应当向frame添加商品列表中的商品
						//如果返回null说明没有此商品
						if(good!=null){
							//添加商品列表
							listItems.add(new PurchaseListItemVO(good,new Integer(0).parseInt(goodsQuantity.getText())));
							//刷新外部类商品列表表格
							Vector newRows=new Vector();
							newRows.add(good.serialNumber);
							newRows.add(good.name);
							newRows.add(goodsQuantity.getText());
							//TODO 这么多价格是个意思？
							newRows.add(new Integer(0).parseInt(goodsQuantity.getText())*good.price);							
							tableData.add(newRows);
							table1.updateUI();
							
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

		private JLabel serialNumberLabel,customerLabel,userLabel,clerkLabel,timeLabel,commentLabel,beforePriceLabel,discountLabel,finalPriceLabel,commodityLabel;
		private JComboBox commodity;
		private JTextField serialNumber,customer,user,time,beforePrice,discount,finalPrice,clerk;
		private JTextArea comment;
		private JButton cancelButton,confirmButton,addItemButton;
		private ArrayList<GoodsVO> goodsList;
		//用来保存商品列表
		ArrayList<PurchaseListItemVO> listItems=new ArrayList<PurchaseListItemVO>();
		ArrayList<SalesListItemVO> salesListItems=new ArrayList<SalesListItemVO>();
		
		
		private JTable table1=new JTable();//商品列表
		private DefaultTableModel model = new DefaultTableModel();//表格模型
		private Vector tableColName=new Vector();
		private Vector tableData=new Vector();
		private Vector tableRows=new Vector();
		
		public AddSalesReceiptFrame(){
			
			this.setTitle("创建销售单");
			this.setVisible(true);
			setBounds(100, 100, 556, 475);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			goodsList=new ArrayList<GoodsVO>();
			
			serialNumberLabel=new JLabel("单据编号");
			serialNumberLabel.setBounds(20,20,80,20);
			getContentPane().add(serialNumberLabel);
			
			
			serialNumber=new JTextField(setSerialNumber(2),JTextField.CENTER);
			serialNumber.setEditable(false);
			serialNumber.setBounds(100, 20, 170, 20);
			getContentPane().add(serialNumber);
			
			customerLabel=new JLabel("销售商");
			customerLabel.setBounds(20, 60, 100, 20);
			getContentPane().add(customerLabel);
			
			customer=new JTextField();
			customer.setBounds(100, 60, 100, 20);
			getContentPane().add(customer);
			
			clerkLabel=new JLabel("业务员");
			clerkLabel.setBounds(210, 60, 100, 20);
			getContentPane().add(clerkLabel);
			//自动填充
			clerk=new JTextField();
			clerk.setBounds(270, 60, 100, 20);
			getContentPane().add(clerk);
			
			timeLabel=new JLabel("创建时间");
			timeLabel.setBounds(20, 100, 100, 20);
			getContentPane().add(timeLabel);
			//自动添加
			time=new JTextField(yearNow+"-"+monthNow+"-"+dayNow);
			time.setEditable(false);
			time.setBounds(100, 100, 100, 20);
			getContentPane().add(time);
			
			commodityLabel=new JLabel("仓库");
			commodityLabel.setBounds(210, 100, 100, 20);
			getContentPane().add(commodityLabel);
			
			commodity=new JComboBox(new String[]{"仓库1"});
			commodity.setBounds(270, 100, 100, 20);
			getContentPane().add(commodity);
			
			userLabel=new JLabel("操作员");
			userLabel.setBounds(400, 100, 100, 20);
			getContentPane().add(userLabel);
			//TODO 自动填充
			user=new JTextField();
			user.setText(userVO.getUserName());
			user.setBounds(440, 100, 100, 20);
			getContentPane().add(user);
			
			beforePriceLabel=new JLabel("折让前价格");
			beforePriceLabel.setBounds(20, 140, 100, 20);
			getContentPane().add(beforePriceLabel);
			
			beforePrice=new JTextField();
			beforePrice.setBounds(100, 140, 100, 20);
			getContentPane().add(beforePrice);
			
			discountLabel=new JLabel("折让");
			discountLabel.setBounds(210, 140, 100, 20);
			getContentPane().add(discountLabel);
			
			discount=new JTextField();
			discount.setBounds(270, 140, 100, 20);
			getContentPane().add(discount);
			
			finalPriceLabel=new JLabel("折让后金额");
			finalPriceLabel.setBounds(20, 180, 100, 20);
			getContentPane().add(finalPriceLabel);
			//TODO 自动填充
			finalPrice=new JTextField();
			finalPrice.setBounds(100, 180, 100, 20);
			getContentPane().add(finalPrice);
			
			commentLabel=new JLabel("备注");
			commentLabel.setBounds(20, 240, 100, 20);
			getContentPane().add(commentLabel);
			
			comment=new JTextArea(50, 50);
			comment.setBounds(100,240, 440, 40);
			getContentPane().add(comment);
			
			addItemButton=new JButton("添加商品");
			addItemButton.setBounds(10, 300, 80, 20);
			getContentPane().add(addItemButton);
			
			addItemButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddItemFrame();					
				}
			});
			
//			String[] columnTitle1={"商品编号","商品名称","商品数量","商品总价"};
//			Object[][] tableData1={
//					new Object[]{"TEST","TEST","100","0"},
//					new Object[]{"TEST2","TEST2","100","0"},
//					           };
			tableColName.add("商品编号");
			tableColName.add("商品名称");
			tableColName.add("商品数量");
			tableColName.add("商品总价");
			
			tableRows.add("Test");
			tableRows.add("Test");
			tableRows.add("100");
			tableRows.add("100");
			
			tableData.add(tableRows);
			
			table1.setModel(model);
			
			model.setDataVector(tableData, tableColName);
			
//			table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.LEFT);
		    table1.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(440,100);
			tablePane1.setLocation(100, 300);
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
					//有点复杂
					SalesReceiptVO receipt=new SalesReceiptVO(serialNumber.getText(), customer.getText(), clerk.getText(), salesList, userVO, commodity.getSelectedItem(), beforePrice.getText(), discount.getText(), finalPrice.getText(), time.getText(), comment.getText());
					
					ResultMessage result=new SalesController().creatReceipt(receipt);
					
					//刷新外部表格
//					TODO
					if(result==ResultMessage.add_success){
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
				setBounds(100, 100, 250, 150);
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
				confirmButton.setBounds(40, 100, 80, 20);
				getContentPane().add(confirmButton);

				cancelButton = new JButton("取消");
				cancelButton.setBounds(130, 100, 80, 20);
				getContentPane().add(cancelButton);
				
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//既然胡韬不改ID为string，这里只好强制转换
						GoodsVO good=new GoodsController().getGoodsByID(new Long(0).parseLong(goodsSerialNumber.getText()));
//						GoodsVO good=new GoodsVO("001", "hutao", "hutao", 100, 100, "a");//测试
						//在这里应当向frame添加商品列表中的商品
						//如果返回null说明没有此商品
						if(good!=null){
							//添加商品列表
							salesListItems.add(new SalesListItemVO(good,new Integer(0).parseInt(goodsQuantity.getText())));
							//刷新外部类商品列表表格
							Vector newRows=new Vector();
							newRows.add(good.serialNumber);
							newRows.add(good.name);
							newRows.add(goodsQuantity.getText());
							//TODO 这么多价格是个意思？
							newRows.add(new Integer(0).parseInt(goodsQuantity.getText())*good.price);							
							tableData.add(newRows);
							table1.updateUI();
							
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
	
	
	//设置单据编号
	public String setSerialNumber(int type){
		String result=null;
		//自动填充
		
		//日期
		String year=String.valueOf(yearNow);
		String day=String.valueOf(dayNow);
		if(day.length()<2){
			day="0"+day;
		}
		String month=String.valueOf(monthNow);
		if(month.length()<2){
			month="0"+month;
		}
		
		String date=year.substring(2, 4)+month+day;
		
		//单据次序
		String order="";
		
		
		ArrayList<PurchaseReceiptPO> list=new PurchaseController().show();
		int count=0;//计算今天单据的个数
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			PurchaseReceiptPO purchaseReceiptPO = (PurchaseReceiptPO) iterator.next();
			
			if(purchaseReceiptPO.getSerialNumber().contains(date)){
				count++;
			}
			
		}
		
		if(count<10){
			order="0000"+count;
		}else if(count<100){
			order="000"+count;
		}else if(count<1000){
			order="00"+count;
		}else if(count<10000){
			order="0"+count;
		}else if(count<100000){
			order=""+count;
		}else{
			System.out.println("You must be crazy!!");
		}
		
		
		
		if(type==TYPE_PURCHASE){
			result="JHD-"+date+"-"+order;
		}else if(type==TYPE_PURCHASE_BACK){
			result="JHTHD-"+date+"-"+order;
		}else if(type==TYPE_SALES){
			result="XSD-"+date+"-"+order;
		}else if(type==TYPE_SALES_BACK){
			result="XSTHD-"+date+"-"+order;
		}
		
		return result;
		
		
	}

	public static void main(String[] args) {
		SalesmanFrameHelper helper=new SalesmanFrameHelper("addSalesReceipt",null);
		

	}
}
