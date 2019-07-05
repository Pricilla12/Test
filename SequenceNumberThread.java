import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Code to generate number chain for ten million numbers which will arrive at 89
 * */
class SeqNumberMultiT extends Thread{

	private String name;
	private int minvalue = 1;
	private int maxvalue = 100;
	private static int count = 0;
	private static int sequencecount = 0;
	HashMap<Integer, Integer> hash_map =  new HashMap<Integer, Integer>(); 

	public SeqNumberMultiT(String name, int minvalue, int maxvalue) {

		this.name = name;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;

	}

	public void run() {
		int minCheck = minvalue;
		int maxCheck = maxvalue;
		
		System.out.println("Number Chain from "+ minCheck +" to " + maxCheck + " are:");
		for (int i = minCheck; i <= maxCheck; i++) {
		//	System.out.print(i+"->");
			sequenceNum(i);

		}
		System.out.println("Initial Mappings are: " + hash_map); 
		System.out.println("Total Count : "+count);
		System.out.println("Number producing Largest Chain : "+Collections.max(hash_map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
	}

	private void sequenceNum(int n) {
		int sum = 0;
		

		try {

			if(n % 2 == 0) {

				sum = n/2;

			}  else {

				sum = 3*n +1 ;

			}

			if (sum == 1) {
			//	System.out.println(sum);
				sequencecount++;
				count++;
				hash_map.put(count, sequencecount);
				sequencecount = 0;
				

			}
			else {
			//	System.out.print(sum+"->");
				sequencecount++;
				sequenceNum(sum);
			} 


		} catch (Exception e) {
			e.printStackTrace();
		}


	}


}

public class SequenceNumberThread {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
		ExecutorService executor = null;
		try {
			// Start the executor process  
			executor = Executors.newFixedThreadPool(10);
			Runnable taskOne = new SeqNumberMultiT("TaskOne", 1, 10);
			executor.execute(taskOne);
			Runnable tasktwo = new SeqNumberMultiT("TaskOne", 21, 30);
			executor.execute(tasktwo);
			
			/*Runnable taskthree = new SeqNumberMultiT("Taskthree", 1000, 10000);
			executor.execute(taskthree);
			Runnable taskfour = new SeqNumberMultiT("Taskfour", 10000, 100000);
			executor.execute(taskfour);
			Runnable taskfive = new SeqNumberMultiT("Taskfive", 100000, 200000);
			executor.execute(taskfive);
			Runnable tasksix = new SeqNumberMultiT("Tasksix", 200000, 500000);
			executor.execute(tasksix);
			Runnable taskseven = new SeqNumberMultiT("Taskseven", 500000, 700000);
			executor.execute(taskseven);
			Runnable taskeight = new SeqNumberMultiT("Taskeight", 700000, 900000);
			executor.execute(taskeight);
			Runnable tasknine = new SeqNumberMultiT("Tasknine", 900000, 1000000);
			executor.execute(tasknine);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			executor.shutdown();
		}




	}

}
