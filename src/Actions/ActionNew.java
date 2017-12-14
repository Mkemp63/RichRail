package Actions;

import javax.swing.JOptionPane;

import Domain.Train;
import Domain.Wagon;

public class ActionNew extends Action{

	private String name;
	private int numSeats;
	private String type;
	private int idWagon;

	@Override
	public void useAction(String input) {
		System.out.println(input);
		try {
			String[] commands = input.split(" ");
			type = commands[1];
			System.out.println(commands[1]);
			if (type.equals("train") && (commands.length == 3)){
				name = commands[2];
				cont.addTrain(name);

			} else if(type.equals("wagon") && commands.length >= 3){
				idWagon = Integer.parseInt(commands[2]);
				if (commands.length == 5) {
					numSeats =  Integer.parseInt(commands[4]);
					cont.addWagon(idWagon, numSeats);
				} else {
					cont.addWagon(idWagon, 20);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "command not correct");
				System.out.println("Unknown command");
			}
		} catch(Exception e){
			System.out.println("Unknown command");
		}
	}
}
