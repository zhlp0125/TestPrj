package com.test;

import java.io.IOException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import com.test.poi.PoiWordWrapper;

public class ReportTest {

	public void createReport(String templateFile) throws JRException,
			IOException {
		Map<String, Object> params = readDoc(templateFile);


//		File sourceFile = new File("E:/codes/reports/report2.jasper");
//		JasperReport jasperReport = (JasperReport) JRLoader
//				.loadObject(sourceFile);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
//				params);
//		JasperExportManager.exportReportToPdfFile(jasperPrint,
//				"E:/codes/reports/report2.pdf");
		

		JRBeanArrayDataSource ds = new JRBeanArrayDataSource();
		JasperFillManager.fillReportToFile("E:/codes/reports/report1.jasper", null);
		JasperPrintManager.printReport("E:/codes/reports/report1.jrprint", true);
		JasperExportManager.exportReportToPdfFile("E:/codes/reports/report2.jrprint", "E:/codes/reports/report1.pdf");

	}

	private Map<String, Object> readDoc(String templateFile) throws IOException {
		PoiWordWrapper poiWord = PoiWordWrapper.getInstance("e:/test.docx");
		ReportBean report = poiWord.parseDoc();
		return report.getMapParams();
	}
}
