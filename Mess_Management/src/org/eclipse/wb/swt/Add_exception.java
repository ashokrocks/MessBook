package org.eclipse.wb.swt;


import java.sql.Statement;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Add_exception extends Dialog {

	protected Object result;
	protected Shell shlExceptions;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Add_exception(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlExceptions.open();
		shlExceptions.layout();
		Display display = getParent().getDisplay();
		while (!shlExceptions.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlExceptions = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlExceptions.setSize(245, 267);
		shlExceptions.setText("Exceptions");
		
		Label lblEnrollmentNumber = new Label(shlExceptions, SWT.NONE);
		lblEnrollmentNumber.setBounds(10, 46, 88, 15);
		lblEnrollmentNumber.setText("Roll Number");
		
		text = new Text(shlExceptions, SWT.BORDER);
		text.setBounds(119, 43, 76, 21);
		
		text_1 = new Text(shlExceptions, SWT.BORDER);
		text_1.setBounds(119, 95, 76, 21);
		
		Label lblMessBill = new Label(shlExceptions, SWT.NONE);
		lblMessBill.setBounds(10, 98, 55, 15);
		lblMessBill.setText("Mess Bill");
		
		Button btnAddException = new Button(shlExceptions, SWT.NONE);
		btnAddException.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CheckValidity cobj = new CheckValidity(text.getText());
				int pntr = cobj.check();

				
				if (pntr == 0) {

					try {
						Statement stm = new DatabaseConn().RetStat();
						System.out.println(text.getText());
						stm.executeUpdate("UPDATE mydb.maintable SET DueFees ='"+text_1.getText()+ "' WHERE stdid = '"+text.getText()+"'");

					} catch (Exception E) {
						E.getStackTrace();
					}
				} else {
					Notification notif = new Notification("Enter Valid ID");
					notif.open();
				}
				
			}
		});
		btnAddException.setBounds(10, 160, 88, 25);
		btnAddException.setText("Add Exception");
		
		Button btnClose = new Button(shlExceptions, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlExceptions.dispose();
			}
		});
		btnClose.setBounds(144, 160, 75, 25);
		btnClose.setText("Close");

	}

}
