package GUI;

import javax.swing.SwingUtilities;

import TaskSpecific.Controller;

public class Main {
	private static Controller controller = Controller.getInstance();
	
	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				Window w = new Window("RichRail");	
				
				controller.addTrain("thomas");
				controller.addWagon(8450, 25);
				controller.addWagon(8550, 40);
				controller.addWagon(8650, 25);

				w.setLocationRelativeTo(null);
				w.setVisible(true);
			}
		});
		
	}
}
