package Actions;

import TaskSpecific.Controller;
import TaskSpecific.TrainController;
import TaskSpecific.WagonController;

public abstract class Action {
	
	public Controller cont = Controller.getInstance();

	public abstract void useAction(String train);
	
}
