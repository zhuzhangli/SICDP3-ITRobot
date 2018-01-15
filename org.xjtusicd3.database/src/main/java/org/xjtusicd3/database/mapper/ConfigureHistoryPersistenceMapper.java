package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;

public interface ConfigureHistoryPersistenceMapper extends IBaseDao<ConfigureHistoryPersistence, String>{
	// abstract:变更列表
	@Select("SELECT * FROM TBL_Configure,TBL_ConfigureHistory where TBL_ConfigureHistory.CONFIGUREID = TBL_Configure.CONFIGUREID   ORDER BY UPDATETIME DESC LIMIT #{0},100")
	List<ConfigureHistoryPersistence> getUpdateCfgs(int startNumber);
}
