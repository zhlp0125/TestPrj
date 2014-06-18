package com.test.poi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.test.ReportBean;

public class PoiWordWrapper {

	private static XWPFDocument doc;

	private static final String EXTENSION_DOC = "doc";

	private static final String EXTENSION_DOCX = "docx";

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	/** doc or docx */
	private String docType;

	private PoiWordWrapper() {
	}

	private PoiWordWrapper(String templateFile) throws Exception {
		if (templateFile == null || templateFile.length() <= 0) {
			throw new Exception("Illegal template file!");
		}
		String[] tempFilePath = templateFile.split("\\.");
		if (StringUtils.equalsIgnoreCase(tempFilePath[tempFilePath.length - 1],
				EXTENSION_DOC)) {
			setDocType(EXTENSION_DOC);
		}
		if (StringUtils.equalsIgnoreCase(tempFilePath[tempFilePath.length - 1],
				EXTENSION_DOCX)) {
			setDocType(EXTENSION_DOCX);
		}
		if (getDocType() == null || getDocType().length() <= 0) {
			throw new Exception("Illegal template file!");
		}
		doc = new XWPFDocument(POIXMLDocument.openPackage(templateFile));
	}

	public static PoiWordWrapper getInstance(String templateFile)
			throws Exception {
		return new PoiWordWrapper(templateFile);
	}

	/**
	 * 文档解析。
	 * 
	 * @return 文档数据对象
	 * @throws IOException
	 *             异常
	 */
	public ReportBean parseDoc() throws IOException {
		ReportBean reportBean = new ReportBean();
		// 获取表格数据
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
								System.out.println(">>>[" + cell.getText()
										+ "]");
								c++;
							}
						}
						r++;
					}
				}
			}
		}

		// 获取段落数据
		List<XWPFParagraph> paragraphs = doc.getParagraphs();
		if (paragraphs != null) {
			StringBuilder lineData = new StringBuilder();
			StringBuilder otherLineData = new StringBuilder();
			ReportBean paragraphData = new ReportBean();
			boolean isUnder = false;
			for (XWPFParagraph paragraph : paragraphs) {
				// 可以吧XWPFRun理解成一小段文字描述的对象，这是word文档的特征，即：文本描述性文档。
				/*
				 * 例： XWPFParagraph paragraph = doc.createParagraph(); XWPFRun
				 * run = paragraph.createRun(); run.setFontFamily("Arial");
				 * run.setFontSize("12"); run.setText("Text!");
				 */
				List<XWPFRun> runs = paragraph.getRuns();
				if (runs != null) {
					for (XWPFRun run : runs) {
						String text = run.getText(0);
						if (text != null) {
							if (!isUnder && text.indexOf("Under") >= 0) {
								isUnder = true;
							}
							if (!isUnder) {
								lineData.append(text);
								System.out.println(">>>[" + text + "]");
							} else {
								otherLineData.append(text);
							}
						}
					}
				}
				if (!isUnder) {
					lineData.append("\n");
				} else {
					otherLineData.append("\n");
				}
			}
			paragraphData.setAllLineData(lineData.toString());
			paragraphData.setOtherLineDate(otherLineData.toString());
			reportBean.setParagraph(paragraphData);
		}

		// 获取图片数据
		List<XWPFPictureData> pictures = doc.getAllPictures();
		if (pictures != null) {
			ReportBean report = reportBean.getParagraph();
			for (XWPFPictureData pic : pictures) {
				String filePath = "D:/" + Math.abs(Math.random() * 100) + "_"
						+ pic.getFileName();
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
				if (report != null) {
					report.setPic(filePath);
				} else {
					report = new ReportBean();
					reportBean.setParagraph(report);
				}

				// Image image = Toolkit.getDefaultToolkit().createImage(bytes);
				// reportBean.putParam("pic" + c, getImage(image));
			}
		}

		return reportBean;
	}

	private BufferedImage getImage(Image image) {

		int width = image.getWidth(null) < 0 ? 1 : image.getWidth(null);
		int height = image.getHeight(null) < 0 ? 1 : image.getHeight(null);
		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();
		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bufferedImage;

	}
}
