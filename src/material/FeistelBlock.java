package material;
import java.math.BigInteger;

public class FeistelBlock
{
	private byte[] _left;
	private byte[] _right;
	private byte[] _array;
	private int _start;
	
	
	public FeistelBlock(int start, byte[] array)
	{
		_start = start;
		_array = array;
		
		_left = new byte[Encryption.BLOCKSIZE/2];
		_right= new byte[Encryption.BLOCKSIZE/2];
		
		for(int i = 0; i < Encryption.BLOCKSIZE/2; i++)
		{
			
			if(start+i < array.length)
			{
				_left[i] = array[start+i];
			}
			if(start+Encryption.BLOCKSIZE/2+i < array.length)
			{
				_right[i] = array[start+Encryption.BLOCKSIZE/2+i];
			}
		}
	}
	
	
	private void swap()
	{
		byte[] temp = _left;
		_left = _right;
		_right = temp;
	}
	
	public void backIntoArray()
	{
		System.arraycopy(_left, 0, _array, _start, Encryption.BLOCKSIZE/2);
		System.arraycopy(_right, 0, _array, _start+Encryption.BLOCKSIZE/2, Encryption.BLOCKSIZE/2);
	}
	
	public void encrypt(byte[] sessionKey)
	{

		BigInteger key = new BigInteger(1, sessionKey);
		for(int i = 0; i < 12; i++)
		{
			BigInteger t = F(new BigInteger(1, _right), key);
			
			byte[] f = Util.toByteArray(t, Encryption.BLOCKSIZE/2);
			for(int k = 0; k < f.length; k++)
			{
				_left[k] = (byte) (_left[k] ^ f[k]);
			}
			swap();
		}
	}
	
	public void decrypt(byte[] sessionKey)
	{
		swap();
		encrypt(sessionKey);
		swap();
	}
	
	private BigInteger F(BigInteger R, BigInteger K)
	{
		BigInteger mod = new BigInteger("2").pow(Encryption.BLOCKSIZE*4).subtract(BigInteger.ONE);
		return R.multiply(R).add(K).mod(mod);
	}
	

	
}
