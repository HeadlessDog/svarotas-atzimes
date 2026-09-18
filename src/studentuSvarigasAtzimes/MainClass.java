package studentuSvarigasAtzimes;

import java.text.DecimalFormat;
import studentuSvarigasAtzimes.Method_class;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		int pdSk = 0;
		int sklSk = 0;
		int[] pdWg;
		int[][] pdRez;
		double[] finalRez;
		String[] sklName;
		String izvele;
		
		boolean validInt;
		int maxNameLength;
		
		do {
			System.out.println("0 - Iziet\n"
			+ "1 - Ievadīt skolēnu skaitu\n"
			+ "2 - Ievadīt skolēnu vārdus\n"
			+ "3 - Ievadīt testu skaitu un smagumu\n"
			+ "4 - Ievadīt testu rezultātus\n"
			+ "5 - Apskatīt gala vērtējumu\n"
			+ "6 - Saglabāt teksta datnē");
			
			do {
				izvele = sc.next();
				
				if(Method_class.isInteger(izvele, 10))
					sklSk = Integer.parseInt(izvele);
				
				validInt = (sklSk < 1 || sklSk > 100000) ? false : true;
					
				if(!validInt)
					System.out.println("Nedarīga vērtība!");
			}while(!validInt);
			
			
			switch(izvele) {
			case "1":
				sklSk = Method_class.skolSk();
				sklName = new String[sklSk];
				finalRez = new double[sklSk];
				break;
			case "2":
				maxNameLength = Method_class.nameEntry(sklSk, sklName);
				break;
			case "3":
				pdSk = Method_class.parbSk();
				pdWg = new int[pdSk];
				pdRez = new int[sklSk][pdSk];
				
				Method_class.smagumuIevade(pdSk, pdWg);
				break;
			case "4":
				Method_class.resEntry(pdSk, sklSk, sklName, pdWg, pdRez);
				break;
			case "5":
				Method_class.calcFin(sklSk, pdSk, pdWg, finalRez, pdRez);
				Method_class.rezOut(maxNameLength, sklSk, pdSk, pdWg, finalRez, pdRez, sklName, df);
				break;
			case "6":
				Method_class.rezFails(maxNameLength, sklSk, pdSk, pdWg, finalRez, pdRez, sklName, df);
				break;
			default:
				System.out.println("Kļūda");
			}
			
		}while(izvele!="0");
		sc.close();
	}

}
