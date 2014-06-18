package com.test.poi;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.test.ReportBean;

public class PoiWordWrapper {

	private static XWPFDocument doc;

	private PoiWordWrapper() {
	}

	private PoiWordWrapper(String templateFile) throws IOException {
		doc = new XWPFDocument(POIXMLDocument.openPackage(templateFile));
	}

	public static PoiWordWrapper getInstance(String templateFile)
			throws IOException {
		return new PoiWordWrapper(templateFile);
	}

	public ReportBean parseDoc() throws IOException {
		ReportBean reportBean = new ReportBean();
		List<XWPFTable> tableList = doc.getTables();
		if (tableList != null) {
			for (XWPFTable table : tableList) {
				List<XWPFTableRow> rows = table.getRows();
				if (rows != null) {
					int r = 1;
					for (XWPFTableRow row : rows) {
						List<XWPFTableCell> cells = row.getTableCells();
						if (cells != null) {
							int c = 1;
							for (XWPFTableCell cell : cells) {
								StringBuilder key = new StringBuilder();
								key.append("r");
								key.append(String.valueOf(r));
								key.append("c");
								key.append(String.valueOf(c));
								reportBean.putParam(key.toString(),
										cell.getText());
								c++;
							}
						}
						r++;
					}
				}
			}
		}

		reportBean.putParam("content", "xxxxxx");

		List<XWPFPictureData> pictures = doc.getAllPictures();
		if (pictures != null) {
			int c = 1;
			for (XWPFPictureData pic : pictures) {
				String filePath = "e:/" + pic.getFileName();
				byte[] bytes = pic.getData();
				OutputStream out = null;
				try {
					out = new FileOutputStream(filePath);
					out.write(bytes);
				} finally {
					if (out != null) {
						out.close();
					}
				}
				Image image = Toolkit.getDefaultToolkit().createImage(bytes);
				reportBean.putParam("pic" + c, image);
				c++;
			}
		}
		return reportBean;
	}
}
