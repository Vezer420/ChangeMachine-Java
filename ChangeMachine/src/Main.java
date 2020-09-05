
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
	static long startTime,stopTime;
	static double Price;
	static String Currency;
	static long ExecTime;
	
	public static void main(String[] args) throws IOException {
	    
		startTime = System.currentTimeMillis();
	    RandGen();
	    ChangeCalc();
	    genTxt();
	    System.out.println("Execution Time: "+ExecTime+" ms");
	    
	    
	  }
	public static void RandGen() 
	{
		
		double longPrice = ThreadLocalRandom.current().nextDouble(10000,100000);
		DecimalFormat trimPrice = new DecimalFormat("#.##");
		Price = Double.parseDouble(trimPrice.format(longPrice).replace(",","."));
		String[] currencyTable = {"HUF","EUR","JPY"};
		int currencyIndex = ThreadLocalRandom.current().nextInt(0,currencyTable.length);
		Currency = currencyTable[currencyIndex];
		System.out.println(Price + " " + Currency);
		
	}
	public static void ChangeCalc()
	{
		switch (Currency) {
		case "HUF":
			 double[] HufValues = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000 };
             double[] numOfValuesUsed = new double[HufValues.length];
             double remainPrice = Price;
             for (int currentChange = HufValues.length-1; currentChange >= 0; currentChange--)
             {
                 int currentChangeindex = (int)(remainPrice / HufValues[currentChange]);
                 numOfValuesUsed[currentChange] = currentChangeindex;
                 remainPrice -= (currentChangeindex * HufValues[currentChange]);
                 if (remainPrice == 0) break;
             }
             for (int i = HufValues.length-1; i >= 0 ; i--)
             {
                 if (numOfValuesUsed[i] != 0)
                 {
                     System.out.print((int)numOfValuesUsed[i]+ " x " + (int)HufValues[i] + " ");
                 }
                 
             }
             System.out.println();
			break;
		case "EUR":
			double[] EurValues = { 0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100, 200, 500 };
            double[] numOfEurValuesUsed = new double[EurValues.length];
            double remainEurPrice = Price;
            
            for (int currentChange = EurValues.length - 1; currentChange >= 0; currentChange--)
            {
                int currentEurChangeindex = (int)(remainEurPrice / EurValues[currentChange]);
                numOfEurValuesUsed[currentChange] = currentEurChangeindex;
                remainEurPrice -= (currentEurChangeindex * EurValues[currentChange]);
                if (remainEurPrice == 0) break;
            }
            for (int i = EurValues.length - 1; i >= 0; i--)
            {
            	if (EurValues[i] > 1.00){
            		if (numOfEurValuesUsed[i] != 0) System.out.print((int)numOfEurValuesUsed[i] + " x " + (int)EurValues[i] + " ");
				}
            	else {
            		if (numOfEurValuesUsed[i] != 0) System.out.print((int)numOfEurValuesUsed[i] + " x " + String.format("%.2f",EurValues[i]) + " ");
				}
                
            }
            System.out.println();
			break;
		case "JPY":
			double[] JpyValues = {1,5,10,50,100,500,1000,2000,5000,10000};
            double[] numOfJpyValuesUsed = new double[JpyValues.length];
            double remainJpyPrice = Price;

            for (int currentChange = JpyValues.length - 1; currentChange >= 0; currentChange--)
            {
                int currentEurChangeindex = (int)(remainJpyPrice / JpyValues[currentChange]);
                numOfJpyValuesUsed[currentChange] = currentEurChangeindex;
                remainJpyPrice -= (currentEurChangeindex * JpyValues[currentChange]);
                if (remainJpyPrice == 0) break;
            }
            for (int i = JpyValues.length - 1; i >= 0; i--)
            {
                if (numOfJpyValuesUsed[i] != 0) System.out.print((int)numOfJpyValuesUsed[i] + " x " + (int)JpyValues[i] + " ");
            }
            System.out.println();
			break;

		default:
			System.out.println("ERROR");
			break;
		}
		
	}
	public static void genTxt() throws IOException 
	{

		String systemName = System.getProperty("user.name") + ".txt";
		// System.out.println(systemName);
		FileWriter mw = new FileWriter(systemName);
		stopTime = System.currentTimeMillis();
		ExecTime = stopTime - startTime;
		mw.write(Price+", "+Currency+", "+ExecTime+" ms");
		mw.close();
	}

}

