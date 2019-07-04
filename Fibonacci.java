# Class to generate fibonacci series and identify the idex of the first term in the Fibonacci sequence to contain 1000 digits
class FibonacciSeries extends Thread{

	public void run() {
		int f1=0,f2=1,f=1,n=1;
		Thread t = Thread.currentThread();
		t.setName("FibonacciSeries");
		System.out.println("Thread "+ Thread.currentThread().getName()+" is running");
		while(true) {
			try {
				System.out.println("F"+n+":"+f);
				f = f1+f2;
				n++;
				if(f > 1000) {
					System.out.println("The index of the first term in the Fibonacci sequence to contain 1000 digits is : F"+n);
					break;
				}
				f1=f2;
				f2=f;

			}catch(Exception ep) {
				System.out.println("Exception in Fibonacci Series"+ep.getMessage());
			}
		}
	}
}

public class Fibonacci {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
		FibonacciSeries fibo = new FibonacciSeries();
		fibo.start();


	}

}
