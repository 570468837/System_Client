package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import VO.AccountVO;
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
	private ReceiptPanel receiptPanel = new ReceiptPanel(this) ;
	private InfoPanel infoPanel = new InfoPanel(this) ;
	
	public FinanceFrame(){
		super() ;
		theFrame = this ;
		this.setSize(1000, 600);
//		this.setTitle("welcome");
		this.setLocationRelativeTo(null);
		this.setLayout(null); 
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		accountPanel.setVisible(false);
		
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
		
		public AccountPanel(JFrame theFrame){
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(200, 100, 150, 255));
			this.setLayout(null);
			this.setVisible(true);
			theFrame.add(this) ;
			
			JLabel accountAdd = new JLabel("添加账户",JLabel.CENTER);
			accountAdd.setBounds(100, 23, 120, 50);
			this.add(accountAdd) ;
			accountAdd.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					new AddAccount();
				}
			});
			
			JLabel accountDelet = new JLabel("删除账户",JLabel.CENTER) ;
			accountDelet.setBounds(200, 23, 120, 50);
			this.add(accountDelet) ;
			accountDelet.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new DeleteAccount() ;
				}
			});
			
			JLabel accountFind = new JLabel("查找账户",JLabel.CENTER) ;
			accountFind.setBounds(300,23,120,50) ;
			this.add(accountFind) ;
			accountFind.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new FindAccount() ;
				}
			});
			
			
			String[] columnNames = {"名称","金额"} ;
			String[][] data = {{"张三","4000"},{"李四","2000"},{"as","dsd"},{"jsdf","dj"},{"jdsk","dfj"}};
			JTable accountTable = new JTable(data,columnNames );
			accountTable.setBackground(Color.white);
			JScrollPane scrollPane = new JScrollPane(accountTable) ;
			scrollPane.setBounds(80,74, 700,400);
			accountTable.setFillsViewportHeight(true);
			this.add(scrollPane) ;
		}
	}
	class AddAccount extends JFrame{
			private JPanel addAccountPane;
			private JTextField nameField;
			private JTextField balanceField;
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
				    	if(!(name.equals("") || balance.equals(""))){
				    		/*
				    		 * 调用addAccount方法
				    		 */
//				    		fController.addAccount(new AccountVO(getName,Double.parseDouble(getBalance))) ;
//				    		System.out.println(this.getClass()) ;
				    		dispose();
			     		}}
					
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
	     		  		fController.deletAccount(new AccountVO(name,0)) ;
				    	dispose();
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
	class FindAccount extends JFrame{

		private JPanel findAccountPane;
		private JTextField nameField;

		/**
		 * Create the frame.
		 */
		public FindAccount() {
			this.setTitle("查找账户");
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(500, 200, 307, 220);
			findAccountPane = new JPanel();
			findAccountPane.setBorder(new EmptyBorder(5, 5, 1, 5));
			setContentPane(findAccountPane);
			findAccountPane.setLayout(null);
			
			JLabel nameLabel = new JLabel("账户名称：");
			nameLabel.setBounds(22, 36, 100, 15);
			findAccountPane.add(nameLabel);
			
			nameField = new JTextField();
			nameField.setBounds(108, 34, 114, 18);
			findAccountPane.add(nameField);
			nameField.setColumns(10);
			
			JButton findButton = new JButton("确定");
			findButton.setBounds(22, 94, 93, 23);
			findAccountPane.add(findButton);
			findButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					String nameOfAccount = nameField.getText() ;
					if(!nameOfAccount.equals("")){
						fController.findAccount(nameOfAccount) ;
						dispose() ;
					}
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(153, 94, 93, 23);
			findAccountPane.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
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
		public ReceiptPanel (JFrame theFrame){
			super() ;
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(200, 100, 150, 255));
			this.setLayout(null);
			theFrame.add(this) ;
			makeCollectionPane = new MakeCollectionOrPayment() ;
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


		private JTextField numberField;
		private JTextField nameOfCustomeField;
		private JTextField nameOfCustomerField;

		/**
		 * Create the frame.
		 */
		public MakeCollectionOrPayment() {
			setBounds(120, 100, 500, 380);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
//			setContentPane(contentPane);
			this.setLayout(null);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			this.add(numberLabel);
			
			JLabel nameOfCustomeLabel = new JLabel("客户名称：");
			nameOfCustomeLabel.setBounds(39, 99, 100, 15);
			this.add(nameOfCustomeLabel);
			
			JLabel nameOfUserLabel = new JLabel("操作员：");
			nameOfUserLabel.setBounds(256, 230, 100, 15);
			this.add(nameOfUserLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总额汇总：");
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			this.add(sumOfMoneyLabel);
			
			numberField = new JTextField();
			numberField.setBounds(129, 96, 100, 21);
			this.add(numberField);
			numberField.setColumns(10);
			
			nameOfCustomeField = new JTextField();
			nameOfCustomeField.setBounds(129, 39, 135, 21);
			this.add(nameOfCustomeField);
			nameOfCustomeField.setColumns(10);
			
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
			
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(129, 181, 100, 21);
			this.add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(235, 184, 21, 15);
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
					/*
					 * 调用逻辑
					 */
					numberField.setText("");
					nameOfCustomeField.setText("");;
					nameOfCustomerField.setText("");
					supplierRadioButton.setSelected(false);
					retailerRadioButton.setSelected(false);
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			this.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					numberField.setText("");
					nameOfCustomeField.setText("");
					nameOfCustomerField.setText("");
					supplierRadioButton.setSelected(false);
					retailerRadioButton.setSelected(false);
				}
			});
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
    		
    		String[] columnNames = {"银行账户","转账金额","备注"} ;
    		String[][] data = {{"dsf","230","dafs"},{"dasd","8760","joe"}} ;
    		table = new JTable(data,columnNames);
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
					/*
					 * 调用逻辑
					 */
					dispose() ;
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

    class MakeCash extends JPanel{


		private JTextField nameOfAccountField;
		private JTextField numberField;
		private JTextField sumOfMoneyField;

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
					/*
					 * 调用逻辑
					 */
					nameOfAccountField.setText("");
					numberField.setText("");
					sumOfMoneyField.setText("");
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			this.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					nameOfAccountField.setText("");
					numberField.setText("");
					sumOfMoneyField.setText("");
				}
			});
		}

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
					/*
					 * 调用逻辑
					 */
					dispose() ;
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
    		
    		String[] columnNames = {"条目名","金额","备注"} ;
    		String[][] data = {{"dsf","230","dafs"},{"dasd","8760","joe"}} ;
    		table = new JTable(data,columnNames);
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
	
	/*
	 * 查看报表的panel
	 */
	class InfoPanel extends JPanel{
		SaleDetailPanel saleDetailPanel ;
		SaleProcessPanel saleProcessPanel ;
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
	    	
	    	
	    	JLabel saleDetailLabel = new JLabel("销售明细表",JLabel.CENTER);
			saleDetailLabel.setBounds(100, 23, 120, 50);
			this.add(saleDetailLabel) ;
			saleDetailLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(true);
					saleProcessPanel.setVisible(false);
//					new SaleDetailPanel() ;
				}
			});
			
			
			JLabel saleProcessLabel = new JLabel("经营里程表",JLabel.CENTER) ;
			saleProcessLabel.setBounds(200, 23, 120, 50);
			this.add(saleProcessLabel) ;
			saleProcessLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(false);
					saleProcessPanel.setVisible(true);
				}
			});
			
			JLabel saleConditionLabel = new JLabel("经营情况表",JLabel.CENTER) ;
			saleConditionLabel.setBounds(300,23,120,50) ;
			this.add(saleConditionLabel) ;
//			JLabel.
			
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
	
	
	public static void main(String[] marg){
		new FinanceFrame() ;
	}
}
