package studentuSvarigasAtzimes;

import java.text.DecimalFormat;
import studentuSvarigasAtzimes.Method_class;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		int pdSk = 0;
		int sklSk = 0;
		int[] pdWg = null;
		int[][] pdRez = null;
		double[] finalRez = null;
		String[] sklName = null;
		String izvele = null;
		
		boolean validInt;
		int maxNameLength = 0;
		
		do {
			System.out.println("0 - Iziet\n"
			+ "1 - Ievadīt skolēnu skaitu\n"
			+ "2 - Ievadīt skolēnu vārdus\n"
			+ "3 - Ievadīt testu skaitu un smagumu\n"
			+ "4 - Ievadīt testu rezultātus\n"
			+ "5 - Apskatīt gala vērtējumu\n"
			+ "6 - Saglabāt teksta datnē");
			
			do {
				int tempInt = 0;
				izvele = sc.nextLine();
				
				if(Method_class.isInteger(izvele, 10))
					tempInt = Integer.parseInt(izvele);
				
				validInt = (tempInt < 0 || tempInt > 100000) ? false : true;
					
				if(!validInt)
					System.out.println("Nedarīga vērtība!");
			}while(!validInt);
			
			
			switch(izvele) {
			case "1":
				sklSk = Method_class.skolSk();
				sklName = new String[sklSk];
				finalRez = new double[sklSk];
				
				if(pdSk > 0) {
					pdRez = new int[sklSk][pdSk];
				}
				break;
			case "2":
				if(sklSk > 0 && sklName != null)
					maxNameLength = Method_class.nameEntry(sklSk, sklName);
				else
					System.out.println("Darbību nevar veikt, nav ievadīts skolēnu skaits.");
				break;
			case "3":
				pdSk = Method_class.parbSk();
				pdWg = new int[pdSk];
				
				if(sklSk > 0) {
					pdRez = new int[sklSk][pdSk];
				}
				
				Method_class.smagumuIevade(pdSk, pdWg);
				break;
			case "4":
				if(sklName != null && !Arrays.stream(sklName).allMatch(Objects::isNull)
						&& pdSk > 0 && sklSk > 0 &&
						pdWg != null && !Arrays.stream(pdWg).allMatch(Objects::isNull))
					Method_class.resEntry(pdSk, sklSk, sklName, pdWg, pdRez);
				else
					System.out.println("Darbība nevar tikt veikta, skolēnu vārdi vai smagumi nav ievadīti.");
				break;
			case "5":
				if(sklSk > 0 && pdSk > 0 && pdWg != null
				&& !Arrays.stream(pdWg).allMatch(Objects::isNull)
				&& pdRez != null && !Arrays.stream(pdRez).allMatch(Objects::isNull)) {
					Method_class.calcFin(sklSk, pdSk, pdWg, finalRez, pdRez);
					Method_class.rezOut(maxNameLength, sklSk, pdSk, pdWg, finalRez, pdRez, sklName, df);
				}
				else
					System.out.println("Darbību nevar veikt, nav ievadīti kādi no datiem");
				break;
			case "6":
				break;
			case "0":
				break;
			default:
				System.out.println("Kļūda");
			}
			
		}while(!izvele.equals("0"));
		sc.close();
	}

}
