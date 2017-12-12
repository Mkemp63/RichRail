package GUI;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Panel() {
		super();
		GridBagLayout jPanelLayout = new GridBagLayout();
		super.setLayout(jPanelLayout);
		
		
		jPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		jPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
		jPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		jPanelLayout.columnWidths = new int[] {7, 7, 7, 7};

	}

}
