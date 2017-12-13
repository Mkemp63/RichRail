package Actions;

import Domain.Train;
import Domain.Wagon;

public class ActionNew extends Action{
	
	private String name;
	private int numSeats;
	private boolean seatsGiven;
	private String type;
	private int id;
	
	public void seats() {
		if (seatsGiven = false) {
			numSeats = 20;
		}
	};
	
	@Override
	public void useAction() {
		if (type.equals("train")) {
			cont.addTrain(name);
		} else if (type.equals("wagon")) {
			cont.addWagon(id, numSeats);
		}
	}

}
