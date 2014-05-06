
public class BuffonsNeedle {

	public static void main(String[] args) {		
		
		int l = 1, d = 1; // Define length of needle and distance
		int w = 2 * d, h = d;
		
		YesOther yo = new YesOther();
		
		boolean running = true;
		
		Center c = (x,theta) -> {return new Point(x,theta % 90D);};
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (running) {
					Point t = c.center(Math.random() * (double) d, Math.random() * 180D);
					if (t.theta % 180D == 0) {
						yo.yes++;
					}
					else {
						if (l / 2D * Math.cos(t.theta * Math.PI / 180D) < l / 2D) {
						}
					}
					yo.other++;
				}
				System.out.println(2D * yo.yes / yo.other);
			}
		};
		
		Thread t = new Thread(r);
		t.start();

	}
	
	interface Center {
		Point center(double x, double theta);
	}
	
}

class Point {
	
	double x,theta;
	
	public Point(double x, double theta) {
		this.x = x;
		this.theta = theta;
	}
}

class YesOther {
	
	public int yes = 0, other = 0;
	
}