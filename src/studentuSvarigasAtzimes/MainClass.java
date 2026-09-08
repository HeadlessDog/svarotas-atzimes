package studentuSvarigasAtzimes;

import java.util.Scanner;

public class MainClass {
	
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int pdSk = 0;
		int sklSk = 0;
		int[] pdWg;
		int[][] pdRez;
		String[] sklName;
		
		System.out.println("Jūs esat pieslēdiez gala atzīmju kalkulatoram.\n");
		boolean validInt;
		do {
			System.out.println("Ievadiet skolēnu skaitu: ");
			String tempInput = sc.next();
			
			if(isInteger(tempInput, 10))
				sklSk = Integer.parseInt(tempInput);
			
			validInt = (sklSk < 1 || sklSk > 100000) ? false : true;
				
			if(!validInt)
				System.out.println("Nedarīga vērtība!");
		}while(!validInt);
		sklName = new String[sklSk];
		
		do {
			System.out.println("Ievadiet pārbaudes darbu/test/eksāmenu skaitu katram skolēnam: ");
			String tempInput = sc.next();
			
			if(isInteger(tempInput, 10))
				pdSk = Integer.parseInt(tempInput);
			
			validInt = (pdSk < 1 || pdSk > 20) ? false : true;
				
			if(!validInt)
				System.out.println("Nedarīga vērtība!");
		}while(!validInt);
		pdWg = new int[pdSk];
		pdRez = new int[sklSk][pdSk];
		
		int totalPercent =0;
		for(int j=0;j<pdSk;j++) {
			do {
				System.out.println("Ievadiet "+(j+1)+". testa smagumu %:\n"
						+ "(visu testu smagumam jāsummējās uz 100%)\n"
						+ "(minmālais smagums ir 5%)\n"
						+ "(vērtībai jābūt veselai)");
				String tempInput = sc.next();
				
				int currPercent = 0;
				if(isInteger(tempInput, 10))
					currPercent = Integer.parseInt(tempInput);
				
				validInt = ((currPercent < 5 || currPercent > 100) || (pdSk > 1 && currPercent == 100)) ? false : true;
					
				if(!validInt)
					System.out.println("Nedarīga vērtība!");
				else {
					totalPercent += currPercent;
					pdWg[j] = currPercent;
				}
			}while(!validInt);
		}
		
		while(totalPercent != 100)
		{
			System.out.println("Smagumi nesaskaitās uz 100, izvēlieties testu kura smagumu izmainīt.\n");
			for(int i=0;i<pdSk;i++)
				System.out.println((i+1)+". tests - "+pdWg[i]+"%");
			
			int currTest = -1;
			do {
				String tempInput = sc.next();
				
				if(isInteger(tempInput, 10))
					currTest = Integer.parseInt(tempInput)-1;
				
				validInt = (currTest < 0 || currTest > pdSk-1) ? false : true;
					
				if(!validInt)
					System.out.println("Nedarīga vērtība!");
			}while(!validInt);
			
			do {
				System.out.println("Ievadiet "+(currTest+1)+". testa smagumu %:\n"
						+ "(visu testu smagumam jāsummējās uz 100%)\n"
						+ "(minmālais smagums ir 5%)\n"
						+ "(vērtībai jābūt veselai)");
				String tempInput = sc.next();
				
				int currPercent = 0;
				if(isInteger(tempInput, 10))
					currPercent = Integer.parseInt(tempInput);
				
				validInt = ((currPercent < 5 || currPercent > 100) || (pdSk > 1 && currPercent == 100)) ? false : true;
					
				if(!validInt)
					System.out.println("Nedarīga vērtība!");
				else {
					totalPercent -= pdWg[currTest];
					pdWg[currTest] = currPercent;
					totalPercent += currPercent;
				}
			}while(!validInt);
		}
		
		
		sc.close();
	}

}
