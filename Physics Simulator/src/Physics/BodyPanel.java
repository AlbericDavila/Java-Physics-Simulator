/*
 * BodyPanel.java
 * Sets up the panel that contains the Free Body's mass and acceleration 
 * and allows these variables to be edited.
 */

package Physics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BodyPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private float mass = 10, acceleration = 0;
	
	public JLabel massLabel, accelerationLabel;
	
	public BodyPanel()
	{
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.acceleration = (float)(Renderer.netForce / this.mass);
		
		massLabel = new JLabel("Mass = " + this.mass + " kg");
		accelerationLabel = new JLabel("Acceleration = " + this.acceleration + " m/s^2");
		
		JButton massButton = new JButton("edit");
		massButton.setActionCommand("MASS");
		massButton.addActionListener(this);
		
		JPanel massPanel = new JPanel();
		massPanel.setLayout(new BoxLayout(massPanel, BoxLayout.X_AXIS));
		massPanel.add(massLabel);
		massPanel.add(Box.createHorizontalGlue());
		massPanel.add(massButton);
		massPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JPanel accelerationPanel = new JPanel();
		accelerationPanel.setLayout(new BoxLayout(accelerationPanel, BoxLayout.X_AXIS));
		accelerationPanel.add(accelerationLabel);
		accelerationPanel.add(Box.createHorizontalGlue());
		accelerationPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		add(massPanel);
		add(accelerationPanel);
		setBorder(BorderFactory.createTitledBorder("FREE BODY"));
		setBounds(5, 0, 300, 100);
	}
	
	public float getMass() { return mass; }
	
	public float getAcceleration() { return acceleration; }
	
	public void modify()
	{
		this.acceleration = (float)(Renderer.netForce / this.mass);
		massLabel.setText("Mass = " + this.mass + " kg");
		accelerationLabel.setText("Acceleration = " + this.acceleration + " m/s^2");
	}

	public void actionPerformed(ActionEvent e) 
	{
		float oldMass = this.mass;
		float newMass = oldMass;
		try
		{
			newMass = Float.parseFloat(JOptionPane.showInputDialog(Main.frame,
					"Enter the body's new mass", "Edit Body", 
					JOptionPane.QUESTION_MESSAGE, null, null, oldMass).toString());
			if(oldMass == newMass)
				return;
			this.mass = newMass;
			this.acceleration = (float)(Renderer.netForce / this.mass);
			massLabel.setText("Mass = " + this.mass + " kg");
			accelerationLabel.setText("Acceleration = " + this.acceleration + " m/s^2");
		}
		catch(NumberFormatException ExceptNFE) 
		{ 
			JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null);
		}
		catch(NullPointerException ExceptNPE) {}
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
