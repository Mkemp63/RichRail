package Actions;

public class ActionAdd extends Action {
	private String nameTrain;
	private int idWagon;
	
	@Override
	public void useAction() {
		
		cont.linkWagon(nameTrain, idWagon);
		
	}
}
