package exem.java33.reflection;
import java.lang.reflect.Field;

public class ReflectionTest2 {
	public static void main(String[] args) throws ClassNotFoundException
	{
		// 클래스 정보를 가져온다.
		Class<?> cls = Class.forName("exem.java33.MyClass1");

		// 멤버 변수를 출력해보자. 
		Field[] fields = cls.getDeclaredFields();
		for( Field field : fields ){
			System.out.println(field.getType().getName()+" "+field.getName());
		}
	}
}
