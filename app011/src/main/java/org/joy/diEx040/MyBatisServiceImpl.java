package org.joy.diEx040;

public class MyBatisServiceImpl {
	
	private MyBatisDaoImpl myBatisDao;	
	
	//setter
	public void setMyBatisDao(MyBatisDaoImpl myBatisDao){
		this.myBatisDao = myBatisDao;
	}
	
	public void myBatisTest(){
		System.out.println("================");
		myBatisDao.insertDB();
		myBatisDao.updateDB();
		System.out.println("================");
	}
}
