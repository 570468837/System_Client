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
			this.setBackground(new Color(200, 100, 150, 250));
			this.setLayout(null);
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
				cancel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						dispose() ;
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
			});/*(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					dispose() ;
				}
			});*/
		}

	}
	
	
	/*
	 * 制定单据的panel
	 */
	class ReceiptPanel extends JPanel{
		JLabel makeCollectionLabel,makePaymentLabel,makeCashLabel;
		public ReceiptPanel (JFrame theFrame){
			super() ;
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(200, 100, 150, 250));
			this.setLayout(null);
			this.setVisible(true);
			theFrame.add(this) ;
			
			makeCollectionLabel = new JLabel("制定收款单",JLabel.CENTER) ;
			makeCollectionLabel.setBounds(100, 23, 120, 50);
			this.add(makeCollectionLabel) ;
			makeCollectionLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new MakeCollection() ;
				}
			});
			
			makePaymentLabel = new JLabel("制定付款单",JLabel.CENTER) ;
			makePaymentLabel.setBounds(200, 23, 120, 50);
			this.add(makePaymentLabel) ;
			makePaymentLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new MakePayment();
				}
			});
			
			makeCashLabel = new JLabel("制定现金费用单",JLabel.CENTER) ;
			makeCashLabel.setBounds(300, 23, 150, 50);
			this.add(makeCashLabel) ;
		}
		
	}
	class MakeCollection extends JFrame{


		private JPanel contentPane;
		private JTextField numberField;
		private JTextField nameOfCustomeField;
		private JTextField nameOfCustomerField;

		/**
		 * Create the frame.
		 */
		public MakeCollection() {
			this.setTitle("制定收款单");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(450, 150, 450, 380);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			this.setVisible(true);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			contentPane.add(numberLabel);
			
			JLabel nameOfCustomeLabel = new JLabel("客户名称：");
			nameOfCustomeLabel.setBounds(39, 99, 100, 15);
			contentPane.add(nameOfCustomeLabel);
			
			JLabel nameOfUserLabel = new JLabel("操作员：");
			nameOfUserLabel.setBounds(256, 215, 100, 15);
			contentPane.add(nameOfUserLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总额汇总：");
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			contentPane.add(sumOfMoneyLabel);
			
			numberField = new JTextField();
			numberField.setBounds(129, 96, 100, 21);
			contentPane.add(numberField);
			numberField.setColumns(10);
			
			nameOfCustomeField = new JTextField();
			nameOfCustomeField.setBounds(129, 39, 135, 21);
			contentPane.add(nameOfCustomeField);
			nameOfCustomeField.setColumns(10);
			
			JRadioButton supplierRadioButton = new JRadioButton("供应商");
			supplierRadioButton.setBounds(240, 95, 71, 23);
			contentPane.add(supplierRadioButton);
			
			JRadioButton retailerRadioButton = new JRadioButton("销售商");
			retailerRadioButton.setBounds(310, 95, 71, 23);
			contentPane.add(retailerRadioButton);
			
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
			
			
			JButton tfListButton = new JButton("转账列表");
			tfListButton.setBounds(39, 140, 100, 23);
			contentPane.add(tfListButton);
			tfListButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new TfListFrame() ;
				}
			});
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(129, 181, 100, 21);
			contentPane.add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(218, 184, 21, 15);
			contentPane.add(yuanLabel);
			
			JLabel userLabel = new JLabel("当前操作员");
			userLabel.setBounds(320, 215, 100, 15);
			contentPane.add(userLabel);
			
			JButton sureButton = new JButton("确定 ") ;
			sureButton.setBounds(80, 270, 100, 23);
			contentPane.add(sureButton) ;
			sureButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose() ;
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			contentPane.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose() ;
				}
			});
		}

	}
    class MakePayment extends JFrame{


		private JPanel contentPane;
		private JTextField numberField;
		private JTextField nameOfCustomeField;
		private JTextField nameOfCustomerField;

		/**
		 * Create the frame.
		 */
		public MakePayment() {
			this.setTitle("制定收款单");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(450, 150, 450, 380);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			this.setVisible(true);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			contentPane.add(numberLabel);
			
			JLabel nameOfCustomeLabel = new JLabel("客户名称：");
			nameOfCustomeLabel.setBounds(39, 99, 100, 15);
			contentPane.add(nameOfCustomeLabel);
			
			JLabel nameOfUserLabel = new JLabel("操作员：");
			nameOfUserLabel.setBounds(256, 215, 100, 15);
			contentPane.add(nameOfUserLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总额汇总：");
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			contentPane.add(sumOfMoneyLabel);
			
			numberField = new JTextField();
			numberField.setBounds(129, 96, 100, 21);
			contentPane.add(numberField);
			numberField.setColumns(10);
			
			nameOfCustomeField = new JTextField();
			nameOfCustomeField.setBounds(129, 39, 135, 21);
			contentPane.add(nameOfCustomeField);
			nameOfCustomeField.setColumns(10);
			
			JRadioButton supplierRadioButton = new JRadioButton("供应商");
			supplierRadioButton.setBounds(240, 95, 71, 23);
			contentPane.add(supplierRadioButton);
			
			JRadioButton retailerRadioButton = new JRadioButton("销售商");
			retailerRadioButton.setBounds(310, 95, 71, 23);
			contentPane.add(retailerRadioButton);
			
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
			
			
			JButton tfListButton = new JButton("转账列表");
			tfListButton.setBounds(39, 140, 100, 23);
			contentPane.add(tfListButton);
			tfListButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new TfListFrame() ;
				}
			});
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(129, 181, 100, 21);
			contentPane.add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(218, 184, 21, 15);
			contentPane.add(yuanLabel);
			
			JLabel userLabel = new JLabel("当前操作员");
			userLabel.setBounds(320, 215, 100, 15);
			contentPane.add(userLabel);
			
			JButton sureButton = new JButton("确定 ") ;
			sureButton.setBounds(80, 270, 100, 23);
			contentPane.add(sureButton) ;
			sureButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose() ;
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(250, 270, 100, 23);
			contentPane.add(cancelButton) ;
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose() ;
				}
			});
		}

	}
         class TfListFrame extends JFrame{

    	private JPanel contentPane;
    	private JTable table;

    	/**
    	 * Create the frame.
    	 */
    	public TfListFrame() {
    		this.setVisible(true);
    		this.setTitle("转账列表");
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    		sureButton.setBounds(50, 200, 100, 30);
    		contentPane.add(sureButton) ;
    		sureButton.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				dispose() ;
    			}
			});
    		
    		JButton cancelButton = new JButton("取消");
    		cancelButton.setBounds(200,200,100,30);
    		contentPane.add(cancelButton) ;
    		cancelButton.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				dispose();
    			}
			});
    	}
}
	class MakeCash extends JFrame{
		
	}
         
	class InfoPanel extends JPanel{
		
		public InfoPanel(JFrame theFrame){
			
		}
		
	}
	public static void main(String[] marg){
		new FinanceFrame() ;
	}
}
