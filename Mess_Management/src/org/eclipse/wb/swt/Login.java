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
import org.eclipse.wb.swt.Pass_error;
import org.eclipse.wb.swt.SWTResourceManager;

public class Login {
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		Shell shlLoginAuthentication = new Shell(display,SWT.CLOSE);
		shlLoginAuthentication.setImage(SWTResourceManager.getImage(
				Login.class,
				"/org/eclipse/wb/swt/a222f4c097105908320fa87a86ddf431.ico"));
		shlLoginAuthentication.setSize(450, 300);
		shlLoginAuthentication.setText("Login Authentication");

		Label lblUsername = new Label(shlLoginAuthentication, SWT.NONE);
		lblUsername.setBounds(108, 96, 55, 15);
		lblUsername.setText("Username");

		Label lblPassword = new Label(shlLoginAuthentication, SWT.NONE);
		lblPassword.setBounds(108, 135, 55, 15);
		lblPassword.setText("Password");

		text = new Text(shlLoginAuthentication, SWT.BORDER);
		text.setBounds(235, 93, 110, 21);

		text_1 = new Text(shlLoginAuthentication, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(235, 132, 110, 21);

		Button btnLogin = new Button(shlLoginAuthentication, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String uname = text.getText();
				String pass = text_1.getText();
				int check = 0;
				Statement stm = new DatabaseConn().RetStat();
				try {
					ResultSet rs = stm
							.executeQuery("select * from mydb.admin;");
					while (rs.next()) {
						if (rs.getString("name").equals(uname)
								&& rs.getString("password").equals(pass)) {
							Main_window mw = new Main_window();
							shlLoginAuthentication.dispose();
							mw.open();
							check = 1;
						}

					}
					if (check == 0) {
						Pass_error pe = new Pass_error(shlLoginAuthentication,
								0);
						pe.open();
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnLogin.setBounds(122, 206, 75, 25);
		btnLogin.setText("Login");

		Button btnCancel = new Button(shlLoginAuthentication, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(257, 206, 75, 25);
		btnCancel.setText("Cancel");

		shlLoginAuthentication.open();
		shlLoginAuthentication.layout();
		while (!shlLoginAuthentication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
