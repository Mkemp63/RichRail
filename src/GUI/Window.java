package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import TaskSpecific.Controller;
import Actions.*;

public class Window extends javax.swing.JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private ActionAdd actionAdd;
	
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
	
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JTextPane tpCommandInput;
	private JTextField tfCommandLine;
	private JTextArea taOverview;
	private JTextArea taOutput;
	private JButton btnExecute;
	
	private HashMap numberOfWagons;
	private int currentNumberOfWagons;
	private int currentTrain = -1;
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
		initGUI();
		
	}
	
	private void initCLIGUI() {
		try 
		{
			this.setTitle("RichRail | Command Line");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				jPanel3 = new JPanel();
				jPanel3.setLayout(new BorderLayout());
				getContentPane().add(jPanel3, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					drawPanel = new JPanel();
					drawPanel.setBackground(Color.WHITE);
					jPanel3.add(drawPanel,BorderLayout.CENTER);
				}
			}
			{
				jPanel4 = new JPanel();
				GridBagLayout jPanel4Layout = new GridBagLayout();
				//jPanel4.setLayout(null);
				jPanel4.setLayout(jPanel4Layout);
				getContentPane().add(jPanel4, new GridBagConstraints(0, 1, 1, 3, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					taOverview = new JTextArea ();
					taOverview.setPreferredSize(new Dimension(400, 200));
					taOverview.setBackground(Color.WHITE);
					taOverview.setForeground(Color.black);
					taOverview.setEditable(false);
					taOverview.setText("Dit is de overview area");
					taOverview.setSize(400, 200);
					taOverview.setVisible(true);
					taOverview.setLineWrap(true);
					JScrollPane scrollpane = new JScrollPane(taOverview);
					jPanel4.add(scrollpane, new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}				
				{
					tfCommandLine = new JTextField(20);
					jPanel4.add(tfCommandLine, new GridBagConstraints(1, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnExecute = new JButton();
					jPanel4.add(btnExecute, new GridBagConstraints(2, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnExecute.setText("Execute command");
					btnExecute.addActionListener(this);
				}
				{
					tpCommandInput = new JTextPane();
					jPanel4.add(tpCommandInput, new GridBagConstraints(0, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jPanel4.setBounds(10, 10, 100, 15);
					jPanel4Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel4Layout.rowHeights = new int[] {7, 7, 7, 7};
					jPanel4Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel4Layout.columnWidths = new int[] {7, 7, 7, 7};
					tpCommandInput.setText("Command input: ");
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
				getContentPane().add(jPanel1, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					drawPanel = new JPanel();
					drawPanel.setBackground(Color.WHITE);
					jPanel1.add(drawPanel,BorderLayout.CENTER);
				}
			}
			{
				jPanel2 = new JPanel();
				GridBagLayout jPanel2Layout = new GridBagLayout();
				//jPanel2.setLayout(null);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					tpTextTrain = new JTextPane();
					jPanel2.add(tpTextTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jPanel2.setBounds(10, 10, 100, 15);
					jPanel2Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel2Layout.rowHeights = new int[] {7, 7, 7, 7};
					jPanel2Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel2Layout.columnWidths = new int[] {7, 7, 7, 7};
					tpTextTrain.setText("train name:");
				}
				{
					tfNewTrain = new JTextField(20);
					jPanel2.add(tfNewTrain, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnNewTrain = new JButton();
					jPanel2.add(btnNewTrain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnNewTrain.setText("make new train");
					btnNewTrain.addActionListener(this);
				}
				{
					ComboBoxModel cbAllTrainsModel = 
						new DefaultComboBoxModel(
								new String[] { });
					cbAllTrains = new JComboBox();
				/*	GridLayout cbAllTrainsLayout = new GridLayout(1, 1);
					cbAllTrainsLayout.setColumns(1);
					cbAllTrainsLayout.setHgap(5);
					//cbAllTrainsLayout.setVgap(5);
					cbAllTrains.setLayout(cbAllTrainsLayout);*/
					jPanel2.add(cbAllTrains, new GridBagConstraints(1, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					cbAllTrains.setModel(cbAllTrainsModel);
				}
				{
					btnChooseTrain = new JButton();
					jPanel2.add(btnChooseTrain, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnChooseTrain.setText("select train");
					btnChooseTrain.addActionListener(this);
				}
				{
					btnDeleteTrain = new JButton();
					jPanel2.add(btnDeleteTrain, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteTrain.setText("delete train");
					btnDeleteTrain.addActionListener(this);
				}
			}
			{
				pnlWagons = new JPanel();
				GridBagLayout jPanel3Layout = new GridBagLayout();
				getContentPane().add(pnlWagons, new GridBagConstraints(1, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel3Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanel3Layout.rowHeights = new int[] {7, 7, 7, 7};
				jPanel3Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanel3Layout.columnWidths = new int[] {7, 7, 7, 7};
				pnlWagons.setLayout(jPanel3Layout);
				pnlWagons.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					tfCurrentTrain = new JTextField();
					pnlWagons.add(tfCurrentTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					tfCurrentTrain.setText("selected: ");
				}
				{
					btnAddWagon1 = new JButton();
					pnlWagons.add(btnAddWagon1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon1.setText("add wagon 1");
					btnAddWagon1.addActionListener(this);
				}
				{
					btnAddWagon2 = new JButton();
					pnlWagons.add(btnAddWagon2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon2.setText("add wagon 2");
					btnAddWagon2.addActionListener(this);
				}
				{
					jButton1 = new JButton();
					pnlWagons.add(jButton1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("add wagon 3");
					jButton1.addActionListener(this);
				}
				{
					btnDeleteWagon1 = new JButton();
					pnlWagons.add(btnDeleteWagon1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon1.setText("delete wagon 1");
					btnDeleteWagon1.addActionListener(this);
				}
				{
					btnDeleteWagon2 = new JButton();
					pnlWagons.add(btnDeleteWagon2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon2.setText("delete wagon 2");
					btnDeleteWagon2.addActionListener(this);
				}
				{
					btnDeleteWagon3 = new JButton();
					pnlWagons.add(btnDeleteWagon3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon3.setText("delete wagon 3");
					btnDeleteWagon3.addActionListener(this);
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()== btnNewTrain)
		{
			String train = tfNewTrain.getText();
			
//			if train does not exist in list alltrains
//			dan toevoegen en tekenen
//			TODO: het bovenstaande en dus toevoegen vanuit controller klasse
			
//			if (train != null && train.trim().length()>0)
//			{
//				train = addTrain(train); // TODO: Vanuit actie klasse de juiste action aanroepen
//				currentTrain = cbAllTrains.getSelectedIndex();
//				drawTrain(train);
//			}
			
			if(controller.trainNotEquals(train) == false) {
//				actionAdd.useAction(train);
			}
		
		}
		else if (event.getSource() == btnChooseTrain)
		{
//			currentTrain veranderen om de wagon acties op uit te voeren
//			de naam en de id dus halen uit de list en deze als variabelen opslaan
//			vervolgens deze variabele gebruiken in de functies die op de wagons slaan
//			Wanneer een trein al wagens heeft moeten deze dus getekend worden met juiste type etc
			
//			if (cbAllTrains.getItemCount() > 0)
//			{
//				String selection = (String)cbAllTrains.getSelectedItem();
//				tfCurrentTrain.setText("selected: " + selection);
//				int ti = cbAllTrains.getSelectedIndex();
//				if (ti != currentTrain)
//				{
//					numberOfWagons.put(currentTrain, currentNumberOfWagons);
//				}
//				currentTrain = ti;
//				try
//				{
//					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
//				}
//				catch (Exception e)
//				{
//					currentNumberOfWagons = 0;
//				}			
//			}
		}
		else if (event.getSource() == btnDeleteTrain)
		{
//			currenttrain weer gebruiken en dan weghalen uit de list in de controller
			
			
//			if (cbAllTrains.getItemCount() > 0)
//			{
//				String t = (String)cbAllTrains.getSelectedItem();
//				cbAllTrains.removeItemAt(cbAllTrains.getSelectedIndex());
//				numberOfWagons.remove(t);
//				repaint();
//				if ((String)cbAllTrains.getSelectedItem() != null)
//				{
//					currentTrain = cbAllTrains.getSelectedIndex();
//					tfCurrentTrain.setText("selected: " + (String)cbAllTrains.getSelectedItem());
//				}
//				else
//				{
//					currentTrain = 0;
//					tfCurrentTrain.setText("selected: ");
//				}
//			}
		}
		else if (event.getSource() == btnAddWagon1)
		{
//			currenttrain gebruiken en wagon 1 eraan toevoegen en tekenen
			
//			currentNumberOfWagons++;
//			drawWagon("Wagon1");
		}
		else if (event.getSource() == btnAddWagon2)
		{
//			currenttrain gebruiken en wagon 2 eraan toevoegen en tekenen

//			currentNumberOfWagons++;
//			drawWagon("Wagon2");
		}
		else if (event.getSource() == jButton1)
		{
//			currenttrain gebruiken en wagon 3 eraan toevoegen en tekenen

//			currentNumberOfWagons++;
//			drawWagon("Wagon3");
		}
		else if (event.getSource() == btnDeleteWagon1)
		{
//			currenttrain gebruiken en wagon 1 ervan verwijderen en dus weghalen uit tekenning

//			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);
		}
//		currenttrain gebruiken en wagon 1 ervan verwijderen en dus weghalen uit tekenning

		else if (event.getSource() == btnDeleteWagon2)
		{
//			currenttrain gebruiken en wagon 2 ervan verwijderen en dus weghalen uit tekenning

//			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);		
		}
		else if (event.getSource() == btnDeleteWagon3)
		{
			String wagonID = "";
//			currenttrain gebruiken en wagon 3 ervan verwijderen en dus weghalen uit tekenning

//			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);		
			eraseWagon(wagonID);
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
