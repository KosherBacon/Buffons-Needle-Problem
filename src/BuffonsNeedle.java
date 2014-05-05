
public class BuffonsNeedle {

	public static void main(String[] args) {		
		
		int l = 1, d = 1; // Define length of needle and distance
		int w = 3 *d, h = d;
		
		boolean running = true;
		
		Center c = (x,y,theta) -> {return new Point(x,y,theta);};
		
		new Thread(
				() -> {
					while (running == true) {
						System.out.println(running);
					}
				}
		).start();

	}
	
	interface Center {
		Point center(double x, double y, double theta);
	}
	
}

class Point {
	
	double x,y,theta;
	
	public Point(double x, double y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
}