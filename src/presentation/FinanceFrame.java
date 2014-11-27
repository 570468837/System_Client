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
//		mangeAccountLabel.setVisible(false);
		mangeAccountLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("click") ;
				accountPanel.setVisible(true);
			}
		});
		this.add(mangeAccountLabel) ;
		
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
			theFrame.add(this) ;
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
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
				JButton addButton = new JButton("添加");
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
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			deleteButton = new JButton("删除");
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
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(500, 200, 307, 220);
			findAccountPane = new JPanel();
			findAccountPane.setBorder(new EmptyBorder(5, 5, 1, 5));
			setContentPane(findAccountPane);
			findAccountPane.setLayout(null);
			
			JLabel nameLabel = new JLabel("账户名称：");
			nameLabel.setBounds(45, 69, 100, 15);
			findAccountPane.add(nameLabel);
			
			nameField = new JTextField();
			nameField.setBounds(165, 66, 119, 21);
			findAccountPane.add(nameField);
			nameField.setColumns(10);
			
			JButton findButton = new JButton("确定");
			findButton.setBounds(60, 146, 93, 23);
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
			cancelButton.setBounds(191, 146, 93, 23);
			findAccountPane.add(cancelButton);
			cancelButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					dispose() ;
				}
			});
		}

	}
	
	
	
	public static void main(String[] marg){
		new FinanceFrame() ;
	}
}
