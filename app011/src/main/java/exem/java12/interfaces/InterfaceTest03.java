package exem.java12.interfaces;

public class InterfaceTest03 {

	public static void main(String[] args) {
		
		Calculator03 myCal = new ChildCalculator03();
		
		int result = myCal.add(5, 4);
		
		System.out.println(result);

	}

}
