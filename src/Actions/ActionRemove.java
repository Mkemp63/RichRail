package Actions;

public class ActionRemove extends Action{

	private String name;
	private int toDelete;
	
	@Override
	public void useAction(String train) {
	
		cont.unlinkWagon(name, toDelete);
		
	}
}
