package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager2;

import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				Window w = new Window("RichRail");
				Panel panel1 = new Panel();
				Panel panel2 = new Panel();
				Text text1 = new Text("Hello World!");
				Text text2 = new Text("Hello Cedric!");

				
				panel1.setBackground(Color.darkGray);
				panel1.add(text1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				w.getContentPane().add(panel1, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				
				panel2.setBackground(Color.LIGHT_GRAY);
				panel2.add(text2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				w.getContentPane().add(panel2, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				
				w.setLocationRelativeTo(null);
				w.setVisible(true);

			}
		});
	}
}
