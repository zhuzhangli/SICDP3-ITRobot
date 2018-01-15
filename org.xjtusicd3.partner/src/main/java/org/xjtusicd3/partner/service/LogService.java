package org.xjtusicd3.partner.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.xjtusicd3.database.helper.LogHelper;
import org.xjtusicd3.database.model.LogPersistence;
@Service
@Transactional
public class LogService {
	/**
	 * 保存日志
	 */
	public void insertLog(LogPersistence myLog) {
		LogHelper.insertLog(myLog);		
	}
	
	/**
	 * abstract:获取用户日志
	 */
	public static List<LogPersistence> getLogs(String userid) {
		List<LogPersistence> logs = LogHelper.getLogs(userid);
		return logs;
	}
	
}
