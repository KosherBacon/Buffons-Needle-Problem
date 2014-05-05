
public class BuffonsNeedle {

	public static void main(String[] args) {		
		
		int l = 1, d = 1; // Define length of needle and distance
		int w = 2 * d, h = d;
		
		int yes = 0, no = 0;
		
		boolean running = true;
		
		Center c = (x,y,theta) -> {return new Point(x,y,theta);};
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (running) {
					Point t = c.center(Math.random() * (double) d, Math.random() * (double) h, Math.random() * 180D);
					if ()
				}
			}
		}

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