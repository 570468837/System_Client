package presentation;

import java.awt.event.*;

import javax.swing.*;


/**
 * @author hutao
 * 用于实现界面的移动
 * 想知道怎么用吗 ╮(╯▽╰)╭
 * 那好吧
 * 
 * 在写的每一个JFrame子类的最后面写上
 * 
 * MoveOfFrame m = new MoveOfFrame(this);
 * this.addMouseListener(m.getPoint);
 * this.addMouseMotionListener(m.move);
 * 
 * 然后在前面的设置里加上一条
 * this.setUndecorated(true);
 * 把边框去掉=、=
 * 
 */
public class MoveOfFrame {
	int x, y;
	GetPoint getPoint;
	Move move;
	public MoveOfFrame(JFrame theFrame) {
		getPoint = new GetPoint();
	    move = new Move(theFrame);
	}
	
	
	
	
	public class GetPoint extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			x = event.getX();
			y = event.getY();	
		}
	}

	class Move extends MouseMotionAdapter {
		JFrame theFrame;
		public Move(JFrame theFrame) {
			this.theFrame = theFrame;
		}
		public void mouseDragged(MouseEvent event) {
			theFrame.setLocation(theFrame.getX() + event.getX() - x, 
					theFrame.getY() + event.getY() - y);
		}
	}
	

}