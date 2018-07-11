package exem.java34.annotation;
import java.lang.reflect.Field;

public class AnnotationTest {

    public static void main(String[] args) throws ClassNotFoundException, 
    IllegalArgumentException, IllegalAccessException, InstantiationException{
        Class<?> obj = Class.forName("exem.java34.annotation.MyClass");
        MyClass myclass = (MyClass) obj.newInstance();
        
        // 필드에 접근한다.
        Field [] fields = myclass.getClass().getDeclaredFields();
        
        // 필드에 부여된 어노테이션을 읽어서 필드값을 어노테이션값으로 바꾼다.
        for( Field field : fields ){
            MyTag myTag = field.getAnnotation(MyTag.class);
            field.setAccessible(true); // private 필드에 대한 접근을 허용함
            field.set(myclass, myTag.myName()); // 어노테이션의 myName에 지정된 값으로 설정
        }
        
        // 제대로 필드값이 바뀌었는지 출력해본다
        System.out.println(myclass.getName());
        System.out.println(myclass.getName2());
    }
}
