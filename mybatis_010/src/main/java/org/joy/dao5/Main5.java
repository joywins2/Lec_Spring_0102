package org.joy.dao5;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Main5 {
	
    private static SqlSessionFactory sqlSessionfactory = null;

	public static void main(String[] args) throws IOException {
		final String namespace = "org.joy.dao5.IF_MapperInterface5";
	    SqlSession session = null;

        Properties prop = new Properties();
        prop.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("url", "jdbc:mysql://127.0.0.1:3306/zbook_ex?useSSL=false&serverTimezone=Asia/Seoul");
        prop.setProperty("user", "joywins2");
        prop.setProperty("password", "tiger");
        

        MyPooledDataSourceFactory mdsf = new MyPooledDataSourceFactory();
        mdsf.setProperties(prop);
        DataSource ds = mdsf.getDataSource();
        
        TransactionFactory trFact = new JdbcTransactionFactory();
        Environment environment = new Environment("development", trFact, ds);
        Configuration config = new Configuration(environment);
        config.addMapper(IF_MapperInterface5.class);
        
        sqlSessionfactory = new SqlSessionFactoryBuilder().build(config);

        try {
        	session = sqlSessionfactory.openSession();
               
            /*
             * ...ErrMsg : is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)
             *    http://blog.naver.com/sk200413/20170506474
             *    네임스페이스 전체 경로를 명시해줌.
             */
            String version = session.selectOne(namespace + ".getMySqlVersion");
            System.out.println("version : " + version);
            

        } finally {

            if (session != null) {
                session.close();
            }
        }

	}

}
