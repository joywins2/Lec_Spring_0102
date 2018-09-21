package org.joy.dao5;

import org.apache.ibatis.annotations.Select;

public interface IF_MapperInterface5 {

	@Select("SELECT now()")
	public String getTime();

	@Select("SELECT VERSION()")
	public String getMySqlVersion();
	
}
