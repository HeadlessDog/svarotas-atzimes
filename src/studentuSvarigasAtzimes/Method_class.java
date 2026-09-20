package studentuSvarigasAtzimes;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
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
					String tempInput = sc.nextLine();
					
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
					String tempInput = sc.nextLine();
					
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
						String tempInput = sc.nextLine();
						
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
						String tempInput = sc.nextLine();
						
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
						String tempInput = sc.nextLine();
						
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
		
		//Skolēnu vārdu ievade ar blank validāciju
		public static int nameEntry(int sklSk, String[] sklName) {
			int maxNameLength =0;
			
			boolean validString;
			for(int j=0;j<sklSk;j++) {
				do {
					System.out.println("Ievadiet "+(j+1)+". skolēna vārdu");
					String tempInput = sc.nextLine();	
					
					validString = !tempInput.isBlank();
					if(!validString)
						System.out.println("Nedarīga vērtība!");
					else {
						sklName[j] = tempInput; 
						maxNameLength = Math.max(maxNameLength, tempInput.length());
					}
				}while(!validString);
			}
			return maxNameLength;
		}
				
		public static void resEntry(int pdSk, int sklSk, String[] sklName, int[] pdWg, int[][] pdRez) {
			//Rezultātu ievade
			for(int i=0;i<sklSk;i++)
			{
				for(int j=0;j<pdSk;j++)
				{
					System.out.println("Ievadi skolēna: "+sklName[i]+" atzīmi par "+(j+1)+". testu:\n"
							+ "(svars "+pdWg[j]+")");
					
					pdRez[i][j] = 0;
					do {
						String tempInput = sc.nextLine();
						
						if(isInteger(tempInput, 10))
							pdRez[i][j] = Integer.parseInt(tempInput);
						
						validInt = (pdRez[i][j] < 1 || pdRez[i][j] > 10) ? false : true;
							
						if(!validInt)
							System.out.println("Nedarīga vērtība!");
					}while(!validInt);
					
				}
			}
		}
		
		public static void calcFin(int sklSk, int pdSk, int[] pdWg, double[] finalRez, int[][] pdRez) {
			//Izrēķina rezultātus
			for(int i=0;i<sklSk;i++)
			{
				double sum=0;
				for(int j=0; j<pdSk;j++)
					sum += (double)pdRez[i][j]*((double)pdWg[j]/100);
				finalRez[i] = sum;
			}
		}
		
		public static void rezOut(int maxNameLength, int sklSk, int pdSk, int[] pdWg, double[] finalRez, int[][] pdRez, String[] sklName, DecimalFormat df) {
			//Gala rezultātu tabulas izvade
			for(int i=0;i<maxNameLength+1;i++)
				System.out.print(" ");
			for(int i=0;i<pdSk;i++)
				System.out.printf("%-17s", "Tests "+(i+1)+". ("+pdWg[i]+"%) ");
			
			System.out.println("Gala rez.");
			for(int i=0;i<sklSk;i++)
			{
				System.out.print(sklName[i]+": ");
				for(int j=0;j<maxNameLength-sklName[i].length();j++)
					System.out.print(" ");
				for(int j=0;j<pdSk;j++)
					System.out.printf("%-17s", pdRez[i][j]);
				System.out.println(df.format(finalRez[i]));
			}
		}
		
		public static void rezFails(int maxNameLength, int sklSk, int pdSk, int[] pdWg, double[] finalRez, int[][] pdRez, String[] sklName, DecimalFormat df) {
			try{
				PrintWriter raksta = new PrintWriter("rezultati.txt");
				//Rezultātu izvade teksta failā
				for(int i=0;i<maxNameLength+1;i++)
					
					raksta.print(" ");
				for(int i=0;i<pdSk;i++)
					raksta.printf("%-17s", "Tests "+(i+1)+". ("+pdWg[i]+"%) ");
				
				raksta.println("Gala rez.");
				for(int i=0;i<sklSk;i++)
				{
					raksta.print(sklName[i]+": ");
					for(int j=0;j<maxNameLength-sklName[i].length();j++)
						raksta.print(" ");
					for(int j=0;j<pdSk;j++)
						raksta.printf("%-17s", pdRez[i][j]);
					raksta.println(df.format(finalRez[i]));
				}	
			} catch (FileNotFoundException e) {
				System.out.println("Nevarēja izveidot failu");
			}
		}
		
		public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
