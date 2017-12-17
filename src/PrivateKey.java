import java.math.BigInteger;

public class PrivateKey
{
	
	BigInteger _mod;

	BigInteger _exponent;
	
	
	public PrivateKey(BigInteger d, BigInteger mod)
	{
		_mod = mod;
		_exponent = d;
	}
}
