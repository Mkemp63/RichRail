package Actions;

import TaskSpecific.Controller;
import TaskSpecific.TrainController;
import TaskSpecific.WagonController;

public abstract class Action {
	
	public Controller cont = Controller.getInstance();
	public TrainController tcont = TrainController.getInstance();
	public WagonController wcont = WagonController.getInstance();

	public abstract void useAction(String train);
	
}
