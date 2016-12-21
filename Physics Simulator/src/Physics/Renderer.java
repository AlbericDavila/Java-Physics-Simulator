/*
 * Renderer.java
 * Renders the vectors in the free body diagram.
 * calculates resultant vector and displays it as blue line.
 */

package Physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Renderer 
{
	private JFrame frame;
	public static double netForce = 0, netVector = 0;
	
	public Renderer(JFrame frame)
	{
		this.frame = frame;
	}
		
	public void render()
	{
		Graphics g = frame.getGraphics();
		g.translate(500, 300);
		
		ArrayList<Force> forceArray = new ArrayList<>();
		for(int i = 1; i < ForceList.listModel.size(); i++)
		{
			forceArray.add(ForceList.forceMap.get(ForceList.listModel.elementAt(i)));
		}
		
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < forceArray.size(); i++)
		{
			float newtons = forceArray.get(i).getForce();
			double rads = toRad(forceArray.get(i).getVector());
			int xInset = Math.round((float)(50 * Math.cos(rads)));
			int yInset = -Math.round((float)(50 * Math.sin(rads)));
			int x =  Math.round((float)(xInset + newtons * Math.cos(rads)));
			int y = Math.round((float)(yInset - newtons * Math.sin(rads)));
			g.drawLine(0, 0, x, y);
		}
		
		ArrayList<Force> xComp = new ArrayList<>();
		ArrayList<Force> yComp = new ArrayList<>();
		double netForceX = 0, netForceY = 0;
		
		// Sum up all the X and Y components vectors into NetForceX and NetForceY.
		for(int i = 0; i < forceArray.size(); i++)
		{
			xComp.add(forceArray.get(i).getCompForceX());
			yComp.add(forceArray.get(i).getCompForceY());
			netForceX += xComp.get(i).getForce();
			netForceY += yComp.get(i).getForce();
		}
		
		// Find the hypotenuse using the Pythagorean Theorem.
		netForce = Math.sqrt(Math.pow(netForceX, 2) + Math.pow(netForceY, 2));
		
		netVector = toDegrees(Math.asin(netForceY / netForce));
		if(netVector < 0) 
			netVector += 360;
			
		// Calcualte resultant vector.
		double rads = toRad(netVector);
		int xInset = Math.round((float)(50 * Math.cos(rads)));
		int yInset = -Math.round((float)(50 * Math.sin(rads)));
		int x =  Math.round((float)(xInset + netForce * Math.cos(rads)));
		int y = Math.round((float)(yInset - netForce * Math.sin(rads)));
		
		NetPanel.forceLabel.setText("Net Force = " + (float)Renderer.netForce + " N");
		NetPanel.vectorLabel.setText("Net Vector = " + (float)Renderer.netVector + "°");
		
		// Draw resultant vector to be blue.
		g.setColor(Color.BLUE);
		g.drawLine(0, 0, x, y);

		g.setColor(Color.GRAY);
		g.fillOval(-50, -50, 100, 100);
		
		g.dispose();
	}
	
	/*
	 * Convert from degrees to radians.
	 */
	public static double toRad(double degrees)
	{
		return degrees * Math.PI / 180;
	}
	
	/*
	 * Convert from radians to degrees.
	 */
	public static double toDegrees(double rads)
	{
		return rads * 180/ Math.PI;
	}
	
}
