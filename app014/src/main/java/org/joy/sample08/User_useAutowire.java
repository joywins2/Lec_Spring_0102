package org.joy.sample08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class User_useAutowire {
	
	/*
	@Autowired를 사용하지 않는 경우, getter/setter 메서드가 필요함.
	IF_Shoes shoes;
	
	public IF_Shoes getShoes(){
		return shoes;
	}
	
	public void setShoes(IF_Shoes shoes){
		this.shoes = shoes;
	}
	@Autowired를 사용하면 아래 한줄이면 됨.
	@Qualifier(xml파일에서 특정한 빈 지정)
     - /app014/src/main/resources/sample07_useAutowire.xml에 복수의 빈이 있고,
       이 중 특정한 빈을 선택해서 의존하게 함.
	*/
	@Autowired
	@Qualifier("adidosShoes")
	IF_Shoes shoes;	
	
	public String getShoesBrand(){
		return "선택한 신발 : "+shoes.getBrand();
	}
	
}
