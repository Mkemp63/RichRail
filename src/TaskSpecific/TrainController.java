package TaskSpecific;

import java.util.ArrayList;

import Domain.Train;
import Domain.Wagon;

public class TrainController extends Controller{

	private static TrainController instance;

	public static TrainController getInstance(){
		if(instance == null)
			instance = new TrainController();
		return instance;
	}
	
	

}
