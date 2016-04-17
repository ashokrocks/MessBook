package org.eclipse.wb.swt;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class CurrentStudent {

	public CurrentStudent() {

	}

	String idCS;
	String tid;
	int pntr;
	private Text text;

	public CurrentStudent(String data) {
		idCS = data;

	}

	public static void main(String[] args) {
		try {
			CurrentStudent window = new CurrentStudent();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		String Title="No Student Present";
		Display display = Display.getDefault();
		Shell shlCurrentStudent = new Shell(display,SWT.CLOSE|SWT.CASCADE);
		shlCurrentStudent.setSize(526, 351);
		
		try {
			Statement stm = new DatabaseConn().RetStat();
			ResultSet rs;

			rs = stm.executeQuery("SELECT * FROM mydb.maintable where stdid ="
					+ idCS + " ;");

			Label label = new Label(shlCurrentStudent, SWT.NONE);
			label.setText("NAME OF STUDENT");
			label.setFont(SWTResourceManager
					.getFont("Segoe UI", 10, SWT.NORMAL));
			label.setBounds(10, 42, 148, 33);
			Label label_1 = new Label(shlCurrentStudent, SWT.NONE);
			label_1.setBounds(178, 44, 148, 27);
			Label label_2 = new Label(shlCurrentStudent, SWT.NONE);
			label_2.setText("BRANCH");
			label_2.setFont(SWTResourceManager.getFont("Segoe UI", 10,
					SWT.NORMAL));
			label_2.setBounds(10, 108, 94, 33);

			Label lblHostel = new Label(shlCurrentStudent, SWT.NONE);
			lblHostel.setText("HOSTEL");
			lblHostel.setFont(SWTResourceManager.getFont("Segoe UI", 10,
					SWT.NORMAL));
			lblHostel.setBounds(10, 166, 64, 27);
			Label label_3 = new Label(shlCurrentStudent, SWT.NONE);
			label_3.setBounds(178, 108, 148, 33);
			Label label_4 = new Label(shlCurrentStudent, SWT.NONE);
			label_4.setBounds(178, 166, 148, 27);

			Label label_5 = new Label(shlCurrentStudent, SWT.NONE);
			label_5.setBounds(178, 221, 86, 27);

			while (rs.next()) {
				label_4.setText(rs.getString("hostelname"));

				label_3.setText(rs.getString("std_branch"));

				label_1.setText(rs.getString("std_name"));
				Title=rs.getString("std_name");
				label_5.setText(rs.getString("DueFees"));
			}

			Button btnPay = new Button(shlCurrentStudent, SWT.NONE);
			btnPay.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						Date dt = new Date();

						SimpleDateFormat sdm = new SimpleDateFormat("MM-yyyy");

						String a = sdm.format(dt);
						tid = a + idCS;
						

						Statement stm = new DatabaseConn().RetStat();
						ResultSet rs = stm
								.executeQuery("SELECT * FROM mydb.transactiontable");
						while (rs.next()) {

							String stdid = rs.getString("std_id");

							if (stdid.equals(idCS)) {
								pntr = 0;
								break;

							} else {
								pntr = 1;
							}

						}

						if (pntr == 1) {
							System.out.println("from ptr 1");
							if(!label_5.getText().equals("0"))
							{System.out.println(tid);

							stm.executeUpdate("Insert into mydb.transactiontable (TransactionId,std_id,Amount_Transacted,Date) values ('"
									+ tid
									+ "' ,"
									+ idCS
									+ ","
									+ label_5.getText() + ", '" + a + "')");
							stm.executeUpdate("Update mydb.maintable set DueFees = ( DueFees - '"
									+ label_5.getText()
									+ "') ,PaidFees = '"
									+ label_5.getText()
									+ "' where stdid = '"
									+ idCS + "'");

							Notification notif = new Notification("Bill Payed");
							notif.open();
							Button btnPrint = new Button(shlCurrentStudent,
									SWT.NONE);
							btnPrint.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent e) {
									
									
										
									
									Print prnt = new Print(label_1.getText(),
											label_4.getText(), label_5
													.getText(), label_3
													.getText());
									prnt.printf();

								}
							});
							btnPrint.setBounds(381, 277, 75, 25);
							btnPrint.setText("Print");
							btnPay.dispose();
							}
							else
							{
							Notification notif = new Notification("No Dues");
							notif.open();
							}
						} else {
							System.out.println("from ptr 0");
							if(!label_5.getText().equals("0"))
							{
							stm.executeUpdate("Update mydb.transactiontable set Amount_Transacted = '"
									+ label_5.getText()
									+ "'"
									+ ", TransactionId = '"
									+ tid
									+ "' ,"
									+ "Date = '"
									+ a
									+ "' "
									+ " where std_id = '" + idCS + "' ;");
							
								
								stm.executeUpdate("Update mydb.maintable set DueFees = ( DueFees - '"
									+ text.getText()
									+ "') ,PaidFees = '"
									+ label_5.getText()
									+ "' where stdid = '"
									+ idCS + "'");

							Notification notif = new Notification("Bill Payed");
							notif.open();
							Button btnPrint = new Button(shlCurrentStudent,
									SWT.NONE);
							btnPrint.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent e) {
									
									Print prnt = new Print(label_1.getText(),
											label_4.getText(), text
													.getText(), label_3
													.getText());
									Thread PrintThread = new Thread(prnt);
									PrintThread.run();

								}
							});
							btnPrint.setBounds(381, 277, 75, 25);
							btnPrint.setText("Print");
							btnPay.dispose();
						}
							else
								{
								Notification notif = new Notification("No Dues");
								notif.open();
								}
								}
					} catch (Exception Err) {
						Err.printStackTrace();
					}
				}
			});
			btnPay.setBounds(229, 277, 75, 25);
			btnPay.setText("PAY");

			Label lblBill = new Label(shlCurrentStudent, SWT.NONE);
			lblBill.setBounds(10, 221, 55, 15);
			lblBill.setText("BILL");
			
			Label lblAmountPayed = new Label(shlCurrentStudent, SWT.NONE);
			lblAmountPayed.setBounds(289, 221, 79, 15);
			lblAmountPayed.setText("Amount Payed");
			
			text = new Text(shlCurrentStudent, SWT.BORDER);
			text.setBounds(387, 218, 76, 21);

		} catch (Exception e) {
			e.printStackTrace();
		}
		shlCurrentStudent.setText(Title);

		shlCurrentStudent.open();
		shlCurrentStudent.layout();
		while (!shlCurrentStudent.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
