package org.eclipse.wb.swt;


import java.sql.Statement;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class Data_entry extends Dialog {

	protected Object result;
	protected Shell shlDataEntry;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Data_entry(Shell parent, int style) {
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
		shlDataEntry = new Shell(getParent(), SWT.SHELL_TRIM);

		shlDataEntry.setSize(560, 272);
		shlDataEntry.setText("Data Entry");

		Label lblStudentId = new Label(shlDataEntry, SWT.NONE);
		lblStudentId.setBounds(31, 38, 55, 15);
		lblStudentId.setText("Student ID");

		Label lblStudentName = new Label(shlDataEntry, SWT.NONE);
		lblStudentName.setBounds(294, 38, 89, 15);
		lblStudentName.setText("Student Name");

		Label lblStudentBranch = new Label(shlDataEntry, SWT.NONE);
		lblStudentBranch.setBounds(31, 107, 89, 15);
		lblStudentBranch.setText("Student Branch");

		Label lblHostelName = new Label(shlDataEntry, SWT.NONE);
		lblHostelName.setText("Hostel Name");
		lblHostelName.setBounds(294, 107, 89, 15);

		text = new Text(shlDataEntry, SWT.BORDER);
		text.setBounds(139, 35, 111, 21);

		text_1 = new Text(shlDataEntry, SWT.BORDER);
		text_1.setBounds(417, 35, 111, 21);

		Combo combo = new Combo(shlDataEntry, SWT.NONE);
		combo.setBounds(417, 107, 111, 23);
		combo.setText("Choose Hostel");
		combo.add("JLN");
		combo.add("CVR");
		combo.add("RNT");
		combo.add("IG");
		combo.add("KC");

		Combo combo_1 = new Combo(shlDataEntry, SWT.NONE);
		combo_1.setBounds(139, 99, 111, 23);
		combo_1.add("IT");
		combo_1.add("CSE");
		combo_1.add("EE");
		combo_1.add("ME");
		combo_1.add("CE");
		combo_1.add("PCE");
		combo_1.add("EI");
		combo_1.add("ECE");
		combo_1.add("BME");

		Button btnDone = new Button(shlDataEntry, SWT.NONE);
		btnDone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Statement stm = new DatabaseConn().RetStat();

					stm.executeUpdate("Insert into mydb.maintable"
							+ "(stdid, std_name , std_branch , hostelname)"
							+ "values ('" + text.getText() + "','"
							+ text_1.getText() + "','" + combo_1.getText()
							+ "','" + combo.getText() + "')");
					text.setText("");
					text_1.setText("");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDone.setBounds(267, 186, 99, 25);
		btnDone.setText("Add to Database");

		Button btnNewButton = new Button(shlDataEntry, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDataEntry.close();
			}
		});
		btnNewButton.setBounds(423, 186, 105, 25);
		btnNewButton.setText("Close");

	}
}
