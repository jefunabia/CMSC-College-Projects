package mp5;

import java.util.Scanner;

public class MP5Rule30 {
	public static int THREAD_COUNT = 10;
	public static int charPerThread;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String str = "";
		String next = "";
		int min = 1;
		int max;
		
		System.out.println("Enter Table Size:");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();

		for (int i = 0; i < x; i++) {
			if (i == x / 2){
				str += '1';
			}
			else{
				str += '0';
			}
				
		}
		System.out.println(str);

		// 10 threads
	    R30Thread[] workers = new R30Thread[THREAD_COUNT];
        charPerThread = x / THREAD_COUNT;
        max = min + charPerThread - 1;
        
        for(int i = 0; i < x - 1; i++){    
            for(int j = 0; j < THREAD_COUNT; j++){
                if(j == THREAD_COUNT - 1){
                    max = x - 1;
                }       
                workers[j] = new R30Thread(min, max, str);
                min = max + 1;
                max = min + charPerThread - 1;
            }
            
            // start workers
            for(int j = 0; j < THREAD_COUNT; j++){
                workers[j].start();
            }
            
            // wait for workers to be done
           for (int j = 0; j < THREAD_COUNT; j++) {
            while (workers[j].isAlive()) {
                try {
                    workers[j].join();
                } catch (InterruptedException e) {
                    System.err.println("thread interrupted: " + e.getMessage());
                }
            }
        }
           
            for(int j = 0; j < THREAD_COUNT; j++){
                next += workers[j].getRes();
            }
            
            System.out.println(next);
            
            str = next;
            next = "";
            
            min = 0;
            max = charPerThread - 1;
        }
        System.out.println("time consumed in ms: " + (System.currentTimeMillis() - startTime));
	}
}
