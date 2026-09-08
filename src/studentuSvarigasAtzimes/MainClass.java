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
		
		int pdSk;
		int sklSk = 0;
		int[] pdWg;
		int[] pdRez;
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
		
		sc.close();
	}

}
