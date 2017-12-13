package Actions;

public class ActionAdd extends Action {
	private String nameTrain;
	private int idWagon;
	
	@Override
	public void useAction(String train) {
		
		cont.linkWagon(nameTrain, idWagon);
		
	}
}
