package com.test.web.logic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.test.ReportBean;
import com.test.poi.PoiWordWrapper;
import com.test.web.action.ItemDataBean;

@Service
public class ExportPdfService {

	public ItemDataBean createItem() {
		ItemDataBean item = new ItemDataBean();
		item.setItem1("Item_" + Math.abs((Math.random() * 10)));
		item.setItem2("Item_" + Math.abs((Math.random() * 10)));
		return item;
	}

	/**
	 * 生成PDF
	 */
	public byte[] createReport(String templateFile, String jasperFile,
			String pdfFilePath) throws Exception {
		ReportBean docData = readDoc(templateFile);

		File sourceFile = new File(jasperFile);
		File pdfFile = new File(pdfFilePath);

		// 2.2 Map作为参数，2 List<Object>作数据源
		InputStream is = new FileInputStream(sourceFile);

		ByteArrayOutputStream bo = new ByteArrayOutputStream();

		JasperRunManager.runReportToPdfStream(
				is,
				bo,
				docData.getMapParams(),
				new JRBeanArrayDataSource(new ReportBean[] { docData
						.getParagraph() }));

		byte[] bytes = bo.toByteArray();

		FileOutputStream fo = new FileOutputStream(pdfFile);
		fo.write(bytes);

		fo.close();
		bo.close();
		is.close();
		return bytes;
	}

	/**
	 * 生成PDF
	 */
	public byte[] createReport(String[] templateFiles, String jasperFile,
			String pdfFilePath) throws Exception {

		ReportBean docAllData = new ReportBean();
		for (String templateFile : templateFiles) {
			ReportBean docData = readDoc(templateFile);
			docAllData.putALlParam(docData.getMapParams());
			docAllData.addParagraph(docData.getParagraph());
		}

		File sourceFile = new File(jasperFile);
		File pdfFile = new File(pdfFilePath);

		// 2.2 Map作为参数，2 List<Object>作数据源
		InputStream is = new FileInputStream(sourceFile);

		ByteArrayOutputStream bo = new ByteArrayOutputStream();

		JasperRunManager.runReportToPdfStream(is, bo,
				docAllData.getMapParams(), new JRBeanCollectionDataSource(
						docAllData.getLstParagraph()));

		byte[] bytes = bo.toByteArray();

		FileOutputStream fo = new FileOutputStream(pdfFile);
		fo.write(bytes);

		fo.close();
		bo.close();
		is.close();
		return bytes;
	}

	/**
	 * 生成PDF
	 */
	public JasperPrint createReportPrint(String templateFile,
			String jasperFile, String pdfFilePath) throws Exception {
		ReportBean docData = readDoc(templateFile);

		File sourceFile = new File(jasperFile);

		// 1: Map作为参数，List<Object>作数据源
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(sourceFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				docData.getMapParams(), new JRBeanArrayDataSource(
						new ReportBean[] { docData.getParagraph() }));
		// 生成文件备份
		JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
		return jasperPrint;
	}

	private ReportBean readDoc(String templateFile) throws Exception {
		PoiWordWrapper poiWord = PoiWordWrapper.getInstance(templateFile);
		ReportBean report = poiWord.parseDoc();
		return report;
	}
}
