package businesslogicservice.LogBLService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import PO.PurchaseLogPO;

public class PurchaseLogController {
	ArrayList<PurchaseLogPO> purchaseLogList=new ArrayList<PurchaseLogPO>();
	
	
	public PurchaseLogController(){
		read();
	}
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Logs/PurchaseLog.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			purchaseLogList=(ArrayList<PurchaseLogPO>)oin.readObject();
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
			fos = new FileOutputStream("Logs/PurchaseLog.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(purchaseLogList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public ArrayList<PurchaseLogPO> show(){
		return this.purchaseLogList;
	}
	
	public void add(PurchaseLogPO log){
		purchaseLogList.add(log);
		save();
		
	}
	
}
