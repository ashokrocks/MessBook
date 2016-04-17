package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class Printwindow {
	String idPW;

	public Printwindow() {

	}

	public Printwindow(String data) {
		idPW = data;

	}

	public static void main(String[] args) {
		try {
			Printwindow window = new Printwindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		Shell shlPrint = new Shell(display,SWT.CLOSE);
		shlPrint.setSize(555, 328);
		shlPrint.setText("Print");

		Label label = new Label(shlPrint, SWT.NONE);
		label.setBounds(63, 43, 247, 15);
		label.setText(idPW);

		shlPrint.open();
		shlPrint.layout();
		while (!shlPrint.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
