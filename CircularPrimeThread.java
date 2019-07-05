import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Code to get the number of circular prime numbers below ten million
 * */
class PrimeNumberThread extends Thread{
	

	private String threadName;
	private int minvalue = 1;
	private int maxvalue = 100;
	private static int count = 0;

	public PrimeNumberThread(String threadName, int minvalue, int maxvalue) {
		// TODO Auto-generated constructor stub
		this.threadName = threadName;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;
		
	}

	public void run() {
		int minCheck = minvalue;
		int maxCheck = maxvalue;
		boolean isPrime = false;
		String primeNumber = "";
		

		for (int i = minCheck; i <= maxCheck; i++) {
			isPrime = checkCircular(Integer.toString(i));
			if (isPrime) {
				primeNumber = primeNumber + i + " ";
				count++;
			}
		}
		System.out.println("Prime circular numbers from "+ minCheck +" to " + maxCheck + " are:");
		System.out.println(primeNumber);
		System.out.println("Total Count : "+count);
	}

    /* Verify if the given number if primary number
     * */
	public boolean checkPrime(int num)
	{

		boolean flag = true;

		if( num == 0 || num == 1) {
			System.out.println(num + " is not a prime number");
			flag = false;
		}

		for(int i=2; i<=num/2;i++) {
			if(num % i == 0) {
				flag = false;

			}
		}
		return flag;


	}

	/* Verify if the given number if circular primary number
     * */
	public  boolean checkCircular(String num) 
	{ 
		boolean flag = true;
		int  l = num.length();

	        for(int i = 0; i < l; i++){

	            String str = num.substring(i,l) + num.substring(0,i);

	           int n = Integer.parseInt(str);

	            if(!checkPrime(n)){
	                flag = false;
	                break;
	            }

	        }
	      return flag;  
	} 


}

public class CircularPrimeThread {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
		// Start the executor process  
		ExecutorService executor = null;
		try {
			executor = Executors.newFixedThreadPool(10);
			Runnable taskOne = new PrimeNumberThread("TaskOne", 1, 100);
			executor.execute(taskOne);
			Runnable tasktwo = new PrimeNumberThread("TaskOne", 100, 1000);
			executor.execute(tasktwo);
			Runnable taskthree = new PrimeNumberThread("Taskthree", 1000, 10000);
			executor.execute(taskthree);
			Runnable taskfour = new PrimeNumberThread("Taskfour", 10000, 100000);
			executor.execute(taskfour);
			Runnable taskfive = new PrimeNumberThread("Taskfive", 100000, 200000);
			executor.execute(taskfive);
			Runnable tasksix = new PrimeNumberThread("Tasksix", 200000, 500000);
			executor.execute(tasksix);
			Runnable taskseven = new PrimeNumberThread("Taskseven", 500000, 700000);
			executor.execute(taskseven);
			Runnable taskeight = new PrimeNumberThread("Taskeight", 700000, 900000);
			executor.execute(taskeight);
			Runnable tasknine = new PrimeNumberThread("Tasknine", 900000, 1000000);
			executor.execute(tasknine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			executor.shutdown();
		}

	
				


	}

}
