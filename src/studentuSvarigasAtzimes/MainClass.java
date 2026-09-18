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
		
		System.out.println("Jūs esat pieslēdiez gala atzīmju kalkulatoram.\n");
		
		sklSk = Method_class.skolSk();
		sklName = new String[sklSk];
		finalRez = new double[sklSk];
		
		//Testu skaita ievade ar validāciju
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
		
		//Visu testu smagumu ievade ar validāciju
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
		//Skolēnu vārdu ievade ar blank validāciju
		int maxNameLength =0;
		sc.nextLine();
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
		
		//Rezultātu ievade
		for(int i=0;i<sklSk;i++)
		{
			for(int j=0;j<pdSk;j++)
			{
				System.out.println("Ievadi skolēna: "+sklName[i]+" atzīmi par "+(j+1)+". testu:\n"
						+ "(svars "+pdWg[j]+")");
				
				pdRez[i][j] = 0;
				do {
					String tempInput = sc.next();
					
					if(isInteger(tempInput, 10))
						pdRez[i][j] = Integer.parseInt(tempInput);
					
					validInt = (pdRez[i][j] < 1 || pdRez[i][j] > 10) ? false : true;
						
					if(!validInt)
						System.out.println("Nedarīga vērtība!");
				}while(!validInt);
				
			}
		}
		
		//Izrēķina rezultātus
		for(int i=0;i<sklSk;i++)
		{
			double sum=0;
			for(int j=0; j<pdSk;j++)
				sum += (double)pdRez[i][j]*((double)pdWg[j]/100);
			finalRez[i] = sum;
		}
		
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
		
		
		sc.close();
	}

}
