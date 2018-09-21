package org.joy.dao3;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 
 */
public class Main3 {
	
    private static SqlSessionFactory factory = null;

	public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session = null;

        reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(IF_MapperInterface3.class);
        
        reader.close();

        try {
            session = factory.openSession();   
            /*
             * ...ErrMsg : is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)
             *    http://blog.naver.com/sk200413/20170506474
             *    네임스페이스 전체 경로를 명시해줌.
             */

            String version = session.selectOne("org.joy.dao3.IF_MapperInterface3.getMySqlVersion");
            System.out.println(version);
            
            String time = session.selectOne("org.joy.dao3.IF_MapperInterface3.getTime");
            System.out.println(time);

        } finally {

            if (session != null) {
                session.close();
            }
        }

	}

}
