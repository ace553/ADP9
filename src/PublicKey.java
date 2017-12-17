import java.math.BigInteger;
import java.util.Base64;

public class PublicKey
{

	BigInteger _mod;

	BigInteger _exponent;

	public PublicKey(BigInteger e, BigInteger mod)
	{
		_mod = mod;
		_exponent = e;
	}
	
	@Override
	public String toString()
	{
		byte[] bytes = new byte[24];
		
		byte[] mod = Util.toByteArray(_mod, 16);
		byte[] expo = Util.toByteArray(_exponent, 8);
		System.arraycopy(expo, 0, bytes, 0, 8);
		System.arraycopy(mod, 0, bytes, 8, 16);
		
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static PublicKey fromBase64(String base64)
	{
		byte[] key = Base64.getDecoder().decode(base64);
		
		byte[] mod = new byte[16];
		byte[] expo = new byte[8];
		
		System.arraycopy(key, 0, expo, 0, 8);
		System.arraycopy(key, 8, mod, 0, 16);
		
		return new PublicKey(new BigInteger(1, expo), new BigInteger(1, mod));
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof PublicKey)
		{
			PublicKey key = (PublicKey) obj;
			return key._exponent.equals(_exponent) && key._mod.equals(_mod);
		}
		return false;
	}
}
