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
	Logger log = new LogWriter("LogFile", "log.txt");

	public Controller(){
		this.loggers = new ArrayList<Logger>();
		addLogger(log);
	}

	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	public boolean trainNotEquals(String name){
		for(Train t: trains){
			if(t.getName().equals(name)){
				return false;		
			}
		}return true;
	}

	public boolean wagonNotEquals(int id){
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
		if(trainNotEquals(train)){
			logs.add("train does not exist.");
			notifyObservers();
		}
		if(wagonNotEquals(wagon)){
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

	public void unlinkWagon(String train, int wagon){
		if(trainNotEquals(train)){
			logs.add("Train does not exist");
			notifyObservers();
		}
		if(wagonNotEquals(wagon)){
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

	public void notifyObservers() {
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
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

}
