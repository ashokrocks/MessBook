package org.eclipse.wb.swt;


import java.sql.Statement;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class RemoveStudents {
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RemoveStudents window = new RemoveStudents();
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
		Shell shlRemoveStudents = new Shell(display,SWT.CLOSE);
		shlRemoveStudents.setText("Remove Students");
		shlRemoveStudents.setSize(450, 217);

		text = new Text(shlRemoveStudents, SWT.BORDER);
		text.setBounds(124, 66, 122, 21);

		Label lblNewLabel = new Label(shlRemoveStudents, SWT.NONE);
		lblNewLabel.setBounds(10, 69, 97, 21);
		lblNewLabel.setText("Student ID");

		Button btnRemoveStudent = new Button(shlRemoveStudents, SWT.NONE);
		btnRemoveStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CheckValidity cobj = new CheckValidity(text.getText());
				int pntr = cobj.check();

				
				if (pntr == 0) {
					try {
						Statement stm = new DatabaseConn().RetStat();
						
						stm.executeUpdate("Delete from mydb.transactiontable where std_id = "
								+ text.getText());
						stm.executeUpdate("Delete from mydb.maintable where stdid = "
								+ text.getText());
						text.setText("");

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					Notification notif = new Notification("Enter Valid ID");
					notif.open();
				}
				
				
			}
		});
		btnRemoveStudent.setBounds(281, 64, 122, 25);
		btnRemoveStudent.setText("Remove Student");

		Button btnDone = new Button(shlRemoveStudents, SWT.NONE);
		btnDone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlRemoveStudents.close();
		
			}
		});
		btnDone.setBounds(328, 148, 75, 25);
		btnDone.setText("Done");
		
	
		shlRemoveStudents.open();
		shlRemoveStudents.layout();
		while (!shlRemoveStudents.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
