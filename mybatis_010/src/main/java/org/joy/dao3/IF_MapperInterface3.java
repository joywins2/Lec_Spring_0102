package org.joy.dao3;

import org.apache.ibatis.annotations.Select;

public interface IF_MapperInterface3 {

	@Select("SELECT now()")
	public String getTime();

	@Select("SELECT VERSION()")
	public String getMySqlVersion();
	
}
