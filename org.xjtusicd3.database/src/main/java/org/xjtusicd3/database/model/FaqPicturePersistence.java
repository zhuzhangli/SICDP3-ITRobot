package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="tbl_faqpicture")
public class FaqPicturePersistence {
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="FAQPICTUREID")
	private String FAQPICTUREID;
	@TableField(columnName="IMGPATH")
	private String IMGPATH;
	@TableField(columnName="DESCRIPTION")
	private String DESCRIPTION;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="STATE")
	private int STATE;
	public String getFAQPICTUREID() {
		return FAQPICTUREID;
	}
	public void setFAQPICTUREID(String fAQPICTUREID) {
		FAQPICTUREID = fAQPICTUREID;
	}
	public String getIMGPATH() {
		return IMGPATH;
	}
	public void setIMGPATH(String iMGPATH) {
		IMGPATH = iMGPATH;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public int getSTATE() {
		return STATE;
	}
	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}	
}
