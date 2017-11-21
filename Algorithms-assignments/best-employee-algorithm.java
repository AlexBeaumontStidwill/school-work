package cmpe365lab1;

import java.io.*;
import java.util.Scanner;


public class best-employee-algorithm{

	public static void main(String[] args) {
		int a = 1;
		float best = 0;
		int position;
		int pos1 = 0;
		
		while(a == 1) {
			
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Name of file (include .csv): ");
		String in = sc.next();
		
		try {
			
			Scanner file = new Scanner(new File(in));
			int newLines = 0;
			float values[] = new float[1000];
			
			float largest = file.useDelimiter(",").nextFloat();
			
			while(file.hasNextFloat()) {

				float number = file.nextFloat();
				values[newLines] = number;
				newLines++;
				
				if(number > largest) {
					largest = number;
					pos1 = newLines;
				}
				
			}
			
			System.out.println("The biggest number in the file is: "+largest + ", found at position " +pos1 );
			
			int third = (int)Math.floor(newLines/Math.E);
			float thirdBest = 0;
			
			for(int i = 0; i < third; i++) {
				if(values[i] > thirdBest) {
					thirdBest = values[i];
				}
			}
			
			for(int i = third; i < values.length; i++) {
				if(values[i] > thirdBest) {
					thirdBest = values[i];
				
					position = i + 1;
					System.out.println("The biggest number using the best employee algorithm is " +thirdBest + ", found at position " +position);
					break;
				}
			}
			
			file.close();
			float deviation = thirdBest/largest * 100;
			
			System.out.println("The best employee algorithm value is " +deviation + "% accurate");
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	
		System.out.println("another file? ('yes' or 'no')?");
		String in1 = sc1.next();
		if(in1.equals("no")) {
			sc.close();
			sc1.close();
			a = 0;
		}
	}
}

}
