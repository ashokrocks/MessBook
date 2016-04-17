package org.eclipse.wb.swt;

import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class Views {
	private Table table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Views window = new Views();
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
		Shell shlViews = new Shell(display,SWT.CLOSE);
		shlViews.setSize(448, 374);
		shlViews.setText("Views");

		TabFolder tabFolder = new TabFolder(shlViews, SWT.NONE);
		tabFolder.setBounds(10, 21, 414, 304);

		TabItem tbtmDaily = new TabItem(tabFolder, SWT.NONE);
		tbtmDaily.setText("Monthly");

		table = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmDaily.setControl(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(108);
		tblclmnNewColumn.setText("Transaction ID ");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Name");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Hostel");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Amount");
		try {
			Date dt = new Date();
			TableItem[] tableItem = new TableItem[600];
			SimpleDateFormat sdm = new SimpleDateFormat("MM-yyyy");

			String a = sdm.format(dt);
			Statement stm = new DatabaseConn().RetStat();
			ResultSet rs;

			rs = stm.executeQuery("SELECT mydb.maintable.std_name , mydb.maintable.hostelname ,mydb.transactiontable.Amount_Transacted,mydb.transactiontable.TransactionId , mydb.transactiontable.Date from mydb.maintable inner join mydb.transactiontable on mydb.maintable.stdid = mydb.transactiontable.std_id where Date='"
					+ a + "'");

			while (rs.next()) {
				int i = 1;

				String[] data = { rs.getString("TransactionId"),
						rs.getString("std_name"), rs.getString("hostelname"),
						rs.getString("Amount_Transacted") };

				tableItem[i] = new TableItem(table, SWT.None);
				tableItem[i].setText(data);
				i++;

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		shlViews.open();
		shlViews.layout();
		while (!shlViews.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
