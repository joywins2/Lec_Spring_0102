package org.joy.dao5;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

public class MyPooledDataSourceFactory implements DataSourceFactory {
	
	private Properties prop;

	@Override
	public void setProperties(Properties props) {

        prop = props;
	}

	@Override
	public DataSource getDataSource() {

        PooledDataSource ds = new PooledDataSource();
        
        ds.setDriver(prop.getProperty("driver"));
        ds.setUrl(prop.getProperty("url"));
        ds.setUsername(prop.getProperty("user"));
        ds.setPassword(prop.getProperty("password"));
        
        return ds;
	}

}
