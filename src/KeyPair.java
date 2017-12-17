import java.math.BigInteger;

public class KeyPair
{
	private PublicKey _public;
	private PrivateKey _private;
	
	public KeyPair(BigInteger e, BigInteger d, BigInteger mod)
	{
		_public = new PublicKey(e, mod);
		_private = new PrivateKey(d, mod);
	}
	
	public PrivateKey getPrivate()
	{
		return _private;
	}
	
	public PublicKey getPublic()
	{
		return _public;
	}
}
