package TaskSpecific;

import java.util.ArrayList;

import Domain.Train;
import Domain.Wagon;

public class WagonController extends Controller {
	private static WagonController instance;


	public static WagonController getInstance(){
		if(instance == null)
			instance = new WagonController();
		return instance;
	}
	
	
}
