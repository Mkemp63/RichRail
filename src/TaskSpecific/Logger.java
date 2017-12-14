package TaskSpecific;

public abstract class Logger {
	protected String name;
	protected String description;

	public abstract boolean log();

	public Logger() {
		name = "Logger";
		description = "No description available";

	}
	
	public Logger(String nm) {
		this();
		name = nm;
		
	}
	
	public Logger(String name, String description)
	{
		this(name);
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nm) {
		this.name = nm;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	
}
