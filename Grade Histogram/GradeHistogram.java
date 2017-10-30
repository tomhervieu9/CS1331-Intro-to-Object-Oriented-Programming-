import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GradeHistogram{


	public static void main(String[] args) throws FileNotFoundException {
		
		int bucketSize;
		int gradeRange;
		String str;
		String s;
		String x;
		double dblx;
	    double y;
		int z;
		int q;

		if (args.length < 2) {
			System.out.println("What bucket size would you like?");
			s = System.console().readLine();
			bucketSize =  Integer.parseInt(s);
		} else {
			str= args[1];
			bucketSize = Integer.parseInt(str);
		}
		gradeRange = (101 / bucketSize)+1; 
		int difference = 100 % bucketSize;
		int[] myArray = new int[gradeRange];
		File dataFile = new File(args[0]);
		Scanner scanner = new Scanner(dataFile);
		while(scanner.hasNext()){
			x = scanner.nextLine().split(",")[1];
			x = x.trim();
			dblx = Double.parseDouble(x);
			y = Math.floor((100-dblx) / bucketSize);
			q = (int) y;
			z = gradeRange - q - 1;
			myArray[z] = myArray[z] + 1;
		}
		for (int i = gradeRange -1; i >= 0; i--) {
			int counter = myArray[i];
			int upperBound = ((i)*bucketSize + difference);
			int lowerBound = Math.max((upperBound + 1 - bucketSize),0);
			String str2 = new String(new char[counter]).replace("\0", "[]");
			System.out.printf("%3d - %3d | %s\n", upperBound, lowerBound, str2);
		}
	}
}