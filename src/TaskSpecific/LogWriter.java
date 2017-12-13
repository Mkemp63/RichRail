package TaskSpecific;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter extends Logger{
	protected String filePath;
	
	public LogWriter(String nm, String fp) {
		super(nm);
		this.filePath = fp;
	}

	@Override
	public boolean log() {
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.close();
			return true;
		} catch (IOException e) {
			System.out.print(e);
			return false;
		}
	}
	
	
}
