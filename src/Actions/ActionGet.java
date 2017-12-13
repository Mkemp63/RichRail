package Actions;

public class ActionGet extends Action {

	private String name;
	private String type;
	private int idWagon;
	
	@Override
	public void useAction() {
		
		if (type.equals("train")){ 
			cont.getSeats(name);
		} else if (type.equals("wagon")) {
			cont.getSeatsFromWagon(idWagon);
		}
		
		
	}

	
}
