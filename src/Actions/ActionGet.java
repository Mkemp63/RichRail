package Actions;

public class ActionGet extends Action {

	private String name;
	private String type;
	private int idWagon;
	
	@Override
	public void useAction() {
		
		if (type.equals("train")){ 
			tcont.getSeats(name);
		} else if (type.equals("wagon")) {
			wcont.getSeatsFromWagon(idWagon);
		}
		
		
	}

	
}
