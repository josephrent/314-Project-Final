import java.math.BigInteger;

public class NaiveTest
{
	public static boolean isPrime(BigInteger candidate)
	{
		BigInteger i = new BigInteger("2");
		
		while(i.compareTo(candidate) < 0) {
	        if (candidate.mod(i).equals(BigInteger.ZERO)) {
	            return false;
	        }
	        i = i.add(BigInteger.valueOf(1));
	    }
	    return true;
	}
}