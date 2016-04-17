package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class About extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public About(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.NONE);

		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(450, 316);
		shell.setText(getText());

		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setImage(SWTResourceManager
				.getImage(About.class,
						"/org/eclipse/wb/swt/Black_and_White_Plate_Spaghetti_091126-230088-189009.jpg"));
		label.setBounds(0, 0, 268, 248);

		Label lblMessManagementSystem = new Label(shell, SWT.NONE);
		lblMessManagementSystem.setFont(SWTResourceManager.getFont("Arial", 18, SWT.NORMAL));
		lblMessManagementSystem.setAlignment(SWT.CENTER);
		lblMessManagementSystem.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblMessManagementSystem.setBounds(285, 46, 138, 60);
		lblMessManagementSystem.setText("MessBook V2.0");

		Label lblGauravSharma = new Label(shell, SWT.NONE);
		lblGauravSharma.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
		lblGauravSharma.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblGauravSharma.setAlignment(SWT.CENTER);
		lblGauravSharma.setBounds(274, 123, 164, 74);
		lblGauravSharma
				.setText("(Added -Multi-Threading, single connection, Add Admins)");

		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();

			}
		});
		btnClose.setBounds(363, 289, 75, 25);
		btnClose.setText("Close");

	}
}
