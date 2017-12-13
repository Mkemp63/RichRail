package TaskSpecific;

public abstract class Logger {
	protected String name;
	public abstract boolean log();

	public Logger() {
		name = "Logger";
	}
	
	public Logger(String nm) {
		this();
		name = nm;
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String nm) {
		this.name = nm;
	}

	
}
