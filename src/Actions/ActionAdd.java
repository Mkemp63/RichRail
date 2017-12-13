package Actions;

public class ActionAdd extends Action {
	private String nameTrain;
	private int idWagon;

	@Override
	public void useAction(String input) {

		System.out.println(input);
		String[] commands = input.split(" ");
		if (commands.length == 4) {
			nameTrain = commands[3];
			idWagon = Integer.parseInt(commands[1]);
			cont.linkWagon(nameTrain, idWagon);
		} else {
			System.out.println("Unknown command");
		}
	}
}
