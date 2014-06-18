package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportBean {

	/** 数据行 */
	private String allLineData;

	/** 另一行 */
	private String otherLineDate;

	private String pic;

	/** 段落集合 */
	private ReportBean paragraph;

	/** 段落集合 */
	private List<ReportBean> lstParagraph;

	/** Jasperreport参数 */
	private Map<String, Object> mapParams;

	public void addParagraph(ReportBean paragraph) {
		if (lstParagraph == null) {
			lstParagraph = new ArrayList<ReportBean>();
		}
		lstParagraph.add(paragraph);
	}

	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * @param pic
	 *            the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * @return the lstParagraph
	 */
	public List<ReportBean> getLstParagraph() {
		return lstParagraph;
	}

	/**
	 * @param lstParagraph
	 *            the lstParagraph to set
	 */
	public void setLstParagraph(List<ReportBean> lstParagraph) {
		this.lstParagraph = lstParagraph;
	}

	/**
	 * @return the otherLine
	 */
	public String getOtherLineDate() {
		return otherLineDate;
	}

	/**
	 * @param otherLine
	 *            the otherLine to set
	 */
	public void setOtherLineDate(String otherLineDate) {
		this.otherLineDate = otherLineDate;
	}

	/**
	 * @return the paragrap
	 */
	public ReportBean getParagraph() {
		return paragraph;
	}

	/**
	 * @param paragrap
	 *            the paragrap to set
	 */
	public void setParagraph(ReportBean paragraph) {
		this.paragraph = paragraph;
	}

	/**
	 * @return the mapParams
	 */
	public Map<String, Object> getMapParams() {
		return mapParams;
	}

	/**
	 * @return the lineData
	 */
	public String getAllLineData() {
		return allLineData;
	}

	/**
	 * @param lineData
	 *            the lineData to set
	 */
	public void setAllLineData(String allLineData) {
		this.allLineData = allLineData;
	}

	/**
	 * @param mapParams
	 *            the mapParams to set
	 */
	public void setMapParams(Map<String, Object> mapParams) {
		this.mapParams = mapParams;
	}

	public void putParam(String key, Object param) {
		if (mapParams == null) {
			mapParams = new HashMap<String, Object>();
		}
		mapParams.put(key, param);
	}

	public void putALlParam(Map<String, Object> params) {
		if (params == null) {
			return;
		}
		if (mapParams == null) {
			mapParams = new HashMap<String, Object>();
		}
		mapParams.putAll(params);
	}
}
