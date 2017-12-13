package Actions;

import Domain.Train;
import Domain.Wagon;

public class ActionNew extends Action{
	
	private String name;
	private int numSeats;
	private boolean seatsGiven;
	private String type;
	private int idWagon;
	
	private int firstInstanceOfSpaces;
	private int secondInstanceOfSpaces;
	private int thirdInstanceOfSpaces;
	private int fourthInstanceOfSpaces;
	
	public void seats() {
		if (seatsGiven = false) {
			numSeats = 20;
		}
	};
	
	@Override
	public void useAction(String input) {
		System.out.println(input);
		String[] commands = input.split(" ");
		type = commands[1];
		System.out.println(commands[1]);
		if (type.equals("train")){
			name = commands[2];
			//vanaf hier weten we dat er een trein moet worden toegevoegd
			tcont.addTrain(name);
		
		} else if(type.equals("wagon")){
			idWagon = Integer.parseInt(commands[2]);
			if (commands.length <= 3) { 
				wcont.addWagon(idWagon, 20);
			} else if (commands.length > 3) {
			numSeats =  Integer.parseInt(commands[4]);	
			//vanaf hier weten we dat er een wagon moet worden toegevoegd
			wcont.addWagon(idWagon, numSeats);
			}
			}
		else{
			
			System.out.println("Unknown command");
		}
	}
}
