package com.test.web.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportPdfForm {

	private List<ItemDataBean> itemList;

	private InputStream inputStream;

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void addItem(ItemDataBean item) {
		if (itemList == null) {
			itemList = new ArrayList<ItemDataBean>();
		}
		itemList.add(item);
	}

	public List<ItemDataBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemDataBean> itemList) {
		this.itemList = itemList;
	}

}
