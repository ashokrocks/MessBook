package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Notification {
	

	String message;
	public Notification(){}
	public Notification(String msg) {
	message = msg;	

	}
	public static void main(String[] args) {
		try {
			Notification window = new Notification();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlNotification = new Shell(display,SWT.CLOSE);
		shlNotification.setSize(235, 211);
		shlNotification.setText("Notification");
		
		Label label = new Label(shlNotification, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label.setBounds(10, 73, 204, 58);
		label.setText(message);
		
		Button btnOk = new Button(shlNotification, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNotification.dispose();
			}
		});
		btnOk.setBounds(63, 137, 75, 25);
		btnOk.setText("OK");
		
		Label label_1 = new Label(shlNotification, SWT.NONE);
		label_1.setAlignment(SWT.CENTER);
		label_1.setImage(SWTResourceManager.getImage(Notification.class, "/javax/swing/plaf/metal/icons/Warn.gif"));
		label_1.setBounds(63, 16, 97, 51);

		shlNotification.open();
		shlNotification.layout();
		while (!shlNotification.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
