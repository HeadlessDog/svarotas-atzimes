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
		
		int maxNameLength = Method_class.nameEntry(sklSk, sklName);
		
		Method_class.resEntry(pdSk, sklSk, sklName, pdWg, pdRez);
		
		Method_class.calcFin(sklSk, pdSk, pdWg, finalRez, pdRez);
		
		Method_class.rezOut(maxNameLength, sklSk, pdSk, pdWg, finalRez, pdRez, sklName, df);
		
		sc.close();
	}

}
