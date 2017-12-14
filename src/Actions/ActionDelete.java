package Actions;

public class ActionDelete extends Action {

	private String name;
	private String type;
	private int idWagon;

	@Override
	public void useAction(String input) {
		System.out.println(input);
		try {
			String[] commands = input.split(" ");
			type = commands[1];
			if (type.equals("train") && (commands.length == 3)){
				name = commands[2];
				cont.deleteTrain(name);

			} else if(type.equals("wagon") && (commands.length == 3)){
				idWagon = Integer.parseInt(commands[2]); 
				cont.deleteWagon(idWagon);
			}
			else{

				System.out.println("Unknown command");
			}
		} catch(Exception e){
			System.out.println("Unknown command");
		}
	}
}
