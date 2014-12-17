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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import businesslogicservice.FinanceBLService.FinanceController;
import presentation.CollectionOrPaymentFrame.ThePane;
import presentation.FinanceFrame.warningDialog;
import presentation.FinanceFrame.MakeCash.AddCaseItem;
import presentation.FinanceFrame.MakeCash.MyTableModel;
import presentation.FinanceFrame.MakeCash.ShowCaseItem;
import ResultMessage.ResultMessage;
import VO.CaseListItemVO;
import VO.CashVO;

public class CashForFinancer extends JFrame {
	ThePane pane ;
	public CashForFinancer (CashVO theVO){
		super("收付款单");
		pane = new ThePane(theVO) ;
		setSize(500, 380);
		this.setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
		this.add(pane) ;
	}
	
	class ThePane extends JPanel{
		private FinanceController fController = new FinanceController() ;
		private JTextField nameOfAccountField;
		private JTextField numberField;
		private JTextField sumOfMoneyField;
		ArrayList<CaseListItemVO> items = new ArrayList<CaseListItemVO>() ;//报销条目
		double sumOfMoney = 0 ;
		/**
		 * Create the frame.
		 * @param theVO 
		 */
		public ThePane(CashVO theVO) {
			setBounds(0, 0, 500, 380);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setLayout(null);
			
			JLabel numberLabel = new JLabel("单据编号：");
			numberLabel.setBounds(39, 42, 100, 15);
			this.add(numberLabel);
			
			JLabel nameOfAccountLabel = new JLabel("银行账户：");
			nameOfAccountLabel.setBounds(39, 99, 100, 15);
			this.add(nameOfAccountLabel);
			
			JLabel sumOfMoneyLabel = new JLabel("总        额："); 
			sumOfMoneyLabel.setBounds(39, 184, 100, 15);
			this.add(sumOfMoneyLabel);
			
			nameOfAccountField = new JTextField();
			nameOfAccountField.setBounds(129, 96, 135, 21);
			this.add(nameOfAccountField);
			nameOfAccountField.setColumns(10);
			nameOfAccountField.setText(theVO.getAccount());
			
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
			
			items = theVO.getCases() ;
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
			sumOfMoneyField.setText(String.valueOf(theVO.getSum()));
			
			JLabel yuanLabel = new JLabel("元");
			yuanLabel.setBounds(270, 184, 21, 15);
			this.add(yuanLabel);
			
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
						
						CashVO cash = new CashVO(number,theVO.getUser(),account,items,Double.parseDouble(sum)) ;
						ResultMessage result = fController.addCash(cash) ;

					    new warningDialog("添加成功");
						
						nameOfAccountField.setText("");
						numberField.setText("");
						sumOfMoneyField.setText("");
						items = new ArrayList<CaseListItemVO>() ;
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
					items = new ArrayList<CaseListItemVO>() ;
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
								
								CaseListItemVO item = new CaseListItemVO(nameOfCase, Double.parseDouble(money), marked) ;
								items.add(item) ;
								setNum();
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
		    		table.addMouseListener(new MouseAdapter() {
		    			public void mouseClicked(MouseEvent e){
		    				Point mousePoint = e.getPoint() ;
		    				if(table.columnAtPoint(mousePoint) == 3){
		    					int i = JOptionPane.showConfirmDialog(null, "确定删除？");
		    					if(i == 0){
		    						int k = table.rowAtPoint(mousePoint) ;
		    						items.remove(k) ;
		    						setNum();
		    						dispose();
		    						new ShowCaseItem() ;
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
			void setNum(){
	    		sumOfMoney = 0;
	    		for(CaseListItemVO theItem : items){
	    			sumOfMoney += theItem.getCaseMoney() ;
	    		}
	    		sumOfMoneyField.setText(String.valueOf(sumOfMoney));
	    	}
			class MyTableModel extends AbstractTableModel{
				private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
				private String[] column ={"条目名","金额","备注","是否删除"} ;

				public MyTableModel(){
					for(CaseListItemVO item : items){
						ArrayList<Object> oneRow = new ArrayList<Object>() ;
						oneRow.add(item.getCasename()) ;
						oneRow.add(item.getCaseMoney()) ;
						oneRow.add(item.getRemark()) ;
						oneRow.add("删除");
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
	public static void main(String[] args){
		FinanceController f = new FinanceController() ;
		ArrayList<CashVO> receipts = f.showCashVOs() ;
		new CashForFinancer(receipts.get(0)) ;
	}
}
