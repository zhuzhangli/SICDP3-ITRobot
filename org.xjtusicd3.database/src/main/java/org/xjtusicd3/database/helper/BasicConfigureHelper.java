package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.BasicConfigurePersistenceMapper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;

public class BasicConfigureHelper {
	//添加至标准配置库
	public static void addToBasicCfg(String basicConfigureId, String configureId, Object department) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		mapper.addToBasicCfg( basicConfigureId,  configureId,  department);
		session.close();		
	}

	
	//查看该软件是否已添加至标准配置库
	public static List<BasicConfigurePersistence> getCfgInfoFromBasic(String configureId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		List<BasicConfigurePersistence> list = mapper.getCfgInfoFromBasic(configureId);
		session.close();
		return list;
	}

}
