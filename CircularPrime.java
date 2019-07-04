/*
 * Code to get the number of circular prime numbers below ten million
 * */
class PrimeNumber extends Thread{

	public void run() {
		//int maxCheck = 100;
		int maxCheck = 1000000;
		boolean isPrime = false;
		String primeNumber = "";
		int count =0;

		for (int i = 1; i <= maxCheck; i++) {
			isPrime = checkCircular(Integer.toString(i));
			if (isPrime) {
				primeNumber = primeNumber + i + " ";
				count = count+1;
			}
		}
		System.out.println("Prime circular numbers from 1 to " + maxCheck + " are:");
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

public class CircularPrime {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("Main Thread");
		System.out.println(Thread.currentThread().getName());
		PrimeNumber prime = new PrimeNumber();
		prime.start();


	}

}
