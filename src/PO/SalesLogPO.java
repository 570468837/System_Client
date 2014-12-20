package PO;

import java.io.Serializable;

public class SalesLogPO implements Serializable{
	private String operation="";
	private String operator="";
	private String time="";
	
	public SalesLogPO(String operation,String operator,String time){
		this.operation=operation;
		this.operator=operator;
		this.time=time;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
