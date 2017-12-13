package Actions;

import TaskSpecific.Controller;

public abstract class Action {
	
	public Controller cont = Controller.getInstance();

	public abstract void useAction();
	
}
