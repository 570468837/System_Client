package presentation;

import javax.swing.*;

import VO.UserVO;

@SuppressWarnings("serial")
public class UserPanel extends JPanel {
	//private UserPanel up = this;
	private UserVO user;
	private JLabel
	    //backgroundLabel,
	    head,
	    nameLabel,
	    sortLabel,
	    levelLabel;
	public UserPanel(UserVO vo) {
		super();
		user = vo;
		this.setBounds(40, 460, 100, 120);
		this.setLayout(null);
		
		head = new JLabel("用户信息");
		head.setBounds(0, 0, 100, 30);
		
		nameLabel = new JLabel(user.getUserName());
		nameLabel.setBounds(0, 30, 100, 30);
		
		String s;
		switch(user.getUserSort()) {
		    case Commodity : s = "库存管理人员"; break;
		    case PurchaseAndSaler : s = "进货销售人员"; break;
		    case Finance : s = "财务人员"; break;
		    case Manager : s = "总经理"; break;
		    case Admin : s = "管理员"; break;
		    default : s = "someting problem";
		}
		sortLabel = new JLabel(s);
		sortLabel.setBounds(0, 60, 100, 30);
		
		levelLabel = new JLabel("用户等级 : " + user.getLevel());
		levelLabel.setBounds(0, 90, 100, 30);
		
		this.add(head);
		this.add(nameLabel);
		this.add(sortLabel);
		this.add(levelLabel);
	}

}
