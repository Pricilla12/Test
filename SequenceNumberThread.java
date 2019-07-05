import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Code to generate sequence number that ends with 1 and identify starting number under one million which 
 * produces the longest chain
 * */
class SequenceNumberThread extends Thread{

	private String name;
	private int minvalue = 1;
	private int maxvalue = 100;
	private  int count = 0;
	private  int sequencecount = 0;
	static ConcurrentHashMap<Integer, Integer> hash_map =  new ConcurrentHashMap<Integer, Integer>(); 
	

	public SequenceNumberThread(String name, int minvalue, int maxvalue) {

		this.name = name;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;

	}

	public void run() {
		int minCheck = minvalue;
		int maxCheck = maxvalue;
		
		System.out.println("Number Chain from "+ minCheck +" to " + maxCheck + " are:");
		for (int i = minCheck; i <= maxCheck; i++) {
			sequencecount = 0;
			//System.out.print(i+"->");
			System.out.println(i);
			int response = sequenceNum(i);
			hash_map.put(i, response);

		}
		System.out.println("Map : "+hash_map);
		System.out.println("Number producing Largest Chain : "+Collections.max(hash_map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
		
		
	}

	private int sequenceNum(int n) {
		int sum = 0;
		//System.out.println("N1 : "+n);

		try {

			if(n % 2 == 0) {

				sum = n/2;

			}  else {

				sum = 3*n +1 ;

			}

			if (sum == 1) {
				System.out.println(sum);
				sequencecount++;
				count++;
				
				return sequencecount ;

			}
			else {
				//System.out.print(sum+"->");
				sequencecount++;
				sequenceNum(sum);
			} 


		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequencecount;


	}

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
		ExecutorService executor = null;
		 
		
		
		try {
			// Start the executor process 
			//Todo : Forloop with each incremental value
			executor = Executors.newFixedThreadPool(10);
			Runnable taskOne = new SequenceNumberThread("TaskOne", 1, 100);
			executor.execute(taskOne);
			Runnable tasktwo = new SequenceNumberThread("TaskOne", 101, 1000);
			executor.execute(tasktwo);
			
			Runnable taskthree = new SequenceNumberThread("Taskthree", 1000, 10000);
			executor.execute(taskthree);
			Runnable taskfour = new SequenceNumberThread("Taskfour", 10000, 100000);
			executor.execute(taskfour);
		//	Runnable taskfive = new SequenceNumberThread("Taskfive", 113385, 200000);
			//executor.execute(taskfive);
			//Runnable tasksix = new SequenceNumberThread("Tasksix", 200000, 300000);
			//executor.execute(tasksix);
			/*Runnable taskseven = new SequenceNumberThread("Taskseven", 500000, 700000);
			executor.execute(taskseven);
			Runnable taskeight = new SequenceNumberThread("Taskeight", 700000, 900000);
			executor.execute(taskeight);
			Runnable tasknine = new SequenceNumberThread("Tasknine", 900000, 1000000);
			executor.execute(tasknine);*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}finally {

			executor.shutdown();
		}

}



}
