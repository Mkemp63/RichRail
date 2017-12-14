package Actions;

public class ActionRemove extends Action{

	private String trainName;
	private int wagonID;

	@Override
	public void useAction(String input) {
		System.out.println(input);
		String[] commands = input.split(" ");
		if (commands.length > 3) {
			for (String i: commands) {
				System.out.println(i);
			}
			trainName = commands[3];
			wagonID = Integer.parseInt(commands[1]);
			cont.unlinkWagon(trainName, wagonID);
		} else {
			System.out.println("Unknown command");
		}

	}
}
