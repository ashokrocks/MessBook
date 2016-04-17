package org.eclipse.wb.swt;


import java.sql.ResultSet;
import java.sql.Statement;

public class CheckValidity {
	int pntr;
	String checkid;

	public CheckValidity() {
	}

	public CheckValidity(String id) {
		checkid = id;
	}
	public int check()
	{
		try {
			Statement stm = new DatabaseConn().RetStat();
			ResultSet rs = stm
					.executeQuery("SELECT * FROM mydb.maintable");
			while (rs.next()) {

				String stdid = rs.getString("stdid");

				if (stdid.equals(checkid)) {
					pntr = 0;
					break;

				} else {
					pntr = 1;
				}

			}

		} catch (Exception err) {
			err.printStackTrace();
		}

		return pntr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
