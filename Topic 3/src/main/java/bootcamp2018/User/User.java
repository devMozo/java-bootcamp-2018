package bootcamp2018.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Represents a User
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@ApiModel(description = "Represents a User that will handle the platform")
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
	@ApiModelProperty(value = "Get the ID of the User", dataType = "Int", allowableValues = "A Unique ID")
	public int getId() {
		return id;
	}
	@ApiModelProperty(value = "Set a new ID", dataType = "Int", allowableValues = "Unique ID")
	public void setId(int id) {
		this.id = id;
	}
	@ApiModelProperty(value = "Get the name of the User", dataType = "String", allowableValues = "An String with a size of less than 20 characters")
	public String getStrName() { return strName; }
	@ApiModelProperty(value = "Set the name of the User", dataType = "String", allowableValues = "An String with a size of less than 20 characters")
	public void setStrName(String strName) {
		this.strName = strName;
	}
	@ApiModelProperty(value = "Get the birthday of the User", dataType = "Date", allowableValues = "A Date")
	public Date getDtBirthday() {
		return dtBirthday;
	}
	@ApiModelProperty(value = "Set the birthday of the User", dataType = "Date", allowableValues = "A Date")
	public void setDtBirthday(Date dtBirthday) {
		this.dtBirthday = dtBirthday;
	}
	@ApiModelProperty(value = "Get the Bio of the User", dataType = "String", allowableValues = "A description text")
	public String getStrBio() {
		return strBio;
	}
	@ApiModelProperty(value = "Set the Bio of the User", dataType = "String", allowableValues = "A description text")
	public void setStrBio(String strBio) {
		this.strBio = strBio;
	}
}
