package org.eclipse.wb.swt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddAdmin {

	protected Shell shlAddAdmin;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddAdmin window = new AddAdmin();
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
		createContents();
		shlAddAdmin.open();
		shlAddAdmin.layout();
		while (!shlAddAdmin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAddAdmin = new Shell(SWT.CLOSE);
		shlAddAdmin.setSize(334, 256);
		shlAddAdmin.setText("Add Admin");
		
		Label lblAdminName = new Label(shlAddAdmin, SWT.NONE);
		lblAdminName.setBounds(10, 36, 90, 21);
		lblAdminName.setText("Name");
		
		text = new Text(shlAddAdmin, SWT.BORDER);
		text.setBounds(167, 33, 136, 21);
		
		Label lblPassword = new Label(shlAddAdmin, SWT.NONE);
		lblPassword.setBounds(10, 80, 75, 15);
		lblPassword.setText("Password");
		
		Label lblYourPassword = new Label(shlAddAdmin, SWT.NONE);
		lblYourPassword.setBounds(10, 131, 90, 15);
		lblYourPassword.setText("Your Password");
		
		text_1 = new Text(shlAddAdmin,SWT.BORDER |SWT.PASSWORD);
		text_1.setBounds(167, 74, 136, 21);
		
		text_2 = new Text(shlAddAdmin,SWT.BORDER |SWT.PASSWORD);
		text_2.setBounds(167, 125, 136, 21);
		
		Button btnCancel = new Button(shlAddAdmin, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAddAdmin.close();
			}
		});
		btnCancel.setBounds(228, 178, 75, 25);
		btnCancel.setText("Cancel");
		
		Button btnConfirm = new Button(shlAddAdmin, SWT.NONE);
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int check=0;
				String name= text.getText();
				String pass= text_1.getText();
				String passOwn= text_2.getText();
				Statement stm = new DatabaseConn().RetStat();
				try {
					ResultSet rs = stm.executeQuery("select * from mydb.admin;");
					while(rs.next())
					{
						if(rs.getString("password").equals(passOwn))
						{
						check=1;
						stm.execute("insert into mydb.admin values ('" +name+ "','"+ pass +"')");
						Notification notifAdd = new Notification("Admin Added");
						notifAdd.open();
						break;
						}
					
					}
					if(check==0)
					{
						Notification notifAdd = new Notification("Wrong Password");
						notifAdd.open();
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnConfirm.setBounds(78, 178, 75, 25);
		btnConfirm.setText("Confirm");

	}
}
