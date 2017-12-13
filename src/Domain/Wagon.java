package Domain;

public class Wagon {
	private int ID;
	private int Seats;

	public Wagon(int id, int seats){
		ID = id;
		Seats = seats;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public int getSeats() {
		return Seats;
	}
	public void setSeats(int seats) {
		Seats = seats;
	}
}
