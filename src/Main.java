import java.math.BigInteger;
import java.util.Base64;
import java.util.Scanner;

import material.Feistel;
import material.KeyPair;
import material.PrivateKey;
import material.PublicKey;
import material.RSA;

class Main
{

	public static void main(String[] args)
	{
		
		KeyPair pair = null;
		@SuppressWarnings("unused")
		PublicKey partnerKey = null;
		Scanner scan = new Scanner(System.in);

		System.out.println("Generating Key Pair");
		pair = RSA.generateKeys();
		System.out.println("Public Key: " + pair.getPublic());

		System.out.println("Enter public Key from Partner:");
		String key = scan.nextLine();
		partnerKey = PublicKey.fromBase64(key);

		while (true)
		{
			System.out.println("Enter String to Decrypt:");
			String s = scan.nextLine();
			System.out.println();
			System.out.println(decrypt(s, pair.getPrivate()));
			if(s.equals("exit"))
			{
				scan.close();
				System.out.println("Exiting");
				System.exit(0);
			}
		}
	}

	public static String decrypt(String s, PrivateKey key)
	{
		byte[] encrypted = Base64.getDecoder().decode(s);
		byte[] encrypedSessionKey = new byte[16];
		byte[] string = new byte[encrypted.length-16];
		System.arraycopy(encrypted, 0, encrypedSessionKey, 0, 16);
		System.arraycopy(encrypted, 16, string, 0, string.length);
		byte[] sessionKey = RSA.getDecrypter().decrypt(encrypedSessionKey, key);
		return new String(Feistel.getDecrypter().decrypt(string, sessionKey));
	}
	@SuppressWarnings("unused")
	private static String encrypt(String s, PublicKey key)
	{
		byte[] sessionKey = Feistel.generateSessionKey();
		byte[] encrypedString = Feistel.getEncrypter().encrypt(s.getBytes(), sessionKey);
		byte[] encrypedKey = RSA.getEncrypter().encrypt(sessionKey, key);

		System.out.println(new BigInteger(1, sessionKey));
		byte[] array = new byte[16 + encrypedString.length];
		System.out.println("Array length: " + array.length);
		System.arraycopy(encrypedKey, 0, array, 0, encrypedKey.length);
		System.arraycopy(encrypedString, 0, array, 16, encrypedString.length);
		
		return Base64.getEncoder().encodeToString(array);
	}
}