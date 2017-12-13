package Actions;

public class ActionAdd extends Action {
	private String trainName;
	private int wagonID;

	@Override
	public void useAction(String input) {

		System.out.println(input);
		String[] commands = input.split(" ");
		if (commands.length == 4) {
			
			trainName = commands[3];
			wagonID = Integer.parseInt(commands[1]);
			cont.linkWagon(trainName, wagonID);
		} else {
			System.out.println("Unknown command");
		}
	}
}
