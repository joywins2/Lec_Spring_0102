package exem.java33.reflection;

public class MyClass1 {

	private String privateStr;
	public static int publicStaticInt;
	public String publicStr;

	public String getMethod1(){
		System.out.println("인자 없는 메소드1");
		return "메소드1 결과값";
	}
	public int getMethod2(int a){
		System.out.println("인자 있는 메소드2");
		return a + 3;
	}
	public static int getMethod3( int a, String b ){
		System.out.println("인자2개 있는 스태틱메소드3");
		return a;
	}
}
