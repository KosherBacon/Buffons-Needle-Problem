import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BuffonsNeedle {

	public static void main(String[] args) {		
		
		int l = 1, d = 1; // Define length of needle and distance
		int w = 2 * d;
		
		BigDecimal w2 = new BigDecimal(w);
		
		YesOther yo = new YesOther();
		
		// Relatively accurate starting point
		yo.setValues(55596480881L, 35393808771L);
		
		boolean running = true;
		
		Center c = (x,theta) -> {return new Point(x,theta % 90D);};
		
		Left left = (theta) -> {return l / 2D * Math.cos(theta * Math.PI / 180D);};
		
		int cpus = Runtime.getRuntime().availableProcessors();
		int maxThreads = cpus * 1;
		maxThreads = (maxThreads > 0 ? maxThreads : 1);
		
		ExecutorService executorService =
			new ThreadPoolExecutor(
				maxThreads,
				maxThreads,
				1,
				TimeUnit.MINUTES, 
				new ArrayBlockingQueue<Runnable>(maxThreads, true),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (running) {
					Point t = c.center(Math.random() * (double) d, Math.random() * 180D);
					if (t.theta % 180D == 0) {
						yo.yes++;
					}
					else {
						// Far left case
						if (t.x < d/2D && left.isLeft(t.theta) + t.x > d/2D) {
							yo.yes++;
						}
						// Middle left case
						else if (t.x > d/2D && t.x - left.isLeft(t.theta) < d/2D) {
							yo.yes++;
						}
						// Middle right case
						else if (t.x < w - (d/2D) && t.x + left.isLeft(t.theta) > w - (d/2D)) {
							yo.yes++;
						}
						// Far right case
						else if (t.x > w - (d/2D) && t.x - left.isLeft(t.theta) < w - (d/2D)) {
							yo.yes++;
						}
					}
					yo.other++;
				}
			}
		};
		
		executorService.submit(r);
	  
		Runnable printer = new Runnable() {
			@Override
			public void run() {
				while (running) {
					System.out.print(w2.multiply(new BigDecimal(yo.other)).divide(new BigDecimal(yo.yes), 20, RoundingMode.HALF_UP) + " " + yo.other + " : " + yo.yes + "\r");
					try {
						java.lang.Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread print = new Thread(printer);
		print.start();
		
	}
	
	interface Center {
		Point center(double x, double theta);
	}
	
	interface Left {
		double isLeft(double theta);
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
	
	public long yes = 0, other = 0;
	
	public void setValues(long yes, long other) {
		this.yes = yes;
		this.other= other;
	}
}