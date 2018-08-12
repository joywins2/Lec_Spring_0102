package org.joy.aop020;

public class ServiceImpl implements IF_Service {
	
	@Override
	public void addBoard(){
		System.out.println("ServiceImpl.addBoard().....");
	}
	@Override
	public void delBoard(){
		System.out.println("ServiceImpl.delBoard().....");
	}
	@Override
	public void getBoard(){
		System.out.println("ServiceImpl.getBoard() 호출.....");
	}
}
