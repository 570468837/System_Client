package businesslogicservice.LogBLService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import PO.SalesLogPO;

public class SalesLogController {
ArrayList<SalesLogPO> salesLogList=new ArrayList<SalesLogPO>();
	
	
	public SalesLogController(){
		read();
	}
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Logs/SalesLog.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			salesLogList=(ArrayList<SalesLogPO>)oin.readObject();
			}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}          
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			FileOutputStream fos;
			ObjectOutputStream oos;
			fos = new FileOutputStream("Logs/SalesLog.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(salesLogList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public ArrayList<SalesLogPO> show(){
		return this.salesLogList;
	}
	
	public void add(SalesLogPO log){
		salesLogList.add(log);
		save();
		
	}
}
