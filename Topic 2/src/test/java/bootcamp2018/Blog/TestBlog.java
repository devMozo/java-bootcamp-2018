package bootcamp2018.Blog;

import org.junit.Assert;
import org.junit.Test;
/**
 * Represents the testing of the blog
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class TestBlog {
	/**
	 * If the user create a post with a null value
	 * and try to insert it inside the blog
	 * throw an exepction
	 */
	@Test(expected = Exception.class)
	public void givenAPostWhenIsNullThenThrowAnException() throws Exception {
		// Null Post
		Post oPost = null;
		// New Blog
		Blog oBlog = new Blog();
		// Try to insert the post
		oBlog.insertPost(oPost);
	}
	/**
	 * If the user create a post with its values
	 * as empty and try to insert it inside the blog
	 * throw an exepction
	 */
	@Test(expected = Exception.class)
	public void givenAPostWhenItsValuesAreEmtpyThenThrowAnExeption() throws Exception {
		// New Post
		Post oPost = new Post();
		// New Blog
		Blog oBlog = new Blog();
		// Try to insert the post
		oBlog.insertPost(oPost);
	}
	/**
	 * Check that the array is
	 * correctly initialized
	 */
	@Test
	public void whenGetTheArray(){
		// New Blog
		Blog oBlog = new Blog();
		// Get the array
		Assert.assertNotEquals(null, oBlog.getAllPosts());
	}
	/**
	 * If the user want to delete a post
	 * we need to make some proofs..
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void whenDeleteAPost() throws IndexOutOfBoundsException{
		// New Blog
		Blog oBlog = new Blog();
		// Create a new Post
		Post oPost = new Post();
		// Try to add its attributes
		try {
			// Set the title
			oPost.setTitle("A title");
			// Set the description
			oPost.setDescription("A Description");
			// Add several time the same post to the blog
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
		// Catch every exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Delete the post from the blog
		oBlog.deletePostByIndex(2);
		// Check that the array of posts has an element less
		Assert.assertEquals(3, oBlog.getAllPosts().size());
		// Delete an incorrect post from the blog
		oBlog.deletePostByIndex(10);
	}
	/**
	 * When the user want the recent posts..
	 */
	@Test
	public void whenGetTheRecentPost(){
		// New Blog
		Blog oBlog = new Blog();
		// Create a new Post
		Post oPost = new Post();
		// Try to add its attributes
		try {
			// Set the title
			oPost.setTitle("A title");
			// Set the description
			oPost.setDescription("A Description");
			// Add several time the same post to the blog
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			oBlog.insertPost(oPost);
			// Check that the array is not null
			Assert.assertNotEquals(null, oBlog.getRecentPosts());
			// Check the returned array
			Assert.assertTrue(oBlog.getRecentPosts().size() > 0 && oBlog.getRecentPosts().size() <= 10);
		// Catch every exception
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
