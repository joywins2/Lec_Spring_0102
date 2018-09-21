package org.joy.dao6;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joy.domain.MemberVO;

public class Main6 {
	
    private static SqlSessionFactory sqlSessionfactory = null;

	public static void main(String[] args) throws IOException {
		final String namespace = "org.joy.dao6.dao6_Mapper";

        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session = null;

        reader = Resources.getResourceAsReader(resource);
        sqlSessionfactory = new SqlSessionFactoryBuilder().build(reader);
        
        reader.close();

        try {
            session = sqlSessionfactory.openSession();   
            /*
             * ...ErrMsg : is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)
             *    http://blog.naver.com/sk200413/20170506474
             *    네임스페이스 전체 경로를 명시해줌.
             */
            String version = session.selectOne(namespace + ".getMySqlVersion");
            System.out.println(version);

            MemberVO mem = session.selectOne(namespace + ".getMember_DynamicSql", "user123");
            System.out.println("mem : " + mem.toString());
                        
            List<MemberVO> members = session.selectList(namespace + ".getMember_DynamicSql");
            
            for (MemberVO member : members) {
            	System.out.println("member : " + member.toString());
            }
            

        } finally {

            if (session != null) {
                session.close();
            }
        }

        
	}

}
