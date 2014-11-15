package presentation;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class CommodityFrame extends JFrame {
	JLabel 
	    backgroundLabel,
	    exitButton,
	    commodityLabel,
	    goodsLabel;
	GoodsPanel goodsPanel;
	CommodityPanel commodityPanel;
	
	
	public CommodityFrame() {
		super();
		
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("welcome");
		this.setLayout(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*background label*/
		backgroundLabel = new JLabel();
		
		
		
		exitButton = new JLabel("X", JLabel.CENTER);
		exitButton.setBounds(950, 0, 50, 50);
		exitButton.setFont(new Font("default", 1, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
	    });
		
		goodsPanel = new GoodsPanel(this);
		//commodityPanel = new CommodityPanel(this);
		
		commodityLabel = new JLabel("Goods", JLabel.CENTER);
		commodityLabel.setBounds(40, 100, 100, 50);
		commodityLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				commodityPanel.setVisible(true);
				goodsPanel.setVisible(false);
			}
		});
		
		
		
		goodsLabel = new JLabel("Commodity", JLabel.CENTER);
		goodsLabel.setBounds(40, 160, 100, 50);
		goodsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goodsPanel.setVisible(true);
				commodityPanel.setVisible(false);
			}
		});
		
		
		
		
		
		this.add(goodsLabel);
		this.add(commodityLabel);
		this.add(exitButton);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	
	
	
	class GoodsPanel extends JPanel {
		
		public GoodsPanel(JFrame theFrame) {
			super();
			
		}
	}
	
	class CommodityPanel extends JPanel {
		public CommodityPanel(JFrame theFrame) {
			super();
			
		}
	}
	
	
	public static void main(String[] args) {
		CommodityFrame c = new CommodityFrame();
	}
	

}
