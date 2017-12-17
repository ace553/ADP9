import java.math.BigInteger;
import java.util.Random;


public class RSA
{
	
	private static RSA.Encrypter encrypter;

	private static RSA.Decrypter decrypter;

	public static RSA.Encrypter getEncrypter()
	{
		if (encrypter == null)
		{
			encrypter = new Encrypter();
		}
		return encrypter;
	}

	public static RSA.Decrypter getDecrypter()
	{
		if (decrypter == null)
		{
			decrypter = new Decrypter();
		}
		return decrypter;
	}

	public static class Encrypter
	{

		public byte[] encrypt(byte[] array, PublicKey key)
		{
			return new BigInteger(1, array).modPow(key._exponent, key._mod).toByteArray();
		}
	}

	public static class Decrypter
	{
		public byte[] decrypt(byte[] array, PrivateKey key)
		{
			return Util.toByteArray(new BigInteger(1, array).modPow(key._exponent, key._mod), Encryption.BLOCKSIZE/2);
		}
	}

	public static KeyPair generateKeys()
	{
		Random rnd = new Random();
		BigInteger p = BigInteger.probablePrime(Encryption.BLOCKSIZE*4, rnd);
		BigInteger q = BigInteger.probablePrime(Encryption.BLOCKSIZE*4, rnd);

		BigInteger n = p.multiply(q);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		BigInteger e = BigInteger.probablePrime(Encryption.BLOCKSIZE*4, rnd);
		while (!e.gcd(phi).equals(BigInteger.ONE))
		{
			e = e.add(BigInteger.ONE);
		}
		BigInteger d = e.modInverse(phi);
		
		return new KeyPair(e, d, n);
	}
}
