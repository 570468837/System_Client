package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ManagerFrame extends JFrame{
	private JLabel backgroundLabel,exitButton,crLabel,infoLabel,promotionLabel;
	private CheckReceiptPanel crPanel=new CheckReceiptPanel(this);
	private InfoPanel infoPanel=new InfoPanel(this);
	private PromotionPanel proPanel=new PromotionPanel(this);
	
    
	public ManagerFrame(){
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
		
		crLabel = new JLabel("审批单据", JLabel.CENTER);
		crLabel.setBounds(40, 100, 100, 50);
		crLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(true);
				infoPanel.setVisible(false);
				proPanel.setVisible(false);
			}
		});
		
		infoLabel = new JLabel("查看报表", JLabel.CENTER);
		infoLabel.setBounds(40, 160, 100, 50);
		infoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(false);
				infoPanel.setVisible(true);
				proPanel.setVisible(false);
			}
		});
		
		promotionLabel = new JLabel("制定促销策略", JLabel.CENTER);
		promotionLabel.setBounds(40, 220, 100, 50);
		promotionLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				crPanel.setVisible(false);
				infoPanel.setVisible(false);
				proPanel.setVisible(true);
			}
		});
		
		
		this.add(exitButton);
		this.add(crLabel);
		this.add(infoLabel);
		this.add(promotionLabel);
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class CheckReceiptPanel extends JPanel {
		//private JTable goodsTable;
		public CheckReceiptPanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));
			
			JLabel Receipt1 = new JLabel("进货单和销售单");
			Receipt1.setBounds(249, 23, 100, 50);
			this.add(Receipt1);
			
			JLabel Receipt2 = new JLabel("收款单和付款单");
			Receipt2.setBounds(505, 23, 100, 50);
			this.add(Receipt2);
			
			JPanel panel1=new JPanel();
			panel1.setBounds(37, 63, 767, 438);
			this.add(panel1);
			
			String[] columnTitle={"单据类型","单据编号","客户","操作员","转账列表","总额汇总"};
			Object[][] tableData={new Object[]{"进货单","XY1321324","飞利浦","胡韬","暂无",1000}};
			JTable table=new JTable(tableData,columnTitle);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		    
			JScrollPane tablePane=new JScrollPane(table);
			tablePane.setPreferredSize(new Dimension(650,420));
			panel1.add(tablePane);
			
			
		
			theFrame.add(this);
		}
	}
	
	class InfoPanel extends JPanel {
		//private JTable goodsTable;
		public InfoPanel(JFrame theFrame) {
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(185, 227, 217, 255));
			
			
			theFrame.add(this);
		}
	}
	
	class PromotionPanel extends JPanel {
		//private JTable goodsTable;
		public PromotionPanel(JFrame theFrame) {
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(173, 137, 115, 255));
			
			
			theFrame.add(this);
		}
	}
	
	public static void main(String[] args){
		new ManagerFrame();
	}
}

