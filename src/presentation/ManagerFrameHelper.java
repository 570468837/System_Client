package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import businesslogicservice.PromotionBLService.PromotionController;
import Config.Level;
import Config.PromotionSort;
import PO.GoodsPO;
import VO.PromotionVO;

public class ManagerFrameHelper {
	public ManagerFrameHelper(String command){
		switch (command){
			case "package":new AddPackageFrame(); break;
			case "gifts": new AddGiftsFrame(); break;
		}
	}
	class AddPackageFrame extends JFrame{
		private JTextField textField;
		private JButton button;
		private JTextField goods1Field;
		private JTextField goods2Field;
		private JLabel offPriceLabel;
		private JTextField offPriceField;
		private JLabel startLabel;
		private JLabel Goods1Label;
		private JLabel GoodsLabel2;
		private JComboBox startMonthNum;
		private JLabel label_4;
		private JComboBox startDayNum;
		private JLabel lblNewLabel_3;
		private JLabel endTimeLabel;
		private JLabel label_8;
		private JComboBox customerLevel;
		private JComboBox endYearNum;
		private JLabel label_5;
		private JComboBox endMonthNum;
		private JLabel label_6;
		private JComboBox endDayNum;
		private JLabel label_9;
		private JButton confirmButton;
		private JButton cancelButton;
		private JLabel yuanLabel;
		public AddPackageFrame(){
			this.setVisible(true);
			setBounds(100, 100, 556, 475);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			
			JLabel titleLabel = new JLabel("制定特价包促销策略");
			titleLabel.setBounds(177, 22, 118, 30);
			getContentPane().add(titleLabel);
			
			JLabel GoodsInPutLabel = new JLabel("请输入特价包中的商品编号：");
			GoodsInPutLabel.setBounds(24, 96, 180, 25);
			getContentPane().add(GoodsInPutLabel);
			
			goods1Field = new JTextField();
			goods1Field.setBounds(249, 82, 97, 21);
			getContentPane().add(goods1Field);
			goods1Field.setColumns(10);
			
			goods2Field = new JTextField();
			goods2Field.setColumns(10);
			goods2Field.setBounds(249, 125, 97, 21);
			getContentPane().add(goods2Field);
			
			offPriceLabel = new JLabel("请输入降价金额：");
			offPriceLabel.setBounds(75, 161, 111, 15);
			getContentPane().add(offPriceLabel);
			
			offPriceField = new JTextField();
			offPriceField.setColumns(10);
			offPriceField.setBounds(196, 158, 97, 21);
			getContentPane().add(offPriceField);
			
			startLabel = new JLabel("请选择策略的起始时间：");
			startLabel.setBounds(24, 210, 150, 15);
			getContentPane().add(startLabel);
			
			Goods1Label = new JLabel("商品1：");
			Goods1Label.setBounds(197, 85, 54, 15);
			getContentPane().add(Goods1Label);
			
			GoodsLabel2 = new JLabel("商品2：");
			GoodsLabel2.setBounds(196, 128, 54, 15);
			getContentPane().add(GoodsLabel2);
			
			int yearNow=getYearNow();
			int monthNow=getMonthNow();
			int dayNow=getDateNow();
			
			String []startyears=new String[5];
			String []endyears=new String[5];
			String []months=new String[12];
			String []dates=new String[31];
			for(int i=0;i<5;i++){
				startyears[i]=String.valueOf(yearNow);
				endyears[i]=String.valueOf(yearNow++);
			}
			for(int i=0;i<12;i++){
				months[i]=String.valueOf(i+1);
			}
			for(int i=0;i<31;i++){
				dates[i]=String.valueOf(i+1);
			}
			
			JComboBox startYearNum = new JComboBox(startyears);
			startYearNum.setBounds(177, 207, 74, 21);
			getContentPane().add(startYearNum);
			
			JLabel lblNewLabel_2 = new JLabel("年");
			lblNewLabel_2.setBounds(261, 210, 20, 15);
			getContentPane().add(lblNewLabel_2);
			
			startMonthNum = new JComboBox(months);
			startMonthNum.setBounds(283, 207, 49, 21);
			getContentPane().add(startMonthNum);
			
			label_4 = new JLabel("月");
			label_4.setBounds(342, 210, 20, 15);
			getContentPane().add(label_4);
			
			startDayNum = new JComboBox(dates);
			startDayNum.setBounds(366, 207, 49, 21);
			getContentPane().add(startDayNum);
			
			lblNewLabel_3 = new JLabel("日");
			lblNewLabel_3.setBounds(425, 210, 20, 15);
			getContentPane().add(lblNewLabel_3);
			
			endTimeLabel = new JLabel("请选择策略的终止时间：");
			endTimeLabel.setBounds(24, 265, 150, 15);
			getContentPane().add(endTimeLabel);
			
			label_8 = new JLabel("请选择享受优惠的最低客户等级：");
			label_8.setBounds(24, 318, 200, 15);
			getContentPane().add(label_8);
			
			customerLevel = new JComboBox(new String[]{"一级","二级","三级","四级","五级VIP"});
			customerLevel.setBounds(238, 315, 60, 21);
			getContentPane().add(customerLevel);
			
			endYearNum = new JComboBox(endyears);
			endYearNum.setBounds(177, 259, 74, 21);
			getContentPane().add(endYearNum);
			
			label_5 = new JLabel("年");
			label_5.setBounds(261, 262, 20, 15);
			getContentPane().add(label_5);
			
			endMonthNum = new JComboBox(months);
			endMonthNum.setBounds(283, 259, 49, 21);
			getContentPane().add(endMonthNum);
			
			label_6 = new JLabel("月");
			label_6.setBounds(342, 262, 20, 15);
			getContentPane().add(label_6);
			
			endDayNum = new JComboBox(dates);
			endDayNum.setBounds(366, 259, 49, 21);
			getContentPane().add(endDayNum);
			endDayNum.repaint();
			
			label_9 = new JLabel("日");
			label_9.setBounds(425, 262, 20, 15);
			getContentPane().add(label_9);
			
			confirmButton = new JButton("确认");
			confirmButton.setBounds(147, 374, 88, 30);
			getContentPane().add(confirmButton);
			
			cancelButton = new JButton("取消");
			cancelButton.setBounds(296, 374, 88, 30);
			getContentPane().add(cancelButton);
			
			yuanLabel = new JLabel("元");
			yuanLabel.setBounds(308, 161, 54, 15);
			getContentPane().add(yuanLabel);
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Date date=new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMM");
					
					ArrayList<GoodsPO> goods=new ArrayList<GoodsPO>();
					goods.add(new GoodsPO(goods1Field.getText(), null, null, 0, 0, null));
					goods.add(new GoodsPO(goods2Field.getText(), null, null, 0, 0, null));
					
					Level customer=null;
					switch((String)customerLevel.getSelectedItem()){
					case "一级": customer=Level.firstClass; break;
					case "二级": customer=Level.secondClass; break;
					case "三级": customer=Level.thirdClass; break;
					case "四级": customer=Level.forthClass; break;
					case "五级VIP": customer=Level.fiveClassVIP; break;
					}
					PromotionVO newvo=new PromotionVO(PromotionSort.Package, dateFormat.format(date),
							goods, 0, Double.parseDouble(offPriceField.getText()), null, 0, 
							(String)startYearNum.getSelectedItem()+"-"+(String)startMonthNum.getSelectedItem()+"-"+(String)startDayNum.getSelectedItem(), 
							(String)endYearNum.getSelectedItem()+"-"+(String)endMonthNum.getSelectedItem()+"-"+(String)endDayNum.getSelectedItem(), 
							customer);
					
					new PromotionController().addPackage(newvo);
					dispose();
				}
				
			});
		
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			}); 
			this.repaint();
		}
	}

	class AddGiftsFrame extends JFrame{
		private JTextField textField;
		private JButton button;
		private JTextField goods1Field;
		private JLabel leastPriceLabel;
		private JTextField leastPriceField;
		private JLabel startLabel;
		private JLabel presentLabel;
		private JComboBox startMonthNum;
		private JLabel label_4;
		private JComboBox startDayNum;
		private JLabel lblNewLabel_3;
		private JLabel endTimeLabel;
		private JLabel label_8;
		private JComboBox customerLevel;
		private JComboBox endYearNum;
		private JLabel label_5;
		private JComboBox endMonthNum;
		private JLabel label_6;
		private JComboBox endDayNum;
		private JLabel label_9;
		private JButton confirmButton;
		private JButton cancelButton;
		private JLabel yuanLabel;
		private JTextField presentsField;
		public AddGiftsFrame(){
				this.setVisible(true);
				setBounds(100, 100, 556, 475);
				this.setLocationRelativeTo(null);
				this.setLayout(null);
				
				JLabel titleLabel = new JLabel("制定送赠品促销策略");
				titleLabel.setBounds(214, 22, 118, 30);
				getContentPane().add(titleLabel);
				
				leastPriceLabel = new JLabel("请输入购物需满金额：");
				leastPriceLabel.setBounds(24, 89, 134, 15);
				getContentPane().add(leastPriceLabel);
				
				leastPriceField = new JTextField();
				leastPriceField.setColumns(10);
				leastPriceField.setBounds(177, 86, 97, 21);
				getContentPane().add(leastPriceField);
				
				startLabel = new JLabel("请选择策略的起始时间：");
				startLabel.setBounds(24, 210, 150, 15);
				getContentPane().add(startLabel);
				
				presentLabel = new JLabel("请输入赠品的商品编号：");
				presentLabel.setBounds(24, 149, 150, 15);
				getContentPane().add(presentLabel);
				
				int yearNow=getYearNow();
				int monthNow=getMonthNow();
				int dayNow=getDateNow();
				
				String []startyears=new String[5];
				String []endyears=new String[5];
				String []months=new String[12];
				String []dates=new String[31];
				for(int i=0;i<5;i++){
					startyears[i]=String.valueOf(yearNow);
					endyears[i]=String.valueOf(yearNow++);
				}
				for(int i=0;i<12;i++){
					months[i]=String.valueOf(i+1);
				}
				for(int i=0;i<31;i++){
					dates[i]=String.valueOf(i+1);
				}
				
				JComboBox startYearNum = new JComboBox(startyears);
				startYearNum.setBounds(177, 207, 74, 21);
				getContentPane().add(startYearNum);
				
				JLabel lblNewLabel_2 = new JLabel("年");
				lblNewLabel_2.setBounds(261, 210, 20, 15);
				getContentPane().add(lblNewLabel_2);
				
				startMonthNum = new JComboBox(months);
				startMonthNum.setBounds(283, 207, 49, 21);
				getContentPane().add(startMonthNum);
				
				label_4 = new JLabel("月");
				label_4.setBounds(342, 210, 20, 15);
				getContentPane().add(label_4);
				
				startDayNum = new JComboBox(dates);
				startDayNum.setBounds(366, 207, 49, 21);
				getContentPane().add(startDayNum);
				
				lblNewLabel_3 = new JLabel("日");
				lblNewLabel_3.setBounds(425, 210, 20, 15);
				getContentPane().add(lblNewLabel_3);
				
				endTimeLabel = new JLabel("请选择策略的终止时间：");
				endTimeLabel.setBounds(24, 265, 150, 15);
				getContentPane().add(endTimeLabel);
				
				label_8 = new JLabel("请选择享受优惠的最低客户等级：");
				label_8.setBounds(24, 318, 200, 15);
				getContentPane().add(label_8);
				
				customerLevel = new JComboBox(new String[]{"一级","二级","三级","四级","五级"});
				customerLevel.setBounds(238, 315, 57, 21);
				getContentPane().add(customerLevel);
				
				endYearNum = new JComboBox(endyears);
				endYearNum.setBounds(177, 259, 74, 21);
				getContentPane().add(endYearNum);
				
				label_5 = new JLabel("年");
				label_5.setBounds(261, 262, 20, 15);
				getContentPane().add(label_5);
				
				endMonthNum = new JComboBox(months);
				endMonthNum.setBounds(283, 259, 49, 21);
				getContentPane().add(endMonthNum);
				
				label_6 = new JLabel("月");
				label_6.setBounds(342, 262, 20, 15);
				getContentPane().add(label_6);
				
				endDayNum = new JComboBox(dates);
				endDayNum.setBounds(366, 259, 49, 21);
				getContentPane().add(endDayNum);
				
				label_9 = new JLabel("日");
				label_9.setBounds(425, 262, 20, 15);
				getContentPane().add(label_9);
				
				confirmButton = new JButton("确认");
				confirmButton.setBounds(147, 374, 88, 30);
				getContentPane().add(confirmButton);
				
				cancelButton = new JButton("取消");
				cancelButton.setBounds(296, 374, 88, 30);
				getContentPane().add(cancelButton);
				
				yuanLabel = new JLabel("元");
				yuanLabel.setBounds(284, 89, 54, 15);
				getContentPane().add(yuanLabel);
				
				presentsField = new JTextField();
				presentsField.setColumns(10);
				presentsField.setBounds(177, 146, 97, 21);
				getContentPane().add(presentsField);
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				}); 
				this.repaint();
			}
		
		
		public int getYearNow(){
			Calendar c=Calendar.getInstance();
			return c.get(Calendar.YEAR);
		}
		
		public int getMonthNow(){
			Calendar c=Calendar.getInstance();
			return c.get(Calendar.MONTH)+1;
		}
		
		public int getDateNow(){
			Calendar c=Calendar.getInstance();
			return c.get(Calendar.DATE);
		}
		}

	
	public int getYearNow(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}
	
	public int getMonthNow(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.MONTH)+1;
	}
	
	public int getDateNow(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.DATE);
	}
	
	public String standardHM(){
		Calendar c=Calendar.getInstance();
		String hour=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String minute=String.valueOf(c.get(Calendar.MINUTE));
		if(hour.length()==1){
			hour="0"+hour;
		}
		if(minute.length()==1){
			minute="0"+minute;
		}
		return hour+minute;
	}
	

	public static void main(String[] args){
		new ManagerFrameHelper("gifts");
	}
}
