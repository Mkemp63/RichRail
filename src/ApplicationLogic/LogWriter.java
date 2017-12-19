package ApplicationLogic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogWriter implements Observer{
	private static LogWriter instance;
	private Controller controller = Controller.getInstance();
	private ArrayList<String> logs = new ArrayList<String>();

	private static final String LOG_FILE = System.getProperty("user.dir")+"/RichRailLog.log";

	public LogWriter() {
		controller.addObserver(this);
		
	}
	
	public void WriteLog(String entry){

		BufferedWriter bw = null;

		try {

			bw =new BufferedWriter(new FileWriter(LOG_FILE,true));
			bw.append(entry);
			bw.newLine();

		} catch (IOException e) {

			e.printStackTrace();

		}finally{

			try {

				bw.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	public static LogWriter getInstance(){
		
		if(instance == null)
			instance = new LogWriter();

		return instance;

	}

	
	
	@Override
	public void update(Observable Obs) {

		for(String log :controller.getLogs()){
			if(!logs.contains(log)){
				logs.add(log);
			}
		}
		
		String s = logs.get(logs.size() - 1);
		
		WriteLog(s);
				
	}

}