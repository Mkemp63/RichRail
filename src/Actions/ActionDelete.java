package Actions;

public class ActionDelete extends Action {

	private String name;
	private String type;
	private int idWagon;

	@Override
	public void useAction(String input) {
		System.out.println(input);
		String[] commands = input.split(" ");
		type = commands[1];
		if (type.equals("train")){
			name = commands[2];
			cont.deleteTrain(name);

		} else if(type.equals("wagon")){
			idWagon = Integer.parseInt(commands[2]);
			if (commands.length <= 3) { 
				cont.deleteWagon(idWagon);
			}
		}
		else{

			System.out.println("Unknown command");
		}

	}
}
