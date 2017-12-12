package GUI;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager2;

import javax.swing.WindowConstants;



public class Window extends javax.swing.JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
		
	}

}
