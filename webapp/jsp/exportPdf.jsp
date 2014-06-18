<%--
/*
 * 导出PDF文件
 *
 * VERSION  DATE        BY           REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00     2014-06-16  ZhangLipeng  程序发布
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2010 Anjuke System. - All Rights Reserved.
 *
 */
--%>
<%@page contentType="text/html;charset=UTF-8"
	errorPage="/html/serverError.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>导出PDF文件</title>
<s:head />
<script language="javascript">
	function exportPdf() {
		/**
		var open_window = window
				.open(
						'',
						'open_window',
						'width=500,height=400,location=no,directories=no,status=yes,menubar=no,resizable=yes');
		open_window.focus();
		open_window.document.write("<html>");
		open_window.document.write("<head><title>");
		open_window.document.write("</title></head>");
		open_window.document.write("<body>");
		open_window.document
				.write("<span style=\"font-size:18pt;color:navy\"><b>");
		open_window.document.write("Now Printing...");
		open_window.document.write("</b></span>");
		open_window.document.write("</body></html>");
		open_window.document.close();
		 **/

		document.getElementById("reportForm").target = "open_window";
		document.getElementById("reportForm").action = "ExportPdfAction";
		document.getElementById("reportForm").submit();
		//return open_window;
	}

	function previewPdf() {
		var open_window = window
				.open(
						'',
						'open_window',
						'width=500,height=400,location=no,directories=no,status=yes,menubar=no,resizable=yes');
		open_window.focus();
		open_window.document
				.write('<APPLET CODE = "JRPrinterApplet.class" CODEBASE = "applets" ARCHIVE = "jasperreports-applet-5.6.0.jar,commons-logging-1.1.jar,commons-collections-3.2.1.jar,print.jar,jasperreports-javaflow-5.6.0.jar" WIDTH = "300" HEIGHT = "200">');
		open_window.document
				.write('<PARAM NAME = CODE VALUE = "JRPrinterApplet.class" >');
		open_window.document
				.write('<PARAM NAME = CODEBASE VALUE = "applets" >');
		open_window.document
				.write('<PARAM NAME = ARCHIVE VALUE = "jasperreports-applet-5.6.0.jar,commons-logging-1.1.jar,commons-collections-3.2.1.jar,print.jar,jasperreports-javaflow-5.6.0.jar" >');
		open_window.document
				.write('<PARAM NAME="type" VALUE="application/x-java-applet;version=1.2.2">');
		open_window.document.write('<PARAM NAME="scriptable" VALUE="false">');
		open_window.document
				.write('<PARAM NAME = "REPORT_URL" VALUE ="PreviewPdfAction">');
		open_window.document.write('</APPLET>');
	}

	function printPdf() {
		var open_window = window
				.open(
						'',
						'open_window',
						'width=500,height=400,location=no,directories=no,status=yes,menubar=no,resizable=yes');
		open_window.focus();
		open_window.document.write("<html>");
		open_window.document.write("<head><title>");
		open_window.document
				.write("</title><script>document.onreadystatechange=function(){if(document.readystate==\"complete\"){widow.close();}}<\/script></head>");
		open_window.document.write("<body>");
		open_window.document
				.write("<span style=\"font-size:18pt;color:navy\"><b>");
		open_window.document.write("Now Printing...");
		open_window.document.write("</b></span>");
		open_window.document.write("</body></html>");
		open_window.document.close();

		document.getElementById("reportForm").target = "open_window";
		document.getElementById("reportForm").action = "PrintPdfAction";
		document.getElementById("reportForm").submit();
		return open_window;
	}
</script>
</head>
<body>
	<form id="reportForm"></form>
	<s:form action="ExportPdfAction" onsubmit="return false;"
		id="exportPdfForm">
		<table>
			<tr>
				<td><input type="button" value="导出PDF文件" onclick="exportPdf()"
					width="150px"></td>
			</tr>
			<tr>
				<td><input type="button" value="预览PDF文件" onclick="previewPdf()"
					width="150px"></td>
			</tr>
			<tr>
				<td><input type="button" value="打印PDF文件" onclick="printPdf()"
					width="150px"></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
