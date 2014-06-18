package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.test.poi.PoiWordWrapper;

public class ReportTest {

	public void createReport(String templateFile) throws Exception {
		ReportBean docData = readDoc(templateFile);

		File sourceFile = new File("E:/codes/reports/report2.jasper");

		// 1: Map��Ϊ������List<Object>������Դ
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(sourceFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				docData.getMapParams(), new JRBeanArrayDataSource(
						new ReportBean[] { docData.getParagraph() }));
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"E:/codes/reports/report2_1.pdf");
		// ��ӡԤ��
		// ��ӡ�ļ�
		JasperPrintManager.printReport(jasperPrint, true);

		// 2:
		// 2.1 ��̬�ı�����(�ղ�����������Դ)
		// InputStream inputStream = new FileInputStream(sourceFile);
		// OutputStream outputStream = new FileOutputStream(
		// "D:/codes/reports/report1.pdf");
		// JasperRunManager.runReportToPdfStream(inputStream, outputStream,
		// new HashMap<String, Object>(), new JREmptyDataSource());
		// outputStream.flush();
		// outputStream.close();
		// inputStream.close();

		// 2.2 Map��Ϊ������2 List<Object>������Դ
		// InputStream inputStream = new FileInputStream(sourceFile);
		// OutputStream outputStream = new FileOutputStream(
		// "D:/codes/reports/report2_2.pdf");
		// JasperRunManager.runReportToPdfStream(inputStream, outputStream,
		// params, new JRBeanCollectionDataSource(docData.getLstParagrap()));
		// outputStream.flush();
		// outputStream.close();
		// inputStream.close();

	}

	private ReportBean readDoc(String templateFile) throws Exception {
		PoiWordWrapper poiWord = PoiWordWrapper.getInstance(templateFile);
		ReportBean report = poiWord.parseDoc();
		return report;
	}
}
