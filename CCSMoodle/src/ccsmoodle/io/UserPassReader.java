package ccsmoodle.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads username and password information from the user-pass.csv file
 * 
 * @author mwdonohue
 *
 */
public class UserPassReader {
	/**
	 * Static method for reading credentials
	 * 
	 * @return HashMap of the credentials from the user-pass file
	 * @throws FileNotFoundException if the file could not be created
	 */
	public static HashMap<String, String> FileReader() throws FileNotFoundException {
		File f = new File("input/user-pass.csv");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("user-pass.csv file could not be created");
			}
		}
		Scanner lineScanner = new Scanner(f);
		HashMap<String, String> map = new HashMap<String, String>();
		if (lineScanner.hasNextLine()) {
			lineScanner.nextLine();
		}
		Scanner dataScanner;
		while (lineScanner.hasNextLine()) {
			String curr = lineScanner.nextLine();
			dataScanner = new Scanner(curr);
			dataScanner.useDelimiter(",");
			String username = dataScanner.next();
			String password = dataScanner.next();
			map.put(username, password);
		}
		lineScanner.close();
		return map;
	}
}
