package TaskSpecific;

import java.util.ArrayList;

import Domain.Train;
import Domain.Wagon;

public class WagonController extends Controller {
	private static WagonController instance;


	public static WagonController getInstance(){
		if(instance == null)
			instance = new WagonController();
		return instance;
	}
	
	public void addWagon(int id, int seats){
		boolean wagonExists = true;
		boolean wagonIDExists = false;
		for(Wagon w : wagons ){ 
			if (id == w.getID()){
				wagonIDExists = true;
				System.out.println("Wagon ID is al gebruikt");
			}
		}
		
		if(wagonIDExists == false ){
			System.out.println("Wagon "+id+" build with "+seats+" seathingplaces");
			wagonExists = false;
			Wagon wagon = new Wagon(id, seats);
			wagons.add(wagon);
			logs.add("Wagon "+id+" build with "+seats+" seathingplaces");
			notifyObservers();
		}

		if(wagonExists) {
			logs.add("Wagon "+id+" not build, it already exist");
			notifyObservers();
		}
	}

	public void getSeatsFromWagon(int wagon){
		if(wagonNotEquals(wagon)){
			logs.add("Wagon does not exist");
			notifyObservers();
		}
		ArrayList<Wagon> WagonList = wagons;
		for(Wagon w: WagonList){
			if(w.getID()==wagon){
				logs.add("Number of seats in wagon "+wagon+":"+w.getSeats());
				notifyObservers();
			}
		}
	}

	public void deleteWagon(int wagon){
		boolean b = false;
		if(wagonNotEquals(wagon)){
			logs.add("Wagon does not exist");
			notifyObservers();
			b = true;
		}
		if(!b){
			for(Wagon w: wagons){
				if(w.getID()==wagon){
					wagons.remove(w);
					for(Train t: trains){
						t.removeWagon(w);
						logs.add("Wagon "+wagon+" deleted");
						notifyObservers();
					}

				}
			}
		}
	}
}
