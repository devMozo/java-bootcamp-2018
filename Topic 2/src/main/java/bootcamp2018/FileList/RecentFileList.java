package bootcamp2018.FileList;

import java.util.ArrayList;
/**
 * Represents the list with recented viewed files
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class RecentFileList {
	// Stack that will contains each files
	private ArrayList<String> arrlRecentFile = new ArrayList();
	// Maximum saved files
	private final int MAXIMUM_FILES = 15;
	/**
	 * Constructo of the class
	 * @return
	 */
	public ArrayList getOStackRecentFile() {
		return this.arrlRecentFile;
	}
	/**
	 * Open a file
	 * @param strFile
	 */
	public void openFile(String strFile) {
		/**
		 * Do something with the file..
		 */
		// Remove the element if exists
		this.arrlRecentFile.remove(strFile);
		// If there is more files thant the allowable
		if(this.arrlRecentFile.size() >= this.MAXIMUM_FILES){
			// Remove the last element
			this.arrlRecentFile.remove(this.MAXIMUM_FILES - 1);
		}
		// Push the element to the stack
		this.arrlRecentFile.add(0, strFile);
	}
}
