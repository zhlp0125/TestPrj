package com.test.web.action;

import java.io.ObjectOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.test.web.logic.ExportPdfService;

public class ExportPdfAction extends ActionSupport implements
		ModelDriven<ExportPdfForm> {

	private ExportPdfForm form = new ExportPdfForm();

	private ExportPdfService service;

	public String init() {
		return SUCCESS;
	}

	/**
	 * 导出pdf文件
	 **/
	public String export() {
		try {
			String tenplateFile = ServletActionContext.getServletContext()
					.getRealPath("/") + "template/test.docx";
			String tenplateFile1 = ServletActionContext.getServletContext()
					.getRealPath("/") + "template/test1.docx";
			String jasperFile = ServletActionContext.getServletContext()
					.getRealPath("/") + "jasper/report2.jasper";
			String pdfFilePath = "e:/report.pdf";
			// byte[] bytes = service.createReport(tenplateFile, jasperFile,
			// pdfFilePath);
			byte[] bytes = service.createReport(new String[] { tenplateFile,
					tenplateFile1 }, jasperFile, pdfFilePath);
			System.out
					.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			if (bytes != null && bytes.length > 0) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				String exportname = "Export";
				response.reset();
				response.setContentType("application/pdf");
				exportname = exportname + "__" + System.currentTimeMillis()
						+ ".pdf";
				response.addHeader("Content-Disposition",
						"attachment; filename=\"" + exportname + "\"");
				response.setContentLength(bytes.length);
				ServletOutputStream sos = response.getOutputStream();
				sos.write(bytes, 0, bytes.length);
				sos.flush();
				sos.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return NONE;
	}

	/**
	 * Applet输出
	 * 
	 * @throws Exception
	 * 
	 * **/
	public String applet() throws Exception {
		String tenplateFile = ServletActionContext.getServletContext()
				.getRealPath("/") + "template/test.docx";
		String jasperFile = ServletActionContext.getServletContext()
				.getRealPath("/") + "jasper/report2.jasper";
		String pdfFilePath = "e:/report.pdf";
		JasperPrint jasperPrint = service.createReportPrint(tenplateFile,
				jasperFile, pdfFilePath);
		if (jasperPrint != null) {
			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("application/octet-stream");
			ServletOutputStream ouputStream;
			ouputStream = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
			oos.writeObject(jasperPrint);
			oos.flush();
			oos.close();
			ouputStream.flush();
			ouputStream.close();
		}
		return NONE;
	}

	/**
	 * 弹出打印框
	 * 
	 * @throws Exception
	 * 
	 * **/
	public String print() throws Exception {
		String tenplateFile = ServletActionContext.getServletContext()
				.getRealPath("/") + "template/test.docx";
		String jasperFile = ServletActionContext.getServletContext()
				.getRealPath("/") + "jasper/report2.jasper";
		String pdfFilePath = "e:/report.pdf";
		JasperPrint jasperPrint = service.createReportPrint(tenplateFile,
				jasperFile, pdfFilePath);
		if (jasperPrint != null) {
			JasperPrintManager.printReport(jasperPrint, true);
		}
		return SUCCESS;
	}

	public ExportPdfForm getForm() {
		return form;
	}

	public void setForm(ExportPdfForm form) {
		this.form = form;
	}

	public ExportPdfService getService() {
		return service;
	}

	@Resource
	public void setService(ExportPdfService service) {
		this.service = service;
	}

	@Override
	public ExportPdfForm getModel() {
		return form;
	}
}
