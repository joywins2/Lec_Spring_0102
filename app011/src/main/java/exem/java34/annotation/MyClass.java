package exem.java34.annotation;

public class MyClass {
    @MyTag(myName="Joy")
    private String name;

    @MyTag
    private String name2;

    public String getName() {
        return name;
    }
    public String getName2() {
        return name2;
    }
}
