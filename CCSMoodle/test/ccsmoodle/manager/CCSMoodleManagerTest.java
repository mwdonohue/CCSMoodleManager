package ccsmoodle.manager;

import java.io.IOException;

import org.junit.Test;

import ccsmoodle.model.InputFileNotFoundException;

public class CCSMoodleManagerTest {
	
	// Testing is done by visual inspection
	@Test
	public void test() throws IOException, InputFileNotFoundException {
		CCSMoodleManager.exportList("input/class-contents.csv", "output/converted.csv");
	}

}
