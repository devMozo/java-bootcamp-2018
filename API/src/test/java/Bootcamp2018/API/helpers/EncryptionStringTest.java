package Bootcamp2018.API.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents the test to the EncryptionSpring's Helper
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class EncryptionStringTest {

	private final String strToEncrypt = new String("1");

	@Before
	public void init(){
	}

	@Test
	public void whenIntanceClass() throws Exception {
		Assert.assertTrue(new EncryptionString() instanceof EncryptionString);
	}
	/**
	 * When pass a string check that the returned string will be correct
	 * @throws Exception
	 */
	@Test
	public void givenAStringWhenEncryptReturnConvertedToMD5() throws Exception {
		Assert.assertEquals("c4ca4238a0b923820dcc509a6f75849b", EncryptionString.encrypt(this.strToEncrypt));
	}
	/**
	 * When pass an invalid string check that the returned string will be empty
	 * @throws Exception
	 */
	@Test
	public  void givenAnInvalidStringWhenEncryptItReturnNull() throws Exception {
		Assert.assertEquals("", EncryptionString.encrypt(null));
	}
}
