package presentation;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
/**
 * 工具类
 * @author hutao
 * 
 */
public class AddWordsChange {
	/**
	 * 提供了一个改变文本框里文字样式的静态方法（"<请输入***>" → ""）
	 * @param jtf 传入的JTextField
	 * @param s 改变的文本内容
	 */
	public static void change(JTextField jtf, String s) {
		jtf.addFocusListener(new FocusListener() {
	    	public void focusGained(FocusEvent e) {
	    		if(jtf.getText().equals(s)) {
	    			jtf.setText("");
	    		}
	    	}
            public void focusLost(FocusEvent e) {
	    		if(jtf.getText().equals("")) {
	    			jtf.setText(s);
	    		}
	    	}
	    });
	}
	

}
