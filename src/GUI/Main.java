package GUI;

import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				Window w = new Window("RichRail");				
				w.setLocationRelativeTo(null);
				w.setVisible(true);
			}
		});
	}
}
