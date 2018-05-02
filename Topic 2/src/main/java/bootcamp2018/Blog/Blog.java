package bootcamp2018.Blog;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a common blog
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Blog {
	// Array that will contain the posts
	private ArrayList<Post> arrPosts = new ArrayList();
	/**
	 * Insert a new Post to the Blog
	 * @param oPost
	 * @throws Exception
	 */
	public void insertPost(Post oPost) throws Exception {
		// If the post is null or has an empty field
		if(oPost == null || oPost.getStrDescription().isEmpty() || oPost.getStrTitle().isEmpty()){
			throw new Exception("Null post or has empty fields");
		// If not..
		} else {
			// Add the post
			this.arrPosts.add(oPost);
		}
	}
	/**
	 * Delete a post that is inside the array
	 * @param i
	 */
	public void deletePostByIndex(int i) throws IndexOutOfBoundsException {
		// If the index position is bigger than the size of the array
		if(i > this.arrPosts.size() - 1){
			// Throw an exception
			throw new IndexOutOfBoundsException("You want to delete a post out of the range..");
		// If not..
		} else {
			// Delete the post by index
			this.arrPosts.remove(i);
		}
	}
	/**
	 * Get the recent posts
	 * @return the first 10 elements of the array
	 */
	public List getRecentPosts() {
		return this.arrPosts.subList(0, 10);
	}
	/**
	 * Get all posts..
	 * @return the complete array
	 */
	public ArrayList getAllPosts() {
		return this.arrPosts;
	}
}
