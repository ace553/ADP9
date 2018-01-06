package material;
import java.math.BigInteger;

public class KeyPair
{
	private PublicKey _public;
	private PrivateKey _private;
	
	public BigInteger _p;
	public BigInteger _q;
	public BigInteger _n;
	public BigInteger _phi;

	public BigInteger _e;
	public BigInteger _d;
	
	
	
	public KeyPair(BigInteger e, BigInteger d, BigInteger mod, BigInteger p, BigInteger q, BigInteger n, BigInteger phi)
	{
		_public = new PublicKey(e, mod);
		_private = new PrivateKey(d, mod);
		
		_p = p;
		_q = q;
		_n = n;
		_phi = phi;
		_e = e;
		_d = d;
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
