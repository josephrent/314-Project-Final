 //This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;


public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) throws FileNotFoundException{
	  primes.clearPrimes();
		File f = new File(Config.DATAPATH + filename); 
		Scanner s = new Scanner(f); 
		boolean more = s.hasNextLine();
		while (more) {//runs until end of file
			String number = s.nextLine().replaceAll(" ", "");
			primes.addPrime(new BigInteger(number));
			more = s.hasNextLine();//end of file?
		}
		s.close();
		return true;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) throws FileNotFoundException{
	  primes.clearCrosses();
	  File f = new File(Config.DATAPATH + filename);
	  Scanner s = new Scanner(f);
	  boolean more = s.hasNextLine();
	  while (more) {//runs until end of file
		   String number = s.nextLine();
		   BigInteger crossOne = new BigInteger(number.substring(0,number.indexOf(",")));
		   BigInteger crossTwo = new BigInteger(number.substring(number.indexOf(",")+1));
		   primes.addCross(new Pair<BigInteger>(crossOne, crossTwo));
		   more = s.hasNextLine();//end of file?
		 }
		 s.close();
		 return true;
	}
  
  public static boolean savePrimes(Primes primes, String filename) throws IOException
  {
	  File f = new File(Config.DATAPATH + filename);
	  if(!f.exists()) {
	    f.createNewFile();
	  }
	  FileWriter writer = new FileWriter(f);

	  for (BigInteger primeNumber : primes.iteratePrimes()) {//iterator
	    String primeToWrite = primeNumber.toString() + "\n";
	    writer.write(primeToWrite);
	  }
	  writer.close();
	  return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename) throws IOException
  {
	  File f = new File(Config.DATAPATH + filename);
	  if(!f.exists()) {
	    f.createNewFile();
	  }
	  FileWriter writer = new FileWriter(f);
	  for (Pair<BigInteger> pair : primes.iterateCrosses()) {//iterator
	    String cross = pair.toString() + "\n";
	    writer.write(cross);
	  }
	  writer.close();
	  return true;
  }
}


