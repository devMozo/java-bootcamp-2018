package bootcamp2018.User;

import java.util.Date;

/**
 * Represents a User
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class User {
	// User's ID
	private int id;
	// User's Name
	private String strName;
	// User's Birthday
	private Date dtBirthday;
	// User's Biography
	private String strBio;
	/**
	 * GETTERS AND SETTERS
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public Date getDtBirthday() {
		return dtBirthday;
	}
	public void setDtBirthday(Date dtBirthday) {
		this.dtBirthday = dtBirthday;
	}
	public String getStrBio() {
		return strBio;
	}
	public void setStrBio(String strBio) {
		this.strBio = strBio;
	}
}
