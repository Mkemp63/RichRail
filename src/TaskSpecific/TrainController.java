package TaskSpecific;

import java.util.ArrayList;

import Domain.Train;
import Domain.Wagon;

public class TrainController extends Controller{

	public void addTrain(String name) {
		if(trainNotEquals(name)){
			Train t = new Train(name);

			logs.add("Train "+name+" build");
			trains.add(t);
			notifyObservers();
		}
		else{
			logs.add("Train "+name+" has not been build");
			notifyObservers();
		}
	}

	public void getSeats(String train){
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			notifyObservers();
		}
		int seats = 0;
		ArrayList<Train> Trainlist = trains;
		ArrayList<Wagon> WagonList = null;
		for(Train t: Trainlist){
			if(t.getName().equals(train)){
				WagonList = t.getWagons();
			}
		}
		for(Wagon w: WagonList){
			seats = seats + w.getSeats();
		}
		logs.add("Number of seats in train "+train+":"+seats);
		notifyObservers();
	}

	public void deleteTrain(String train){
		boolean b = false;
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			notifyObservers();
			b = true;
		}
		if(!b){
			Train tr = new Train();
			for(Train t: trains){
				if(t.getName().equals(train)){

					tr = t;

				}
			}
			logs.add("Train "+train+" deleted");
			trains.remove(tr);
			notifyObservers();
		}
	}

}
