package bootcamp2018.Blog;

import org.junit.Assert;
import org.junit.Test;
/**
 * Represents the testing of the posts
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class TestPost {
	/**
	 * If the user create a Post's Title with an
	 * empty string must return false
	 */
	@Test(expected = Exception.class)
	public void givenATitleWhenIsEmptyThenThrowException() throws Exception {
		// Create a new Post
		Post oPost = new Post();
		// Set the empty title
		oPost.setTitle("");
	}
	/**
	 * If the user create a Post's Title with a
	 * null string must return false
	 */
	@Test(expected = Exception.class)
	public void givenATitleWhenIsNullThenThrowException() throws Exception {
		// Create a new Post
		Post oPost = new Post();
		// Set the empty title
		oPost.setTitle(null);
	}
	/**
	 * If the user create a Post's Description with an
	 * empty string must return false
	 */
	@Test(expected = Exception.class)
	public void givenADescriptionWhenIsEmptyThenThrowException() throws Exception {
		// Create a new Post
		Post oPost = new Post();
		// Set the empty title
		oPost.setDescription("");
	}
	/**
	 * If the user create a Post's Description with a
	 * null string must return false
	 */
	@Test(expected = Exception.class)
	public void givenADescriptionWhenIsNullThenThrowException() throws Exception {
		// Create a new Post
		Post oPost = new Post();
		// Set the empty title
		oPost.setDescription(null);
	}
}
