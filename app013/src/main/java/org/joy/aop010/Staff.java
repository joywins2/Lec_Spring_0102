package org.joy.aop010;

public class Staff {
	
	private String name;
	private int age;
	private String dept;
	
	//setter, getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public void getInfo(){
		System.out.println("이름 :"+getName());
		System.out.println("나이 :"+getAge());
		System.out.println("부서 :"+getDept());
	}
}
