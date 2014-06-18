package main;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.test.ReportTest;
import com.test.poi.PoiWordWrapper;

public class Startup {
	public static void main(String[] args) {
		try {
			ReportTest reportTest = new ReportTest();
			reportTest.createReport("e:/test.docx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
