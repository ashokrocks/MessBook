package org.eclipse.wb.swt;

import java.awt.*;
import java.awt.print.*;

public class Print implements Printable, Runnable {
	String name, hname, amont, branch;

	public Print() {
	}

	public Print(String nm, String hnm, String amt, String br) {
		name = nm;
		hname = hnm;
		amont = amt;
		branch = br;

	}

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		g.drawString("SAMRAT ASHOK TECHNOLOGICAL INSTITUTE , VIDISHA \n\n\n",
				150, 100);
		g.drawString("NAME OF THE STUDENT -     " + name + "\n\n\n", 100, 150);
		g.drawString("BRANCH OF THE STUDENT -   " + branch + "\n\n\n", 100, 200);
		g.drawString("HOSTEL NAME-              " + hname + "\n\n\n", 100, 250);
		g.drawString("AMOUNT PAYED-             " + amont + "\n\n\n", 100, 300);

		return PAGE_EXISTS;
	}

	void printf() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	public void run() {
		printf();
	}

	public static void main(String[] args) {
		// Print obj = new Print();
		// obj.printf();

	}

}
