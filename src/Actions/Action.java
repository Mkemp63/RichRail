package Actions;

import ApplicationLogic.Controller;


public abstract class Action {
	
	public Controller cont = Controller.getInstance();

	public abstract void useAction(String input);
	
}
