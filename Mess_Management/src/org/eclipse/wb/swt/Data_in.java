package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.Add_exception;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import java.sql.*;

public class Data_in extends Dialog {

	protected Object result;
	protected Shell shlDataEntry;
	private Text text;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Data_in(Shell parent, int style) {
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
		shlDataEntry.open();
		shlDataEntry.layout();
		Display display = getParent().getDisplay();
		while (!shlDataEntry.isDisposed()) {
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
		shlDataEntry = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlDataEntry.setSize(450, 300);
		shlDataEntry.setText("Data Entry");

		Label label = new Label(shlDataEntry, SWT.NONE);
		label.setBounds(10, 33, 55, 15);

		Label lblNewLabel = new Label(shlDataEntry, SWT.NONE);
		lblNewLabel.setBounds(10, 52, 94, 15);
		lblNewLabel.setText("Choose Hostel");

		Combo combo = new Combo(shlDataEntry, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ho_name = combo.getText();
				System.out.println(ho_name);
			}
		});
		combo.setBounds(159, 49, 91, 23);
		combo.add("JLN");
		combo.add("CVR");
		combo.add("RNT");
		combo.add("KC");
		combo.add("IG");

		Label lblSetMessBill = new Label(shlDataEntry, SWT.NONE);
		lblSetMessBill.setBounds(10, 131, 94, 15);
		lblSetMessBill.setText("Set Mess Bill");

		text = new Text(shlDataEntry, SWT.BORDER);
		text.setBounds(159, 125, 91, 21);

		Button btnExceptions = new Button(shlDataEntry, SWT.NONE);
		btnExceptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Add_exception addEx = new Add_exception(shlDataEntry,
						getStyle());
				addEx.open();
			}
		});
		btnExceptions.setBounds(327, 126, 75, 25);
		btnExceptions.setText("Exceptions");

		Button btnDone = new Button(shlDataEntry, SWT.NONE);
		btnDone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					Statement stm = new DatabaseConn().RetStat();
					System.out.println(text.getText());
					stm.executeUpdate("UPDATE mydb.maintable SET DueFees ='"
							+ text.getText() + "' WHERE hostelname = '"
							+ combo.getText() + "'");

				} catch (Exception E) {
					E.getStackTrace();
				}

			}
		});
		btnDone.setBounds(248, 226, 75, 25);
		btnDone.setText("Done");

		Button btnCancel = new Button(shlDataEntry, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDataEntry.dispose();
			}
		});
		btnCancel.setBounds(349, 226, 75, 25);
		btnCancel.setText("Cancel");

	}
}
