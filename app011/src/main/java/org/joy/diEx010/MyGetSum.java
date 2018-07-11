package org.joy.diEx010;

public class MyGetSum {
	
	public GetSum getSum;
	private int a;
	private int b;
	

	public MyGetSum(){		
	}
	
	public void sum(){
		getSum.sum(a, b);
	}
	
	public void setGetSum(GetSum getsum) {
		this.getSum = getsum;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setB(int b) {
		this.b = b;
	}
	
}
