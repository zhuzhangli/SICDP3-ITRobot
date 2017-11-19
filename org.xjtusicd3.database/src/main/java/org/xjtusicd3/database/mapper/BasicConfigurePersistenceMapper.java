package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.BasicConfigurePersistence;

public interface BasicConfigurePersistenceMapper extends IBaseDao<BasicConfigurePersistence, String>{

	//添加至标准配置库
	@Insert("INSERT INTO TBL_BasicConfigure VALUES(#{0},#{1},#{2})")
	void addToBasicCfg(String basicConfigureId, String configureId, Object department);

	
	//查看该软件是否已添加至标准配置库
	@Select("SELECT * FROM TBL_BasicConfigure WHERE CONFIGUREID=#{0}")
	List<BasicConfigurePersistence> getCfgInfoFromBasic(String configureId);

}
