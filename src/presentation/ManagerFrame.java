package presentation;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;













import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;















import presentation.FinanceFrame.MyTableModel;
import presentation.FinanceFrame.SaleConditionPanel;
import presentation.FinanceFrame.SaleDetailPanel;
import presentation.FinanceFrame.SaleProcessPanel;
import presentation.FinanceFrame.ShowListFrame;
import presentation.FinanceFrame.warningDialog;
import presentation.SalesmanFrameHelper.AddPurchaseReceiptFrame;
import presentation.SalesmanFrameHelper.AddSalesReceiptFrame;
import businesslogicservice.ApprovalBLService.ApprovalBLService_Controller;
import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.FinanceBLService.FinanceController;
import businesslogicservice.GoodsBLService.GoodsController;
import businesslogicservice.InfoBLService.InfoController;
import businesslogicservice.PromotionBLService.PromotionController;
import businesslogicservice.PurchseBLService.PurchaseController;
import businesslogicservice.SaleBLService.SalesController;
import Config.Level;
import Config.PromotionSort;
import Config.UserSort;
import PO.GoodsPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import VO.CaseListItemVO;
import VO.CashVO;
import VO.CollectionOrPaymentVO;
import VO.GoodsVO;
import VO.PromotionVO;
import VO.PurchaseListItemVO;
import VO.PurchaseReceiptVO;
import VO.ReportCommodityVO;
import VO.SalesReceiptVO;
import VO.ScreeningConditionVO;
import VO.SendCommodityVO;
import VO.TransferListItemVO;
import VO.UserVO;


public class ManagerFrame extends JFrame{
	GoodsController gController=new GoodsController();
	private JLabel backgroundLabel,exitButton,crLabel,infoLabel,promotionLabel;
	private CheckReceiptPanel crPanel=new CheckReceiptPanel(this);
	private InfoPanel infoPanel=new InfoPanel(this);
	private PromotionPanel proPanel=new PromotionPanel(this);
	private UserPanel userPanel;
	private InfoController infoController = new InfoController() ;
	private FinanceController fController = new FinanceController() ;
	
	public ManagerFrame(UserVO uservo){   //总Frame
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
		
		userPanel=new UserPanel(uservo);
		
		this.add(exitButton);
		this.add(crLabel);
		this.add(infoLabel);
		this.add(promotionLabel);
		this.add(userPanel);
		
		
		
		MoveOfFrame m = new MoveOfFrame(this);
		this.setVisible(true);
	}
	
	class CheckReceiptPanel extends JPanel {      //审批报表panel
		//private JTable goodsTable;
		JPanel panel1,panel2,panel3,panel4;
		JScrollPane tablePane1,tablePane2,tablePane3,tablePane4;
		
		public CheckReceiptPanel(JFrame theFrame) {
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(147, 224, 255, 255));
			 //选择性标签
			JLabel Receipt1 = new JLabel("进货单和进货退货单",JLabel.CENTER);
			Receipt1.setBounds(130, 23, 120, 50);
			this.add(Receipt1);
			
			JLabel Receipt2 = new JLabel("销售单和销售退货单",JLabel.CENTER);
			Receipt2.setBounds(290, 23, 120, 50);
			this.add(Receipt2);
			
			JLabel Receipt3 = new JLabel("收款单和付款单",JLabel.CENTER);
			Receipt3.setBounds(440, 23, 120, 50);
			this.add(Receipt3);
			
			JLabel Receipt4 =new JLabel("库存赠送单",JLabel.CENTER);
			Receipt4.setBounds(565, 23, 120, 50);
			this.add(Receipt4);
			
			panel1=new JPanel();
			panel1.setLayout(null);
			panel1.setBounds(37, 63, 767, 438);
			panel1.setBackground(new Color(179, 197,135));
			this.add(panel1);
			
			panel2=new JPanel();
			panel2.setLayout(null);
			panel2.setBounds(37, 63, 767, 438);
			panel2.setBackground(new Color(140, 197,135));
			this.add(panel2);
			
			panel3=new JPanel();
			panel3.setLayout(null);
			panel3.setBounds(37, 63, 767, 438);
			panel3.setBackground(new Color(174, 221,129));
			this.add(panel3);
			
			panel4=new JPanel();
			panel4.setLayout(null);
			panel4.setBounds(37, 63, 767, 438);
			panel4.setBackground(new Color(150, 100,110));
			this.add(panel4);

			Receipt1.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(true);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(false);
					
				}
			});
			
			Receipt2.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					panel4.setVisible(false);
					
				}
			});
			
			Receipt3.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					panel4.setVisible(false);
					
				}
			});
			
			Receipt4.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(true);
					
				}
			});
			// 表一
			table1Refresh();
			
			
			// 表二
			table2Refresh();
			
		
			// 表三
			table3Refresh();
			
			//表四
			table4Refresh();
			
			theFrame.add(this);
		}
		public void table1Refresh(){
			String[] columnTitle1={"单据编号","供应商","仓库","操作员","入库商品列表","备注",
					"总额合计","审批通过"};
					
		
			ArrayList<ArrayList<Object>> tableData1=new ArrayList<ArrayList<Object>>();

			ArrayList<PurchaseReceiptPO> shows=new PurchaseController().show();
			for(int i=0;i<shows.size();i++){
				if(!(shows.get(i).isApprovedByManager()==false&&shows.get(i).isApprovedByCommodity()==false)){
					shows.remove(i);
					i--;
				}
			}
			
			ArrayList<ArrayList<PurchaseListItemPO>> itemList=new ArrayList<ArrayList<PurchaseListItemPO>>(); 
			for(int i=0;i<shows.size();i++){
				PurchaseReceiptPO p=shows.get(i);
				ArrayList<Object> oneData=new ArrayList<Object>();
				oneData.add(p.getSerialNumber());
				oneData.add(p.getCustomerPO().getName());
				oneData.add("1");
				oneData.add(p.getUserPO().getUserName());
				oneData.add("点击查看");   itemList.add(p.getPurchaseList());
				oneData.add(p.getComments());
				oneData.add(p.getTotalPrice());
				oneData.add(new Boolean(false));
				tableData1.add(oneData);
			}
			
			
			JTable table1=new JTable(new ManagerTableModel(tableData1,columnTitle1));
			table1.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table1.setDefaultRenderer(Object.class, render);
		    
		    table1.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e){
		    		int column=table1.columnAtPoint(e.getPoint());
		    		if(column==4){
		    			int row=table1.rowAtPoint(e.getPoint());
		    			if(row>=0){
		    			new GoodsInfoFrame(itemList.get(row),null);
		    			}
		    		}
		    	}
			});
		    
		    if(tablePane1!=null)
		    	tablePane1.setVisible(false);    //重要！防止表格出现错位
			tablePane1=new JScrollPane(table1);
			tablePane1.setSize(630,400);
			tablePane1.setLocation(50, 10);
			panel1.add(tablePane1);
			
			JButton allButton1=new JButton("全选");
			allButton1.setBounds(685,150,73,30);
			panel1.add(allButton1);
			JButton allNotButton1=new JButton("全不选");
			allNotButton1.setBounds(685,185,73,30);
			panel1.add(allNotButton1);
			JButton doneButton1=new JButton("审批");
			doneButton1.setBounds(685, 379, 73, 30);
			panel1.add(doneButton1);
			
			allButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table1.getRowCount();i++)
							table1.setValueAt(true, i, 7);
					}
			});
			
			allNotButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table1.getRowCount();i++)
							table1.setValueAt(false, i, 7);
						}
			});   
			
			doneButton1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<PurchaseReceiptPO> isApproved=new ArrayList<PurchaseReceiptPO>();
					ArrayList<PurchaseReceiptPO> notApproved=new ArrayList<PurchaseReceiptPO>();
					for(int i=0;i<tableData1.size();i++){
						if((Boolean)tableData1.get(i).get(7)==true)
							isApproved.add(shows.get(i));
						else
							notApproved.add(shows.get(i));
					}
					new ApprovalBLService_Controller().purchaseChangeGoods(isApproved);
					new ApprovalBLService_Controller().purchaseChangeCustomer(isApproved);
					
					new ApprovalBLService_Controller().purchaseNotPassed(notApproved);
					
					table1Refresh();
					
				}
			});
		}
	
		public void table2Refresh(){
			String[] columnTitle2={"单据编号","客户","业务员","操作员","仓库","出货商品清单","折让前总额",
					"折让","使用代金券金额","折让后总额","备注","审批通过"};
			
			ArrayList<ArrayList<Object>> tableData2=new ArrayList<ArrayList<Object>>();
			
			ArrayList<SalesReceiptPO> shows=new SalesController().show();
			for(int i=0;i<shows.size();i++){
				if(!(shows.get(i).isApprovedByManager()==false&&shows.get(i).isApprovedByCommodity()==false)){
					shows.remove(i);
					i--;
				}
			}
			
			ArrayList<ArrayList<SalesListItemPO>> salesItems=new ArrayList<ArrayList<SalesListItemPO>>();
			for(int i=0;i<shows.size();i++){
				SalesReceiptPO s=shows.get(i);
				System.out.println("<<"+s.getSalesList().size());
				ArrayList<Object> oneData=new ArrayList<Object>();
				oneData.add(s.getSerialNumber());
				oneData.add(s.getCustomerPO().getName());
				oneData.add(s.getSalesman());
				oneData.add(s.getUserPO().getUserName());
				oneData.add("仓库一");
				oneData.add("点击查看");  salesItems.add(s.getSalesList());
				oneData.add(s.getPriceBefore());
				oneData.add(s.getDiscout());
				oneData.add(s.getVocher());
				oneData.add(s.getFinalprice());
				oneData.add(s.getComment());
				oneData.add(new Boolean(false));
				tableData2.add(oneData);
			}
			
			
			JTable table2=new JTable(new ManagerTableModel(tableData2,columnTitle2));
			table2.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table2.setDefaultRenderer(Object.class, render);
		    
		    table2.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e){
		    		int column=table2.columnAtPoint(e.getPoint());
		    		if(column==5){
		    			int row=table2.rowAtPoint(e.getPoint());
		    			if(row>=0)
		    			new GoodsInfoFrame(null, salesItems.get(row));
		    		}
		    	}
			});
		    
		    if(tablePane2!=null)
		    	tablePane2.setVisible(false);
			tablePane2=new JScrollPane(table2);
			tablePane2.setSize(630,400);
			tablePane2.setLocation(50, 10);
			panel2.add(tablePane2);
				
			JButton allButton2=new JButton("全选");
			allButton2.setBounds(685,150,73,30);
			panel2.add(allButton2);
			JButton allNotButton2=new JButton("全不选");
			allNotButton2.setBounds(685,185,73,30);
			panel2.add(allNotButton2);
			JButton doneButton2=new JButton("审批");
			doneButton2.setBounds(685, 379, 73, 30);
			panel2.add(doneButton2);
			
			allButton2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					for(int i=0;i<table2.getRowCount();i++)
							table2.setValueAt(true, i, 11);
					}
			});
			
			allNotButton2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
						for(int i=0;i<table2.getRowCount();i++)
							table2.setValueAt(false, i, 11);
						}
			});   
			
			doneButton2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ArrayList<SalesReceiptPO> isApproved=new ArrayList<SalesReceiptPO>();
					ArrayList<SalesReceiptPO> notApproved=new ArrayList<SalesReceiptPO>();
					
					for(int i=0;i<tableData2.size();i++){
						if((Boolean)tableData2.get(i).get(11)==true)
							isApproved.add(shows.get(i));
						else
							notApproved.add(shows.get(i));
					}
					//TODO
					// 这里之前因为过多的创建goodscontroller对象，导致不同的线程中的迭代器对同一list进行修改产生错误
					//
					
					new ApprovalBLService_Controller().salesChangeGoods(isApproved);
					new ApprovalBLService_Controller().addSendCommodityReceipt(isApproved);
					
					new ApprovalBLService_Controller().salesChangeCustomer(isApproved);  //同时update操作
					
					new ApprovalBLService_Controller().salesNotPassed(notApproved);
					table2Refresh();
				}
			});
		}
		
		public void table3Refresh(){
			String[] columnTitle3={"单据编号","客户","操作员","转账列表","总额汇总","审批通过"};
			
			ArrayList<ArrayList<Object>> tableData3=new ArrayList<ArrayList<Object>>();
			
			ArrayList<CollectionOrPaymentVO> shows=new FinanceController().showCollectionOrPaymentVOs();
			for(int i=0;i<shows.size();i++){
				if(!(shows.get(i).isApprovedByManager()==false&&shows.get(i).isApprovedByFinancer()==false)){
					shows.remove(i);
					i--;
				}
			}
			
			ArrayList<ArrayList<TransferListItemVO>> transfers=new ArrayList<ArrayList<TransferListItemVO>>();
			for(int i=0;i<shows.size();i++){
				CollectionOrPaymentVO v=shows.get(i);
				ArrayList<Object> oneData=new ArrayList<Object>();
				oneData.add(v.getNumber());
				oneData.add(v.getCustomer());
				oneData.add(v.getUser());
				oneData.add("点击查看");  transfers.add(v.getTrList());
				oneData.add(v.getTotal());
				oneData.add(new Boolean(false));
				tableData3.add(oneData);
			}
			
			JTable table3=new JTable(new ManagerTableModel(tableData3,columnTitle3));
			table3.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table3.setDefaultRenderer(Object.class, render);
		    
		    table3.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e){
		    		int column=table3.columnAtPoint(e.getPoint());
		    		if(column==3){
		    			int row=table3.rowAtPoint(e.getPoint());
		    			if(row>=0)
		    			new TransferFrame(transfers.get(row));
		    		}
		    	}
			});
		    
		    if(tablePane3!=null)
		    	tablePane3.setVisible(false);
			tablePane3=new JScrollPane(table3);
			tablePane3.setSize(630,400);
			tablePane3.setLocation(50, 10);
			panel3.add(tablePane3);
			
			JButton allButton3=new JButton("全选");
			allButton3.setBounds(685,150,73,30);
			panel3.add(allButton3);
			JButton allNotButton3=new JButton("全不选");
			allNotButton3.setBounds(685,185,73,30);
			panel3.add(allNotButton3);
			JButton doneButton3=new JButton("审批");
			doneButton3.setBounds(685, 379, 73, 30);
			panel3.add(doneButton3);
			
			allButton3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table3.getRowCount();i++)
							table3.setValueAt(true, i, 5);
					}
			});
			
			allNotButton3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table3.getRowCount();i++)
							table3.setValueAt(false, i, 5);
						}
			});         
			
			doneButton3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ArrayList<CollectionOrPaymentVO> isApproved=new ArrayList<CollectionOrPaymentVO>();
					ArrayList<CollectionOrPaymentVO> notApproved=new ArrayList<CollectionOrPaymentVO>();
					for(int i=0;i<tableData3.size();i++){
						if((Boolean)tableData3.get(i).get(5)==true)
							isApproved.add(shows.get(i));
						else
							notApproved.add(shows.get(i));
					}
					new ApprovalBLService_Controller().collectionOrPaymentChangeCustomer(isApproved);
					
					new ApprovalBLService_Controller().collectionOrPaymentNotPassed(notApproved);
					
					table3Refresh();
				}
			});
		}
		
		public void table4Refresh(){
			String[] columnTitle4={"客户","商品编号","商品名称","商品数量","审批通过"};
			
			ArrayList<ArrayList<Object>> tableData4=new ArrayList<ArrayList<Object>>();
			
			ArrayList<SendCommodityVO> shows=new CommodityController().showUncheckedSend();
			for(int i=0;i<shows.size();i++){
				if(shows.get(i).checked!=SendCommodityVO.UNCHECKED){
					shows.remove(i);
					i--;
				}
			}
			
			for(int i=0;i<shows.size();i++){
				SendCommodityVO s=shows.get(i);
				ArrayList<Object> oneData=new ArrayList<Object>();
				oneData.add(s.customerVOName);
				oneData.add(s.goodsVOId);
				oneData.add(gController.getGoodsByID(s.goodsVOId).name);
				oneData.add(s.num);
				oneData.add(new Boolean(false));
				tableData4.add(oneData);
			}
			
			JTable table4=new JTable(new ManagerTableModel(tableData4,columnTitle4));
			table4.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table4.setDefaultRenderer(Object.class, render);
		    
		    if(tablePane4!=null)
		    	tablePane4.setVisible(false);
			tablePane4=new JScrollPane(table4);
			tablePane4.setSize(630,400);
			tablePane4.setLocation(50, 10);
			panel4.add(tablePane4);
			
			JButton allButton4=new JButton("全选");
			allButton4.setBounds(685,150,73,30);
			panel4.add(allButton4);
			JButton allNotButton4=new JButton("全不选");
			allNotButton4.setBounds(685,185,73,30);
			panel4.add(allNotButton4);
			JButton doneButton4=new JButton("审批");
			doneButton4.setBounds(685, 379, 73, 30);
			panel4.add(doneButton4);
			
			allButton4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<table4.getRowCount();i++)
							table4.setValueAt(true, i, 4);
					}
			});
			
			allNotButton4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
						for(int i=0;i<table4.getRowCount();i++)
							table4.setValueAt(false, i, 4);
						}
			});   
			
			doneButton4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ArrayList<SendCommodityVO> isApproved=new ArrayList<SendCommodityVO>();
					ArrayList<SendCommodityVO> NotApproved=new ArrayList<SendCommodityVO>();
					for(int i=0;i<tableData4.size();i++){
						if((Boolean)tableData4.get(i).get(4)==true)
							isApproved.add(shows.get(i));
						else
							NotApproved.add(shows.get(i));
					}
					new ApprovalBLService_Controller().sendCommodityUpdate(isApproved);
					
					new ApprovalBLService_Controller().sendCommodityNotPassed(NotApproved);
					
					table4Refresh();
				}
			});
		}
	}  
	
	class InfoPanel extends JPanel{
		SaleDetailPanel saleDetailPanel ;
		SaleProcessPanel saleProcessPanel ;
		SaleConditionPanel saleConditionPanel ;
		public InfoPanel(JFrame theFrame){
			super() ;
	    	this.setBounds(140, 25, 835, 550);
	    	this.setBackground(new Color(200, 100, 150, 255));
	    	this.setLayout(null);
	    	theFrame.add(this) ;
	    	saleDetailPanel  = new SaleDetailPanel() ;
	    	this.add(saleDetailPanel) ;
//	    	saleDetailPanel.beginTimeField.requestFocus();
	    	saleProcessPanel = new SaleProcessPanel() ;
	    	this.add(saleProcessPanel);
	    	saleProcessPanel.setVisible(false);
	    	saleConditionPanel = new SaleConditionPanel() ;
	    	this.add(saleConditionPanel) ;
	    	saleConditionPanel.setVisible(false);
	    	this.setVisible(false);
	    	
	    	
	    	JLabel saleDetailLabel = new JLabel("销售明细表",JLabel.CENTER);
			saleDetailLabel.setBounds(100, 23, 120, 50);
			this.add(saleDetailLabel) ;
			saleDetailLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(true);
					saleProcessPanel.setVisible(false);
					saleConditionPanel.setVisible(false);
				}
			});
			
			
			JLabel saleProcessLabel = new JLabel("经营历程表",JLabel.CENTER) ;
			saleProcessLabel.setBounds(200, 23, 120, 50);
			this.add(saleProcessLabel) ;
			saleProcessLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(false);
					saleProcessPanel.setVisible(true);
					saleConditionPanel.setVisible(false);
				}
			});
			
			JLabel saleConditionLabel = new JLabel("经营情况表",JLabel.CENTER) ;
			saleConditionLabel.setBounds(300,23,120,50) ;
			this.add(saleConditionLabel) ;
			saleConditionLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					saleDetailPanel.setVisible(false);
					saleProcessPanel.setVisible(false);
					saleConditionPanel.setVisible(true);
				}
			});
			
			this.repaint();
	    }
	}
	class SaleDetailPanel extends JPanel{

		private JTextField beginTimeField;
		private JTextField endTimeField;
		private JTextField nameOfGoodField;
		private JTextField nameOfCustomerField;
		private JTextField nameOfRetailerField;
		private JTextField storageField;
		private JTable saleDetailTable;
		JScrollPane jsc  ;
		ArrayList<SalesReceiptPO> receipts = new ArrayList<SalesReceiptPO>() ;

		/**
		 * Create the panel.
		 */
		public SaleDetailPanel() {
			
			super();
			setLayout(null);
			this.setBackground(Color.LIGHT_GRAY);
			this.setBounds(80,74, 700,400);
			 
			refreshTable();
			
			JLabel timesLabel = new JLabel("时间区间：");
			timesLabel.setBounds(44, 24, 100, 15);
			add(timesLabel);
			
			beginTimeField = new JTextField() ;
			beginTimeField.setBounds(187, 21, 122, 21);
			add(beginTimeField);
			beginTimeField.setColumns(10);
			beginTimeField.setText("<例如2014/10/10>");
			
			
			JLabel beginTimeLabel = new JLabel("起始时间");
			beginTimeLabel.setBounds(123, 22, 100, 18);
			add(beginTimeLabel);
			new AddWordsChange(beginTimeField, "<例如2014/10/10>") ;
			
			JLabel endTimeLabel = new JLabel("截止时间");
			endTimeLabel.setBounds(351, 24, 100, 15);
			add(endTimeLabel);
			
			endTimeField = new JTextField();
			endTimeField.setColumns(10);
			endTimeField.setBounds(415, 21, 122, 21);
			add(endTimeField);
			endTimeField.setText("<例如2014/11/10>");
			new AddWordsChange(endTimeField, "<例如2014/11/10>") ;
			
			JLabel nameOfGoodLabel = new JLabel("商品名称");
			nameOfGoodLabel.setBounds(123, 51, 100, 15);
			add(nameOfGoodLabel);
			
			nameOfGoodField = new JTextField();
			nameOfGoodField.setBounds(187, 52, 122, 21);
			add(nameOfGoodField);
			nameOfGoodField.setColumns(10);
			
			JLabel nameOfCustomerLabel = new JLabel("客户名称");
			nameOfCustomerLabel.setBounds(351, 55, 100, 15);
			add(nameOfCustomerLabel);
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(415, 52, 122, 21);
			add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel nameOfRetailerLabel = new JLabel("业务员");
			nameOfRetailerLabel.setBounds(123, 86, 100, 15);
			add(nameOfRetailerLabel);
			
			nameOfRetailerField = new JTextField();
			nameOfRetailerField.setBounds(187, 83, 122, 21);
			add(nameOfRetailerField);
			nameOfRetailerField.setColumns(10);
			
			JLabel storageLabel = new JLabel("仓库");
			storageLabel.setBounds(351, 86, 100, 15);
			add(storageLabel);
			
			storageField = new JTextField();
			storageField.setBounds(415, 83, 122, 21);
			add(storageField);
			storageField.setColumns(10);
			
			
			
			
			JButton sureButton = new JButton("确定");
			sureButton.setBounds(578, 24, 93, 23);
			add(sureButton);
			sureButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String beginTime = beginTimeField.getText() ;
					String endTime = endTimeField.getText() ;
					String nameOfGood = nameOfGoodField.getText() ;
					String nameOfCustomer = nameOfCustomerField.getText();
					String nameOfRetailer = nameOfRetailerField.getText() ;
					String storage = storageField.getText() ;
					String typeOfReceipt = "XSD";
					if(beginTime.equals("<例如2014/10/10>")||endTime.equals("<例如2014/11/10>")){
						new warningDialog("请输入完整的时间区间");
					}else{
						ScreeningConditionVO condition = new ScreeningConditionVO(beginTime,endTime,typeOfReceipt,nameOfGood,nameOfCustomer,nameOfRetailer,storage) ;
						receipts = infoController.showSalesDetailsInfo(condition) ;
						refreshTable(receipts); 
					}
				}
			});
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(578, 68, 93, 23);
			add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					beginTimeField.setText("<例如2014/10/10>");
					endTimeField.setText("<例如2014/11/10>");
					nameOfGoodField.setText("");
					nameOfCustomerField.setText("");
					nameOfRetailerField.setText("");
					storageField.setText("");
					receipts = new ArrayList<SalesReceiptPO>() ;
					refreshTable( receipts);
				}
			});

		}
		public void refreshTable(){
			String[] columNames ={"单据编号","客户","业务员","操作员","仓库","商品清单","折让前总额","折让","代金券金额","折让后总额","备注"};
			String[][] datas = {{"数据1","数据2","数据3","数据4","数据5","数据6","数据7","数据8","数据9","数据10","数据11"},{"……","……","……","……","……","……","……","……","……","……","……"}} ;
			saleDetailTable = new JTable(datas,columNames) ;
            saleDetailTable.setBackground(Color.white);
            
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    
		    jsc = new JScrollPane(saleDetailTable) ;	
		    jsc.setBounds(5, 121, 690, 240);
            saleDetailTable.setFillsViewportHeight(true);
            this.add(jsc) ;
		}
		public void refreshTable(ArrayList<SalesReceiptPO> receipts){
			String[] columNames ={"单据编号","客户","业务员","操作员","仓库","商品清单","折让前总额","折让","代金券金额","折让后总额","备注"};
			ArrayList<Object> objects = new ArrayList<Object>() ;
			for(SalesReceiptPO item:receipts ){
				objects.add(item) ;
			}
			saleDetailTable = new JTable(new MyTableModel(objects,columNames,"XSD")) ;
			saleDetailTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					Point mousePoint = e.getPoint()  ;
					if(saleDetailTable.columnAtPoint(mousePoint)== 5){
						SalesReceiptPO thePO = receipts.get(saleDetailTable.rowAtPoint(mousePoint)) ;
						String[] column2 = {"编号","名称","型号","数量","单价","金额","商品备注"};
						ArrayList<Object> list = new ArrayList<>(thePO.getSalesList()) ;
						new ShowListFrame(list, column2,"商品清单");
					}
				}
			});
			saleDetailTable.setBackground(Color.white);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    saleDetailTable.setDefaultRenderer(Object.class, render);
			
		    if(jsc != null)
		    	jsc.setVisible(false);
		    jsc = new JScrollPane(saleDetailTable) ;	
		    jsc.setBounds(5, 121, 690, 240);
			saleDetailTable.setFillsViewportHeight(true);
			this.add(jsc) ;
		}
		
	}
	class SaleProcessPanel extends JPanel{
		private JTextField beginTimeField;
		private JTextField endTimeField;
		private JTextField nameOfCustomerField;
		private JTextField nameOfRetailerField;
		private JTextField storageField;
		private JTable table;
		private JScrollPane jsc ;
		private JPanel thePanel;
		ArrayList<Object> result ;
		Object theSaveReceipt ; 
		String typeOfReceipt  ;
		int markColumn = 10000 ;
		int currentRow = 10000 ;
		/**
		 * Create the panel.
		 */
		public SaleProcessPanel() {
			freshTable();
			setLayout(null);
			this.setBounds(80,74, 700,400);
			this.setBackground(Color.LIGHT_GRAY);
			thePanel = this ;
			
			JLabel timesLabel = new JLabel("时间区间");
			timesLabel.setBounds(44, 59, 69, 15);
			add(timesLabel);
			
			beginTimeField = new JTextField();
			beginTimeField.setBounds(188, 56, 122, 21);
			add(beginTimeField);
			beginTimeField.setColumns(10);
			beginTimeField.setText("<例如2014/10/10>");
			new AddWordsChange(beginTimeField, "<例如2014/10/10>") ;
			
			JLabel beginTimeLabel = new JLabel("起始时间");
			beginTimeLabel.setBounds(124, 57, 54, 18);
			add(beginTimeLabel);
			
			JLabel endTimeLabel = new JLabel("截止时间");
			endTimeLabel.setBounds(351, 59, 54, 15);
			add(endTimeLabel);
			
			endTimeField = new JTextField();
			endTimeField.setColumns(10);
			endTimeField.setBounds(415, 56, 122, 21);
			add(endTimeField);
			endTimeField.setText("<例如2014/11/10>");
			new AddWordsChange(endTimeField, "<例如2014/11/10>") ;
			
			JLabel nameOfCustomerLabel = new JLabel("客户名称");
			nameOfCustomerLabel.setBounds(44, 90, 54, 15);
			add(nameOfCustomerLabel);
			
			nameOfCustomerField = new JTextField();
			nameOfCustomerField.setBounds(124, 87, 77, 21);
			add(nameOfCustomerField);
			nameOfCustomerField.setColumns(10);
			
			JLabel nameOfRetailerLabel = new JLabel("业务员");
			nameOfRetailerLabel.setBounds(238, 90, 54, 15);
			add(nameOfRetailerLabel);
			
			nameOfRetailerField = new JTextField();
			nameOfRetailerField.setBounds(293, 87, 77, 21);
			add(nameOfRetailerField);
			nameOfRetailerField.setColumns(10);
			
			JLabel storageLabel = new JLabel("仓库");
			storageLabel.setBounds(415, 90, 54, 15);
			add(storageLabel);
			
			storageField = new JTextField();
			storageField.setBounds(459, 87, 77, 21);
			add(storageField);
			storageField.setColumns(10);
			
			JButton sureButton = new JButton("确定");
			sureButton.setBounds(578, 35, 93, 23);
			add(sureButton);
			
			JButton cancelButton = new JButton("取消");
			cancelButton.setBounds(578, 68, 93, 23);
			add(cancelButton);
			
			JLabel typeOfReceiptLabel = new JLabel("单据类型");
			typeOfReceiptLabel.setBounds(44, 25, 54, 15);
			add(typeOfReceiptLabel);
			
			String[] typeOfReceipts1 = {"销售类","进货类","财务类","库存类"} ;
			JComboBox jcs1 = new JComboBox(typeOfReceipts1) ;
			jcs1.setBounds(123, 21, 100, 23);
			add(jcs1) ;
			
			String[][] typeOfReceipts2 = {{"销售出货单","销售退货单"},{"进货单","进货退货单"},{"收款单","付款单","现金费用单"},{"报溢单","报损单","赠送单"}};
			JComboBox jcs2 = new JComboBox() ;
			jcs2.setBounds(250, 21, 100, 23);
			add(jcs2) ;
			
			jcs1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JComboBox temp = (JComboBox)e.getSource() ;
					String type1 = (String) temp.getSelectedItem() ;
					if(jcs2.getItemCount() !=0){
						jcs2.removeAllItems();
						jcs2.updateUI();
						jcs2.setSelectedItem("");
					}
					if(type1.equals(typeOfReceipts1[0])){
						for(int i = 0 ;i<typeOfReceipts2[0].length;i++){
							jcs2.addItem(typeOfReceipts2[0][i]);
						}
					}
					if(type1.equals(typeOfReceipts1[1])){
						for(int i = 0 ;i<typeOfReceipts2[1].length;i++){
							jcs2.addItem(typeOfReceipts2[1][i]);
						}
					}
					if(type1.equals(typeOfReceipts1[2])){
						for(int i = 0 ;i<typeOfReceipts2[2].length;i++){
							jcs2.addItem(typeOfReceipts2[2][i]);
						}
					}
					if(type1.equals(typeOfReceipts1[3])){
						for(int i = 0 ;i<typeOfReceipts2[3].length;i++){
							jcs2.addItem(typeOfReceipts2[3][i]);
						}
					}
				}
			});;
			
			
            cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					currentRow = 10000;
					markColumn = 10000;
					typeOfReceipt = "" ;
					jcs1.setSelectedIndex(0);
					jcs2.removeAllItems();
					thePanel.remove(jsc);
					freshTable();
					beginTimeField.setText("<例如2014/10/10>") ;	
					endTimeField.setText("<例如2014/11/10>") ;
					nameOfCustomerField.setText("");
					nameOfRetailerField.setText("");
					storageField.setText("");
				}
			});
			sureButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					currentRow = 10000;
					markColumn = 10000;
					String beginTime = beginTimeField.getText() ;
					String endTime = endTimeField.getText() ;
					String nameOfCustomer = nameOfCustomerField.getText() ;
					String nameOfRetailer = nameOfRetailerField.getText() ;
					String storage = storageField.getText() ;
					if(jcs2.getSelectedItem() == null){
						new warningDialog("请选择单据类型") ;
					}else{
					if(jcs2.getSelectedItem().equals("销售出货单"))
						typeOfReceipt = "XSD";
					else
						if(jcs2.getSelectedItem().equals("销售退货单"))
							typeOfReceipt = "XSTHD" ;
						else
							if((jcs2.getSelectedItem().equals("进货单")))
								typeOfReceipt = "JHD" ;
							else
								if(jcs2.getSelectedItem().equals("进货退货单"))
									typeOfReceipt = "JHTHD" ;
								else
									if(jcs2.getSelectedItem().equals("收款单"))
										typeOfReceipt = "SKD";
									else
										if(jcs2.getSelectedItem().equals("付款单"))
											typeOfReceipt = "FKD";
										else
											if(jcs2.getSelectedItem().equals("现金费用单"))
												typeOfReceipt = "XJFYD" ;
											else
												if(jcs2.getSelectedItem().equals("报溢单"))
													typeOfReceipt = "BYD" ;
												else
													if(jcs2.getSelectedItem().equals("报损单"))
														typeOfReceipt = "BSD" ;
													else
														typeOfReceipt = "ZSD" ;
					
					    if(beginTime.equals("<例如2014/10/10>")||endTime.equals("<例如2014/11/10>")){
					    	new warningDialog("请输入时间区间") ;
					    }else{
					    		result = infoController.showSalesProcessInfo(new ScreeningConditionVO(beginTime,endTime,typeOfReceipt,"",nameOfCustomer,nameOfRetailer,storage)) ;
					    		thePanel.remove(jsc);
					    		System.out.println(result.size());
					    		freshTable(result, typeOfReceipt);
					    }
					}
					
				}
			});
		}
	    private void freshTable() {
	    	String[] columnNames = {"单据属性1","单据属性2","……"};
	    	String[][] data = {{"数据11","数据12","……"},{"数据21","数据22","……"},{"数据31","数据32","……"}};
			
			table = new JTable(data,columnNames) ;
			table.setBackground(Color.white);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		
			jsc = new JScrollPane(table) ;
			 jsc.setBounds(5, 121, 690, 240);
			add(jsc);
		}
		private void freshTable(ArrayList<Object> objects ,String type) {
			if(type.equals("SKD")||type.equals("FKD")){//收付款单
				markColumn = 3 ;
				String[] column = {"单据编号","客户","操作员","转账列表","总额汇总"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint() ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i ;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2= {"银行账户","转账金额","备注"} ;
							CollectionOrPaymentVO theVO = (CollectionOrPaymentVO)objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>(theVO.getTrList()) ;
							new ShowListFrame(list,column2 , "转账列表") ;
						}
					}
				});
			}
			if(type.equals("XJFYD")){//现金费用单
				markColumn = 3 ;
				String[] column = {"单据编号","操作员","银行账户","条目清单","总额"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint() ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2 = {"条目名","金额","备注"} ;
							CashVO  theVO = (CashVO)objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>(theVO.getCases()) ;
							new ShowListFrame(list, column2, "条目清单") ;
						}
					}
				});
			}
			if(type.equals("XSD")){//销售单
				markColumn = 5 ;
				String[] column ={"单据编号","客户","业务员","操作员","仓库","商品清单","折让前总额","折让","代金券金额","折让后总额","备注"};
				table = new JTable(new MyTableModel(objects,column,type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint()  ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i ;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2 = {"编号","名称","型号","数量","单价","金额","商品备注"};
						
							SalesReceiptPO thePO= (SalesReceiptPO) objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>(thePO.getSalesList()) ;
							new ShowListFrame(list, column2,"出货商品清单");
						}
					}
				});
			}
			if(type.equals("XSTHD")){//销售退货单
				markColumn = 5 ;
				String[] column ={"单据编号","客户","业务员","操作员","仓库","商品清单","折让前总额","折让","代金券金额","折让后总额","备注"};
				table = new JTable(new MyTableModel(objects,column,type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint()  ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i ;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2 = {"编号","名称","型号","数量","单价","金额","商品备注"};
							
							SalesReceiptPO thePO= (SalesReceiptPO) objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>(thePO.getSalesList()) ;
							new ShowListFrame(list, column2,"出货商品清单");
						}
					}
				});
			}
			if(type.equals("JHD")){//进货单
				markColumn = 4 ;
				String[] column = {"单据编号","供应商","仓库","操作员","入库商品列表","备注","总额合计"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint()  ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i ;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2 = {"编号","名称","型号","数量","单价","金额","商品备注"};
							PurchaseReceiptPO thePO= (PurchaseReceiptPO) objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>(thePO.getPurchaseList()) ;
							System.out.println(list.size());
							new ShowListFrame(list, column2,"入库商品列表");
						}
					}
				});
			}
			if(type.equals("JHTHD")){//进货退货单
				markColumn = 4 ;
				String[] column = {"单据编号","供应商","仓库","操作员","出库商品列表","备注","总额合计"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint()  ;
						int i = table.rowAtPoint(mousePoint) ;
						currentRow = i ;
						if(table.columnAtPoint(mousePoint)== markColumn){
							String[] column2 = {"编号","名称","型号","数量","单价","金额","商品备注"};
							
							PurchaseReceiptPO thePO= (PurchaseReceiptPO) objects.get(i) ;
							ArrayList<Object> list = new ArrayList<>() ;
							for(PurchaseListItemPO item :thePO.getPurchaseList()){
								GoodsPO goodPO = item.getGoodsPO() ;
							    GoodsVO good = new GoodsVO(goodPO.getSerialNumber(), goodPO.getName(), goodPO.getModel(), goodPO.getPrice(), goodPO.getSalePrice(), goodPO.getLatestPrice(), goodPO.getLatestSalePrice(), goodPO.getGoodsClassNum()) ;
								PurchaseListItemVO itemVO = new PurchaseListItemVO(good, item.getQuantity()) ;
								list.add(itemVO) ;
							}
							new ShowListFrame(list, column2,"出库商品列表");
						}
					}
				});
			}
			if(type.equals("BYD")){//报溢单
				String[] column = {"商品编号","商品数量","商品单价","日期"};
				table = new JTable(new MyTableModel(objects, column, type));
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint() ;
						currentRow = table.rowAtPoint(mousePoint) ;
					}
				});
			}
			if(type.equals("BSD")){//报损单
				String[] column = {"商品编号","商品数量","商品单价","日期"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint() ;
						currentRow = table.rowAtPoint(mousePoint) ;
					}
				});
			}
			if(type.equals("ZSD")){//赠送单
				String[]  column = {"商品编号","客户名称","商品数量","商品单价","日期"} ;
				table = new JTable(new MyTableModel(objects, column, type)) ;
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						Point mousePoint = e.getPoint() ;
						currentRow = table.rowAtPoint(mousePoint) ;
					}
				});
			}
			
            table.setBackground(Color.white);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		
			jsc = new JScrollPane(table) ;
			 jsc.setBounds(5, 121, 690, 240);
			add(jsc);
		}
		
}
    class SaleConditionPanel extends JPanel{
    	private JTextField beginTimeField;
    	private JTextField endTimeField;
    	private JTextField sumOfComeInField;//总收入
    	private JTextField discountField;//总折让
    	private JTextField saleComeInField;//销售收入
    	private JTextField overFlowField;//报溢
    	private JTextField changeCostField;//成本调价
    	private JTextField diffOfInAndOutField;//进退货差价
    	private JTextField voucherField;//代金券与实际金额差价
    	private JTextField sumOfCostField;//总支出
    	private JTextField saleCostField;//销售成本
    	private JTextField breakageCostField;//报损支出
    	private JTextField sendCostField;//商品赠送
    	private JTextField profitField;//总利润

    	/**
    	 * Create the panel.
    	 */
    	public SaleConditionPanel() {
    		setLayout(null);
    		setBounds(80,74, 700,400);
    		
    		JLabel timesLabel = new JLabel("时间区间：");
    		timesLabel.setBounds(44, 21, 68, 15);
    		add(timesLabel);
    		
    		JLabel beginTiemLabel = new JLabel("起始时间");
    		beginTiemLabel.setBounds(122, 21, 68, 15);
    		add(beginTiemLabel);
    		
    		beginTimeField = new JTextField();
    		beginTimeField.setBounds(200, 18, 110, 21);
    		add(beginTimeField);
    		beginTimeField.setColumns(10);
    		beginTimeField.setText("<例如2014/10/10>");
    		new AddWordsChange(beginTimeField, "<例如2014/10/10>") ;
    		
    		JLabel endTimeLabel = new JLabel("截止时间");
    		endTimeLabel.setBounds(339, 21, 68, 15);
    		add(endTimeLabel);
    		
    		endTimeField = new JTextField();
    		endTimeField.setBounds(417, 18, 110, 21);
    		add(endTimeField);
    		endTimeField.setColumns(10);
    		endTimeField.setText("<例如2014/11/10>");
    		new AddWordsChange(endTimeField, "<例如2014/11/10>") ;
    		
    		
    		
    		JPanel comeInPanel = new JPanel();
    		comeInPanel.setBounds(103, 61, 538, 126);
    		comeInPanel.setBackground(Color.red);
    		add(comeInPanel);
    		comeInPanel.setLayout(null);
    		
    		JLabel sumOfComeInLabel = new JLabel("总收入");
    		sumOfComeInLabel.setBounds(10, 21, 54, 15);
    		comeInPanel.add(sumOfComeInLabel);
    		
    		sumOfComeInField = new JTextField();
    		sumOfComeInField.setBounds(76, 18, 66, 21);
    		comeInPanel.add(sumOfComeInField);
    		sumOfComeInField.setColumns(10);
    		
    		JLabel discountLabel = new JLabel("折让");
    		discountLabel.setBounds(167, 21, 54, 15);
    		comeInPanel.add(discountLabel);
    		
    		discountField = new JTextField();
    		discountField.setBounds(212, 18, 66, 21);
    		comeInPanel.add(discountField);
    		discountField.setColumns(10);
    		
    		JLabel saleComeInLabel = new JLabel("销售收入");
    		saleComeInLabel.setBounds(23, 60, 54, 15);
    		comeInPanel.add(saleComeInLabel);
    		
    		saleComeInField = new JTextField();
    		saleComeInField.setBounds(10, 95, 66, 21);
    		comeInPanel.add(saleComeInField);
    		saleComeInField.setColumns(10);
    		
    		JLabel overFlowLabel = new JLabel("商品报溢");
    		overFlowLabel.setBounds(92, 60, 72, 15);
    		comeInPanel.add(overFlowLabel);
    		
    		overFlowField = new JTextField();
    		overFlowField.setBounds(92, 95, 66, 21);
    		comeInPanel.add(overFlowField);
    		overFlowField.setColumns(10);
    		
    		JLabel changCostLabel = new JLabel("成本调价");
    		changCostLabel.setBounds(197, 60, 72, 15);
    		comeInPanel.add(changCostLabel);
    		
    		changeCostField = new JTextField();
    		changeCostField.setBounds(197, 95, 66, 21);
    		comeInPanel.add(changeCostField);
    		changeCostField.setColumns(10);
    	
    		
    		JLabel diffOfInAndOutLabel = new JLabel("进货退货差价");
    		diffOfInAndOutLabel.setBounds(293, 60, 96, 15);
    		comeInPanel.add(diffOfInAndOutLabel);
    		
    		diffOfInAndOutField = new JTextField();
    		diffOfInAndOutField.setBounds(304, 95, 66, 21);
    		comeInPanel.add(diffOfInAndOutField);
    		diffOfInAndOutField.setColumns(10);
    		
    		JLabel voucherLabel = new JLabel("代金券与实际收款差额");
    		voucherLabel.setBounds(399, 60, 140, 15);
    		comeInPanel.add(voucherLabel);
    		
    		voucherField = new JTextField();
    		voucherField.setBounds(425, 95, 66, 21);
    		comeInPanel.add(voucherField);
    		voucherField.setColumns(10);
    		
    		JPanel sumOfCostPanel = new JPanel();
    		sumOfCostPanel.setBounds(103, 207, 538, 101);
    		sumOfCostPanel.setBackground(Color.yellow);
    		add(sumOfCostPanel);
    		sumOfCostPanel.setLayout(null);
    		
    		JLabel sumOfCostLabel = new JLabel("总支出");
    		sumOfCostLabel.setBounds(10, 18, 54, 15);
    		sumOfCostPanel.add(sumOfCostLabel);

    		sumOfCostField = new JTextField();
    		sumOfCostField.setBounds(77, 15, 66, 21);
    		sumOfCostPanel.add(sumOfCostField);
    		sumOfCostField.setColumns(10);
    		
    		JLabel saleCostLabel = new JLabel("销售支出");
    		saleCostLabel.setBounds(87, 46, 54, 15);
    		sumOfCostPanel.add(saleCostLabel);
    		
    		saleCostField = new JTextField();
    		saleCostField.setBounds(77, 70, 66, 21);
    		sumOfCostPanel.add(saleCostField);
    		saleCostField.setColumns(10);
    		
    		JLabel breakageCostLabel = new JLabel("商品报损");
    		breakageCostLabel.setBounds(212, 46, 54, 15);
    		sumOfCostPanel.add(breakageCostLabel);
    		
    		breakageCostField = new JTextField();
    		breakageCostField.setBounds(200, 70, 66, 21);
    		sumOfCostPanel.add(breakageCostField);
    		breakageCostField.setColumns(10);
    		
    		JLabel sendCostLabel = new JLabel("商品赠出");
    		sendCostLabel.setBounds(340, 46, 54, 15);
    		sumOfCostPanel.add(sendCostLabel);
    		
    		sendCostField = new JTextField();
    		sendCostField.setBounds(328, 70, 66, 21);
    		sumOfCostPanel.add(sendCostField);
    		sendCostField.setColumns(10);
    		
    		JPanel profitPanel = new JPanel();
    		profitPanel.setBounds(103, 326, 538, 35);
    		profitPanel.setBackground(Color.green);
    		add(profitPanel);
    		profitPanel.setLayout(null);
    		
    		JLabel profitLabel = new JLabel("利润");
    		profitLabel.setBounds(10, 10, 54, 15);
    		profitPanel.add(profitLabel);
    		
    		profitField = new JTextField();
    		profitField.setBounds(74, 7, 66, 21);
    		profitPanel.add(profitField);
    		profitField.setColumns(10);
    		
    		JLabel typeOfComeInLabel = new JLabel("收入类");
    		typeOfComeInLabel.setBounds(24, 109, 54, 15);
    		add(typeOfComeInLabel);
    		
    		JLabel typeOfCostLabel = new JLabel("支出类");
    		typeOfCostLabel.setBounds(24, 254, 54, 15);
    		add(typeOfCostLabel);
    		
    		JLabel typeOfProfitLabel = new JLabel("利润");
    		typeOfProfitLabel.setBounds(24, 336, 54, 15);
    		add(typeOfProfitLabel);
    		
    		JLabel sureLabel = new JLabel("查询",JLabel.CENTER) ;
    		sureLabel.setBounds(535, 21, 68, 15);
    		add(sureLabel) ;
    		sureLabel.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				String beginTime = beginTimeField.getText() ;
    				String endTime = endTimeField.getText() ;
    				if(beginTime.equals("<例如2014/10/10>")||endTime.equals("<例如2014/10/10>")){
    					new warningDialog("请输入时间区间");
    				}else{
    					double[] result = infoController.showSalesConditionInfo(beginTime, endTime) ;
    					double inCome = result[1] +result[2] +result[3] +result[4] +result[5] ;
    					double getOut = result[6] +result[7] +result[8] ;
    					double profit = inCome - getOut ;
    					sumOfComeInField.setText(String.valueOf(inCome)); 
    					discountField.setText(String.valueOf(result[0]));
    					saleComeInField.setText(String.valueOf(result[1]));
    					overFlowField.setText(String.valueOf(result[2]));
    					changeCostField.setText(String.valueOf(result[3]));
    					diffOfInAndOutField.setText(String.valueOf(result[4]));
    					voucherField.setText(String.valueOf(result[5])) ;
    					sumOfCostField.setText(String.valueOf(getOut));
    					saleCostField.setText(String.valueOf(result[6]));
    					breakageCostField.setText(String.valueOf(result[7]));
    					sendCostField.setText(String.valueOf(result[8]));
    					profitField.setText(String.valueOf(profit));
    				}
    			}
			});
    		JLabel cancle = new JLabel("撤销");
    		cancle.setBounds(610, 21, 68, 15);
    		add(cancle) ;
    		cancle.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				beginTimeField.setText("<例如2014/10/10>");
    				endTimeField.setText("<例如2014/11/10>");
    				sumOfComeInField.setText("");
    				discountField.setText("");
    				saleComeInField.setText("");
    				overFlowField.setText("");
    				changeCostField.setText("");
    				diffOfInAndOutField.setText("");
    				voucherField.setText("");
    				sumOfCostField.setText("");
    				saleCostField.setText("");
    				breakageCostField.setText("");
    				sendCostField.setText("");
    				profitField.setText("");
    			}
			});
    	}
}

	class MyTableModel extends AbstractTableModel{
		private ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
		private String[] columnOfReceipt ;
		 

		public MyTableModel(ArrayList<Object> theDatas ,String[] theColumn,String type){
			if(type.equals("XSD")){//销售单
			for(Object object : theDatas){
				SalesReceiptPO receipt = (SalesReceiptPO)object ;
				ArrayList<Object> oneRow = new ArrayList<Object>() ;
				oneRow.add(receipt.getSerialNumber()) ;
				oneRow.add(receipt.getCustomerPO().getName()) ;
				oneRow.add(receipt.getSalesman()) ;
				oneRow.add(receipt.getUserPO().getUserName()) ;
				oneRow.add(receipt.getCommodityNum()) ;
				oneRow.add("展开");
				oneRow.add(receipt.getPriceBefore()) ;
				oneRow.add(receipt.getDiscout()) ;
				oneRow.add(String.valueOf(receipt.getVocher())) ;
				oneRow.add(receipt.getFinalprice()) ;
				oneRow.add(receipt.getComment()) ;
				datas.add(oneRow) ;
			}
			}
			if(type.equals("XSTHD")){//销售退货单
				for(Object object : theDatas){
					SalesReceiptPO receipt = (SalesReceiptPO)object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(receipt.getSerialNumber()) ;
					oneRow.add(receipt.getCustomerPO().getName()) ;
					oneRow.add(receipt.getSalesman()) ;
					oneRow.add(receipt.getUserPO().getUserName()) ;
					oneRow.add(receipt.getCommodityNum()) ;
					oneRow.add("展开");
					oneRow.add(String.valueOf(receipt.getPriceBefore())) ;
					oneRow.add(String.valueOf(receipt.getDiscout())) ;
					oneRow.add(String.valueOf(receipt.getVocher())) ;
					oneRow.add(String.valueOf(receipt.getFinalprice())) ;
					oneRow.add(receipt.getComment()) ;
					datas.add(oneRow) ;                                          
				}
			}
			
			if(type.equals("商品清单")||type.equals("出货商品清单")){//商品清单
				for(Object object : theDatas){
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					SalesListItemPO item = (SalesListItemPO)object ;
					oneRow.add(item.getGoodsPO().getSerialNumber()) ;
					oneRow.add(item.getGoodsPO().getName()) ;
					oneRow.add(item.getGoodsPO().getModel()) ;
					oneRow.add(item.getQuantity());
					oneRow.add(item.getGoodsPO().getSalePrice()) ;
					oneRow.add(item.getTotalPrice()) ;
					oneRow.add(item.getGoodsPO().getComment()) ;
					datas.add(oneRow) ;
				}
			}
			
			if(type.equals("SKD")||type.equals("FKD")){//收付款单
				for(Object object: theDatas){
					CollectionOrPaymentVO receipt = (CollectionOrPaymentVO)object;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(receipt.getNumber());
					oneRow.add(receipt.getCustomer()) ;
					oneRow.add(receipt.getUser()) ;
					oneRow.add("展开");
					oneRow.add(receipt.getTotal()) ;
					datas.add(oneRow);
				}
			}
			if(type.equals("转账列表")){//收付款单中的转账列表
				for(Object object : theDatas){
					TransferListItemVO theItem = (TransferListItemVO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theItem.getAccount()) ;
					oneRow.add(theItem.getTransferMoney()) ;
					oneRow.add(theItem.getRemark()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("XJFYD")){//现金费用单
				for(Object object : theDatas){
					CashVO receipt = (CashVO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(receipt.getNumber()) ;
					oneRow.add(receipt.getUser()) ;
					oneRow.add(receipt.getAccount()) ;
					oneRow.add("展开") ;
					oneRow.add(receipt.getSum()) ;
					datas.add(oneRow) ;
 				}
			}
			if(type.equals("条目清单")){//销售单中的条目清单
				for(Object object : theDatas){
					CaseListItemVO theCase = (CaseListItemVO)object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theCase.getCasename()) ;
					oneRow.add(theCase.getCaseMoney()) ;
					oneRow.add(theCase.getRemark()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("JHD")){//进货单
				for(Object object : theDatas){
					PurchaseReceiptPO receipt = (PurchaseReceiptPO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(receipt.getSerialNumber()) ;
					oneRow.add(receipt.getCustomerPO().getName()) ;
					oneRow.add("仓库一") ;
					oneRow.add(receipt.getUserPO().getUserName()) ;
					oneRow.add("展开") ;
					oneRow.add(receipt.getComments()) ;
					oneRow.add(receipt.getTotalPrice()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("入库商品列表")){//进货单中的入库商品列表
				for(Object object : theDatas){
					PurchaseListItemPO thePO  = (PurchaseListItemPO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(thePO.getGoodsPO().getSerialNumber()) ;
					oneRow.add(thePO.getGoodsPO().getName()) ;
					oneRow.add(thePO.getGoodsPO().getModel()) ;
					oneRow.add(thePO.getQuantity()) ;
					oneRow.add(thePO.getGoodsPO().getPrice()) ;
					oneRow.add(thePO.getTotalPrice()) ;
					oneRow.add(thePO.getGoodsPO().getComment()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("JHTHD")){//进货退货单
				for(Object object : theDatas){
					PurchaseReceiptPO receipt = (PurchaseReceiptPO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(receipt.getSerialNumber()) ;
					oneRow.add(receipt.getCustomerPO().getName()) ;
					oneRow.add("仓库一") ;
					oneRow.add(receipt.getUserPO().getUserName()) ;
					oneRow.add("展开") ;
					oneRow.add(receipt.getComments()) ;
					oneRow.add(receipt.getTotalPrice()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("出库商品列表")){//进货退货单中的出库商品列表
				for(Object object : theDatas){
					PurchaseListItemPO thePO  = (PurchaseListItemPO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(thePO.getGoodsPO().getSerialNumber()) ;
					oneRow.add(thePO.getGoodsPO().getName()) ;
					oneRow.add(thePO.getGoodsPO().getModel()) ;
					oneRow.add(thePO.getGoodsPO().getPrice()) ;
					oneRow.add(thePO.getTotalPrice()) ;
					oneRow.add(thePO.getGoodsPO().getComment()) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("BYD") || type.equals("BSD")){//报溢单和报损单
				for(Object object:theDatas){
					ReportCommodityVO theVO = (ReportCommodityVO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theVO.goodsVOId) ;
					oneRow.add(theVO.num) ;
					oneRow.add(theVO.price) ;
					oneRow.add(new String(new SimpleDateFormat("yyyy/MM/dd").format(theVO.date))) ;
					datas.add(oneRow) ;
				}
			}
			if(type.equals("ZSD")){
				for(Object object :theDatas){
					SendCommodityVO theVO = (SendCommodityVO) object ;
					ArrayList<Object> oneRow = new ArrayList<Object>() ;
					oneRow.add(theVO.goodsVOId) ;
					oneRow.add(theVO.customerVOName) ;
					oneRow.add(theVO.num) ;
					oneRow.add(theVO.price) ;
					oneRow.add(new String(new SimpleDateFormat("yyyy/MM/dd").format(theVO.date))) ;
					datas.add(oneRow) ;
				}
			}
			columnOfReceipt = theColumn ;
		}

	    public MyTableModel(ArrayList<CollectionOrPaymentVO> receipts ,String[] column,boolean isPass) {
			// TODO Auto-generated constructor stub
	    	for(CollectionOrPaymentVO receipt : receipts){
				ArrayList<Object> oneRow = new ArrayList<Object>() ;
				oneRow.add(receipt.getNumber());
				oneRow.add(receipt.getCustomer()) ;
				oneRow.add(receipt.getUser()) ;
				oneRow.add("展开");
				oneRow.add(receipt.getTotal()) ;
				datas.add(oneRow);
			}
	    	columnOfReceipt = column ;
		}
		@Override
		public String getColumnName(int col)
	     {
	          return columnOfReceipt[col];
	     }
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnOfReceipt.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return datas.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			// TODO Auto-generated method stub
			return datas.get(row).get(column);
		}
		public boolean isCellEditable(int row, int col) { 
				return false ;
		}
		public void setValueAt(Object value, int row, int col) {  
	        datas.get(row).set(col,  value);
	        fireTableCellUpdated(row, col);  
	    }	
	}
    class ShowListFrame extends JFrame{

    	private JPanel contentPane;
    	private JTable table;

    	/**
    	 * Create the frame.
    	 */
    	public ShowListFrame(ArrayList<Object> items,String[] column) {
    		this.setVisible(true);
    		this.setTitle("商品清单");
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		setBounds(500, 200, 350, 300);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPane.setBackground(Color.yellow);
    		setContentPane(contentPane);
    		contentPane.setLayout(null);
 
    		table = new JTable(new MyTableModel(items,column,"SPQD"));
    		table.setBackground(Color.white);
    		
    		JScrollPane jsc = new JScrollPane(table);
    		jsc.setBounds(0,0,350,170);
    		jsc.setBackground(Color.green);
    		contentPane.add(jsc) ;
    		
    		JButton sureButton = new JButton("确定");
    		sureButton.setBounds(110, 200, 100, 30);
    		contentPane.add(sureButton) ;
    		sureButton.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e){
    				dispose() ;
    			}
			});
    		
    	}
    	public ShowListFrame(ArrayList<Object> items,String[] column,String name){
    		this.setVisible(true);
    		this.setTitle(name);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		setBounds(500, 200, 350, 300);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPane.setBackground(Color.yellow);
    		setContentPane(contentPane);
    		contentPane.setLayout(null);
 
    		table = new JTable(new MyTableModel(items,column,name));
    		table.setBackground(Color.white);
    		
    		JScrollPane jsc = new JScrollPane(table);
    		jsc.setBounds(0,0,350,170);
    		jsc.setBackground(Color.green);
    		contentPane.add(jsc) ;
    		
    		JButton sureButton = new JButton("确定");
    		sureButton.setBounds(110, 200, 100, 30);
    		contentPane.add(sureButton) ;
    		sureButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
    		
    	}
}
    
    class warningDialog extends JDialog{
		public warningDialog(String warnings){
			this.setSize(284, 158);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setModal(true);
			
			JLabel warningLabel = new JLabel(warnings,JLabel.CENTER);
			warningLabel.setFont(new Font("宋体",Font.BOLD,14));
			
			this.add(warningLabel);
		}
	}
	
	
	class PromotionPanel extends JPanel {     //制定促销策略panel
		private JTable table;
		private JScrollPane tablePane; 
		public PromotionPanel(JFrame theFrame) {
			this.setVisible(false);
			this.setLayout(null);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(173, 137, 115, 255));
			
			JLabel add1=new JLabel("添加特价包策略");
			add1.setBounds(80,40,100,20);
			this.add(add1);
			
			add1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//new ManagerFrameHelper("package");
					ManagerFrameHelper m=new ManagerFrameHelper("");
					ManagerFrameHelper.AddPackageFrame addFrame=m.new AddPackageFrame();
					addFrame.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							tableRefresh();
						}
					});
					
				}
			});
			
			JLabel add2=new JLabel("添加达总价送赠品策略");
			add2.setBounds(200,40,130,20);
			this.add(add2);
			
			add2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//new ManagerFrameHelper("gifts");
					ManagerFrameHelper m=new ManagerFrameHelper("");
					ManagerFrameHelper.AddGiftsFrame addFrame=m.new AddGiftsFrame();
					addFrame.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							tableRefresh();
						}
					});
					
				}
			});
			
			JLabel add3=new JLabel("添加达总价送代金券策略");
			add3.setBounds(360,40,150,20);
			this.add(add3);
			
			add3.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//new ManagerFrameHelper("voucher");
					ManagerFrameHelper m=new ManagerFrameHelper("");
					ManagerFrameHelper.AddVoucherFrame addFrame=m.new AddVoucherFrame();
					addFrame.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							tableRefresh();
						}
					});
				}
			});
			
			JLabel delete=new JLabel("删除策略");
			delete.setBounds(520,40,130,20);
			this.add(delete);
			
			delete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//new ManagerFrameHelper("deletePromotion");
					ManagerFrameHelper m=new ManagerFrameHelper("");
					ManagerFrameHelper.deletePromotionFrame deleteFrame=m.new deletePromotionFrame();
					deleteFrame.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							tableRefresh();
						}
					});
				}
			});
			
			JLabel refresh=new JLabel("刷新列表");
			refresh.setBounds(727,40,130,20);
			this.add(refresh);
			
			refresh.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					tableRefresh();
				}
			});
			
			tableRefresh();
		
			theFrame.add(this);
		}
		
		public ArrayList<ArrayList<Object>> getTableData(){
			ArrayList<PromotionVO> promotions=new ArrayList<PromotionVO>(new PromotionController().show());
			ArrayList<ArrayList<Object>> tableData=new ArrayList<ArrayList<Object>>();
			for(int i=0;i<promotions.size();i++){
				ArrayList<Object> datas=new ArrayList<Object>();
				datas.add((PromotionSort)promotions.get(i).getPromotionType());
				datas.add((String)promotions.get(i).getPromotionId());
				ArrayList<GoodsPO> pGoods=(ArrayList<GoodsPO>)promotions.get(i).getPromotionGoods();
				if(pGoods!=null)
					datas.add(pGoods.get(0).getName()+"、"+pGoods.get(1).getName());
				else
					datas.add("无");
				datas.add((double)promotions.get(i).getLeastPrice());
				datas.add((double)promotions.get(i).getOffPrice());
				ArrayList<GoodsPO> presents=(ArrayList<GoodsPO>)promotions.get(i).getPresents();
				if(presents!=null)
					datas.add(presents.get(0).getName());
				else
					datas.add("无");
				datas.add((int)promotions.get(i).getVoucher());
				datas.add((String)promotions.get(i).getStartTime());
				datas.add((String)promotions.get(i).getEndTime());
				datas.add((Level)promotions.get(i).getCustomer());
				switch ((PromotionSort)datas.get(0)) {
				case Package:
					datas.set(3, 0);  //leastPrice
					datas.set(5, "无");   //presents
					datas.set(6, 0);   //voucher
					break;
				case Gifts:
					datas.set(2, "无");
					datas.set(4, 0);
					datas.set(6,0);
					break;
				case Voucher:
					datas.set(2, "无");
					datas.set(4, 0);
					datas.set(5, "无");
					break;
				}
				tableData.add(datas);
			} 
			return tableData;
		}
		
		public  void tableRefresh(){
			String[] columnTitle={"策略类型","策略编号","组合商品降价","优惠需达金额","降价金额","赠品列表",
					"赠送代金券金额","起始时间","终止时间","客户最低等级"};
			ArrayList<ArrayList<Object>> tableData=getTableData();
			table=new JTable(new ManagerTableModel(tableData,columnTitle));
			table.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		    
		    table.getModel().addTableModelListener(new TableModelListener(){     //检测是否有内容更改
		    	public void tableChanged(TableModelEvent e) {     //进行的操作
		    	}
		    });
		    table.repaint();
		    
		    if(tablePane!=null)
		    	tablePane.setVisible(false);    //重要！防止表格出现错位
		    tablePane=new JScrollPane(table);
		    tablePane.setSize(700,400);
			tablePane.setLocation(80, 74);
			tablePane.repaint();
			this.add(tablePane);
		}
	}
	
	class ManagerTableModel extends AbstractTableModel{      //表格模型
		private ArrayList<ArrayList<Object>> tableData;
		private String[] columnTitle;
		public ManagerTableModel(ArrayList<ArrayList<Object>> data,String[] title) {
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
			 return true;
		 }
		
		public void setValueAt(Object value, int row, int col) {  
	        tableData.get(row).set(col,  value);
	        fireTableCellUpdated(row, col);  
	    }	
	}

	class GoodsInfoFrame extends JFrame{
		public GoodsInfoFrame(ArrayList<PurchaseListItemPO> purchaseItems,ArrayList<SalesListItemPO> salesItems){
			this.setVisible(true);
			setBounds(100, 100, 585, 451);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			JLabel titleLabel = new JLabel("商品列表");
			titleLabel.setBounds(266, 22, 57, 23);
			getContentPane().add(titleLabel);
			
			String[] tableTitle={"商品编号","商品名称","商品型号","数量","单价","金额","备注"};
			ArrayList<ArrayList<Object>> tableData=new ArrayList<ArrayList<Object>>();
			if(salesItems==null){
			for(int i=0;i<purchaseItems.size();i++){
				GoodsPO goods=purchaseItems.get(i).getGoodsPO();
				ArrayList<Object> oneData=new ArrayList<Object>();
				oneData.add(goods.getSerialNumber());
				oneData.add(goods.getName());
				oneData.add(goods.getModel());
				oneData.add(purchaseItems.get(i).getQuantity());
				oneData.add(goods.getPrice());
				oneData.add(purchaseItems.get(i).getTotalPrice());
				oneData.add(goods.getComment());
				tableData.add(oneData);
				}
			}
			else if(purchaseItems==null){
				for(int i=0;i<salesItems.size();i++){
					GoodsPO goods=salesItems.get(i).getGoodsPO();
					ArrayList<Object> oneData=new ArrayList<Object>();
					oneData.add(goods.getSerialNumber());
					oneData.add(goods.getName());
					oneData.add(goods.getModel());
					oneData.add(salesItems.get(i).getQuantity());
					oneData.add(goods.getSalePrice());
					oneData.add(salesItems.get(i).getTotalPrice());
					oneData.add(goods.getComment());
					tableData.add(oneData);
					}
			}
					
		    JTable table=new JTable(new ManagerTableModel(tableData, tableTitle));
		    table.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		    
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 55, 549, 335);
			getContentPane().add(scrollPane);
			 
			this.repaint();
		}
		
	}
	
	class TransferFrame extends JFrame{
		public TransferFrame(ArrayList<TransferListItemVO> transfers){
			this.setVisible(true);
			setBounds(100, 100, 585, 451);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			JLabel titleLabel = new JLabel("转账列表");
			titleLabel.setBounds(266, 22, 57, 23);
			getContentPane().add(titleLabel);
			
			String[] tableTitle={"银行账户","转账金额","备注"};
			ArrayList<ArrayList<Object>> tableData=new ArrayList<ArrayList<Object>>();
			if(transfers!=null){
				for(int i=0;i<transfers.size();i++){
					TransferListItemVO t=transfers.get(i);
					ArrayList<Object> oneData=new ArrayList<Object>();
					oneData.add(t.getAccount());
					oneData.add(t.getTransferMoney());
					oneData.add(t.getRemark());
					tableData.add(oneData);
					}
				}
			JTable table=new JTable(new ManagerTableModel(tableData, tableTitle));
		    table.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, render);
		    
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 55, 549, 335);
			getContentPane().add(scrollPane);
			 
			this.repaint();
			}
		}

	public static void main(String[] args){
		new ManagerFrame(new UserVO("yaomengzhou", "123", UserSort.Manager,3));
	}
}

