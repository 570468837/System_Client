package presentation;

import javax.swing.JFrame;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		super();
		
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		LoginFrame l = new LoginFrame();
		
	}

}
