import java.util.Scanner;

public class CollatzArrayZerosAlexBeaumontStidwill{
	
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		String ans = "yes";
		System.out.println("Positive Number:");
		int n = sc.nextInt();	
		int check[] = new int[n*2];
		
		while(true) {
			System.out.println("Positive number (same as above if first time):");
			int num = sc.nextInt();	
			long startTime = System.nanoTime();
			
			while (num != 1) {
				if (num % 2 == 0) {
					if(num <= n) {
					if(check[num-1] == 0)
						check[num-1] = num;
					else {
						System.out.println(num + " already in array");
						break;
					}
					}
					num = num / 2; 
				} else {
					if(num <= n) {
					if(check[num-1] == 0)
						check[num-1] = num;
					else {
						System.out.println(num + " already in array");
						break;
					}
					}
					num = (num * 3) + 1;
				}
				
			}
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println("This took " + duration + " nanoseconds");
			System.out.println("Another Number? If yes, type 'yes': ");
			ans = sc1.nextLine();
			if(ans.equals("yes")) {
				continue;
			} else {
				sc1.close();
				break;
			}
			
		}
		
		sc.close();
		
	}
}