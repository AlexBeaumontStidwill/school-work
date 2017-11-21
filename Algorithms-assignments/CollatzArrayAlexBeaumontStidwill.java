import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CollatzArrayAlexBeaumontStidwill{
	
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		String ans = "yes";
		
		while(true) {
			System.out.println("Positive Number:");
			int num = sc.nextInt();
			long startTime = System.nanoTime();
			list.add(num);
			while (num != 1) {
				if (num % 2 == 0) {
					num = num / 2;
					if(list.contains(num)) {
						System.out.println(num + " already in list!");
						break;
				} else 
						
							list.add(num);	
					
				} else {
					num = num * 3 + 1;
					if(list.contains(num)) {
						System.out.println(num + " already in list!");
						break;
					} else {
						
							list.add(num);
					}
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
		
		System.out.println("This is the list of all numbers: ");
		System.out.println(list);
		sc.close();
		
	}
}