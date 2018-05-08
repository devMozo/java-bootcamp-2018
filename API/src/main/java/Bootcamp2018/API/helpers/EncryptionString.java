package Bootcamp2018.API.helpers;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Represents my own encryptor
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class EncryptionString {
	/**
	 * Encrypt a String based in a key
	 * @param strClearText
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String strClearText) throws Exception{
		// By default the response is empty
		String strData = "";
		// Get the instance of an MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		// Update the message digest with the string's bytes
		md.update(strClearText.getBytes());
		// Make something
		byte[] digest = md.digest();
		// New StringBuffer to save each byte
		StringBuffer sb = new StringBuffer();
		// For each byte
		for (byte b : digest) {
			// Add to the string bugger
			sb.append(String.format("%02x", b & 0xff));
		}
		// Return the buffer as string
		return sb.toString();
	}
}
