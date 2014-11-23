package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.GoodsBLService.GoodsController;
/**
 * 
 * @author hutao
 *
 */
@SuppressWarnings("serial")
public class CommodityFrame extends JFrame {
	private JFrame theFrame;
	private JLabel 
	    backgroundLabel,
	    exitButton,
	    commodityLabel,
	    goodsLabel,
	    alarmLabel;
	private GoodsPanel goodsPanel;
	private CommodityPanel commodityPanel;
	
	
	public CommodityFrame() {
		super();
		
		theFrame = this;
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
		goodsPanel.setVisible(true);
		commodityPanel = new CommodityPanel(this);
		commodityPanel.setVisible(false);
		
		

		goodsLabel = new JLabel("Goods", JLabel.CENTER);
		goodsLabel.setBounds(40, 100, 100, 50);
		goodsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goodsPanel.setVisible(true);
				commodityPanel.setVisible(false);
			}
		});
		
		
		
		commodityLabel = new JLabel("Commodity", JLabel.CENTER);
		
		commodityLabel.setBounds(40, 160, 100, 50);
		commodityLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				commodityPanel.setVisible(true);
				goodsPanel.setVisible(false);
			}
		});
		
		
		alarmLabel = new JLabel("alarming", JLabel.CENTER);
		alarmLabel.setBounds(40, 470, 100, 50);
		alarmLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				AlarmFrame a = new AlarmFrame(theFrame);
				theFrame.setEnabled(false);
			}
		});
		
		
		
		this.add(goodsLabel);
		this.add(commodityLabel);
		this.add(alarmLabel);
		this.add(exitButton);
		
		@SuppressWarnings("unused")
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	
	
	
	class GoodsPanel extends JPanel {
		private JScrollPane jsp;
		private GoodsController gc = new GoodsController();
		private JTable goodsTable;
		private JLabel searchLabel = new JLabel("搜索", JLabel.CENTER);
		private JTextField searchField = new JTextField("<请输入商品关键字>");
		
		public GoodsPanel(JFrame theFrame) {
			super();
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(150, 255, 150, 255));
			this.setLayout(null);
			
			searchField.setBounds(310, 20, 200, 25);
			searchField.addFocusListener(new FocusListener() {
		    	public void focusGained(FocusEvent e) {
		    		if(searchField.getText().equals("<请输入商品关键字>")) {
		    			searchField.setText("");
		    		}
		    	}
	            public void focusLost(FocusEvent e) {
		    		if(searchField.getText().equals("")) {
		    			searchField.setText("<请输入商品关键字>");
		    		}
		    	}
		    });
			
			
			searchLabel.setFont(new Font("default", 1, 18));
			searchLabel.setBounds(530, 19, 60, 25);
			searchLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					
				}
		});
			
			
			
			
			
			
			
			
			
			
			this.add(searchField);
			this.add(searchLabel);
			//this.add(goodsTable); 
			theFrame.add(this);
		}
		/**
		 * 
		 * @return 获取所有商品信息
		 */
		private String[][] getTableInfo() {
			
			return null;
		}
		
	}
	
	class CommodityPanel extends JPanel {
		private JScrollPane jsp;
		private JTable comCheckTable;
		private JTable comInvenTable;
		private CommodityController cc = new CommodityController();
		private JLabel
		    comCheckLabel,
		    comInvenLabel,
		    sendLabel,
		    reportLabel,
		    exportLabel;
		    
		
		public CommodityPanel(JFrame theFrame) {
			super();
			
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(170, 255, 170, 255));
			this.setLayout(null);
			
			
			comInvenLabel = new JLabel("库存盘点");
			
			comCheckLabel = new JLabel("库存查看");
			
			sendLabel = new JLabel("制定赠送单");
			
			reportLabel = new JLabel("制定报单");
			
			/*
			 * 初始化显示库存盘点界面
			 */
			String[] citHead = {"", "名称", "型号", "库存数量", "库存均价", "批次", "批号", "出厂日期"};
			String[][] citInfo = cc.inventoryCommodity().icInfo;
			comInvenTable = new JTable(citInfo, citHead);
			comInvenTable.setAutoResizeMode(0);
			comInvenTable.setRowHeight(25);
			comInvenTable.setPreferredSize(new Dimension(825, citInfo.length * 25));
			comInvenTable.setEnabled(false);
			comInvenTable.setFont(new Font("default", 0, 16));
			comInvenTable.getTableHeader().setReorderingAllowed(false);
			comInvenTable.getTableHeader().setEnabled(false);
			comInvenTable.getTableHeader().setFont(new Font("default", 1, 17));
			comInvenTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
			comInvenTable.getColumnModel().getColumn(0).setPreferredWidth(30);
			jsp = new JScrollPane(comInvenTable);
	    	jsp.setHorizontalScrollBar(null);
			jsp.setBounds(5, 100, 825, 400);
			/*
			 * 导出excel键
			 */
			exportLabel = new JLabel("export");
			exportLabel.setBounds(700, 500, 100, 50);
			exportLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {}
			});
			
			
			
			this.add(jsp);
			this.add(exportLabel);
			theFrame.add(this);
		}
		
	}
	/**
	 * 报警单区域，包括报警单和总经理的回执
	 */
	class AlarmFrame extends JFrame {
		JLabel exitLabel;
		JFrame alarmFrame, comFrame;
		public AlarmFrame(JFrame theFrame) {
			alarmFrame = this;
			comFrame = theFrame;
			this.setSize(400, 300);
			this.setLocationRelativeTo(theFrame);
			this.setLayout(null);
			this.setUndecorated(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			exitLabel = new JLabel("X", JLabel.CENTER);
			exitLabel.setBounds(350, 0, 50, 50);
			exitLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					comFrame.setVisible(true);
					alarmFrame.dispose();
					comFrame.setEnabled(true);
					
				}
			});
			
			
			this.add(exitLabel);
			
			@SuppressWarnings("unused")
			MoveOfFrame m = new MoveOfFrame(this);
			this.setVisible(true);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CommodityFrame c = new CommodityFrame();
	}
	

}
