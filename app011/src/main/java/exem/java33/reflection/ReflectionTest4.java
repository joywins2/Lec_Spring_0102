package exem.java33.reflection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest4 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		Class<?> cls = Class.forName("exem.java33.MyClass1");
		Object obj = cls.newInstance();
		
		// 인자없는 메소드 가져와서 호출하기
        Method meth = cls.getMethod("getMethod1", null);
        String str = (String) meth.invoke(obj);
        System.out.println(str);

        		// 인자 1개있는 메소드 가져와서 호출하기
        Method meth2 = cls.getMethod("getMethod2", int.class);
        //...https://stackoverflow.com/questions/3661413/how-to-cast-an-object-to-an-int
        //int i2 = (int) meth2.invoke(obj, 10);
        int i2 = (Integer) meth2.invoke(obj, 10);
        System.out.println(i2);

        // 인자 2개 있는 static 메소드 가져와서 호출하기
        Method meth3 = cls.getMethod("getMethod3", int.class, String.class);
        int i3 = (Integer) meth3.invoke(obj, 10, "안녕"); // 인스턴스로 호출가능
        int i4 = (Integer) meth3.invoke(cls, 10, "안녕"); // 클래스명으로 호출가능
        int i5 = (Integer) meth3.invoke(null, 10, "안녕"); // null로 호출가능
        System.out.println(i3);
	}
}
