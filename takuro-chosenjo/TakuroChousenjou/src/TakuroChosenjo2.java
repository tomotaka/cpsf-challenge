import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class TakuroChosenjo2 {
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String data = "0001010111100111010111110110001100111010011010101011010101100011101100101110101110011001110001000101000111101001100111001010001111110100100010001100100111100000000111011100010100001011110000000100011100010100011111100010111111110000101110001101001010111001";
		String keyStr = "thisistestkey123";
		byte[] keyBytes = keyStr.getBytes();
		
		byte[] dataBytes = BitParser.decode(data);
		
		byte[] decryptedData = AESCipher.decrypt(dataBytes, keyBytes);
		String decryptedStr = new String(decryptedData);
		System.out.println(decryptedStr);
	}

}
