package presentation;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 财务人员界面
 * @author shengyu
 */
public class FinanceFrame extends JFrame{
	private JFrame theFrame ;
	private JLabel exitButton ; 
	
	public FinanceFrame(){
		super() ;
		theFrame = this ;
		this.setSize(1000, 600);
//		this.setTitle("welcome");
		this.setLocationRelativeTo(null);
		this.setLayout(null); 
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		exitButton = new JLabel("X",JLabel.CENTER) ;
		exitButton.setBounds(950, 0, 50, 50);
		exitButton.setFont(new Font("default", 1, 20));
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		this.add(exitButton) ;

		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	public static void main(String[] marg){
		new FinanceFrame() ;
	}
}
