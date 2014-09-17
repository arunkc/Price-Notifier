/**
 *Author		: 	Arun Kumar Konduru Chandra
 *Purpose		:	Zappos Challenge
 *Date			:	02/19/2014
 *Description 	:	Application using Zappos API that lets a user pick their desired product(s) 
 *					and then notifies them when the price hit at least 20% off the original price.*/

package notifierPackage;

import java.awt.EventQueue;

// Launch the application.
public class PriceNotifier{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotifierUI frame = new NotifierUI();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					NotifierUI.lblStatus.setText(e.toString());
				}
			}
		});
	}
}

