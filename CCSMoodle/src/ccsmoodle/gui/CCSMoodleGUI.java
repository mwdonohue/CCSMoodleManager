package ccsmoodle.gui;

import ccsmoodle.manager.CCSMoodleManager;
import ccsmoodle.model.InputFileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CCSMoodleGUI {
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the CCS Moodle User Conversion Software!");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println("Make sure you have placed your input.csv file and word-list.txt in the input directory.");
		System.out.println(
				"If you do not have a user-pass.csv file in the input directory, one will be generated for you.");
		System.out.println("The converted file will appear in the output directory");
		Scanner inputReader = new Scanner(System.in);
		System.out.println();
		System.out.print("Enter the name of the file to convert: ");
		String inputFileName = inputReader.next();

		boolean flag = false;
		while (flag == false) {
			try {
				CCSMoodleManager.exportList("input/" + inputFileName, "output/converted.csv");
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InputFileNotFoundException e) {
				System.out.print("Input file could not be found... Try again: ");
				inputFileName = inputReader.next();
			}
		}
		System.out.println("Done!");
		inputReader.close();
	}
}
