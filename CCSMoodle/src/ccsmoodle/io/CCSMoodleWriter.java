package ccsmoodle.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import ccsmoodle.model.Course;
import ccsmoodle.model.User;

public class CCSMoodleWriter {
	public static void FileWriter(HashMap<User, ArrayList<Course>> map, String filename) throws FileNotFoundException {
		PrintStream ps = new PrintStream(new File(filename));
		ps.println("username,firstname,lastname,email,password,course1,role1,course2,role2,course3,role3,course4,role4,course5,role5,course6,role6,course7,role7,course8,role8,course9,role9");
		Iterator<Entry<User, ArrayList<Course>>> entrySet = map.entrySet().iterator();
		while (entrySet.hasNext()) {
			Entry<User, ArrayList<Course>> curr = entrySet.next();
			User currUser = curr.getKey();
			ArrayList<Course> currList = curr.getValue();
			ps.print(currUser.getUser() +"," + currUser.getFirst() + "," + currUser.getLast() +"," + currUser.getEmail() + "," + currUser.getPassword() + ",");
			for (int i = 0; i < currList.size(); i++) {
				Course currCourse = currList.get(i);
				ps.print(currCourse.getName() + ",");
				if (currCourse.getTeacher().equals(currUser.getUser())) {
					ps.print("editingteacher" + ",");
				}
				else {
					ps.print("student" + ",");
				}
			}
			ps.println();
		}
	}
}
