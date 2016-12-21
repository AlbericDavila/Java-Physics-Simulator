/*
 * Main.java
 * Creates the frame/window and serves as program's starting point.
 */

package Physics;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable 
{
	public static final String TITLE = "Physics Simulator";
	public static final Dimension SCREEN_SIZE = new Dimension(800, 625);
	
	public static JFrame frame;
	public static JPanel screenPanel;
	public static BodyPanel bodyPanel;
	
	public Renderer rend;
	public ForceList forceList;
	
	public static void main(String[] args) 
	{
		// Create Main and start it.
		Main main = new Main();
		new Thread(main).start();
	}
	
	public Main()
	{
		/*
		 * Create the frame/window.
		 */
		frame = new JFrame();
		frame.setSize(SCREEN_SIZE);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setResizable(false);
		frame.setLayout(null);
		
		rend = new Renderer(frame);
		forceList = new ForceList();
		bodyPanel = new BodyPanel();
		screenPanel = new JPanel(null);
		screenPanel.setBackground(Color.LIGHT_GRAY);
		screenPanel.add(bodyPanel);
		screenPanel.add(new NetPanel());
		
		// Set Force List (NET FORCE List) bounds and add it to the frame.
		forceList.setBounds(0, 0, 200, 580);
		screenPanel.setBounds(200, 0, 600, 580);
		frame.add(forceList);
		frame.add(screenPanel);
		
		frame.setVisible(true);
	}
	
	public void run()
	{
		while(true)
		{
			rend.render();
			
			/*
			 *  Render the screen 20 times a second (20 fps).
			 *  Optimizes processor usage.
			 */
			try { Thread.sleep(50); }
			catch(InterruptedException ie) {}
		}
	}


}
