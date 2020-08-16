package ccsmoodle.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import ccsmoodle.model.Course;
import ccsmoodle.model.InputFileNotFoundException;
import ccsmoodle.model.User;

public class CCSMoodleReader {
	public static HashMap<User, ArrayList<Course>> FileReader(String fileName)
			throws IOException, InputFileNotFoundException {
		File f = new File(fileName);
		if (!f.exists()) {
			throw new InputFileNotFoundException();
		}
		Scanner lineScanner = new Scanner(f);
		HashMap<User, ArrayList<Course>> map = new HashMap<User, ArrayList<Course>>();
		lineScanner.nextLine();
		Scanner dataScanner;
		while (lineScanner.hasNextLine()) {
			String curr = lineScanner.nextLine();
			dataScanner = new Scanner(curr);
			dataScanner.useDelimiter(",");
			String className = dataScanner.next();
			String firstName = dataScanner.next();
			String lastName = dataScanner.next();
			int teacher = dataScanner.nextInt();

			String sClassName = className.strip();
			String sFirstName = firstName.strip();
			String sLastName = lastName.strip();

			String userName = userCreator(sFirstName, sLastName, map);
			String email = userName + "@ccsmb.com";
			String password = passwordCreator();

			HashMap<String, String> userPassMap = UserPassReader.FileReader();
			if (userPassMap.get(userName) != null) {
				password = userPassMap.get(userName);
			} else {
				PrintWriter out = new PrintWriter(new FileWriter("input/user-pass.csv", true));
				out.append(userName + "," + password + "\n");
				out.close();
			}
			User user = new User(sFirstName, sLastName, userName, email, teacher, password);
			Course current = new Course(sClassName, "");
			if (teacher == 1) {
				current.setTeacher(userName);
			}
			if (!map.containsKey(user)) {
				ArrayList<Course> userCourseList = new ArrayList<Course>();
				userCourseList.add(current);
				map.put(user, userCourseList);
			} else {
				ArrayList<Course> userCourseList = map.get(user);
				userCourseList.add(current);
			}
		}
		lineScanner.close();
		return map;
	}

	public static String userCreator(String first, String last, HashMap<User, ArrayList<Course>> map) {
		StringBuilder sb = new StringBuilder();
		sb.append(first.substring(0, 1));
		sb.append(last);
		String returnString = sb.toString().toLowerCase();
		while (returnString.contains("\'")) {
			int index = returnString.indexOf("\'");
			sb.deleteCharAt(index);
			returnString = sb.toString().toLowerCase();
		}
		Iterator<User> users = map.keySet().iterator();
		while (users.hasNext()) {
			User current = users.next();
			if (current.getUser().equals(returnString)
					&& (!current.getFirst().equals(first) || !current.getLast().equals(last))) {
				char lastChar = returnString.substring(returnString.length() - 1).toCharArray()[0];
				if (Character.isDigit(lastChar)) {
					int characterDigit = Integer.parseInt(Character.toString(lastChar));
					characterDigit++;
					returnString = returnString.substring(0, returnString.length() - 1) + characterDigit;
				} else {
					returnString = returnString.substring(0, returnString.length()) + 1;
				}
			}
		}
		return returnString;
	}

	public static String passwordCreator() throws FileNotFoundException {
		Random randInt = new Random();
		File f = new File("input/word-list.txt");
		Scanner s = new Scanner(f);
		HashMap<Integer, String> passMap = new HashMap<Integer, String>();
		Integer x = 1;
		while (s.hasNext()) {
			String curr = s.next();
			passMap.put(x, curr);
			x++;
		}
		
		s.close();
		String word1 = passMap.get(randInt.nextInt(passMap.size() - 1) + 1);
		String word2 = passMap.get(randInt.nextInt(passMap.size() - 1) + 1);
		int number = randInt.nextInt(89) + 10;
		StringBuilder sb = new StringBuilder();
		sb.append(word1);
		sb.append("-");
		sb.append(word2.toUpperCase());
		sb.append("-");
		sb.append(number);
		return sb.toString();
	}
}
