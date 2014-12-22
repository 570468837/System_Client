package presentation;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;












import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;











import businesslogicservice.ApprovalBLService.ApprovalBLService_Controller;
import businesslogicservice.CommodityBLService.CommodityController;
import businesslogicservice.FinanceBLService.FinanceController;
import businesslogicservice.GoodsBLService.GoodsController;
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
import VO.CollectionOrPaymentVO;
import VO.PromotionVO;
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
					table1Refresh();
				}
			});
			
			Receipt2.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					panel4.setVisible(false);
					table2Refresh();
				}
			});
			
			Receipt3.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					panel4.setVisible(false);
					table3Refresh();
				}
			});
			
			Receipt4.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(true);
					table4Refresh();
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
			
			
			JTable table1=new JTable(new MyTableModel(tableData1,columnTitle1));
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
		    
			JScrollPane tablePane1=new JScrollPane(table1);
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
			System.out.println(shows);
			for(int i=0;i<shows.size();i++){
				if(!(shows.get(i).isApprovedByManager()==false&&shows.get(i).isApprovedByCommodity()==false)){
					shows.remove(i);
					i--;
				}
			}
			
			ArrayList<ArrayList<SalesListItemPO>> salesItems=new ArrayList<ArrayList<SalesListItemPO>>();
			for(int i=0;i<shows.size();i++){
				SalesReceiptPO s=shows.get(i);
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
			
			
			JTable table2=new JTable(new MyTableModel(tableData2,columnTitle2));
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
		    
			JScrollPane tablePane2=new JScrollPane(table2);
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
					ApprovalBLService_Controller ac = new ApprovalBLService_Controller();
					ac.salesChangeGoods(isApproved);
					ac.addSendCommodityReceipt(isApproved);
					
					ac.salesChangeCustomer(isApproved);  //同时update操作
					
					ac.salesNotPassed(notApproved);
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
			
			JTable table3=new JTable(new MyTableModel(tableData3,columnTitle3));
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
		    
			JScrollPane tablePane3=new JScrollPane(table3);
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
			
			JTable table4=new JTable(new MyTableModel(tableData4,columnTitle4));
			table4.setFillsViewportHeight(true);     //显示表头
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();   //设置单元格内容居中
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table4.setDefaultRenderer(Object.class, render);
		    
		    
			JScrollPane tablePane4=new JScrollPane(table4);
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
	
	class InfoPanel extends JPanel {     //查看报表信息panel
		//private JTable goodsTable;
		public InfoPanel(JFrame theFrame) {
			this.setVisible(false);
			this.setBounds(140, 25, 835, 550);
			this.setBackground(new Color(185, 227, 217, 255));
			
			theFrame.add(this);
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
			table=new JTable(new MyTableModel(tableData,columnTitle));
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
	
	class MyTableModel extends AbstractTableModel{      //表格模型
		private ArrayList<ArrayList<Object>> tableData;
		private String[] columnTitle;
		public MyTableModel(ArrayList<ArrayList<Object>> data,String[] title) {
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
					oneData.add(goods.getPrice());
					oneData.add(salesItems.get(i).getTotalPrice());
					oneData.add(goods.getComment());
					tableData.add(oneData);
					}
			}
					
		    JTable table=new JTable(new MyTableModel(tableData, tableTitle));
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
			JTable table=new JTable(new MyTableModel(tableData, tableTitle));
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

