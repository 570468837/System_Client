package presentation;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class LoginFrame extends JFrame {
	
	JTextField loginName;
	JPasswordField loginPassword;
	JComboBox<String> loginType;
	
	JButton loginButton;
	
	
	public LoginFrame() {
		super();
		
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		this.setLayout(null);
		this.setUndecorated(true);
		
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
	    loginButton = new JButton("登录");
		
	    
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
	    loginButton.setFont(new Font("default", 1, 24));
	    
	    
	    
		
		
		
		this.add(loginName);
		this.add(loginPassword);
		this.add(loginType);
		this.add(loginButton);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.addMouseListener(m.getPoint);
		this.addMouseMotionListener(m.move);
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		LoginFrame l = new LoginFrame();
		
	}

}




