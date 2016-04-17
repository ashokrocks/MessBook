package org.eclipse.wb.swt;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Main_window {
	Text text;
	int pntr;

	public Main_window() {

	}

	public static void main(String[] args) {
		try {
			Main_window window = new Main_window();
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
		Shell shlMessPayment = new Shell(display,SWT.CLOSE|SWT.MIN|SWT.MAX);
		shlMessPayment.setImage(SWTResourceManager.getImage(Main_window.class,
				"/org/eclipse/wb/swt/a222f4c097105908320fa87a86ddf431.ico"));
		shlMessPayment.setSize(688, 354);
		shlMessPayment.setText("Messbook");
		

		Menu menu = new Menu(shlMessPayment, SWT.BAR);
		shlMessPayment.setMenuBar(menu);
		

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("File");

		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);

		MenuItem mntmToday = new MenuItem(menu_1, SWT.NONE);
		mntmToday.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Views newView = new Views();
				newView.open();
			}
		});
		mntmToday.setText("Views");

		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setText("Exit");

		MenuItem mntmDatabase = new MenuItem(menu, SWT.CASCADE);
		mntmDatabase.setText("Database");

		Menu menu_3 = new Menu(mntmDatabase);
		mntmDatabase.setMenu(menu_3);

		MenuItem mntmSetBill = new MenuItem(menu_3, SWT.NONE);
		mntmSetBill.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Data_in Datain = new Data_in(shlMessPayment, 0);
				Datain.open();
			}
		});
		mntmSetBill.setText("Set Monthly Bill");

		MenuItem mntmAddRemove = new MenuItem(menu_3, SWT.NONE);
		mntmAddRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Data_entry dataentry = new Data_entry(shlMessPayment, 0);
				dataentry.open();

			}
		});
		mntmAddRemove.setText("Add Students");

		MenuItem mntmRemoveStudents = new MenuItem(menu_3, SWT.NONE);
		mntmRemoveStudents.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RemoveStudents remstu = new RemoveStudents();
				remstu.open();
			}
		});
		mntmRemoveStudents.setText("Remove Students");
		
		MenuItem mntmAddAdmin = new MenuItem(menu_3, SWT.NONE);
		mntmAddAdmin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddAdmin adAdmin = new AddAdmin();
				adAdmin.open();
			}
		});
		mntmAddAdmin.setText("Add Admin");

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_2 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_2);

		MenuItem mntmHelpTopics = new MenuItem(menu_2, SWT.NONE);
		mntmHelpTopics.setText("Help Topics");

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				About abobj= new About(shlMessPayment, 0);
				abobj.open();
			}
		});
		mntmAbout.setText("About");

		text = new Text(shlMessPayment, SWT.BORDER);
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		text.setBounds(245, 130, 191, 29);

		Button btnNewButton = new Button(shlMessPayment, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 10,
				SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String id = text.getText();
				CheckValidity cobj = new CheckValidity(id);
				pntr = cobj.check();

				
				if (pntr == 0) {

					CurrentStudent cs = new CurrentStudent(id);
					cs.open();
				} else {
					Notification notif = new Notification("Enter Valid ID");
					notif.open();
				}

			}
		});
		btnNewButton.setText("PAY BILL");
		btnNewButton.setBounds(496, 126, 94, 33);

		Label lblEnrollmentNumber = new Label(shlMessPayment, SWT.NONE);
		lblEnrollmentNumber.setFont(SWTResourceManager.getFont("Segoe UI", 10,
				SWT.NORMAL));
		lblEnrollmentNumber.setBounds(31, 131, 175, 29);
		lblEnrollmentNumber.setText("ENROLLMENT NUMBER");

		shlMessPayment.open();
		shlMessPayment.layout();
		while (!shlMessPayment.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
