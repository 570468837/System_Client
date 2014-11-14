package presentation;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class LoginFrame extends JFrame {
	
	JTextField loginName;
	JPasswordField loginPassword;
	JComboBox<String> loginType;
	
	JLabel nameLabel, passwordLabel, typeLabel;
	
	JButton loginButton;
	
	
	public LoginFrame() {
		super();
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] s = new String[] {
		        "请选择用户类型",
		    	"库存管理人员",
		    	"销售人员",
		    	"财务人员",
		    	"总经理",
		    	"管理员",
		};
		
		loginType = new JComboBox<String>(s);
		loginName = new JTextField("<请输入用户名>");
	    loginPassword = new JPasswordField("<请输入密码>");
	    nameLabel = new JLabel();
	    passwordLabel = new JLabel();
	    typeLabel = new JLabel();
	    loginButton = new JButton("登录");
		
	    nameLabel.setBounds(30, 65, 100, 80);
	    nameLabel.setText("用户名");
	    nameLabel.setFont(new Font("default", 1, 16));
	    nameLabel.setVisible(true);
	    
	    passwordLabel.setBounds(30, 125, 100, 80);
	    passwordLabel.setText("密码");
	    passwordLabel.setFont(new Font("default", 1, 16));
	    passwordLabel.setVisible(true);
	    
	    typeLabel.setBounds(30, 5, 100, 80);
	    typeLabel.setText("用户类型");
	    typeLabel.setFont(new Font("default", 1, 16));
	    typeLabel.setVisible(true);
	    
	    
	    loginType.setBounds(110, 32, 200, 32);
	    loginType.setFont(new Font("default", 0, 16));
	    
	    loginName.setBounds(110, 94, 200, 32);
	    loginName.setFont(new Font("default", 0, 16));
	    loginName.addFocusListener(new FocusListener() {
	    	public void focusGained(FocusEvent e) {
	    		if(loginName.getText().equals("<请输入用户名>")) {
	    			loginName.setText("");
	    		}
	    	}
            public void focusLost(FocusEvent e) {
	    		if(loginName.getText().equals("")) {
	    			loginName.setText("<请输入用户名>");
	    		}
	    	}
	    });
	    
	    
	    loginPassword.setBounds(110, 155, 200, 32);
	    loginPassword.setFont(new Font("default", 0, 16));
	    loginPassword.setEchoChar((char) 0);
	    loginPassword.addFocusListener(new FocusListener() {
	    	public void focusGained(FocusEvent e) {
	    		if(new String(loginPassword.getPassword()).equals("<请输入密码>")) {
	    			loginPassword.setText("");
	    			loginPassword.setEchoChar('*');
	    		}
	    	}
            public void focusLost(FocusEvent e) {
	    		if(new String(loginPassword.getPassword()).equals("")) {
	    			loginPassword.setText("<请输入密码>");
	    			loginPassword.setEchoChar((char) 0);
	    		}
	    	}
	    	
	    });
	    
	    
	    loginButton.setBounds(150, 200, 100, 50);
	    loginButton.setFont(new Font("微软雅黑", 1, 24));
	    
	    
	    
		
		
		
		this.add(loginName);
		this.add(loginPassword);
		this.add(loginType);
		this.add(nameLabel);
		this.add(passwordLabel);
		this.add(typeLabel);
		this.add(loginButton);
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		LoginFrame l = new LoginFrame();
		
	}

}
