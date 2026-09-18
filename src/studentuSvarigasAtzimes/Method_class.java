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
	
		//Visu testu smagumu ievade ar validāciju
		public static void smagumuIevade(int pdSk, int[] pdWg) {
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
				
				//Summatīvo procentu pārbaudes cikls
				while(totalPercent != 100)
				{
					System.out.println("Smagumi nesaskaitās uz 100, izvēlieties testu kura smagumu izmainīt.\n");
					for(int i=0;i<pdSk;i++)
						System.out.println((i+1)+". tests - "+pdWg[i]+"%");
					
					//Testa izvēle ar validāciju
					int currTest = -1;
					do {
						String tempInput = sc.next();
						
						if(isInteger(tempInput, 10))
							currTest = Integer.parseInt(tempInput)-1;
						
						validInt = (currTest < 0 || currTest > pdSk-1) ? false : true;
							
						if(!validInt)
							System.out.println("Nedarīga vērtība!");
					}while(!validInt);
					//Jauno procentu ievadīšana ar validāciju
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
		}
		public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
