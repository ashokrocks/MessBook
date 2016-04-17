package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Pass_error extends Dialog {

	protected Object result;
	protected Shell shlError;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Pass_error(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlError.open();
		shlError.layout();
		Display display = getParent().getDisplay();
		while (!shlError.isDisposed()) {
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
		shlError = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlError.setImage(SWTResourceManager.getImage(Pass_error.class, "/javax/swing/plaf/metal/icons/Error.gif"));
		shlError.setSize(214, 140);
		shlError.setText("Error");
		
		Label lblWrongPassword = new Label(shlError, SWT.NONE);
		lblWrongPassword.setBounds(53, 10, 109, 15);
		lblWrongPassword.setText("Wrong Password");
		
		Button btnOk = new Button(shlError, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlError.dispose();
				
			}
		});
		btnOk.setBounds(62, 57, 75, 25);
		btnOk.setText("OK");

	}

}
