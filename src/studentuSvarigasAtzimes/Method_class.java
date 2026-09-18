package studentuSvarigasAtzimes;

import java.util.Scanner;

public class Method_class {
	static Scanner sc = new Scanner(System.in);
	static boolean validInt;
	//Funkcija kas pārbauda vai ievadītais ir integer un atgriež true or false
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
		
		//Skolēnu skaita ievade ar validāciju
		public static int skolSk() {
			int sklSk =0;
				do {
					System.out.println("Ievadiet skolēnu skaitu: ");
					String tempInput = sc.next();
					
					if(isInteger(tempInput, 10))
						sklSk = Integer.parseInt(tempInput);
					
					validInt = (sklSk < 1 || sklSk > 100000) ? false : true;
						
					if(!validInt)
						System.out.println("Nedarīga vērtība!");
				}while(!validInt);
				return sklSk;
		}
		
		//Testu skaita ievade ar validāciju
		public static int parbSk() {
			int pdSk =0;
				do {
					System.out.println("Ievadiet pārbaudes darbu/test/eksāmenu skaitu katram skolēnam: ");
					String tempInput = sc.next();
					
					if(isInteger(tempInput, 10))
						pdSk = Integer.parseInt(tempInput);
					
					validInt = (pdSk < 1 || pdSk > 20) ? false : true;
						
					if(!validInt)
						System.out.println("Nedarīga vērtība!");
				}while(!validInt);
			return pdSk;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
