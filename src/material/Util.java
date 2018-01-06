package material;
import java.math.BigInteger;

public class Util
{

	
	public static byte[] longToBytes(long l) {
	    byte[] result = new byte[8];
	    for (int i = 7; i >= 0; i--) {
	        result[i] = (byte)(l & 0xFF);
	        l >>= 8;
	    }
	    return result;
	}
	
	
	public static byte[] toByteArray(BigInteger i, int bytesize)
	{
		byte[] out = new byte[bytesize];
		BigInteger mod = new BigInteger("2");
		mod = mod.pow(bytesize*8);
		i = i.mod(mod);
		
		int cplength =  i.toByteArray().length;
		int startdst = bytesize - cplength;
		
		if(i.bitLength() % 8 != 0)
		{
			System.arraycopy(i.toByteArray(), 0, out, startdst, cplength);
		}
		else
		{
			System.arraycopy(i.toByteArray(), 1, out, startdst+1, cplength-1);
		}
		
		return out;
		
	}
	
}
