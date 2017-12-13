package TaskSpecific;

import java.util.ArrayList;
import java.util.Iterator;

import Domain.*;

public class Controller implements Observable {
	private static Controller instance;
	public ArrayList<String> logs = new ArrayList<String>();
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	public ArrayList<Train> trains = new ArrayList<Train>();
	public ArrayList<Wagon> wagons = new ArrayList<Wagon>();
	protected ArrayList<Logger> loggers;
	Logger log = new LogWriter("LogFile", "log.rtf");

	public Controller(){
		this.loggers = new ArrayList<Logger>();
		addLogger(log);
	}

	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	public void addTrain(String name) {
		if(trainExist(name)){
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

	public boolean trainExist(String name){
		for(Train t: trains){
			if(t.getName().equals(name)){
				return false;		
			}
		}return true;
	}

	public void addWagon(int id, int seats){
		boolean wagonExists = true;
		boolean wagonIDExists = false;
		for(Wagon w : wagons ){ 
			if (id == w.getID()){
				wagonIDExists = true;
			}
		}

		if(wagonIDExists == false ){
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

	public boolean wagonExist(int id){
		ArrayList<Wagon> WagonList = wagons;
		for(Wagon w: WagonList){
			if(w.getID()==id){
				return false;
			}
		}return true;
	}

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

	public void linkWagon(String train, int wagon){
		if(trainExist(train)){
			logs.add("train does not exist.");
			notifyObservers();
		}
		if(wagonExist(wagon)){
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
							notifyObservers();
						}else{
							logs.add("Wagon is already in linked to another train.");
							notifyObservers();
						}
					}		
				}
			}
		}
	}

	public void getSeatsFromWagon(int wagon){
		if(wagonExist(wagon)){
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

	public void getSeats(String train){
		if(trainExist(train)){
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

	public void deleteWagon(int wagon){
		boolean b = false;
		if(wagonExist(wagon)){
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
	
	public void deleteTrain(String train){
		boolean b = false;
		if(trainExist(train)){
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

	public void unlinkWagon(String train, int wagon){
		if(trainExist(train)){
			logs.add("Train does not exist");
			notifyObservers();
		}
		if(wagonExist(wagon)){
			logs.add("Wagon does not exist");
			notifyObservers();
		}
		boolean b = false;
		for(Train t: trains){
			if(t.getName().equals(train)){
					for(Wagon w: t.getWagons()){
						if(w.getID()==wagon){
							b = true;
							t.removeWagon(w);
							logs.add("Wagon "+wagon+" unlinked from train "+ train);
							notifyObservers();
						}
					}
				}
			}
		if(!b){
			logs.add("Wagon "+wagon+" not unlinked from train "+ train);
			notifyObservers();
		}
		}
	
	private void notifyObservers() {
		Iterator<Observer> oi = observers.iterator();
		while( oi.hasNext() ) {
			Observer ob = (Observer) oi.next();
			ob.update( this );
		}
	}



	public void addLogger(Logger logger) {

		loggers.add(logger);
	}

	public void removeLogger(Logger logger) {
		if(loggers.contains(logger)) {
			loggers.remove(logger);
		}
	}

	public ArrayList<Logger> getLogs(){
		return loggers;
	}

	public void setWriters(ArrayList<Logger> loggers) {
		this.loggers = loggers;
	}
	
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}
	
}
