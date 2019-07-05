import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Code to generate number chain for ten million numbers which will arrive at 89
 * */
class NumberChainMultiT extends Thread{
	
	private String name;
	private int minvalue = 1;
	private int maxvalue = 100;
	private static int count = 0;

	public NumberChainMultiT(String name, int minvalue, int maxvalue) {
	
		this.name = name;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;
		
	}

	public void run() {
		int minCheck = minvalue;
		int maxCheck = maxvalue;
		
		System.out.println("Number Chain from "+ minCheck +" to " + maxCheck + " are:");
		for (int i = minCheck; i <= maxCheck; i++) {
			System.out.print(i+"->");
			numberChain(i);
		
		}
		System.out.print("Total Count : "+count);
	}

	private void numberChain(int n) {
		int sum = 0;
		
		try {
			while (n != 0) 
			{ 
				sum = (int) (sum + Math.pow(n % 10,2)); 
				n = n/10; 
			}
			if (sum == 89 || sum == 1) {
				
				System.out.println(sum);
				if(sum == 89) {
					count++;
					
				}
				
			}
			else {
				System.out.print(sum + "->");
				numberChain(sum);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}


}

public class NumberChainThread {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
	    try {
			// Start the executor process  
			ExecutorService executor = Executors.newFixedThreadPool(10);
			Runnable taskOne = new NumberChainMultiT("TaskOne", 1, 100);
			executor.execute(taskOne);
			Runnable tasktwo = new NumberChainMultiT("TaskOne", 100, 1000);
			executor.execute(tasktwo);
			Runnable taskthree = new NumberChainMultiT("Taskthree", 1000, 10000);
			executor.execute(taskthree);
			Runnable taskfour = new NumberChainMultiT("Taskfour", 10000, 100000);
			executor.execute(taskfour);
			Runnable taskfive = new NumberChainMultiT("Taskfive", 100000, 200000);
			executor.execute(taskfive);
			Runnable tasksix = new NumberChainMultiT("Tasksix", 200000, 500000);
			executor.execute(tasksix);
			Runnable taskseven = new NumberChainMultiT("Taskseven", 500000, 700000);
			executor.execute(taskseven);
			Runnable taskeight = new NumberChainMultiT("Taskeight", 700000, 900000);
			executor.execute(taskeight);
			Runnable tasknine = new NumberChainMultiT("Tasknine", 900000, 10000000);
			executor.execute(tasknine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
				


	}

}
