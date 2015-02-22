package fjr.com.epi.cycle;

public class Circle {

	private double radius; 
	private double centerX; 
	private double centerY; 
	
	private double angle; 
	
	public  Circle(double centerX, double centerY, double diameter){
		this.centerX = centerX; 
		this.centerY = centerY; 
		this.radius = diameter; 
	}
	
	public Circle(){
		
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double diameter) {
		this.radius = diameter;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
	
}
