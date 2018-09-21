package org.joy.dao2;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joy.domain.MemberVO;

/*
 * ...260p.매퍼인터페이스를 사용하기 위해서 sqlSession.getMapper(매퍼인터페이스.class) 메서드를 사용함.
 */
public class MemberDao2 {
	
	public void save(MemberVO member){
		  SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  //...260p.매퍼인터페이스를 사용하기 위해서 sqlSession.getMapper(매퍼인터페이스.class) 메서드를 사용함.
		  IF_MapperInterface2 mapper = session.getMapper(IF_MapperInterface2.class);
		  mapper.insertMember(member);
		  session.commit();
		  session.close();
		}
		public void update(MemberVO member){
		  SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();	
		  //...260p.매퍼인터페이스를 사용하기 위해서 sqlSession.getMapper(매퍼인터페이스.class) 메서드를 사용함.
		  IF_MapperInterface2 mapper = session.getMapper(IF_MapperInterface2.class);
		  mapper.updateMember(member);
		  session.commit();
		  session.close();
		}
		public void delete(String user_id){
		  SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();	
		  //...260p.매퍼인터페이스를 사용하기 위해서 sqlSession.getMapper(매퍼인터페이스.class) 메서드를 사용함.
		  IF_MapperInterface2 mapper = session.getMapper(IF_MapperInterface2.class);
		  mapper.deleteMember(user_id);
		  session.commit();
		  session.close();
		}
		
		public MemberVO selectMemberByUserId(String user_id) {
		  SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();	
		  //...260p.매퍼인터페이스를 사용하기 위해서 sqlSession.getMapper(매퍼인터페이스.class) 메서드를 사용함.
		  IF_MapperInterface2 mapper = session.getMapper(IF_MapperInterface2.class);
		  MemberVO member = mapper.selectMemberByUserId(user_id);
		  session.close();
		  return member;
		}

}
