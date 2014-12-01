package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Config.Level;
import Config.Sort;
import PO.CustomerPO;
import ResultMessage.ResultMessage;
import VO.CustomerVO;
import businesslogicservice.CustomerBLService.CustomerController;

public class SalesmanFrameHelper {

	public SalesmanFrameHelper(String command) {
		switch (command) {
		case "addCustomer":
			new AddCustomerFrame();
			break;
		case "addPurchase":
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

		public AddCustomerFrame() {
			this.setTitle("增加客户");
			this.setVisible(true);
			setBounds(100, 100, 556, 475);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);

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

	public static void main(String[] args) {
		new SalesmanFrameHelper("addCustomer");

	}
}
