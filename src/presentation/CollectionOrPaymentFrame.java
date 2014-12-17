package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import businesslogicservice.FinanceBLService.FinanceController;
import presentation.FinanceFrame.warningDialog;
import presentation.FinanceFrame.MakeCollectionOrPayment.AddTfListFrame;
import presentation.FinanceFrame.MakeCollectionOrPayment.MyTableModel;
import presentation.FinanceFrame.MakeCollectionOrPayment.ShowTfListFrame;
import PO.CollectionOrPaymentPO;
import ResultMessage.ResultMessage;
import VO.CollectionOrPaymentVO;
import VO.TransferListItemVO;

public class CollectionOrPaymentFrame extends JFrame{
	ThePane pane;
	public CollectionOrPaymentFrame(CollectionOrPaymentVO theVO){
		super("收付款单");
		pane = new ThePane(theVO) ;
		setSize(500, 380);
		this.setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
		this.add(pane) ;

}
   class ThePane extends JPanel{

    FinanceController fController = new FinanceController() ;
	private JTextField nameOfCustomerField;
	private JTextField numberOfReceiptField;
	private JTextField sumOfMoneyField;
	ArrayList<TransferListItemVO> tfAccounts = new ArrayList<TransferListItemVO>() ;//储存转账账户
	double sumOfMoney = 0 ;
	/**
	 * Create the frame.
	 */
	public ThePane(CollectionOrPaymentVO theVO) {
		setBounds(0, 0, 500, 380);
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
		nameOfCustomerField.setText(theVO.getCustomer());
		
		numberOfReceiptField = new JTextField();
		numberOfReceiptField.setBounds(129, 39, 135, 21);
		this.add(numberOfReceiptField);
		numberOfReceiptField.setColumns(10);
		
		
		
		sumOfMoneyField = new JTextField();
		sumOfMoneyField.setBounds(129, 181, 100, 21);
		this.add(sumOfMoneyField);
		sumOfMoneyField.setColumns(10);
		sumOfMoneyField.setText(String.valueOf(theVO.getTotal()));
		
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
		if(theVO.getNumber().substring(0,3).equals("SKD")){
			c.setSelected(true);
			numberOfReceiptField.setText(fController.getReceiptNumber("SKD"));
		}else{
			f.setSelected(true);
			numberOfReceiptField.setText(fController.getReceiptNumber("FKD"));
		}
		
		JLabel nameOfCustomeLabel = new JLabel("客户名称：");
		nameOfCustomeLabel.setBounds(39, 99, 100, 15);
		this.add(nameOfCustomeLabel);
		
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
		if(theVO.getTypeOfCustomer().equals("供应商")){
			supplierRadioButton.setSelected(true);
			retailerRadioButton.setSelected(false);
		}
		if(theVO.getTypeOfCustomer().equals("销售商")){
			retailerRadioButton.setSelected(true);
			supplierRadioButton.setSelected(false);
		}
		

		JButton addTfListButton = new JButton("添加转账账户");
		addTfListButton.setBounds(39, 140, 130, 23);
		this.add(addTfListButton);
		addTfListButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new AddTfListFrame() ;
			}
		});
		
		tfAccounts = theVO.getTrList() ;
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
					CollectionOrPaymentVO collectionOrPayment = new CollectionOrPaymentVO(receiptNumber,nameOfCustomer,typeOfCustomer,theVO.getUser(),tfAccounts,Double.parseDouble(sumOfMoneys),false,false) ;
					ResultMessage result = fController.addCollectionOrPaymentVO(collectionOrPayment) ;
					dispose();
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
				dispose();
			}
		});
	}
	class MyTableModel extends AbstractTableModel{
		private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
		private String[] column ={"银行账户","转账金额","备注","是否删除"} ;

		public MyTableModel(){
			for(TransferListItemVO theAccount : tfAccounts){
				ArrayList<Object> oneRow = new ArrayList<Object>() ;
				oneRow.add(theAccount.getAccount()) ;
				oneRow.add(theAccount.getTransferMoney()) ;
				oneRow.add(theAccount.getRemark()) ;
				oneRow.add("删除") ;
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
    		table.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				Point mousePoint = e.getPoint() ;
    				if(table.columnAtPoint(mousePoint) == 3){
    					int i = JOptionPane.showConfirmDialog(null, "确定删除该？");
    					if(i == 0){
    						int k = table.rowAtPoint(mousePoint) ;
    						tfAccounts.remove(k) ;
    						sumOfMoneyField.setText(String.valueOf(getTotal())) ;
    						dispose();
    						new ShowTfListFrame() ;
    					}
    				}
    			}
			});
    		
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
    public double getTotal(){
    	double total = 0 ;
    	for(TransferListItemVO theVO : tfAccounts){
    		total += theVO.getTransferMoney() ;
    	}
    	return total ;
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
					
					String nameOfAccount = nameOfAccountField.getText() ;
					String numOfTf = numOfTfField.getText() ;
					String marked = markedField.getText() ;
					if(nameOfAccount.equals("")||numOfTf.equals("")||marked.equals("")){
						new warningDialog("信息不完整");
					}else{
						TransferListItemVO theItemVO = new TransferListItemVO(nameOfAccount, Double.parseDouble(numOfTf), marked) ;
				    	tfAccounts.add(theItemVO) ;
				    	dispose() ;
				    	sumOfMoney = getTotal() ;
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
}


   public static void main(String[] args){
	   FinanceController f = new FinanceController() ;
	   ArrayList<CollectionOrPaymentVO> receipts = f.showCollectionOrPaymentVOs() ;
	   new CollectionOrPaymentFrame(receipts.get(0)) ;
   }
}
