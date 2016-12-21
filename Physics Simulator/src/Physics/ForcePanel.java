/*
 * ForcePanel.java
 * Allows editing of Force and Vector of forces added in ForceList.java.
 */

package Physics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ForcePanel extends JPanel implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	public static final String EDIT = "edit";
	private static final String FORCE = "FORCE";
	private static final String VECTOR = "VECTOR";
	
	private JLabel forceLabel, vectorLabel;
	private JButton forceButton, vectorButton;
	private JPanel forcePanel, vectorPanel;
	private Force force;
	
	public ForcePanel(Force force)
	{
		super();
		this.force = force;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		forceLabel = new JLabel("Force = " + force.getForce() + " N");
		vectorLabel = new JLabel("Vector = " + force.getVector() + "°");
		
		// Set Force and Vector buttons.
		forceButton = new JButton(EDIT);
		forceButton.setActionCommand(FORCE);
		forceButton.addActionListener(this);
		vectorButton = new JButton(EDIT);
		vectorButton.setActionCommand(VECTOR);
		vectorButton.addActionListener(this);
		
		// Set Force panel.
		forcePanel = new JPanel();
		forcePanel.setLayout(new BoxLayout(forcePanel, BoxLayout.X_AXIS));
		forcePanel.add(forceLabel);
		forcePanel.add(Box.createHorizontalGlue());
		forcePanel.add(forceButton);
		forcePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// Set Vector panel.
		vectorPanel = new JPanel();
		vectorPanel.setLayout(new BoxLayout(vectorPanel, BoxLayout.X_AXIS));
		vectorPanel.add(vectorLabel);
		vectorPanel.add(Box.createHorizontalGlue());
		vectorPanel.add(vectorButton);
		vectorPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// Add Force and Vector panels.
		add(forcePanel);
		add(vectorPanel);
		setBorder(BorderFactory.createTitledBorder(force.name.toUpperCase()));
		setBounds(5, 0, 300, 100);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		
		try
		{
			switch(action)
			{
			case FORCE:
				float oldForce = force.getForce();
				float newForce = Float.parseFloat(JOptionPane.showInputDialog(Main.frame,
						"Enter the force's new magnitude", "Edit Force", 
						JOptionPane.QUESTION_MESSAGE, null, null, oldForce).toString());
				if(newForce == oldForce)
					return;
				force.setForce(newForce);
				forceLabel.setText("Force = " + force.getForce() + " N");
				break;
			case VECTOR:
				float oldVector = force.getForce();
				float newVector = Float.parseFloat(JOptionPane.showInputDialog(Main.frame,
						"Enter the force's new vector", "Edit Force", 
						JOptionPane.QUESTION_MESSAGE, null, null, oldVector).toString());
				if(newVector == oldVector)
					return;
				force.setVector(newVector);
				vectorLabel.setText("Vector = " + force.getVector() + "°");
				break;
			}
		} 
		catch(NumberFormatException exceptNFE) 
		{
			JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null);
		}
		catch(NullPointerException exceptNPE) { }
		finally
		{
			// Refresh the screen.
			Main.screenPanel.setVisible(false);
			Main.screenPanel.revalidate();
			Main.screenPanel.repaint();
			Main.screenPanel.setVisible(true);
		}
	}
}
