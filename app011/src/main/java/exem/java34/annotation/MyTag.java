package exem.java34.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
/*
 * ...어노테이션 적용될 위치.
 *     FIELD : 필드 선언부에 달아두는 태그.
 *     METHOD : 메소드에 달아두는 태그.
 *     CONSTRUCTOR :  생성자에 달아두는 태그.
 *     PARAMETER : 매개변수에 달아두는 태그.
 */
@Retention(RetentionPolicy.RUNTIME)
/*
 * ...RUNTIME : 실행시에도 유지.
 *    CLASS : 클래스파일까지 유지.
 *    SOURCE : 소스코드에만 존재.
 */
public @interface MyTag { //...마치 인터페이스를 만든것 처럼 작동.
    String myName() default "홍길동";
}
