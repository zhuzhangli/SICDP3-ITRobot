package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ServerPersistence;


public interface ServerPersistenceMapper extends IBaseDao<ServerPersistence, String>{
	//获取服务器特有信息
	@Select("SELECT * FROM TBL_Server WHERE EQUIPMENTID = #{0}")
	List<ServerPersistence> getServerInfoById(String equipmentid);
	
	//添加服务器信息
	@Insert("INSERT INTO TBL_Server VALUES(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11},#{12},#{13},#{14},#{15},#{16},#{17},#{18},#{19})")
	void addServerEquipment(String equipmentId, String osVersion, String computerName, String pCI, String uSB,
			String path, String rAM_EXCHANGEAREAUSE, String pARTATIONUSE, String iDLERAM,
			String oS_TIME_USERNUM_LOAD, String oSLOAD, String fIREWALL, String rOUTINGTABLE, String hASCONTACT,
			String nETWORK, String pROCESS, String rEALTIMEPROCESS, String aCTIVEUSER, String bIOS,
			String networkCard);
	
	
	
	
	
	
	
	
	
	
	
	
	//zpz_获取用户所有信息
		@Select("SELECT * FROM TBL_Server")
		List<ServerPersistence> getServer();













		
		
}
