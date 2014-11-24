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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Config.UserSort;
import presentation.ManagerFrame.MyTableModel;

public class AdminFrame extends JFrame{
	private JLabel exitButton;
	private JLabel manageLabel;
	private adPanel adPanel=new adPanel(this);
	
	public AdminFrame(){
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
		this.add(exitButton);
		
		manageLabel = new JLabel("账号管理", JLabel.CENTER);
		manageLabel.setBounds(40, 100, 100, 50);
		this.add(manageLabel);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class adPanel extends JPanel{
		public adPanel(JFrame theFrame){
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));
			this.setVisible(true);
			
			String[] columnTitle1={"用户名","用户身份","用户权限","用户信息"};
			Object[][] tableData1={
					new Object[]{"小明",UserSort.Commodity,4,"库存管理员"},
					new Object[]{"小宇",UserSort.Finance,5,"财务人员"},
					           };
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table1.setDefaultRenderer(Object.class, render);
		    
		    table1.getModel().addTableModelListener(new TableModelListener(){
		    	public void tableChanged(TableModelEvent e) {
		    		int row = e.getFirstRow();
		            int column = e.getColumn();
		            //进行的操作
		    	}
		    	
		    });


		    
			JScrollPane tablePane1=new JScrollPane(table1);
			tablePane1.setSize(700,400);
			tablePane1.setLocation(80, 74);
			this.add(tablePane1);
			
			theFrame.add(this);
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
			 if(col==2||col==3)
				 return true;
			 else
				 return false;
		 }
		
		public void setValueAt(Object value, int row, int col) {  
	        tableData[row][col] = value;  
	        fireTableCellUpdated(row, col);  
	    }	
		
		



		
	}
	
	class setFrame extends JFrame{
		private JTextField textfield;
		public setFrame(){
			this.setSize(400, 276);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			
			JLabel label = new JLabel("请设置新的用户权限");
			label.setFont(new Font("宋体",1,16));
			label.setBounds(118, 10, 162, 50);
			getContentPane().add(label);
			
			JButton button1 = new JButton("1");
			button1.setBounds(29, 85, 100, 29);
			getContentPane().add(button1);
			
			JButton button2 = new JButton("2");
			button2.setBounds(147, 85, 100, 29);
			getContentPane().add(button2);
			
			JButton button3 = new JButton("3");
			button3.setBounds(257, 85, 100, 29);
			getContentPane().add(button3);
			
			JLabel label_1 = new JLabel("您选择的是：");
			label_1.setBounds(29, 153, 72, 40);
			getContentPane().add(label_1);
			
			JTextField textField = new JTextField();
			textField.setBounds(99, 163, 66, 21);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("1");
				}
			});
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("2");
				}
			});
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("3");
				}
			});
			
			JButton comButton = new JButton("完成");
			comButton.setBounds(223, 162, 93, 23);
			getContentPane().add(comButton);
		}
		
		public String getContent(){
			return textfield.getText();
		}
	}
	
	public static void main(String[] args){
		new AdminFrame();
	}
}
