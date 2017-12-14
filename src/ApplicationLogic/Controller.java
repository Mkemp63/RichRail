package ApplicationLogic;

import java.util.ArrayList;
import java.util.Iterator;

import Domain.*;

public class Controller implements Observable {
	private static Controller instance;
	public ArrayList<String> logs = new ArrayList<String>();
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	public ArrayList<Train> trains = new ArrayList<Train>();
	public ArrayList<Wagon> wagons = new ArrayList<Wagon>();

	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	//Check if train exists
	public boolean trainNotEquals(String name){
		for(Train t: trains){
			if(t.getName().equals(name)){
				return false;		
			}
		}return true;
	}

	//Check if wagon exists
	public boolean wagonNotEquals(int id){
		ArrayList<Wagon> WagonList = wagons;
		for(Wagon w: WagonList){
			if(w.getID()==id){
				return false;
			}
		}return true;
	}
	
	//Check if wagon is linked
	public boolean wagonInUse(Wagon wagon){
		ArrayList<Wagon> WagonList = null;
		for(Train t: trains){
			WagonList = t.getWagons();
			for(Wagon w: WagonList){
				if(w.equals(wagon)){
					return false;
				}
			}
		}
		return true;
	}

	//Link wagon to train
	public void linkWagon(String train, int wagon){
		System.out.println("Check Train: "+ train + " Wagon: " + wagon);

		if (trainNotEquals(train)) {
			System.out.println("Wagon doet not exist.");
			logs.add("Wagon doet not exist.");
			notifyObservers();
		}
		if (wagonNotEquals(wagon)) {
			System.out.println("Wagon doet not exist.");
			logs.add("Wagon doet not exist.");
			notifyObservers();
		}

		for(Train t: trains){
			if(t.getName().equals(train)){
				for(Wagon w: wagons){
					if(w.getID()==wagon){
						if(wagonInUse(w)){
							t.addWagon(w);
							logs.add("Wagon "+wagon+" linked to train "+ train);
							System.out.println("Wagon "+wagon+" linked to train "+ train);
							notifyObservers();
						}else {
							System.out.println("Wagon already linked to a train.");
							logs.add("Wagon already linked to a train.");
							notifyObservers();
						}
					}
				}

			}
		}
	}

	//Unlink wagon to train
	public void unlinkWagon(String train, int wagon){
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			notifyObservers();
		}
		if(wagonNotEquals(wagon)){
			logs.add("Wagon does not exist");
			notifyObservers();
		}
		boolean linked = false;
		for(Train t: trains){
			if(t.getName().equals(train)){
				ArrayList<Wagon> wagons = t.getWagons();
				for(Wagon w : wagons){
					if(w.getID()==wagon){
						linked = true;
						t.removeWagon(w);
						logs.add("Wagon "+wagon+" unlinked from train "+ train);
						System.out.println("Wagon "+wagon+" unlinked from train "+ train);
						notifyObservers();
					}
				}
			}
		}
		if(linked = false){
			logs.add("Wagon "+wagon+" not unlinked from train "+ train);
			System.out.println("Wagon "+wagon+" not unlinked from train "+ train);
			notifyObservers();
		}
	}

	//Update windows
	public void notifyObservers() {

		if(observers.size() == 0) {
			System.out.println("Er zijn nog geen observers");
		} else {
			Iterator<Observer> oi = observers.iterator();
			while( oi.hasNext() ) {
				Observer ob = (Observer) oi.next();
				ob.update( this );
				System.out.println("notified observers");
			}
		}
	}


	//Train Specific
	public void addTrain(String name) {
		if(trainNotEquals(name)){
			Train t = new Train(name);

			logs.add("Train "+name+" build");
			System.out.println("Train "+name+" build");
			trains.add(t);
			notifyObservers();
		}
		else{
			logs.add("Train "+name+" has not been build");
			System.out.println("Train "+name+" has not been build");
			notifyObservers();
		}
	}
	//Train Specific
	public void getSeats(String train){
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			notifyObservers();
		} else {
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
			System.out.println("Number of seats in train "+train+":"+seats);
			logs.add("Number of seats in train "+train+":"+seats);
			notifyObservers();
		}
	}
	//Train Specific
	public void deleteTrain(String train){
		boolean b = false;
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			System.out.println("Train does not exist");
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
			System.out.println("Train "+train+" deleted");
			logs.add("Train "+train+" deleted");
			trains.remove(tr);
			notifyObservers();
		}
	}

	//Wagon Specific	
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
	//Wagon Specific	
	public void getSeatsFromWagon(int wagon){
		if(wagonNotEquals(wagon)){
			System.out.println("Wagon does not exist");
			logs.add("Wagon does not exist");
			notifyObservers();
		}
		ArrayList<Wagon> WagonList = wagons;
		for(Wagon w: WagonList){
			if(w.getID()==wagon){
				System.out.println("Number of seats in wagon "+wagon+" is "+w.getSeats());
				logs.add("Number of seats in wagon "+wagon+": "+w.getSeats());
				notifyObservers();
			}
		}
	}
	//Wagon Specific	
	public void deleteWagon(int wagon){
		boolean exists = false;
		if(wagonNotEquals(wagon)){
			logs.add("Wagon does not exist");
			System.out.println("Wagon does not exist");
			notifyObservers();
			exists = true;
		}
		if(exists = true){
			for(Wagon w: wagons){
				if(w.getID()==wagon){
					wagons.remove(w);
					for(Train t: trains){
						t.removeWagon(w);
						System.out.println("Wagon" + wagon + "is deleted");
						logs.add("Wagon "+wagon+" deleted");
						notifyObservers();
					}

				}
			}
		}
	}


	public ArrayList<String> getLogs(){
		return logs;
	}
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

}
