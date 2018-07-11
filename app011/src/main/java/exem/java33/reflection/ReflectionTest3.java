package exem.java33.reflection;
import java.lang.reflect.Method;

public class ReflectionTest3 {
	public static void main(String[] args) throws ClassNotFoundException
	{
		// 클래스 정보를 가져온다.
		Class<?> cls = Class.forName("exem.java33.MyClass1");

		// 메소드의 이름 출력.
		Method[] methods = cls.getDeclaredMethods();
		
		for( Method method : methods ){
			StringBuffer sb = new StringBuffer();
			sb.append(method.getName());

			// 메소드 인자 타입 출력
			Class<?>[] argTypes = method.getParameterTypes();
			sb.append("(");
			int size = argTypes.length;
			for( Class<?> argType : argTypes ){
				String argName = argType.getName();
				sb.append(argName + " val");
				if( --size != 0 ){
					sb.append(", ");
				}
			}
			sb.append(")");

			// 리턴타입 출력
			Class<?> returnType = method.getReturnType();
			sb.append(" : " + returnType.getName());

			System.out.println(sb);
		}
	}
}
