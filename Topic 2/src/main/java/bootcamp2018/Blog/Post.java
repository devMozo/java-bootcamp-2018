package bootcamp2018.Blog;

import java.util.ArrayList;

/**
 * Represents each post that will be added to the blog
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Post {
	// State..
	private String strTitle;
	private String strDescription;
	/**
	 * Set the title to the Post
	 * @param title
	 * @throws Exception
	 */
	public void setTitle(String title) throws Exception {
		// If the title is empty
		if(title == null || title.isEmpty()) {
			throw new Exception("You have inserted an empty title");
		// If not..
		} else {
			// Set the title
			this.strTitle = title;
		}
	}
	/**
	 * Set the description to the Post
	 * @param description
	 * @throws Exception
	 */
	public void setDescription(String description) throws Exception {
		// If the description is empty
		if(description == null || description.isEmpty()) {
			throw new Exception("You have inserted an empty description");
		// If not..
		} else {
			// Set the description
			this.strDescription = description;
		}
	}
	/**
	 * Get the Post's Title
	 * @return The title
	 */
	public String getStrTitle() {
		return this.strTitle;
	}
	/**
	 * Get the Post's Description
	 * @return The description
	 */
	public String getStrDescription() {
		return this.strDescription;
	}
}
