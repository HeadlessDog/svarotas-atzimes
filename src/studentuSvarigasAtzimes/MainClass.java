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
		
		pdSk = Method_class.parbSk();
		pdWg = new int[pdSk];
		pdRez = new int[sklSk][pdSk];
		
		Method_class.smagumuIevade(pdSk, pdWg);
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
