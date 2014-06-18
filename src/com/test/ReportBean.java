package com.test;

import java.util.HashMap;
import java.util.Map;

public class ReportBean {
	/**
	 * @return the r1c1
	 */
	public String getR1c1() {
		return r1c1;
	}

	/**
	 * @param r1c1 the r1c1 to set
	 */
	public void setR1c1(String r1c1) {
		this.r1c1 = r1c1;
	}

	/** 字段1 */
	private String r1c1;

	/**
	 * @return the mapParams
	 */
	public Map<String, Object> getMapParams() {
		return mapParams;
	}

	/**
	 * @param mapParams
	 *            the mapParams to set
	 */
	public void setMapParams(Map<String, Object> mapParams) {
		this.mapParams = mapParams;
	}

	/** 参数列表 */
	private Map<String, Object> mapParams;

	public void putParam(String key, Object param) {
		if (mapParams == null) {
			mapParams = new HashMap<String, Object>();
		}
		mapParams.put(key, param);
	}

}
