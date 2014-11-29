package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import businesslogicservice.UserBLService.UserController;
import Config.UserSort;
import ResultMessage.ResultMessage;
import VO.UserVO;


public class AdminFrame extends JFrame{
	private JLabel exitButton;
	private JLabel manageLabel;
	private adminPanel adPanel=new adminPanel(this);
	
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
	
	class adminPanel extends JPanel{
		private JTable table1;
		private JScrollPane tablePane1;
		public adminPanel(JFrame theFrame){
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));
			this.setVisible(true);
			
			JLabel addLabel = new JLabel("添加用户",JLabel.CENTER);
			addLabel.setBounds(80, 25, 80, 35);
			this.add(addLabel);
			
			addLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new addFrame();
			}
			});
			
			JLabel deleteLabel = new JLabel("删除用户",JLabel.CENTER);
			deleteLabel.setBounds(190, 25, 88, 35);
			this.add(deleteLabel);
			
			deleteLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new deleteFrame();
				}
			});
			
			JTextField searchField=new JTextField();
			searchField.setBounds(400, 28, 150, 25);
			searchField.setColumns(10);
			this.add(searchField);
			
			JLabel searchLabel=new JLabel("搜索",JLabel.CENTER);
			searchLabel.setBounds(530, 25, 88, 35);
			this.add(searchLabel);
			
			
			JLabel refreshLabel = new JLabel("刷新列表",JLabel.CENTER);
			refreshLabel.setBounds(725, 25, 88, 35);
			this.add(refreshLabel);
			
			refreshLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					tableRefresh();
	
				}
			});
			
			tableRefresh();    //刷新列表
			
			this.repaint();
			theFrame.add(this);
		}
		
		public ArrayList<ArrayList<Object>> getTableData(){
			ArrayList<UserVO> uservos=new ArrayList<UserVO>(new UserController().show());
			ArrayList<ArrayList<Object>> tableData=new ArrayList<ArrayList<Object>>();
			for(int i=0;i<uservos.size();i++){
				ArrayList<Object> datas=new ArrayList<Object>();
				datas.add(uservos.get(i).getUserName());
				datas.add(uservos.get(i).getPassword());
				datas.add(uservos.get(i).getUserSort());
				datas.add(uservos.get(i).getLevel());
				tableData.add(datas);
			} 
			return tableData;
		}
		
		public  void tableRefresh(){
			String[] columnTitle={"用户名","用户密码","用户身份","用户等级"};
			ArrayList<ArrayList<Object>> tableData=getTableData();
			table1=new JTable(new MyTableModel(tableData,columnTitle));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table1.setDefaultRenderer(Object.class, render);
		    
		    table1.repaint();
		    table1.updateUI();
		    
		    table1.getModel().addTableModelListener(new TableModelListener(){     //检测是否有内容更改
		    	public void tableChanged(TableModelEvent e) {     //进行的操作
		    		System.out.println();
		    		int row = e.getFirstRow();
		    		UserVO uservo=new UserVO(null, null, null, 0);
		    		uservo.setUserName((String)table1.getValueAt(row, 0));
		    		uservo.setPassword((String)table1.getValueAt(row, 1));
		    		uservo.setUserSort((UserSort)table1.getValueAt(row, 2));
		    		uservo.setLevel((int)table1.getValueAt(row, 3));
		    		new UserController().update(uservo);
		    	}
		    });
		    
		    if(tablePane1!=null)
		    	tablePane1.setVisible(false);    //重要！防止表格出现错位
			tablePane1=new JScrollPane(table1);
			tablePane1.setSize(700,400);
			tablePane1.setLocation(80, 74);
			tablePane1.setEnabled(false);
			tablePane1.repaint();
			tablePane1.updateUI();
			
			this.add(tablePane1);
		}
	}
	
	class MyTableModel extends AbstractTableModel{      //表格模型
		private ArrayList<ArrayList<Object>> tableData;
		private String[] columnTitle;
		public MyTableModel(ArrayList<ArrayList<Object>> data,String[] title) {
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
			return tableData.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			
			return columnTitle.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return tableData.get(rowIndex).get(columnIndex);
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
	        tableData.get(row).set(col,  value);
	        fireTableCellUpdated(row, col);  
	    }	
	}
	
	class addFrame extends JFrame{
		private JTextField nameField;
		private JTextField passwordField;
		public addFrame(){
			this.setSize(503, 366);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			getContentPane().setLayout(null);
			
			JLabel registerLabel = new JLabel("注册用户");
			registerLabel.setFont(new Font("宋体",1,16));
			registerLabel.setBounds(213, 28, 68, 24);
			getContentPane().add(registerLabel);
			
			JLabel nameLabel = new JLabel("用户名：");
			nameLabel.setBounds(134, 72, 55, 37);
			getContentPane().add(nameLabel);
			
			nameField = new JTextField();
			nameField.setBounds(213, 80, 127, 21);
			getContentPane().add(nameField);
			nameField.setColumns(10);
			
			JLabel passwordLabel = new JLabel("密  码：");
			passwordLabel.setBounds(134, 122, 64, 15);
			getContentPane().add(passwordLabel);
			
			passwordField = new JTextField();
			passwordField.setColumns(10);
			passwordField.setBounds(213, 119, 127, 21);
			getContentPane().add(passwordField);
			
			JLabel userSortLabel = new JLabel("用户身份：");
			userSortLabel.setBounds(134, 159, 68, 15);
			getContentPane().add(userSortLabel);
			
			JComboBox userSortcomboBox = new JComboBox(new String[]{"库存管理人员","进货销售人员","财务人员","总经理","管理员"});
			userSortcomboBox.setBounds(213, 156, 104, 21);
			getContentPane().add(userSortcomboBox);
			
			JLabel userLevelLabel = new JLabel("用户等级：");
			userLevelLabel.setBounds(134, 198, 68, 15);
			getContentPane().add(userLevelLabel);
			
			JComboBox userLevelComboBox = new JComboBox(new String[]{"1","2","3"});
			userLevelComboBox.setBounds(213, 195, 39, 21);
			getContentPane().add(userLevelComboBox);
			
			JButton registerButton = new JButton("注册");
			registerButton.setBounds(134, 250, 93, 37);
			getContentPane().add(registerButton);
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(256, 250, 93, 37);
			getContentPane().add(cancelButton);
			
			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					UserVO newVO=new UserVO(nameField.getText(), passwordField.getText(),null,userLevelComboBox.getSelectedIndex()+1);
					if(checkValid()){
						switch((String)userSortcomboBox.getSelectedItem()){
						case "库存管理人员":newVO.setUserSort(UserSort.Commodity); break;
						case "进货销售人员":newVO.setUserSort(UserSort.PurchaseAndSaler); break;
						case "财务人员": newVO.setUserSort(UserSort.Finance);  break;
						case "总经理": newVO.setUserSort(UserSort.Manager);  break;
						case "管理员": newVO.setUserSort(UserSort.Admin);  break;
						}
						ResultMessage result=new UserController().add(newVO);
						if(result==ResultMessage.add_success){
						dispose();
						}
						else
							new warningDialog("已存在相同的用户名！");
					}
					else{
						new warningDialog("用户名和密码不能为空！");
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
				
			});
		}
		
		public boolean checkValid(){
			boolean valid=false;
			if(!nameField.getText().equals("")&&!passwordField.getText().equals(""))
				valid=true;
			return valid;
		}
		
	}

	class deleteFrame extends JFrame{
		private JPanel contentPane;
		private JTextField textField;
		private JButton confirmButton;
		
		public deleteFrame(){
		this.setSize(503, 366);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel registerLabel = new JLabel("删除用户");
		registerLabel.setFont(new Font("宋体",1,16));
		registerLabel.setBounds(213, 28, 68, 24);
		getContentPane().add(registerLabel);
		
		JLabel remindinglabel = new JLabel("请输入要删除的用户名：");
		remindinglabel.setBounds(66, 121, 200, 51);
		getContentPane().add(remindinglabel);
		
		textField = new JTextField();
		textField.setBounds(206, 132, 213, 30);
		textField.setColumns(10); 
		getContentPane().add(textField);
		
		confirmButton = new JButton("确认删除");
		confirmButton.setBounds(124, 249, 93, 37);
		getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.setBounds(282, 249, 93, 37);
		getContentPane().add(cancelButton);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValid()){
					UserVO deleteUserVO=new UserVO(textField.getText(), null, null,0);
					ResultMessage result=new UserController().delete(deleteUserVO);
					if(result==ResultMessage.delete_success){
					dispose();
					}
					else{
						new warningDialog("不存在此用户名！");
					}
				}
				else{
					new warningDialog("删除用户的用户名不能为空！");
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		}
		
		
		public boolean checkValid(){
			if(!textField.getText().equals(""))
				return true;
			else
				return false;
		}
	}
	
	class warningDialog extends JDialog{
		public warningDialog(String warnings){
			this.setSize(284, 158);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			this.setVisible(true);
			this.setModal(true);
			
			JLabel warningLabel = new JLabel(warnings,JLabel.CENTER);
			warningLabel.setBounds(50, 28, 200, 50);
			warningLabel.setFont(new Font("宋体",Font.BOLD,14));
			
			this.add(warningLabel);
		}
	}
	public static void main(String[] args){
		new AdminFrame();
	}
}
