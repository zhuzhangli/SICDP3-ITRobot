package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao; 
import org.xjtusicd3.database.model.LogPersistence;

public interface LogPersistenceMapper extends IBaseDao<LogPersistence,String>
{
	//获取用户日志
	@Select("SELECT * FROM TBL_Log WHERE USERID=#{0} ORDER BY LOGTIME DESC")
	public List<LogPersistence> getLogs(String userid);
	
	// zpz_get information of TBL_Log
	@Select("SELECT * FROM TBL_Log")
	public List<LogPersistence> getLog();
}
