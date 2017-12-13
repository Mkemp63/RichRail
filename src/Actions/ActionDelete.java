package Actions;

public class ActionDelete extends Action {
	
	private String name;
	private String type;
	private int idWagon;

	@Override
	public void useAction() {
		
		if (type.equals("train")) {
			tcont.deleteTrain(name);
		} else if (type.equals("wagon")) {
			wcont.deleteWagon(idWagon);
		}
		
	}

}
