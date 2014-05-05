
public class BuffonsNeedle {

	public static void main(String[] args) {		
		
		int l = 1, d = 1; // Define length of needle and distance
		int w = 2 * d, h = d;
		
		int yes = 0, no = 0;
		
		boolean running = true;
		
		Center c = (x,theta) -> {return new Point(x,theta);};
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (running) {
					Point t = c.center(Math.random() * (double) d, Math.random() * 180D);
					if (t.theta % 180D == 0) {
						
					}
				}
			}
		};

	}
	
	interface Center {
		Point center(double x, double theta);
	}
	
}

class Point {
	
	double x,y,theta;
	
	public Point(double x, double theta) {
		this.x = x;
		this.theta = theta;
	}
	
}