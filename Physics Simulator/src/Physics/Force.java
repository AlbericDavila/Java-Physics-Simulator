/*
 * Force.java
 * Handles Force and Vectors.
 * Calculates the X andd Y component vectors.
 */

package Physics;

public class Force 
{
	private float force, vector;
	public String name;
	
	public Force(String name)
	{
		this.name = name;
		this.force = 0;
		this.vector = 0;
	}
	
	public Force(String name, float newtons, float vector)
	{
		this(name);
		this.force = newtons;
		this.vector = vector;
	}
	
	public void setName(String name) { this.name = name; }
	public void setForce(float force) { this.force = force; }
	public void setVector(float vector) { this.vector = vector; }
	
	public float getForce() { return force; }
	public float getVector() { return vector; }
	
	public String toString() { return name + ":" + force + "@" + vector; }
	public Object clone() { return new Force(name, force, vector);}
	public boolean equals(Object obj)
	{
		if(obj instanceof Force)
		{
			Force objForce = (Force)((Force)obj).clone();
			return objForce.force == force && objForce.vector == vector ? true : false;
		}
		return false;
	}
	
	/*
	 * Get component vector from X axis.
	 * Needed to calculate the resultant vector.
	 */
	public Force getCompForceX()
	{
		double newVector = vector;
		float x = (float)(Math.cos(Renderer.toRad(newVector)) * getForce());
		return new Force("", x, 0);
	}
	
	/*
	 * Get component vector from Y axis.
	 * Needed to calculate the resultant vector.
	 */
	public Force getCompForceY()
	{
		double newVector = vector;
		float y = (float)(Math.sin(Renderer.toRad(newVector)) * getForce());
		return new Force("", y, 90);
	}

}
