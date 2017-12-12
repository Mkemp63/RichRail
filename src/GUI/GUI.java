package GUI;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import GUI.GUI;

import javax.swing.SwingUtilities;

public class GUI extends javax.swing.JFrame{
	
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				GUI inst = new GUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

public GUI() 
{
	super();
	initGUI();
}

private void initGUI() 
{
	{
		this.setTitle("RichRail");
		GridBagLayout thisLayout = new GridBagLayout();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		thisLayout.rowHeights = new int[] {7, 7, 7, 7};
		thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		thisLayout.columnWidths = new int[] {7, 7, 7, 7};
		getContentPane().setLayout(thisLayout);
		pack();
		setSize(800, 600);
	}
}
}