package Actions;

import javax.swing.JOptionPane;

public class ActionRemove extends Action{

	private String trainName;
	private int wagonID;
	private String term;

	@Override
	public void useAction(String input) {
		System.out.println(input);
		try {
			String[] commands = input.split(" ");
			term = commands[2];
			if (commands.length == 4 && term.equals("from")) {
				trainName = commands[3];
				wagonID = Integer.parseInt(commands[1]);
				cont.unlinkWagon(trainName, wagonID);
			} else {
				JOptionPane.showMessageDialog(null, "command not correct");
				System.out.println("Unknown command");
			}
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "command not correct");
			System.out.println("Unknown command");
		}
	}
}

