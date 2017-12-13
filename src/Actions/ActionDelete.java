package Actions;

public class ActionDelete extends Action {
	
	private String name;
	private String type;
	private int idWagon;

	@Override
	public void useAction() {
		
		if (type.equals("train")) {
			cont.deleteTrain(name);
		} else if (type.equals("wagon")) {
			cont.deleteWagon(idWagon);
		}
		
	}

}
