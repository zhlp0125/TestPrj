import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class JRPrinterApplet extends javax.swing.JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7942411091824562782L;
	private URL url = null;
//	private javax.swing.JPanel pnlMain;
//	private javax.swing.JButton btnView;
//	private javax.swing.JButton btnPrint;
//	private JasperPrint jasperPrint;

//	public JRPrinterApplet() {
//		initComponents();
//	}
//
//	private void initComponents() {// GEN-BEGIN:initComponents
//		pnlMain = new javax.swing.JPanel();
//		btnPrint = new javax.swing.JButton();
//		btnView = new javax.swing.JButton();
//
//		btnPrint.setText("Print the report");
//		btnPrint.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				btnPrintActionPerformed(evt);
//			}
//		});
//
//		pnlMain.add(btnPrint);
//
//		btnView.setText("View the report");
//		btnView.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				btnViewActionPerformed(evt);
//			}
//		});
//
//		pnlMain.add(btnView);
//
//		getContentPane().add(pnlMain, java.awt.BorderLayout.WEST);
//
//	}
//
//	protected void btnViewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnViewActionPerformed
//		// Add your handling code here:
//		if (url != null) {
//			try {
//				if (jasperPrint == null) {
//					jasperPrint = (JasperPrint) JRLoader.loadObject(url);
//				}
//				if (jasperPrint != null) {
//					ViewerFrame viewerFrame = new ViewerFrame(
//							this.getAppletContext(), jasperPrint);
//					viewerFrame.setVisible(true);
//				} else {
//					JOptionPane.showMessageDialog(this, "Empty report.");
//				}
//			} catch (Exception e) {
//				StringWriter swriter = new StringWriter();
//				PrintWriter pwriter = new PrintWriter(swriter);
//				e.printStackTrace(pwriter);
//				JOptionPane.showMessageDialog(this, swriter.toString());
//			}
//		} else {
//			JOptionPane.showMessageDialog(this, "Source URL not specified");
//		}
//	}
//
//	protected void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPrintActionPerformed
//		// Add your handling code here:
//		if (url != null) {
//			if (jasperPrint == null) {
//				try {
//					jasperPrint = (JasperPrint) JRLoader.loadObject(url);
//				} catch (Exception e) {
//					StringWriter swriter = new StringWriter();
//					PrintWriter pwriter = new PrintWriter(swriter);
//					e.printStackTrace(pwriter);
//					JOptionPane.showMessageDialog(this, swriter.toString());
//				}
//			}
//
//			if (jasperPrint != null) {
//				final JasperPrint print = jasperPrint;
//
//				Thread thread = new Thread(new Runnable() {
//					public void run() {
//						try {
//							JasperPrintManager.printReport(print, true);
//						} catch (Exception e) {
//							StringWriter swriter = new StringWriter();
//							PrintWriter pwriter = new PrintWriter(swriter);
//							e.printStackTrace(pwriter);
//							JOptionPane.showMessageDialog(null,
//									swriter.toString());
//						}
//					}
//				});
//
//				thread.start();
//			} else {
//				JOptionPane.showMessageDialog(this, "Empty report.");
//			}
//		} else {
//			JOptionPane.showMessageDialog(this, "Source URL not specified");
//		}
//	}

	public void init() {
		String strUrl = getParameter("REPORT_URL");
		if (strUrl != null) {
			try {
				url = new URL(getCodeBase(), strUrl);// 从获得html参数中获得报表URL
				System.out.println("url=========" + url.toURI());// 要是servlet的路径
			} catch (Exception e) {
				StringWriter swriter = new StringWriter();
				PrintWriter pwriter = new PrintWriter(swriter);
				e.printStackTrace(pwriter);
				JOptionPane.showMessageDialog(this, swriter.toString());
			}
		} else {
			JOptionPane.showMessageDialog(this, "Source URL not specified");
		}
	}

	public void start() {
		if (url != null) {
			try {
				final JasperPrint print = (JasperPrint)JRLoader.loadObject(url);// 发送对象请求，获得JasperPrint对象

				// 调用方法打印所获得的JasperPrint对象
//				JasperPrintManager.printReport((JasperPrint) print, true);

				// 调用方法打印所获得的JasperPrint对象
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							JasperPrintManager.printReport(print, true);
						} catch (Exception e) {
							StringWriter swriter = new StringWriter();
							PrintWriter pwriter = new PrintWriter(swriter);
							e.printStackTrace(pwriter);
							JOptionPane.showMessageDialog(null,
									swriter.toString());
						}
					}
				});
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}