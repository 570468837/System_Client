package presentation;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Config.UserSort;
import ResultMessage.ResultMessage;
import VO.AccountVO;
import VO.CaseListItemVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.TransferListItemVO;
import VO.UserVO;
import businesslogicservice.FinanceBLService.FinanceController;

/*
 * 财务人员界面
 * @author shengyu
 */
public class FinanceFrame extends JFrame{
	private FinanceController fController = new FinanceController() ;
	private JFrame theFrame ;
	private JLabel exitButton,mangeAccountLabel,infoLabel,makeReceiptLabel; 
	private AccountPanel accountPanel = new AccountPanel(this) ;
	private UserVO user ;
	private ReceiptPanel receiptPanel = new ReceiptPanel(this,user) ;
	private InfoPanel infoPanel = new InfoPanel(this) ;
	
	public FinanceFrame(UserVO uservo){
		
		super() ;
		theFrame = this ;
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null); 
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		accountPanel.setVisible(true);
		
		user = uservo ;
		
		exitButton = new JLabel("X",JLabel.CENTER) ;
		exitButton.setBounds(950, 0, 50, 50);
		exitButton.setFont(new Font("default", 1, 20));
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			} 
		});
		this.add(exitButton) ;

		mangeAccountLabel = new JLabel("账户管理",JLabel.CENTER) ;
		mangeAccountLabel.setBounds(40, 100, 100, 50);
		mangeAccountLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
//				System.out.println("click") ;
				accountPanel.setVisible(true);
				receiptPanel.setVisible(false);
				infoPanel.setVisible(false);
			}
		});
		this.add(mangeAccountLabel) ;
		
		makeReceiptLabel = new JLabel("制定单据",JLabel.CENTER) ;
		makeReceiptLabel.setBounds(40, 160, 100, 50);
		this.add(makeReceiptLabel) ;
		makeReceiptLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				receiptPanel.setVisible(true);
				accountPanel.setVisible(false);
				infoPanel.setVisible(false);
			}
		});
		
		infoLabel = new JLabel("查看报表",JLabel.CENTER) ;
		infoLabel.setBounds(40, 220, 100, 50);
		this.add(infoLabel) ;
		infoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				receiptPanel.setVisible(false);
				accountPanel.setVisible(false);
				infoPanel.setVisible(true);
			}
		});
		
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	
	/*
	 * 账户管理的panel
	 */
	class AccountPanel extends JPanel{
		JLabel addAccount,deletAccount,updatAccount,findAccount;
		JTable accountTable ;
		JScrollPane scrollPane ;
		
		public AccountPanel(JFrame theFrame){
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(200, 100, 150, 255));
			this.setLayout(null);
			this.setVisible(true);
			theFrame.add(this) ;
			refreshTable(fController.show()) ;
			this.setVisible(false);
			
			JLabel accountAdd = new JLabel("添加账户",JLabel.CENTER);
			accountAdd.setBounds(100, 23, 120, 50);
			this.add(accountAdd) ;
			accountAdd.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					JFrame frame = new AddAccount();
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							refreshTable(fController.show());
						}
					});
				}
			});
			
			JLabel accountDelet = new JLabel("删除账户",JLabel.CENTER) ;
			accountDelet.setBounds(200, 23, 120, 50);
			this.add(accountDelet) ;
			accountDelet.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					JFrame frame = new DeleteAccount();
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							refreshTable(fController.show());
						}
					});
				}
			});
			JLabel accNameFind = new JLabel("账户名称：",JLabel.CENTER) ;
			accNameFind.setBounds(300,23,120,50) ;
			this.add(accNameFind) ;
			
			JLabel accountFind = new JLabel("查找",JLabel.CENTER) ;
			accountFind.setBounds(500,23,120,50) ;
			this.add(accountFind) ;
			
			JTextField findField = new JTextField() ;
			findField.setBounds(400, 40, 120, 20);
			this.add(findField) ;
			accountFind.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					String name = findField.getText() ;
					if(!name.equals("")){
						refreshTable(fController.findAccount(name));
						findField.setText("");
					}
				}
			});
			
			JLabel refreshLabel = new JLabel("刷新列表",JLabel.CENTER);
			refreshLabel.setBounds(600, 40, 120, 20);
			this.add(refreshLabel) ;
			refreshLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					refreshTable(fController.show());
				}
			});
			
		}
		public void refreshTable(ArrayList<AccountVO> theAccounts){
			String[] columNames = {"账户名称","账户余额"} ;
			ArrayList<AccountVO> accounts = theAccounts ;
			accountTable = new JTable(new MyTableModel(accounts,columNames)) ;
			accountTable.setBackground(Color.white);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    accountTable.setDefaultRenderer(Object.class, render);
			
		    accountTable.getModel().addTableModelListener(new TableModelListener(){     //检测是否有内容更改
		    	public void tableChanged(TableModelEvent e) {     //进行的操作
		    		int row = e.getFirstRow();
		    		AccountVO updAccount = new AccountVO((String)accountTable.getValueAt(row, 0),Double.parseDouble((String)accountTable.getValueAt(row, 1))) ;
		    		fController.updateAccount(updAccount) ;
		    	}
		    }) ;
		    if(scrollPane != null)
		    	scrollPane.setVisible(false);
		    scrollPane = new JScrollPane(accountTable) ;	
    		scrollPane.setBounds(80,74, 700,400);
			accountTable.setFillsViewportHeight(true);
			this.add(scrollPane) ;
			
			
		}
		class MyTableModel extends AbstractTableModel{
			private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
			private String[] column ;

			public MyTableModel(ArrayList<AccountVO> theDatas ,String[] theColumn){
				for(AccountVO theAccount : theDatas){
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theAccount.getName()) ;
					oneRow.add(String.valueOf(theAccount.getBalance())) ;
					datas.add(oneRow) ;
				}
				column = theColumn ;
			}

			@Override
			public String getColumnName(int col)
		     {
		          return column[col];
		     }
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return column.length;
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return datas.size();
			}

			@Override
			public Object getValueAt(int row, int column) {
				// TODO Auto-generated method stub
				return datas.get(row).get(column);
			}
			public boolean isCellEditable(int row, int col) { 
					return false ;
			}
			public void setValueAt(Object value, int row, int col) {  
		        datas.get(row).set(col,  value);
		        fireTableCellUpdated(row, col);  
		    }	
		}
	}
	
	class AddAccount extends JFrame{
			private JPanel addAccountPane;
			private JTextField nameField;
			private JTextField balanceField;
			ResultMessage result ;
			/**
			 * Create the frame.
			 */
			public AddAccount() {
//				this.setLocationRelativeTo(null);
				this.setTitle("增加账户");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(500, 200, 312, 258);
				addAccountPane = new JPanel();
				addAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(addAccountPane);
				addAccountPane.setLayout(null);
				
				JLabel nameLabel = new JLabel("账户名称：");
				nameLabel.setBounds(10, 28, 100, 15);
				addAccountPane.add(nameLabel);
				
				nameField = new JTextField();
				nameField.setBounds(87, 25, 124, 21);
				addAccountPane.add(nameField);
				nameField.setColumns(10);
				
				JLabel balanceLabel = new JLabel("账户余额：");
				balanceLabel.setBounds(10, 91, 100, 15);
				addAccountPane.add(balanceLabel);
				
				balanceField = new JTextField();
				balanceField.setBounds(87, 88, 124, 21);
				addAccountPane.add(balanceField);
				balanceField.setColumns(10);
				
				JButton addButton = new JButton("确定");
				addButton.setBounds(34, 158, 93, 23);
				addAccountPane.add(addButton);
				addButton.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						String name = nameField.getText() ;
			    		String balance =balanceField.getText() ;
				   		if(name.equals("")||balance.equals("")){
				    		new warningDialog("账户信息不完整！") ;
				   		}else{
				    		AccountVO accout = new AccountVO(name,Double.parseDouble(balance)) ;
					   		result = fController.addAccount(accout) ;
					   		if(result.equals(ResultMessage.add_success)){
       				    		new warningDialog("添加成功") ;
       				    		
					   		    dispose();
				    		}else{
				    			new warningDialog("该账户已存在，添加失败") ;
				    		}
				   		}
					}
				});
				
				JButton cancel = new JButton("取消");
				cancel.setBounds(154, 158, 93, 23);
				addAccountPane.add(cancel);
				cancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				
				this.setVisible(true);
			}

	}
	class DeleteAccount extends JFrame{

		private JPanel delAccountPane;
		private JTextField nameField;
		private JButton deleteButton;
		private JButton cancelButton;
		ResultMessage result ;

		/**
		 * Create the frame.
		 */
		public DeleteAccount() {
			this.setTitle("删除账户");
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(500, 200, 307, 220);
			delAccountPane = new JPanel();
			delAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(delAccountPane);
			delAccountPane.setLayout(null);
			
			JLabel nameLabel = new JLabel("账户名称：");
			nameLabel.setBounds(22, 36, 100, 15);
			delAccountPane.add(nameLabel);
			
			nameField = new JTextField();
			nameField.setBounds(108, 34, 114, 18);
			delAccountPane.add(nameField);
			nameField.setColumns(10);
			
			deleteButton = new JButton("确定");
			deleteButton.setBounds(22, 94, 93, 23);
			delAccountPane.add(deleteButton);
			deleteButton.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					String name = nameField.getText() ;
					if(!name.equals("")){
	     		  		result = fController.deletAccount(new AccountVO(name,0)) ;
	     		  		if(result.equals(ResultMessage.delete_success)){
	     		  			new warningDialog("删除成功");
	     		  			dispose();
	     		  			System.out.print("222222");
	     		  		}else{
	     		  			new warningDialog("系统中不存在该账户，删除失败") ;
	     		  			System.out.print("33333333");
	     		  		}
					}else{
						new warningDialog("输入信息不完整") ;
					}
				}
			});
			
			cancelButton = new JButton("取消");
			cancelButton.setBounds(153, 94, 93, 23);
			delAccountPane.add(cancelButton);
			cancelButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					dispose() ;
				}
			});
		}

}

	
	
	/*
	 * 制定单据的panel
	 */
	class ReceiptPanel extends JPanel{
		JLabel makeCollectionOrPaymentLabel,makeCashLabel; 
		MakeCollectionOrPayment makeCollectionPane ;
		MakeCash makeCashPane ;
		public ReceiptPanel (JFrame theFrame,UserVO user){
			super() ;
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(200, 100, 150, 255));
			this.setLayout(null);
			this.setVisible(false);
			theFrame.add(this) ;
			makeCollectionPane = new MakeCollectionOrPayment(user) ;
			this.add(makeCollectionPane) ;
			makeCashPane = new MakeCash(); 
			this.add(makeCashPane);
			makeCashPane.setVisible(false);
			
			makeCollectionOrPaymentLabel = new JLabel("制定收/付款单",JLabel.CENTER) ;
			makeCollectionOrPaymentLabel.setBounds(210, 23, 120, 50);
			this.add(makeCollectionOrPaymentLabel) ;
			makeCollectionOrPaymentLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					makeCollectionPane.setVisible(true);
					makeCashPane.setVisible(false);
				}
			});
			makeCashLabel = new JLabel("制定现金费用单",JLabel.CENTER) ;
			makeCashLabel.setBounds(380, 23, 150, 50);
			this.add(makeCashLabel) ;
			makeCashLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					makeCollectionPane.setVisible(false);
					makeCashPane.setVisible(true);
				}
			});
		}
		
	}
	class MakeCollectionOrPayment extends JPanel{


		private JTextField nameOfCustomerField;
		private JTextField numberOfReceiptField;
		private JTextField sumOfMoneyField;
		ArrayList<ArrayList<String>> tfAccounts = new ArrayList<ArrayList<String>>() ;//储存转账账户
		double sumOfMoney = 0 ;
		/**
		 * Create the frame.
		 */
		public MakeCollectionOrPayment(UserVO user) {
			setBounds(120, 100, 500, 380);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setLayout(null);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			this.add(numberLabel);
			
			JRadioButton c = new JRadioButton("收款单");
			c.setBounds(270, 37, 71, 23);
			this.add(c);
			
			JRadioButton f = new JRadioButton("付款单");
			f.setBounds(340, 37, 71, 23);
			this.add(f);
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(129, 96, 100, 21);
			this.add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			numberOfReceiptField = new JTextField();
			numberOfReceiptField.setBounds(129, 39, 135, 21);
			this.add(numberOfReceiptField);
			numberOfReceiptField.setColumns(10);
			
			
			sumOfMoneyField = new JTextField();
			sumOfMoneyField.setBounds(129, 181, 100, 21);
			this.add(sumOfMoneyField);
			sumOfMoneyField.setColumns(10);
			
			c.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					c.setSelected(true);
					f.setSelected(false);
					numberOfReceiptField.setText(fController.getReceiptNumber("SKD"));
				}
			});
			f.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					c.setSelected(false);
					f.setSelected(true);
					numberOfReceiptField.setText(fController.getReceiptNumber("FKD"));
				}
			});
			
			JLabel nameOfCustomeLabel = new JLabel("客户名称：");
			nameOfCustomeLabel.setBounds(39, 99, 100, 15);
			this.add(nameOfCustomeLabel);
			
			JLabel nameOfUserLabel = new JLabel("操作员：");
			nameOfUserLabel.setBounds(256, 230, 100, 15);
			this.add(nameOfUserLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总额汇总：");
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			this.add(sumOfMoneyLabel);
			
			
			
			
			
			JRadioButton supplierRadioButton = new JRadioButton("供应商");
			supplierRadioButton.setBounds(240, 95, 71, 23);
			this.add(supplierRadioButton);
			
			JRadioButton retailerRadioButton = new JRadioButton("销售商");
			retailerRadioButton.setBounds(310, 95, 71, 23);
			this.add(retailerRadioButton);
			
			supplierRadioButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					supplierRadioButton.setSelected(true);
					retailerRadioButton.setSelected(false);
				}
			});
			retailerRadioButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					supplierRadioButton.setSelected(false);
					retailerRadioButton.setSelected(true);
				}
			});
			

			JButton addTfListButton = new JButton("添加转账账户");
			addTfListButton.setBounds(39, 140, 130, 23);
			this.add(addTfListButton);
			addTfListButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new AddTfListFrame() ;
				}
			});
			
			JButton showTfListButton = new JButton("查看已添加账户") ;
			showTfListButton.setBounds(200, 140, 130, 23);
			this.add(showTfListButton) ;
			showTfListButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new ShowTfListFrame() ;
				}
			});
			
			
			
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(235, 184, 21, 15);
			this.add(yuanLabel);
			
//			JLabel userLabel = new JLabel(user.getUserName());
			JLabel userLabel = new JLabel("当前用户");
			userLabel.setBounds(320, 230, 100, 15);
			this.add(userLabel);
			
			JButton sureButton = new JButton("确定 ") ;
			sureButton.setBounds(80, 270, 100, 23);
			this.add(sureButton) ;
			sureButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String receiptNumber = numberOfReceiptField.getText() ;
					String nameOfCustomer = nameOfCustomerField.getText() ;
					String sumOfMoneys = sumOfMoneyField.getText() ;
					String typeOfCustomer = "" ;
					if(supplierRadioButton.isSelected()){
						typeOfCustomer = "供应商";
					}
					if(retailerRadioButton.isSelected()){
		     			typeOfCustomer = "销售商" ;
					}
					if(receiptNumber.equals("")||nameOfCustomer.equals("")||sumOfMoneys.equals("")||typeOfCustomer.equals("")){
						new warningDialog("信息不完整");
					}else{
						ArrayList<TransferListItemVO> tfList = new ArrayList<TransferListItemVO>() ;
						for(int i = 0;i<tfAccounts.size() ;i++){
							ArrayList<String> account = tfAccounts.get(i) ;
							tfList.add(new TransferListItemVO(account.get(0), Double.parseDouble(account.get(1)),account.get(2))) ;
						}
						ResultMessage result = fController.addCollectionOrPaymentVO(new CollectionOrPaymentVO(receiptNumber,nameOfCustomer,typeOfCustomer,user.getUserName(),tfList,Double.parseDouble(sumOfMoneys))) ;
						new warningDialog("添加成功");
						nameOfCustomerField.setText("");
						numberOfReceiptField.setText("");;
						sumOfMoneyField.setText("");
						supplierRadioButton.setSelected(false);
						retailerRadioButton.setSelected(false);
						f.setSelected(false);
						c.setSelected(false);
						tfAccounts = new ArrayList<ArrayList<String>>() ;
						sumOfMoney = 0 ;
					}
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			this.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					nameOfCustomerField.setText("");
					numberOfReceiptField.setText("");
					sumOfMoneyField.setText("");
					tfAccounts = new ArrayList<ArrayList<String>>() ;
					supplierRadioButton.setSelected(false);
					retailerRadioButton.setSelected(false);
					f.setSelected(false);
					c.setSelected(false);
				}
			});
		}
		class MyTableModel extends AbstractTableModel{
			private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
			private String[] column ={"银行账户","转账金额","备注"} ;

			public MyTableModel(){
				for(ArrayList<String> theAccount : tfAccounts){
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theAccount.get(0)) ;
					oneRow.add(theAccount.get(1)) ;
					oneRow.add(theAccount.get(2)) ;
					datas.add(oneRow) ;
				}
			}

			@Override
			public String getColumnName(int col)
		     {
		          return column[col];
		     }
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return column.length;
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return datas.size();
			}

			@Override
			public Object getValueAt(int row, int column) {
				// TODO Auto-generated method stub
				return datas.get(row).get(column);
			}
			public boolean isCellEditable(int row, int col) { 
					return false ;
			}
			public void setValueAt(Object value, int row, int col) {  
		        datas.get(row).set(col,  value);
		        fireTableCellUpdated(row, col);  
		    }	
		}
	    

	    class ShowTfListFrame extends JFrame{

	    	private JPanel contentPane;
	    	private JTable table;

	    	/**
	    	 * Create the frame.
	    	 */
	    	public ShowTfListFrame() {
	    		this.setVisible(true);
	    		this.setTitle("转账列表");
	    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    		setBounds(500, 200, 350, 300);
	    		contentPane = new JPanel();
	    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    		contentPane.setBackground(Color.yellow);
	    		setContentPane(contentPane);
	    		contentPane.setLayout(null);
	 
	    		table = new JTable(new MyTableModel());
	    		table.setBackground(Color.white);
	    		
	    		JScrollPane jsc = new JScrollPane(table);
	    		jsc.setBounds(0,0,350,170);
	    		jsc.setBackground(Color.green);
	    		contentPane.add(jsc) ;
	    		
	    		JButton sureButton = new JButton("确定");
	    		sureButton.setBounds(110, 200, 100, 30);
	    		contentPane.add(sureButton) ;
	    		sureButton.addMouseListener(new MouseAdapter() {
	    			public void mouseClicked(MouseEvent e){
	    				dispose() ;
	    			}
				});
	    		
	    	}
	}
	

		class AddTfListFrame extends JFrame{


			private JPanel contentPane;
			private JTextField nameOfAccountField;
			private JTextField numOfTfField;
			private JTextField markedField;


			/**
			 * Create the frame.
			 */
			public AddTfListFrame() {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(500, 200, 350, 300);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				this.setVisible(true);
				
				JLabel nameOfAccountLabel = new JLabel("银行账户：");
				nameOfAccountLabel.setBounds(41, 37, 66, 15);
				contentPane.add(nameOfAccountLabel);
				
				nameOfAccountField = new JTextField();
				nameOfAccountField.setBounds(139, 34, 110, 21);
				contentPane.add(nameOfAccountField);
				nameOfAccountField.setColumns(10);
				
				JLabel numOfMoneyLabel = new JLabel("转账金额：");
				numOfMoneyLabel.setBounds(41, 96, 66, 15);
				contentPane.add(numOfMoneyLabel);
				JLabel yuanLabel = new JLabel("元");
				yuanLabel.setBounds(260, 95, 20, 15);
				contentPane.add(yuanLabel) ;
				
				numOfTfField = new JTextField();
				numOfTfField.setBounds(139, 93, 110, 21);
				contentPane.add(numOfTfField);
				numOfTfField.setColumns(10);
				
				JLabel markedLabel = new JLabel("备        注：");
				markedLabel.setBounds(41, 149, 66, 15);
				contentPane.add(markedLabel);
				
				markedField = new JTextField();
				markedField.setBounds(139, 146, 110, 21);
				contentPane.add(markedField);
				markedField.setColumns(10);
				
				JButton sureButton = new JButton("确定");
				sureButton.setBounds(70, 187, 66, 23);
				contentPane.add(sureButton);
				sureButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ArrayList<String> tfAccount = new ArrayList<String>(); 
						String nameOfAccount = nameOfAccountField.getText() ;
						String numOfTf = numOfTfField.getText() ;
						String marked = markedField.getText() ;
						if(nameOfAccount.equals("")||numOfTf.equals("")||marked.equals("")){
							new warningDialog("信息不完整");
						}else{
			    			tfAccount.add(nameOfAccountField.getText()) ;
					    	tfAccount.add(numOfTfField.getText()) ;
					    	tfAccount.add(markedField.getText()) ;
					    	tfAccounts.add(tfAccount) ;
					    	dispose() ;
					    	sumOfMoney += Double.parseDouble(numOfTf) ;
							sumOfMoneyField.setText(String.valueOf(sumOfMoney));
						}
					}
				});
				
				JButton cancelButton = new JButton("取消");
				cancelButton.setBounds(170, 187, 74, 23);
				contentPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose() ;
					}
				});
			}

		}
	}

    class MakeCash extends JPanel{


		private JTextField nameOfAccountField;
		private JTextField numberField;
		private JTextField sumOfMoneyField;
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>() ;//报销条目
		double sumOfMoney = 0 ;
		/**
		 * Create the frame.
		 */
		public MakeCash() {
			setBounds(120, 100, 500, 380);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setLayout(null);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			this.add(numberLabel);
			
			JLabel nameOfAccountLabel = new JLabel("银行账户：");
			nameOfAccountLabel.setBounds(39, 99, 100, 15);
			this.add(nameOfAccountLabel);
			
			JLabel nameOfUserLabel = new JLabel("操作员：");
			nameOfUserLabel.setBounds(256, 230, 100, 15);
			this.add(nameOfUserLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总        额："); 
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			this.add(sumOfMoneyLabel);
			
			nameOfAccountField = new JTextField();
			nameOfAccountField.setBounds(129, 96, 135, 21);
			this.add(nameOfAccountField);
			nameOfAccountField.setColumns(10);
			
			numberField = new JTextField();
			numberField.setBounds(129, 39, 135, 21);
			this.add(numberField);
			numberField.setColumns(10);
			numberField.setText(fController.getReceiptNumber("XJFYD"));
			
			JButton addCaseItemButton = new JButton("添加条目");
			addCaseItemButton.setBounds(50, 140, 100, 23);
			this.add(addCaseItemButton);
			addCaseItemButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new AddCaseItem() ;
				}
			});
			
			JButton showCaseItemButton = new JButton("查看已添加条目") ;
			showCaseItemButton.setBounds(200, 140, 130, 23);
			this.add(showCaseItemButton) ;
			showCaseItemButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new ShowCaseItem();
				}
			});
			
			sumOfMoneyField = new JTextField();
			sumOfMoneyField.setBounds(129, 181, 135, 21);
			this.add(sumOfMoneyField);
			sumOfMoneyField.setColumns(10);
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(270, 184, 21, 15);
			this.add(yuanLabel);
			
			JLabel userLabel = new JLabel("当前操作员");
			userLabel.setBounds(320, 230, 100, 15);
			this.add(userLabel);
			
			JButton sureButton = new JButton("确定 ") ;
			sureButton.setBounds(80, 270, 100, 23);
			this.add(sureButton) ;
			sureButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String account = nameOfAccountField.getText() ;
					String number = numberField.getText() ;
					String sum = sumOfMoneyField.getText() ;
					if(account.equals("")||number.equals("")||sum.equals("")){
						new warningDialog("信息不完整");
					}else{
						ArrayList<CaseListItemVO> cases = new ArrayList<CaseListItemVO>() ;
						for(int i = 0;i<items.size();i++){
							ArrayList<String> thecase = items.get(i) ;
							CaseListItemVO theCase = new CaseListItemVO(thecase.get(0), Double.parseDouble(thecase.get(1)), thecase.get(2)) ;
							cases.add(theCase) ;
						}
						CashVO cash = new CashVO(number,userLabel.getText(),account,cases,Double.parseDouble(sum)) ;
						ResultMessage result = fController.addCash(cash) ;

					    new warningDialog("添加成功");
						
						nameOfAccountField.setText("");
						numberField.setText("");
						sumOfMoneyField.setText("");
						items = new ArrayList<ArrayList<String>>() ;
						sumOfMoney = 0 ;
						numberField.setText(fController.getReceiptNumber("XJFYD"));
					}
					
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			this.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					items = new ArrayList<ArrayList<String>>() ;
					sumOfMoney = 0 ;
					nameOfAccountField.setText("");
					sumOfMoneyField.setText("");
				}
			});
		}

		
		
		
		   class AddCaseItem extends JFrame{


				private JPanel contentPane;
				private JTextField nameOfCaseField;
				private JTextField numOfTfField;
				private JTextField markedField;


				/**
				 * Create the frame.
				 */
				public AddCaseItem() {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setBounds(500, 200, 350, 300);
					contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(contentPane);
					contentPane.setLayout(null);
					this.setVisible(true);
					
					JLabel nameOfCase = new JLabel("条目名：");
					nameOfCase.setBounds(41, 37, 66, 15);
					contentPane.add(nameOfCase);
					
					nameOfCaseField = new JTextField();
					nameOfCaseField.setBounds(139, 34, 110, 21);
					contentPane.add(nameOfCaseField);
					nameOfCaseField.setColumns(10);
					
					JLabel numOfMoneyLabel = new JLabel("金    额：");
					numOfMoneyLabel.setBounds(41, 96, 66, 15);
					contentPane.add(numOfMoneyLabel);
					JLabel yuanLabel = new JLabel("元");
					yuanLabel.setBounds(260, 95, 20, 15);
					contentPane.add(yuanLabel) ;
					
					numOfTfField = new JTextField();
					numOfTfField.setBounds(139, 93, 110, 21);
					contentPane.add(numOfTfField);
					numOfTfField.setColumns(10);
					
					JLabel markedLabel = new JLabel("备    注：");
					markedLabel.setBounds(41, 149, 66, 15);
					contentPane.add(markedLabel);
					
					markedField = new JTextField();
					markedField.setBounds(139, 146, 110, 21);
					contentPane.add(markedField);
					markedField.setColumns(10);
					
					JButton sureButton = new JButton("确定");
					sureButton.setBounds(70, 187, 66, 23);
					contentPane.add(sureButton);
					sureButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String nameOfCase = nameOfCaseField.getText() ;
							String money = numOfTfField.getText() ;
							String marked = markedField.getText() ;
							if(nameOfCase.equals("")||money.equals("")||marked.equals("")){
								new warningDialog("信息不完整");
							}else{
								sumOfMoney += Double.parseDouble(money) ;
								sumOfMoneyField.setText(String.valueOf(sumOfMoney));
								ArrayList<String> item = new ArrayList<String>() ;
								item.add(nameOfCase) ;
								item.add(money) ;
								item.add(marked) ;
								items.add(item) ;
								dispose() ;
							}
						}
					});
					
					JButton cancelButton = new JButton("取消");
					cancelButton.setBounds(170, 187, 74, 23);
					contentPane.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dispose() ;
						}
					});
				}

			}
	
    
			class ShowCaseItem extends JFrame{

		    	private JPanel contentPane;
		    	private JTable table;

		    	/**
		    	 * Create the frame.
		    	 */
		    	public ShowCaseItem() {
		    		this.setVisible(true);
		    		this.setTitle("条目清单");
		    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    		setBounds(500, 200, 350, 300);
		    		contentPane = new JPanel();
		    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    		contentPane.setBackground(Color.yellow);
		    		setContentPane(contentPane);
		    		contentPane.setLayout(null);
		    		
		    		table = new JTable(new MyTableModel());
		    		table.setBackground(Color.white);
		    		
		    		JScrollPane jsc = new JScrollPane(table);
		    		jsc.setBounds(0,0,350,170);
		    		jsc.setBackground(Color.green);
		    		contentPane.add(jsc) ;
		    		
		    		JButton sureButton = new JButton("确定");
		    		sureButton.setBounds(110, 200, 100, 30);
		    		contentPane.add(sureButton) ;
		    		sureButton.addMouseListener(new MouseAdapter() {
		    			public void mouseClicked(MouseEvent e){
		    				dispose() ;
		    			}
					});
		    		
		    	}
		}
			
			class MyTableModel extends AbstractTableModel{
				private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
				private String[] column ={"条目名","金额","备注"} ;

				public MyTableModel(){
					for(ArrayList<String> item : items){
						ArrayList<Object> oneRow = new ArrayList<Object>() ;
						oneRow.add(item.get(0)) ;
						oneRow.add(item.get(1)) ;
						oneRow.add(item.get(2)) ;
						datas.add(oneRow) ;
					}
				}

				@Override
				public String getColumnName(int col)
			     {
			          return column[col];
			     }
				public int getColumnCount() {
					// TODO Auto-generated method stub
					return column.length;
				}

				@Override
				public int getRowCount() {
					// TODO Auto-generated method stub
					return datas.size();
				}

				@Override
				public Object getValueAt(int row, int column) {
					// TODO Auto-generated method stub
					return datas.get(row).get(column);
				}
				public boolean isCellEditable(int row, int col) { 
						return false ;
				}
				public void setValueAt(Object value, int row, int col) {  
			        datas.get(row).set(col,  value);
			        fireTableCellUpdated(row, col);  
			    }	
			}
    }
 

	
	/*
	 * 查看报表的panel
	 */
	class InfoPanel extends JPanel{
		SaleDetailPanel saleDetailPanel ;
		SaleProcessPanel saleProcessPanel ;
		SaleConditionPanel saleConditionPanel ;
		public InfoPanel(JFrame theFrame){
			super() ;
	    	this.setBounds(140, 25, 835, 550);
	    	this.setBackground(new Color(200, 100, 150, 255));
	    	this.setLayout(null);
	    	theFrame.add(this) ;
	    	saleDetailPanel  = new SaleDetailPanel() ;
	    	this.add(saleDetailPanel) ;
	    	saleProcessPanel = new SaleProcessPanel() ;
	    	this.add(saleProcessPanel);
	    	saleProcessPanel.setVisible(false);
	    	saleConditionPanel = new SaleConditionPanel() ;
	    	this.add(saleConditionPanel) ;
	    	saleConditionPanel.setVisible(false);
	    	this.setVisible(false);
	    	
	    	
	    	JLabel saleDetailLabel = new JLabel("销售明细表",JLabel.CENTER);
			saleDetailLabel.setBounds(100, 23, 120, 50);
			this.add(saleDetailLabel) ;
			saleDetailLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(true);
					saleProcessPanel.setVisible(false);
					saleConditionPanel.setVisible(false);
				}
			});
			
			
			JLabel saleProcessLabel = new JLabel("经营里程表",JLabel.CENTER) ;
			saleProcessLabel.setBounds(200, 23, 120, 50);
			this.add(saleProcessLabel) ;
			saleProcessLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(false);
					saleProcessPanel.setVisible(true);
					saleConditionPanel.setVisible(false);
				}
			});
			
			JLabel saleConditionLabel = new JLabel("经营情况表",JLabel.CENTER) ;
			saleConditionLabel.setBounds(300,23,120,50) ;
			this.add(saleConditionLabel) ;
			saleConditionLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(false);
					saleProcessPanel.setVisible(false);
					saleConditionPanel.setVisible(true);
				}
			});
			
			this.repaint();
	    }
	}
	class SaleDetailPanel extends JPanel{

		private JTextField beginTimeField;
		private JTextField endTimeField;
		private JTextField nameOfGoodField;
		private JTextField nameOfCustomerField;
		private JTextField nameOfUserField;
		private JTextField storageField;
		private JTable saleDetailTable;

		/**
		 * Create the panel.
		 */
		public SaleDetailPanel() {
			super();
			setLayout(null);
			this.setBackground(Color.LIGHT_GRAY);
			this.setBounds(80,74, 700,400);
			
			JLabel timesLabel = new JLabel("时间区间：");
			timesLabel.setBounds(44, 24, 100, 15);
			add(timesLabel);
			
			beginTimeField = new JTextField() ;
			beginTimeField.setBounds(187, 21, 122, 21);
			add(beginTimeField);
			beginTimeField.setColumns(10);
			
			JLabel beginTimeLabel = new JLabel("起始时间");
			beginTimeLabel.setBounds(123, 22, 100, 18);
			add(beginTimeLabel);
			
			JLabel endTimeLabel = new JLabel("截止时间");
			endTimeLabel.setBounds(351, 24, 100, 15);
			add(endTimeLabel);
			
			endTimeField = new JTextField();
			endTimeField.setColumns(10);
			endTimeField.setBounds(415, 21, 122, 21);
			add(endTimeField);
			
			JLabel nameOfGoodLabel = new JLabel("商品名称");
			nameOfGoodLabel.setBounds(123, 51, 100, 15);
			add(nameOfGoodLabel);
			
			nameOfGoodField = new JTextField();
			nameOfGoodField.setBounds(187, 52, 122, 21);
			add(nameOfGoodField);
			nameOfGoodField.setColumns(10);
			
			JLabel nameOfCustomerLabel = new JLabel("客户名称");
			nameOfCustomerLabel.setBounds(351, 55, 100, 15);
			add(nameOfCustomerLabel);
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(415, 52, 122, 21);
			add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel nameOfUserLabel = new JLabel("业务员");
			nameOfUserLabel.setBounds(123, 86, 100, 15);
			add(nameOfUserLabel);
			
			nameOfUserField = new JTextField();
			nameOfUserField.setBounds(187, 83, 122, 21);
			add(nameOfUserField);
			nameOfUserField.setColumns(10);
			
			JLabel storageLabel = new JLabel("仓库");
			storageLabel.setBounds(351, 86, 100, 15);
			add(storageLabel);
			
			storageField = new JTextField();
			storageField.setBounds(415, 83, 122, 21);
			add(storageField);
			storageField.setColumns(10);
			
			String[] columnNames = {"单据编号","客户","业务员","操作员","仓库","出货商品清单","折让前总额","折让","使用代金券金额","折让后总额","备注"};
			
			String[][] data = {{"111","sad","sd","sd","3","f","20","10","1","9","fg"},
					{"75","kk","ll","kkk","uu","cx","fda","ghf","fgdd","gfd","fgdf"}};
			
			saleDetailTable = new JTable(data,columnNames);
//			saleDetailTable.setBounds(54, 121, 597, 253);
			saleDetailTable.setFillsViewportHeight(true);
			
			JScrollPane jsc = new JScrollPane(saleDetailTable) ;
			jsc.setBounds(54, 121, 597, 253);
			add(jsc);
			
			JButton sureButton = new JButton("确定");
			sureButton.setBounds(578, 24, 93, 23);
			add(sureButton);
			sureButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					/*
					 * 调用方法
					 */
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(578, 68, 93, 23);
			add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					beginTimeField.setText("");
					endTimeField.setText("");
					nameOfGoodField.setText("");
					nameOfCustomerField.setText("");
					nameOfUserField.setText("");
					storageField.setText("");
					
				}
			});

		}

	}
	class SaleProcessPanel extends JPanel{
		private JTextField beginTimeField;
		private JTextField endTimeField;
		private JTextField nameOfCustomerField;
		private JTextField nameOfUserField;
		private JTextField storageField;
		private JTable table;

		/**
		 * Create the panel.
		 */
		public SaleProcessPanel() {
			setLayout(null);
			this.setBounds(80,74, 700,400);
			this.setBackground(Color.LIGHT_GRAY);
			
			JLabel timesLabel = new JLabel("时间区间");
			timesLabel.setBounds(44, 59, 69, 15);
			add(timesLabel);
			
			beginTimeField = new JTextField();
			beginTimeField.setBounds(188, 56, 122, 21);
			add(beginTimeField);
			beginTimeField.setColumns(10);
			
			JLabel beginTimeLabel = new JLabel("起始时间");
			beginTimeLabel.setBounds(124, 57, 54, 18);
			add(beginTimeLabel);
			
			JLabel endTimeLabel = new JLabel("截止时间");
			endTimeLabel.setBounds(351, 59, 54, 15);
			add(endTimeLabel);
			
			endTimeField = new JTextField();
			endTimeField.setColumns(10);
			endTimeField.setBounds(415, 56, 122, 21);
			add(endTimeField);
			
			JLabel nameOfCustomerLabel = new JLabel("客户名称");
			nameOfCustomerLabel.setBounds(44, 90, 54, 15);
			add(nameOfCustomerLabel);
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(124, 87, 77, 21);
			add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel nameOfUserLabel = new JLabel("业务员");
			nameOfUserLabel.setBounds(238, 90, 54, 15);
			add(nameOfUserLabel);
			
			nameOfUserField = new JTextField();
			nameOfUserField.setBounds(293, 87, 77, 21);
			add(nameOfUserField);
			nameOfUserField.setColumns(10);
			
			JLabel storageLabel = new JLabel("仓库");
			storageLabel.setBounds(415, 90, 54, 15);
			add(storageLabel);
			
			storageField = new JTextField();
			storageField.setBounds(459, 87, 77, 21);
			add(storageField);
			storageField.setColumns(10);
			String[] columnNames = {"单据编号","客户","业务员","操作员","仓库","出货商品清单","折让前总额","折让","使用代金券金额","折让后总额","备注"};
			
			String[][] data = {{"111","sad","sd","sd","3","f","20","10","1","9","fg"},
					{"75","kk","ll","kkk","uu","cx","fda","ghf","fgdd","gfd","fgdf"}};
			table = new JTable(data,columnNames);
			table.setBounds(54, 121, 597, 253);
			add(table);
			
			JButton sureButton = new JButton("确定");
			sureButton.setBounds(578, 35, 93, 23);
			add(sureButton);
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(578, 68, 93, 23);
			add(cancelButton);
			
			JLabel typeOfReceiptLabel = new JLabel("单据类型");
			typeOfReceiptLabel.setBounds(44, 25, 54, 15);
			add(typeOfReceiptLabel);
			
			JRadioButton saleRadioButton = new JRadioButton("销售类");
			saleRadioButton.setBounds(123, 21, 77, 23);
			add(saleRadioButton);
			
			JRadioButton purchaseRadioButton = new JRadioButton("进货类");
			purchaseRadioButton.setBounds(222, 21, 77, 23);
			add(purchaseRadioButton);
			
			JRadioButton financeRadioButton = new JRadioButton("财务类");
			financeRadioButton.setBounds(328, 21, 77, 23);
			add(financeRadioButton);
			
			JRadioButton commodityRadioButton = new JRadioButton("库存类");
			commodityRadioButton.setBounds(432, 21, 77, 23);
			add(commodityRadioButton);

		}
}
    class SaleConditionPanel extends JPanel{
    	private JTextField beginTimeField;
    	private JTextField endTimeField;
    	private JTextField sumOfComeInField;//总收入
    	private JTextField discountField;//总折让
    	private JTextField saleComeInField;//销售收入
    	private JTextField overFlowField;//报溢
    	private JTextField changeCostField;//成本调价
    	private JTextField diffOfInAndOutField;//进退货差价
    	private JTextField voucherField;//代金券与实际金额差价
    	private JTextField sumOfCostField;//总支出
    	private JTextField saleCostField;//销售成本
    	private JTextField breakageCostField;//报损支出
    	private JTextField sendCostField;//商品赠送
    	private JTextField profitField;//总利润

    	/**
    	 * Create the panel.
    	 */
    	public SaleConditionPanel() {
    		setLayout(null);
    		setBounds(80,74, 700,400);
    		
    		JLabel timesLabel = new JLabel("时间区间：");
    		timesLabel.setBounds(44, 21, 68, 15);
    		add(timesLabel);
    		
    		JLabel beginTiemLabel = new JLabel("起始时间");
    		beginTiemLabel.setBounds(122, 21, 68, 15);
    		add(beginTiemLabel);
    		
    		beginTimeField = new JTextField();
    		beginTimeField.setBounds(200, 18, 84, 21);
    		add(beginTimeField);
    		beginTimeField.setColumns(10);
    		
    		JLabel endTimeLabel = new JLabel("截止时间");
    		endTimeLabel.setBounds(319, 21, 68, 15);
    		add(endTimeLabel);
    		
    		endTimeField = new JTextField();
    		endTimeField.setBounds(397, 18, 84, 21);
    		add(endTimeField);
    		endTimeField.setColumns(10);
    		
    		JPanel comeInPanel = new JPanel();
    		comeInPanel.setBounds(103, 61, 538, 126);
    		comeInPanel.setBackground(Color.red);
    		add(comeInPanel);
    		comeInPanel.setLayout(null);
    		
    		JLabel sumOfComeInLabel = new JLabel("总收入");
    		sumOfComeInLabel.setBounds(10, 21, 54, 15);
    		comeInPanel.add(sumOfComeInLabel);
    		
    		sumOfComeInField = new JTextField();
    		sumOfComeInField.setBounds(76, 18, 66, 21);
    		comeInPanel.add(sumOfComeInField);
    		sumOfComeInField.setColumns(10);
    		
    		JLabel discountLabel = new JLabel("折让");
    		discountLabel.setBounds(167, 21, 54, 15);
    		comeInPanel.add(discountLabel);
    		
    		discountField = new JTextField();
    		discountField.setBounds(212, 18, 66, 21);
    		comeInPanel.add(discountField);
    		discountField.setColumns(10);
    		
    		JLabel saleComeInLabel = new JLabel("销售收入");
    		saleComeInLabel.setBounds(23, 60, 54, 15);
    		comeInPanel.add(saleComeInLabel);
    		
    		saleComeInField = new JTextField();
    		saleComeInField.setBounds(10, 95, 66, 21);
    		comeInPanel.add(saleComeInField);
    		saleComeInField.setColumns(10);
    		
    		JLabel overFlowLabel = new JLabel("商品报溢");
    		overFlowLabel.setBounds(92, 60, 72, 15);
    		comeInPanel.add(overFlowLabel);
    		
    		overFlowField = new JTextField();
    		overFlowField.setBounds(92, 95, 66, 21);
    		comeInPanel.add(overFlowField);
    		overFlowField.setColumns(10);
    		
    		JLabel changCostLabel = new JLabel("成本调价");
    		changCostLabel.setBounds(197, 60, 72, 15);
    		comeInPanel.add(changCostLabel);
    		
    		changeCostField = new JTextField();
    		changeCostField.setBounds(197, 95, 66, 21);
    		comeInPanel.add(changeCostField);
    		changeCostField.setColumns(10);
    	
    		
    		JLabel diffOfInAndOutLabel = new JLabel("进货退货差价");
    		diffOfInAndOutLabel.setBounds(293, 60, 96, 15);
    		comeInPanel.add(diffOfInAndOutLabel);
    		
    		diffOfInAndOutField = new JTextField();
    		diffOfInAndOutField.setBounds(304, 95, 66, 21);
    		comeInPanel.add(diffOfInAndOutField);
    		diffOfInAndOutField.setColumns(10);
    		
    		JLabel voucherLabel = new JLabel("代金券与实际收款差额");
    		voucherLabel.setBounds(399, 60, 140, 15);
    		comeInPanel.add(voucherLabel);
    		
    		voucherField = new JTextField();
    		voucherField.setBounds(425, 95, 66, 21);
    		comeInPanel.add(voucherField);
    		voucherField.setColumns(10);
    		
    		JPanel sumOfCostPanel = new JPanel();
    		sumOfCostPanel.setBounds(103, 207, 538, 101);
    		sumOfCostPanel.setBackground(Color.yellow);
    		add(sumOfCostPanel);
    		sumOfCostPanel.setLayout(null);
    		
    		JLabel sumOfCostLabel = new JLabel("总支出");
    		sumOfCostLabel.setBounds(10, 18, 54, 15);
    		sumOfCostPanel.add(sumOfCostLabel);

    		sumOfCostField = new JTextField();
    		sumOfCostField.setBounds(77, 15, 66, 21);
    		sumOfCostPanel.add(sumOfCostField);
    		sumOfCostField.setColumns(10);
    		
    		JLabel saleCostLabel = new JLabel("销售支出");
    		saleCostLabel.setBounds(87, 46, 54, 15);
    		sumOfCostPanel.add(saleCostLabel);
    		
    		saleCostField = new JTextField();
    		saleCostField.setBounds(77, 70, 66, 21);
    		sumOfCostPanel.add(saleCostField);
    		saleCostField.setColumns(10);
    		
    		JLabel breakageCostLabel = new JLabel("商品报损");
    		breakageCostLabel.setBounds(212, 46, 54, 15);
    		sumOfCostPanel.add(breakageCostLabel);
    		
    		breakageCostField = new JTextField();
    		breakageCostField.setBounds(200, 70, 66, 21);
    		sumOfCostPanel.add(breakageCostField);
    		breakageCostField.setColumns(10);
    		
    		JLabel sendCostLabel = new JLabel("商品赠出");
    		sendCostLabel.setBounds(340, 46, 54, 15);
    		sumOfCostPanel.add(sendCostLabel);
    		
    		sendCostField = new JTextField();
    		sendCostField.setBounds(328, 70, 66, 21);
    		sumOfCostPanel.add(sendCostField);
    		sendCostField.setColumns(10);
    		
    		JPanel profitPanel = new JPanel();
    		profitPanel.setBounds(103, 326, 538, 35);
    		profitPanel.setBackground(Color.green);
    		add(profitPanel);
    		profitPanel.setLayout(null);
    		
    		JLabel profitLabel = new JLabel("利润");
    		profitLabel.setBounds(10, 10, 54, 15);
    		profitPanel.add(profitLabel);
    		
    		profitField = new JTextField();
    		profitField.setBounds(74, 7, 66, 21);
    		profitPanel.add(profitField);
    		profitField.setColumns(10);
    		
    		JLabel typeOfComeInLabel = new JLabel("收入类");
    		typeOfComeInLabel.setBounds(24, 109, 54, 15);
    		add(typeOfComeInLabel);
    		
    		JLabel typeOfCostLabel = new JLabel("支出类");
    		typeOfCostLabel.setBounds(24, 254, 54, 15);
    		add(typeOfCostLabel);
    		
    		JLabel typeOfProfitLabel = new JLabel("利润");
    		typeOfProfitLabel.setBounds(24, 336, 54, 15);
    		add(typeOfProfitLabel);
    	}
}

    class warningDialog extends JDialog{
		public warningDialog(String warnings){
			this.setSize(284, 158);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setModal(true);
			
			JLabel warningLabel = new JLabel(warnings,JLabel.CENTER);
			warningLabel.setFont(new Font("宋体",Font.BOLD,14));
			
			this.add(warningLabel);
		}
	}
	public static void main(String[] marg){
		new FinanceFrame(new UserVO("shengyu", "123", UserSort.Finance, 1)) ;
	}
}
