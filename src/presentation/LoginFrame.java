package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Config.UserSort;
import ResultMessage.ResultMessage;
import VO.UserVO;
import businesslogicservice.UserBLService.UserController;
/**
 * 
 * @author hutao
 *
 */
@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	JFrame thisFrame;
	JTextField loginName;
	JPasswordField loginPassword;
	JComboBox<String> loginType;
	JLabel 
	    backgroundLabel,
	    loginButton,
	    exitButton;
	
	
	public LoginFrame() {
		super();
		
		this.setSize(370, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("welcome");
		this.setLayout(null);
		this.setUndecorated(true); //去边框
		this.setBackground(new Color(255, 255, 255, 100));
		ImageIcon background = new ImageIcon("image/sign_in_frame.png");
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(background.getIconWidth(), background.getIconHeight());
		this.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		
		
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
	    loginButton = new JLabel("sign in",JLabel.CENTER);
	    exitButton = new JLabel("exit",JLabel.CENTER);
        
	    
		
		
	    
	    loginType.setBounds(80, 180, 200, 32);
	    loginType.setFont(new Font("default", 1, 16));
	    
	    loginName.setBounds(80, 230, 200, 32);
	    loginName.setFont(new Font("default", 0, 16));
	    loginName.enableInputMethods(false);   //屏蔽输入法
	    new AddWordsChange(loginName, "<请输入用户名>");
	    
	    
	    loginPassword.setBounds(80, 280, 200, 32);
	    loginPassword.setFont(new Font("default", 0, 16));
	    loginPassword.setEchoChar((char) 0);
	    loginName.enableInputMethods(false);
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
	    
	    thisFrame = this;
	    loginButton.setBounds(70, 350, 100, 50);
	    loginButton.setFont(new Font("Serif", 0, 20));
	    loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String readName=loginName.getText();
				String readPassword=new String(loginPassword.getPassword());
				UserSort readUserSort=null;
				switch((String)loginType.getSelectedItem()) {
				case "库存管理人员": readUserSort=UserSort.Commodity; break;
				case "销售人员": readUserSort=UserSort.PurchaseAndSaler; break;
				case "财务人员": readUserSort=UserSort.Finance;  break;
				case "总经理": readUserSort=UserSort.Manager; break;
				case "管理员": readUserSort=UserSort.Admin;  break;
				default:  break;
				}
				if(!((String)loginType.getSelectedItem()).equals("请选择用户类型")){
					UserVO user=new UserVO(readName,readPassword,readUserSort,1);
					if(new UserController().login(user)
							==ResultMessage.login_success){
						user=new UserController().find(user.getUserName()).get(0);
				switch((String)loginType.getSelectedItem()) {
				case "库存管理人员": new CommodityFrame(user); break;
				case "销售人员": new SalesmanFrame(user); break;
				case "财务人员": new FinanceFrame(user); break;
				case "总经理": new ManagerFrame(user); break;
				case "管理员": new AdminFrame(user); break;
				default:  break;
				}
				thisFrame.dispose();
					}
					else{               //密码错误
						new warningDialog("用户名或密码错误！");
					}
				}
				else{
					new warningDialog("请选择用户类型！");
				}
			}
	    });
	    
	    exitButton.setBounds(180, 350, 100, 50);
	    exitButton.setFont(new Font("Serif", 0, 20));
	    exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
	    	public void mouseEntered(MouseEvent e) {
	    		//改变鼠标样式
	    	}
	    });
		
		
		
		this.add(loginName);
		this.add(loginPassword);
		this.add(loginType);
		this.add(loginButton);
		this.add(exitButton);
		
		@SuppressWarnings("unused")
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class warningDialog extends JDialog{
		public warningDialog(String warnings){
			this.setSize(284, 158);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setModal(true);
			
			JLabel warningLabel = new JLabel(warnings,JLabel.CENTER);
			warningLabel.setBounds(50, 28, 200, 50);
			warningLabel.setFont(new Font("宋体",Font.BOLD,14));
			
			this.add(warningLabel);
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		LoginFrame l = new LoginFrame();
		
	}
	
	

}




