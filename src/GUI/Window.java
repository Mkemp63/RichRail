package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import Actions.*;
import ApplicationLogic.*;
import Domain.Train;
import Domain.Wagon;

public class Window extends javax.swing.JFrame implements ActionListener, Observer {
	// variabelen declareren
	private static final long serialVersionUID = 1L;

	private Controller controller = Controller.getInstance();
	private ArrayList<String> logs = new ArrayList<String>();
	

	private JPanel jPanel1;
	private JTextPane tpTextTrain;
	private JButton btnDeleteWagon3;
	private JButton btnDeleteWagon2;
	private JButton btnDeleteWagon1;
	private JButton jButton1;
	private JPanel pnlWagons;
	private JButton btnAddWagon2;
	private JButton btnAddWagon1;
	private JTextField tfCurrentTrain;
	private JButton btnDeleteTrain;
	private JButton btnChooseTrain;
	private JComboBox cbAllTrains;
	private JButton btnNewTrain;
	private JTextField tfNewTrain;
	private JPanel jPanel2;
	private JPanel drawPanel;
	private JButton btnOtherWindow;

	private JPanel jPanel4;
	private JTextField tfCommandLine;
	private JTextArea taOverview;
	private JTextArea taOutput;
	private JButton btnExecute;
	private JButton btnNewWindow;
	
	private JTextPane tpTextWagon;
	private JTextField tfNewWagon;
	private JButton btnNewWagon;
	private JComboBox cbAllWagons;
	private JButton btnChooseWagon;
	private JButton btnDeleteWagon;
	private JTextField tfCurrentWagon;
	private JButton btnLinkWagon;
	private JButton btnUnlinkWagon;
	private JTextPane tpSeats;
	private JTextField tfSeats;
	
	private JButton btnOpenNewWindow;

	private HashMap numberOfWagons;
	private int currentNumberOfWagons;
	private int currentTrain = -1;
	private int currentWagon = -1;
	private int OFFSET = 100;
	private int TRAINLENGTH = 100;

	public Window(String title) {
		super();
		this.setTitle(title);
		GridBagLayout layout = new GridBagLayout();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		layout.rowHeights = new int[] {7, 7, 7, 7};
		layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		layout.columnWidths = new int[] {7, 7, 7, 7};
		getContentPane().setLayout(layout);
		pack();
		setSize(800, 600);
		this.setController(controller);	
		// initCLIGUI methode wordt aangeroepen en uitgevoerd
		initCLIGUI();
		logs.addAll(controller.getLogs());


	}

	// methode voor het initialiseren van de CLI GUI
	private void initCLIGUI() {
		try
		{
			// grid rijen en kolommen worden gezet
			this.setTitle("RichRail | Command Line");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				GridBagConstraints c = new GridBagConstraints();
				jPanel4 = new JPanel();
				GridBagLayout jPanel4Layout = new GridBagLayout();
				//jPanel4.setLayout(null);
				jPanel4.setLayout(jPanel4Layout);
				// vanaf hier worden alle componenten aan het panel toegevoegd
				getContentPane().add(jPanel4, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					taOverview = new JTextArea ();
					taOverview.setPreferredSize(new Dimension(350, 400));
					taOverview.setBackground(Color.WHITE);
					taOverview.setForeground(Color.black);
					taOverview.setEditable(false);
					taOverview.setText("Dit is de overview area");
					taOverview.setSize(400, 200);
					taOverview.setVisible(true);
					taOverview.setLineWrap(true);
					JScrollPane scrollpane = new JScrollPane(taOverview);
					c.gridy = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					jPanel4.add(scrollpane, c);
				}
				{
					taOutput = new JTextArea ();
					taOutput.setPreferredSize(new Dimension(350, 400));
					taOutput.setBackground(Color.BLACK);
					taOutput.setForeground(Color.WHITE);
					taOutput.setEditable(false);

					// methode om de logs te laten zien in de textarea
					String s = "";
					for(String string: logs){
						s = string+ "\n" + s;
					}

					taOutput.setText(s);
					taOutput.setSize(400, 200);
					taOutput.setVisible(true);
					taOutput.setLineWrap(true);
					JScrollPane scrollpane1 = new JScrollPane(taOutput);
					c.gridy = 0;
					c.gridx = 2;
					jPanel4.add(scrollpane1, c);
				}
				{
					tfCommandLine = new JTextField(20);
					c.gridy = 2;
					c.gridx = 1;
					tfCommandLine.setText("Command Line Input");
					jPanel4.add(tfCommandLine, c);
				}
				{
					btnExecute = new JButton();
					c.gridy = 3;
					c.gridx = 1;
					jPanel4.add(btnExecute, c);
					btnExecute.setText("Execute command");
					btnExecute.addActionListener(this);
				}
				{
					btnNewWindow = new JButton();
					c.gridy = 2;
					c.gridx= 2;
					jPanel4.add(btnNewWindow, c);
					btnNewWindow.setText("Open other window");
					btnNewWindow.addActionListener(this);
				}
				{
					btnOpenNewWindow = new JButton();
					c.gridy = 3;
					c.gridx = 2;
					jPanel4.add(btnOpenNewWindow,c);
					btnOpenNewWindow.setText("Open extra window");
					btnOpenNewWindow.addActionListener(this);
				}

			}
			pack();
			setSize(800, 600);
			numberOfWagons = new HashMap();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	// methode voor het initialiseren van de poorrail GUI,
	// aangepast naar behoefte
	private void initGUI() {
		try 
		{
			this.setTitle("RichRail");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				jPanel1 = new JPanel();
				jPanel1.setLayout(new BorderLayout());
				getContentPane().add(jPanel1, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					drawPanel = new JPanel();
					drawPanel.setBackground(Color.WHITE);
					jPanel1.add(drawPanel,BorderLayout.CENTER);
				}
			}
			{
				GridBagConstraints gbc = new GridBagConstraints();
				jPanel2 = new JPanel();
				GridBagLayout jPanel2Layout = new GridBagLayout();
				//jPanel2.setLayout(null);
				jPanel2.setLayout(jPanel2Layout);
				// componenten aan panel toevoegen
				getContentPane().add(jPanel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					tpTextTrain = new JTextPane();
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tpTextTrain, gbc);
					jPanel2.setBounds(10, 10, 100, 15);
					jPanel2Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanel2Layout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7};
					jPanel2Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel2Layout.columnWidths = new int[] {7, 7, 7, 7};
					tpTextTrain.setText("Train name:");
				}
				{
					tfNewTrain = new JTextField(20);
					gbc.gridx = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tfNewTrain, gbc);
				}
				{
					btnNewTrain = new JButton();
					gbc.gridx = 2;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnNewTrain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnNewTrain.setText("Make new train");
					btnNewTrain.addActionListener(this);
				}
				{
					cbAllTrains = new JComboBox();
					cbAllTrains.removeAllItems();
					for (Train t : controller.trains) {
						cbAllTrains.addItem(t.getName());
					}					
					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 2;
					jPanel2.add(cbAllTrains, gbc);
				}
				{
					btnChooseTrain = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnChooseTrain, gbc);
					btnChooseTrain.setText("Select train");
					btnChooseTrain.addActionListener(this);
				}
				{
					btnDeleteTrain = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 2;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnDeleteTrain, gbc);
					btnDeleteTrain.setText("Delete train");
					btnDeleteTrain.addActionListener(this);
				}
				{
					btnOtherWindow = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 6;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnOtherWindow, gbc);
					btnOtherWindow.setText("Open CLI Window");
					btnOtherWindow.addActionListener(this);
				}
				{
					tpTextWagon = new JTextPane();
					gbc.gridx = 0;
					gbc.gridy = 3;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tpTextWagon, gbc);
					tpTextWagon.setText("Wagon name:");
				}
				{
					tfNewWagon = new JTextField(20);
					gbc.gridx = 1;
					gbc.gridy = 3;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tfNewWagon, gbc);
				}
				{
					btnNewWagon = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 3;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnNewWagon, gbc);
					btnNewWagon.setText("Make new wagon");
					btnNewWagon.addActionListener(this);
				}
				{
					cbAllWagons = new JComboBox();
					cbAllWagons.removeAllItems();
					for (Wagon w : controller.wagons) {
						cbAllWagons.addItem(w.getID());
					}					
					gbc.gridx = 1;
					gbc.gridy = 4;
					gbc.gridwidth = 1;
					gbc.gridheight = 3;
					jPanel2.add(cbAllWagons, gbc);
				}
				{
					btnChooseWagon = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 4;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnChooseWagon, gbc);
					btnChooseWagon.setText("Select wagon");
					btnChooseWagon.addActionListener(this);
				}
				{
					btnDeleteWagon = new JButton();
					gbc.gridx = 2;
					gbc.gridy = 5;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(btnDeleteWagon, gbc);
					btnDeleteWagon.setText("Delete wagon");
					btnDeleteWagon.addActionListener(this);
				}
				{
					tpSeats = new JTextPane();
					gbc.gridx = 0;
					gbc.gridy = 7;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tpSeats, gbc);
					tpSeats.setText("Give seats:");
				}
				{
					tfSeats = new JTextField(20);
					gbc.gridx = 1;
					gbc.gridy = 7;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					jPanel2.add(tfSeats, gbc);
				}
			}
			// er wordt hier een nieuwe panel aan het bovenstaande panel toegevoegd
			{	GridBagConstraints gbc = new GridBagConstraints();
				pnlWagons = new JPanel();
				GridBagLayout jPanel3Layout = new GridBagLayout();
				getContentPane().add(pnlWagons, new GridBagConstraints(1, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel3Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanel3Layout.rowHeights = new int[] {7, 7, 7, 7};
				jPanel3Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanel3Layout.columnWidths = new int[] {7, 7, 7, 7};
				pnlWagons.setLayout(jPanel3Layout);
				// hier worden weer de componenten toegevoegd aan het panel
				pnlWagons.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					tfCurrentTrain = new JTextField();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					pnlWagons.add(tfCurrentTrain, gbc);
					tfCurrentTrain.setText("Selected: ");
				}
				{
					tfCurrentWagon = new JTextField();
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					pnlWagons.add(tfCurrentWagon, gbc);
					tfCurrentWagon.setText("Selected: ");
				}
				{
					btnLinkWagon = new JButton();
					gbc.gridx = 0;
					gbc.gridy = 3;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					pnlWagons.add(btnLinkWagon, gbc);
					btnLinkWagon.setText("Link selected wagon to selected train");
					btnLinkWagon.addActionListener(this);
				}
				{
					btnUnlinkWagon = new JButton();
					gbc.gridx = 0;
					gbc.gridy = 4;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					pnlWagons.add(btnUnlinkWagon, gbc);
					btnUnlinkWagon.setText("Unlink selected wagon to selected train");
					btnUnlinkWagon.addActionListener(this);
				}
			}
			pack();
			setSize(800, 600);
			numberOfWagons = new HashMap();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void setController(Controller con){
		//Dit is nodig voor de Observer
		this.controller = con;   
		con.addObserver(this);

	} 

	// deze methode update de logs die je uiteindelijk in de textarea ziet
	@Override
	public void update(Observable subject) {
		String overviewString = "";

		//Overview Area train update:
		taOverview.setText(overviewString);
		taOverview.setText(taOverview.getText() + "Trains: \n");
		for (Train tr : controller.trains){
			overviewString = overviewString + "(" + tr.getName() + ")";
			for (Wagon w : tr.getWagons()){
				overviewString = overviewString + "-(" + w.getID() + ")";
			}
			overviewString = overviewString + "\n";
		}
		taOverview.setText(taOverview.getText() +overviewString);

		overviewString = "";
		//Overview Area wagon update:
		taOverview.setText(taOverview.getText() + "Wagons: \n");
		for (Wagon w : controller.wagons){
			overviewString = overviewString + "(" + w.getID() + ")";
		}
		taOverview.setText(taOverview.getText() +overviewString);
		overviewString = "";



		//Log update:
		for(String log :controller.getLogs()){
			if(!logs.contains(log)){
				logs.add(log);
			}
		}
		String s = "";
		for(String log: logs){
			s = s + log+ "\n";
		}
		taOutput.setText(s);
	}

	// hier worden alle button acties genomen en krijgen ze er een actie aan gehangen
	// die wordt uitgevoerd als op de button geklikt wordt
	@Override
	public void actionPerformed(ActionEvent event) {
		Action cmdAdd = new ActionAdd();
		Action cmdDelete = new ActionDelete();
		Action cmdRemove = new ActionRemove();
		Action cmdNew = new ActionNew();
		Action cmdGet = new ActionGet();
		String firstWord;

		if (event.getSource()== btnExecute)
		{
			String inputCommand = tfCommandLine.getText();
			if (inputCommand != null && inputCommand.length() > 0 && inputCommand.charAt(inputCommand.length() - 1) == ';') {
				inputCommand = inputCommand.substring(0, inputCommand.length() - 1);
				String[] commands = inputCommand.split(" ");
				firstWord = commands[0];
				if (firstWord.equals("new")){
					cmdNew.useAction(inputCommand);
				} else if(firstWord.equals("add")){
					cmdAdd.useAction(inputCommand);
				}
				else if(firstWord.equals("delete")){
					cmdDelete.useAction(inputCommand);
				}
				else if(firstWord.equals("getnumseats")){
					cmdGet.useAction(inputCommand);
				}
				else if(firstWord.equals("remove")){
					cmdRemove.useAction(inputCommand);

					tfCommandLine.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "command not correct");
				}
			}else {
				JOptionPane.showMessageDialog(null, "command not correct");
				System.out.println("geen ;");
			}
			tfCommandLine.setText("");
		}
		else if (event.getSource() == btnNewWindow) {
			getContentPane().remove(jPanel4);
			initGUI();
		}
		else if (event.getSource() == btnOpenNewWindow) {
			new Window("RichRail2");
		}
		else if(event.getSource() == btnOtherWindow) {
			getContentPane().remove(jPanel1);
			getContentPane().remove(jPanel2);
			getContentPane().remove(pnlWagons);
			initCLIGUI();
		}
		else if (event.getSource() == btnNewTrain) {
			String train = tfNewTrain.getText();
			if (train != null && train.trim().length()>0)
			{
				if (controller.trainNotEquals(train)) {
					controller.addTrain(train);
					currentTrain = cbAllTrains.getSelectedIndex();
					cbAllTrains.removeAllItems();
					for (Train t : controller.trains) {
						cbAllTrains.addItem(t.getName());
					}
				}
			}
		}
		else if (event.getSource() == btnChooseTrain)
		{
			if (cbAllTrains.getItemCount() > 0)
			{
				String selection = (String)cbAllTrains.getSelectedItem();
				tfCurrentTrain.setText("Selected: " + selection);
				int ti = cbAllTrains.getSelectedIndex();
				if (ti != currentTrain)
				{
					numberOfWagons.put(currentTrain, currentNumberOfWagons);
				}
				currentTrain = ti;
				try
				{
					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
				}
				catch (Exception e)
				{
					currentNumberOfWagons = 0;
				}			
			}
		}
		else if (event.getSource() == btnDeleteTrain) {
			if (cbAllTrains.getItemCount() > 0)
			{
				String t = (String)cbAllTrains.getSelectedItem();
				cbAllTrains.removeItemAt(cbAllTrains.getSelectedIndex());
				numberOfWagons.remove(t);
				controller.deleteTrain(t);
				cbAllTrains.removeAllItems();
				for (Train train : controller.trains) {
					cbAllTrains.addItem(train.getName());
				}
				repaint();
				if ((String)cbAllTrains.getSelectedItem() != null)
				{
					currentTrain = cbAllTrains.getSelectedIndex();
					tfCurrentTrain.setText("selected: " + (String)cbAllTrains.getSelectedItem());
				}
				else
				{
					currentTrain = 0;
					tfCurrentTrain.setText("Selected: ");
				}
			}
		}
		else if (event.getSource() == btnNewWagon) {
			int wagon = Integer.parseInt(tfNewWagon.getText());
			int seats = Integer.parseInt(tfSeats.getText());
			if(wagon > 0 && seats > 0) {
				if(controller.wagonNotEquals(wagon)) {
					controller.addWagon(wagon, seats);
					currentWagon = cbAllWagons.getSelectedIndex();
					cbAllWagons.removeAllItems();
					for (Wagon w : controller.wagons) {
						cbAllWagons.addItem(w.getID());
					}
				}
			}
		}
		else if (event.getSource() == btnChooseWagon) {
			if (cbAllWagons.getItemCount() > 0)
			{
				int selection = (int) cbAllWagons.getSelectedItem();
				tfCurrentWagon.setText("Selected: " + selection);
				int ti = cbAllWagons.getSelectedIndex();					
				currentWagon = ti;
				try
				{
					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
				}
				catch (Exception e)
				{
					currentNumberOfWagons = 0;
				}			
			}
		}
		else if (event.getSource() == btnDeleteWagon) {
			if (cbAllWagons.getItemCount() > 0)
			{
				int w = (int)cbAllWagons.getSelectedItem();
				cbAllWagons.removeItemAt(cbAllWagons.getSelectedIndex());
				numberOfWagons.remove(w);
				controller.deleteWagon(w);
				cbAllWagons.removeAllItems();
				for (Wagon wagon : controller.wagons) {
					cbAllTrains.addItem(wagon.getID());
				}
//				if ((String)cbAllWagons.getSelectedItem() != null)
//				{
//					currentWagon = cbAllWagons.getSelectedIndex();
//					tfCurrentWagon.setText("selected: " + (String)cbAllWagons.getSelectedItem());
//				}
//				else
//				{
//					currentWagon = 0;
//					tfCurrentWagon.setText("Selected: ");
//				}
				currentWagon = 0;
				tfCurrentWagon.setText("Selected: ");
			}
		}
		else if (event.getSource() == btnLinkWagon) {
			String selection = (String)cbAllTrains.getSelectedItem();
			controller.linkWagon(selection, currentWagon);
		}
		else if (event.getSource() == btnUnlinkWagon) {
			String selection = (String)cbAllTrains.getSelectedItem();
			controller.unlinkWagon(selection, currentWagon);
		}
	}

	public void drawTrain (String train) 
	{
		if (train != "")
		{
			Graphics g = drawPanel.getGraphics();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(30,80+currentTrain*OFFSET,80,40);
			g.fillRect(80,60+currentTrain*OFFSET,30,30);
			g.drawRoundRect(85, 40+currentTrain*OFFSET, 20, 20, 20, 20);
			g.drawRoundRect(85, currentTrain*OFFSET, 40, 40, 40, 40);
			g.setColor(Color.BLACK);
			g.fillRoundRect(35, 120+currentTrain*OFFSET, 20, 20, 20, 20);
			g.fillRoundRect(80, 120+currentTrain*OFFSET, 20, 20, 20, 20);
			g.drawString(train,40,105+currentTrain*OFFSET);
		}
	}

	public void drawWagon (String wagon) 
	{
		Graphics g = drawPanel.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(30+currentNumberOfWagons*TRAINLENGTH,80+currentTrain*OFFSET,80,40);
		g.setColor(Color.BLACK);
		g.fillRoundRect(35+currentNumberOfWagons*TRAINLENGTH, 120+currentTrain*OFFSET, 20, 20, 20, 20);
		g.fillRoundRect(80+currentNumberOfWagons*TRAINLENGTH, 120+currentTrain*OFFSET, 20, 20, 20, 20);
		g.drawString(wagon,40+currentNumberOfWagons*TRAINLENGTH,105+currentTrain*OFFSET);
	}

	public void eraseTrain (String train) {

	}

	public void eraseWagon (String wagon) {

	}

}