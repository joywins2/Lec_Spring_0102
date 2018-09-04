package org.joy.sample08;

public class User_withoutAutowire {
	
	/*
	@Autowired를 사용하지 않는 경우, getter/setter 메서드가 필요함.
	*/
	IF_Shoes shoes;
	
	public IF_Shoes getShoes(){
		return shoes;
	}
	
	public void setShoes(IF_Shoes shoes){
		this.shoes = shoes;
	}
	/*
	@Autowired를 사용하면 아래 한줄이면 됨.
	@Autowired
	@Qualifier("adidosShoes")
	IF_Shoes shoes;
	*/		
	
	public String getShoesBrand(){
		return "선택한 신발 : "+shoes.getBrand();
	}
	
}
