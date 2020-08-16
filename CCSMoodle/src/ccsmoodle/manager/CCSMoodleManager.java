package ccsmoodle.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ccsmoodle.io.CCSMoodleReader;
import ccsmoodle.io.CCSMoodleWriter;
import ccsmoodle.model.Course;
import ccsmoodle.model.InputFileNotFoundException;
import ccsmoodle.model.User;

public class CCSMoodleManager {
	public static void exportList(String fileName, String outputfilename) throws IOException, InputFileNotFoundException {
		HashMap<User, ArrayList<Course>> map = CCSMoodleReader.FileReader(fileName);
		CCSMoodleWriter.FileWriter(map, outputfilename);
	}
}
