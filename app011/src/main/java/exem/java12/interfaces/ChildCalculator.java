package exem.java12.interfaces;

public class ChildCalculator extends Calculator {

	@Override
	public int minus() {
		
		//super.result = super.result -1;
		result = result -1;
		return result;
	}

}
