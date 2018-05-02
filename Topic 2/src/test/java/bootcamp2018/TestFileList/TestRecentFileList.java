package bootcamp2018.TestFileList;

import bootcamp2018.FileList.RecentFileList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Represents the testing of the list with recented viewed files
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class TestRecentFileList {
	/**
	 * Test if the stack isn't null
	 */
	@Test
	public void whenIntanceClassStackMustNotBeNull(){
		// Create a new instance
		RecentFileList oRecentFileList = new RecentFileList();
		// Check if isn't null
		Assert.assertNotNull(oRecentFileList.getOStackRecentFile());
	}
	/**
	 * Test if the stack has 0 elements in the beginning
	 */
	@Test
	public void whenIntanceClassStackMustBeEmpty(){
		// Create a new instance
		RecentFileList oRecentFileList = new RecentFileList();
		// Get the dimmension of the stack
		int iSizeRecentFileList = oRecentFileList.getOStackRecentFile().size();
		// Check if is OK
		Assert.assertEquals(0, iSizeRecentFileList);
	}
	/**
	 * Test if the new openned file is added at the top of the stack
	 */
	@Test
	public void whenAddAFileThenAddItToTheBeginningOfTheStack(){
		// Create a new instance
		RecentFileList oRecentFileList = new RecentFileList();
		// Add a file
		oRecentFileList.openFile("../algo.jpg");
		// Get the added file's string
		String strLastRecentFileList = (String) oRecentFileList.getOStackRecentFile().get(0);
		// Check if is OK
		Assert.assertEquals("../algo.jpg", strLastRecentFileList);
	}
	/**
	 * Test if the new openned file is added at the top of the stack
	 * and if the file exists before, we move it to the top
	 */
	@Test
	public void whenAddAnExistedFileThenMoveItToTheTopOfTheStack(){
		// Create a new instance
		RecentFileList oRecentFileList = new RecentFileList();
		// Add a file
		oRecentFileList.openFile("../algo.jpg");
		oRecentFileList.openFile("../algo2.jpg");
		oRecentFileList.openFile("../algo.jpg");
		// Get the added Stack
		ArrayList<String> arrRecentFileList = oRecentFileList.getOStackRecentFile();
		// Check that the top file should be the last inserted
		Assert.assertEquals("../algo.jpg", (String) arrRecentFileList.get(0));
		// And also check that there is no one equal to the other
		Assert.assertEquals(2, arrRecentFileList.size());
		// Check if the array has the correct element
		Assert.assertArrayEquals(new String[]{"../algo.jpg", "../algo2.jpg"}, arrRecentFileList.toArray());
	}
	/**
	 * Test if we add more than 15 elements,
	 * we need to remove the oldest
	 * and maintain the stack in 15 elements
	 */
	@Test
	public void whenAddMoreThan15ElementsThenRemoveTheOldestAndKeepTheStackIn15Elements(){
		// Create a new instance
		RecentFileList oRecentFileList = new RecentFileList();
		// Open 16 files
		for (int i = 0; i <= 16; i++) {
			String strFile = "../algo".concat(String.valueOf(i)).concat(".jpg");
			// Add a file
			oRecentFileList.openFile(strFile);
		}
		// Get the added Stack
		ArrayList<String> arrRecentFileList = oRecentFileList.getOStackRecentFile();
		// Check that the top file should be the last inserted
		Assert.assertEquals("../algo16.jpg", (String) arrRecentFileList.get(0));
		// And also check that the stack has 15 elements (this is the maximum)
		Assert.assertEquals(15, arrRecentFileList.size());
		// Check that the last position isn't "../algo0.jpg"
		Assert.assertNotEquals( "../algo0.jpg", arrRecentFileList.get(arrRecentFileList.size() - 1));
	}
}
