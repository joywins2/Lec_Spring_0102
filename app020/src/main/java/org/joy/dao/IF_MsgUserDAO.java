package org.joy.dao;
//...467p.PointDAO.
//   포인트와 메시지는 다른 도메인으로 분리함.

public interface IF_MsgUserDAO {

	public void updatePoint(String uid,int point)throws Exception;	
	
}
