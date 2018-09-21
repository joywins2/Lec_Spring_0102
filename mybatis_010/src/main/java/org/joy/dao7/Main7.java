package org.joy.dao7;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joy.domain.MemberVO;

public class Main7 {
	
    private static SqlSessionFactory sqlSessionfactory = null;

	public static void main(String[] args) throws IOException {
		final String namespace = "org.joy.dao7.IF_MapperInterface7";

        SqlSession session = null;
        
        sqlSessionfactory = MyBatisUtil.getSqlSessionFactory();
        sqlSessionfactory.getConfiguration().addMapper(IF_MapperInterface7.class);

        try {
            session = sqlSessionfactory.openSession();   
            /*
             * ...ErrMsg : is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)
             *    http://blog.naver.com/sk200413/20170506474
             *    네임스페이스 전체 경로를 명시해줌.
             */
            String version = session.selectOne(namespace + ".getTime");
            System.out.println(version);

            MemberVO mem = session.selectOne(namespace + ".selectMemberByUserId", "user123");
            System.out.println("mem : " + mem.toString());
                        
            List<MemberVO> members = session.selectList(namespace + ".selectMembersByUserName", "JoyWins");
            
            for (MemberVO member : members) {
            	System.out.println("member : " + member.toString());
            }
           
            MemberVO mem2 = new MemberVO("new_id01", "new_pw01", "JoyWins", "new_email01");
            //session.update(namespace + ".insertMember", mem2);
            //session.commit();            

        } finally {

            if (session != null) {
                session.close();
            }
        }
        
	}

}
