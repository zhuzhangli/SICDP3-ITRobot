package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.BasicConfigureHelper;
import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.helper.SoftHelper;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.SoftPersistence;
import org.xjtusicd3.portal.view.ChangeIndexView;
import org.xjtusicd3.portal.view.ConfigureSoftView;

public class ConfigureService 
{
	//获取所有软件配置信息
	public static List<ConfigureSoftView> getAllSofts() {
		List<ConfigureSoftView> configureSoftViews = new ArrayList<ConfigureSoftView>();
		
		List<ConfigurePersistence> softLists = ConfigureHelper.getAllSofts(0);

		for(ConfigurePersistence softList:softLists){
			ConfigureSoftView configureSoftView = new ConfigureSoftView();
			
			configureSoftView.setCONFIGUREID(softList.getCONFIGUREID());
			configureSoftView.setCONFIGURENAME(softList.getCONFIGURENAME());
			configureSoftView.setCONFIGURETYPE(softList.getCONFIGURETYPE());
			configureSoftView.setFILESIZE(softList.getFILESIZE());
			configureSoftView.setURL(softList.getURL());
			configureSoftView.setDOWNLOADTIMES(softList.getDOWNLOADTIMES());			
			configureSoftView.setPRODUCER(softList.getPRODUCER());
			configureSoftView.setCONFIGURETIME(softList.getCONFIGURETIME());
			configureSoftView.setISCONFIGURE(softList.getISCONFIGURE());
			
			//查找软件信息
			List<SoftPersistence> sList = SoftHelper.getSoftInfo(softList.getCONFIGUREID());			
			configureSoftView.setLOGO(sList.get(0).getLOGO());
			configureSoftView.setSCORE(sList.get(0).getSCORE());
			configureSoftView.setDESCRIPTION(sList.get(0).getDESCRIPTION());
			
			configureSoftViews.add(configureSoftView);
			
		}
		
		return configureSoftViews;
	}
	
	
	//获取ID对应软件信息
	public static ConfigureSoftView getSoftInfoById(String configureId) {
		ConfigureSoftView softView = new ConfigureSoftView();
		
		List<SoftPersistence> softInfo = SoftHelper.getSoftInfo(configureId);
		
		softView.setCONFIGUREID(configureId);
		softView.setINTRODUCTION(softInfo.get(0).getINTRODUCTION());
		softView.setVERSIONTYPE(softInfo.get(0).getVERSIONTYPE());
		softView.setVERSION(softInfo.get(0).getVERSION());
		softView.setSOFTTYPE(softInfo.get(0).getSOFTTYPE());
		softView.setWEBSITE(softInfo.get(0).getWEBSITE());
		
		
		return softView;
	}
	
	
	//添加至标准配置库
	public static void addToBasicCfg(String configureId) {
		
		BasicConfigureHelper.addToBasicCfg(UUID.randomUUID().toString(),configureId,null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static String getPartConfig() 
//	{
//		String result = "";
//		List<ConfigurePersistence> list = ConfigureHelper.getPartConfig();
//		for (int i = 0; i < list.size(); i++) 
//		{
//			result += "{\"id\":\""+(i+1)+"\",\"configureProducer\":\""+list.get(i).getPRODUCER()+"\",\"configureName\":\""+list.get(i).getCONFIGURENAME()+"\",\"configureType\":\""+list.get(i).getCONFIGURETYPE()+"\"}";
//			if(i < list.size()-1)
//			{
//				result += ",";
//			}else
//			{
//				result += "";
//			}
//		}
//		System.out.println(result);
//		return result;
//	}
//	public static void main(String[] args) 
//	{
//		getPartConfig();
//	}
	public static List<ConfigurePersistence> getConfigure() 
	{
		List<ConfigurePersistence> configurelist = ConfigureHelper.getAllConfig();
		return configurelist;
		
	}

	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:50:36
	 * @param startNumber 
	 */
	public static List<ChangeIndexView> getConfigureHistory(int startNumber) {
		// TODO Auto-generated method stub
				List<ChangeIndexView> changeIndexViews = new ArrayList<ChangeIndexView>();
				List<ConfigureHistoryPersistence> configureHistoryPersistences = ConfigureHelper.getUpdateCfgs(startNumber);
				for(ConfigureHistoryPersistence configureHistoryPersistence:configureHistoryPersistences){
					ChangeIndexView changeIndexView = new ChangeIndexView();
					List<ConfigurePersistence> configurePersistences =ConfigureHelper.getCfgs(startNumber);
					changeIndexView.setCONFIGURENAME(configurePersistences.get(0).getCONFIGURENAME());
					changeIndexView.setVERSION(configureHistoryPersistence.getVERSION());
					changeIndexView.setURL(configureHistoryPersistence.getURL());
					changeIndexView.setUPDATETIME(configureHistoryPersistence.getUPDATETIME());
					changeIndexView.setREMARKS(configureHistoryPersistence.getREMARKS());
					changeIndexViews.add(changeIndexView);
				}
				
				return changeIndexViews;
			}

	


	
}
