package material;
import java.util.Random;

public class Feistel
{

	private static Feistel.Encrypter encrypter;

	private static Feistel.Decrypter decrypter;
	
	
	public static Feistel.Encrypter getEncrypter()
	{
		if(encrypter == null)
		{
			encrypter = new Encrypter();
		}
		return encrypter;
	}
	
	public static Feistel.Decrypter getDecrypter()
	{
		if(decrypter == null)
		{
			decrypter = new Decrypter();
		}
		return decrypter;
	}
	

	public static class Encrypter
	{
		
		public byte[] encrypt(byte[] array, byte[] key)
		{
			byte[] encrypted = new byte[array.length + (Encryption.BLOCKSIZE-(array.length % Encryption.BLOCKSIZE))];
			
			for(int i = 0; i < encrypted.length; i++)
			{
				encrypted[i] = 0x20;
			}
			
			System.arraycopy(array, 0, encrypted, 0, array.length);
			
			for(int i = 0; i < encrypted.length; i += Encryption.BLOCKSIZE)
			{
				FeistelBlock fb = new FeistelBlock(i, encrypted);
				fb.encrypt(key);
				fb.backIntoArray();
			}
			return encrypted;
		}
	}
	
	public static class Decrypter
	{
		public byte[] decrypt(byte[] array, byte[] key)
		{
			byte[] decrypted = new byte[array.length];
			System.arraycopy(array, 0, decrypted, 0, array.length);
			
			for(int i = 0; i < decrypted.length; i += Encryption.BLOCKSIZE)
			{
				FeistelBlock fb = new FeistelBlock(i, decrypted);
				fb.decrypt(key);
				fb.backIntoArray();
			}
			return decrypted;
		}
	}
	
	
	public static byte[] generateSessionKey()
	{
		Random r = new Random();
		long key = r.nextLong();
		return Util.longToBytes(key);
	}
	

}
