package CW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// PLEASE NOTE : SCANNERS IN THE SELECTION METHODS ARE NOT CLOSED BECAUSE THEY CAUSE "NoSuchElementException" OTHERWISE.
// After a lot of research this seems to be a bug with the reason being that closing a scanner in a do while will close
// System.in as well (with no way to reopen) so the scanners are left open to prevent this problem.
// Original Link: https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

/** This is the UniClient Class. 
 * 	<p><b>Synopsis</b> : The goal of the UniClient class is to allow the user to 
 *  easily create, save and alter information about students at a university.</p>
 *  <p><b>Summary</b> : This is essentially a runner class that allows the user to 
 *  interact with all the classes made to create and store information about 
 *  students. It ensures that all entries are formatted correctly before they are 
 *  saved. It is also responsible for writing everything to the files 
 *  SmartCardRecords and StudentRecords. This class achieves this using Scanner and
 *  the previously established hierarchy.</p>
 *  @author Raven Duffy B7044359
 */
public class UniClient {
	// ADD CORRECT ENTRY CHECK
	
	private static int choice; // THIS DECIDES WHAT amendStudentData WILL CHANGE
	private static int StudentIDLength = 5; // This is a constant that holds the
											// length any studentID should have
	
	// These constants are the files that will be used to read from and write to.
	private static File SmartCardRecords = new File("SmartCardRecords");
	private static File StudentRecords = new File("StudentRecords");
	
	// This initialises a new ArrayList of base type Students 
	private static ArrayList<Students> StudentObjects = new ArrayList<Students>(); // will hold the "Students" objects
	
	// This will run the file check method, runTitle method and runOptions methods.
	public static void main(String[] args) throws IOException {
		checkFiles();
		
		runTitle();
		runOptions();
	}
	
	/** The proceed method is a method that allows the program to waits to move on
	 *  to the next part until the user enters 1
	 */
	private static void proceed() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in); // New userinput scanner is created
		
		System.out.print("\n\nWhen you would like to proceed enter 1: "); // prompt
		
		// *all user input sections will use a variation on this logic and only the
		// differences will be explained
		String userInput; // This will be used to take user Input
		boolean number = false; // this will be used to end the loop
		do { // do while to allow the userInput to be defined
			userInput = in.nextLine(); // userInput defined
			try { // try catch to make sure the user is entering a number
				int userChoice = Integer.parseInt(userInput);
				number = true; // number is set to true
				if (userChoice != 1) { // if the number isn't 1 then number is false
					System.out.print("Please enter 1 to proceed: "); // prompt
					number = false; // number is set to false
				}
			} catch (NumberFormatException e) { // If the userInput was not a number
				System.out.print("Please enter 1 to proceed: "); // prompt
				number = false; // number is set to false
			}
		} while(number == false); // loops while number is false
	}
	
	/** This method runs the "title" of sorts where the user is asked if they want
	 *  an introduction to the client or if they would like to skip to the next 
	 *  section
	 */
	private static void runTitle() {
		System.out.println("Student Data Manager\n"); // Prints out the section
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("If you would like to see a summary of this program please press 1 and then enter.\n"
				+ "Otherwise please press 2 then enter: ");
		
		// as mentioned before this code is the same as the code in proceed however,
		// in this case there are two possible inputs, 1 prints out the description
		// and they runs proceed, 2 skips to the next section
		String userInput;
		boolean number = false;
		do {
			userInput = in.nextLine();
			try {
				int userChoice = Integer.parseInt(userInput);
				if (userChoice == 1) {
					System.out.println("This program allows for easy organisation of student data.\nIt will prompt the user"
							+ " to create, change or delete certain types of students.\nThese student types are: Undergraduate, "
							+ "Post Graduate Taught and Post Graduate Research.\nWhen asking the user what to select the program"
							+ " will present the options in a list\nformat. i.e\n\t1. Choice One\n\t2. Choice Two\nPlease select"
							+ " the number to the left of the choice to tell the program what you are\nselecting.\nThis is the way"
							+ " the program will work the majority of the time, however occasionally the user will\nbe prompted to"
							+ " enter text, in these cases the program will format your entry automatically.\nFinally all the"
							+ " entries will be saved between two text documents named \"StudentRecords\" and \"SmartCardRecords\"\n"
							+ "for future reference.");
				}
				number = true;
				if (userChoice != 1 && userChoice != 2) {
					System.out.print("Please enter either 1 or 2: ");
					number = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Please enter either 1 or 2: ");
				number = false;
			}
		} while(number == false);
		
		if ((userInput).equals("1")) {
			proceed();
		}
	}
	
	/** This method opens a loop that will constantly run options that affect the 
	 *  student information saved until the fifth option (to exit) is selected. The
	 *  user may enter numbers between 1 and 5 otherwise the program will prompt
	 *  them to re-enter.
	 *  @throws IOException
	 */
	private static void runOptions() throws IOException {
		refreshStudentObjects(); // runs refreshStudentObjects to make sure
								 // everything is up to date
		
		boolean exit = false; // this will be used as the exit condition

		while (exit == false) { // this loop runs until the exit condition is true
			// this selection method is the same as the one in proceed but there are
			// five options now instead of just 1
			
			System.out.println("\nThe program will now display your options: ");

			System.out.println("1. Number of Students"
					+ "\n   This will return the number of students of a given type or the total number"
					+ "\n\n2. Register New Student"
					+ "\n   This will all the user to enter a new student into the database"
					+ "\n\n3. Amend Student Data"
					+ "\n   This will allow the user to change a previously entered student"
					+ "\n\n4. Terminate Student"
					+ "\n   This will allow the user to remove a certain student from the database"
					+ "\n\n5. Exit"
					+ "\n   This will close the program");

			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			System.out.print("Please enter the number corresponding to the choice you would like: ");

			String userInput;
			boolean number = false;
			do {
				userInput = in.nextLine();
				try { 
					// if the user enters a valid number that is between 1 and 5 the
					// program selects the method that relates to their choice
					int userChoice = Integer.parseInt(userInput);
					if (userChoice == 1) {
						System.out.println("\nYou have selected the number of students.");
						noOfStudentsSelection(); // This runs noOfStudentsSelection
					}
					else if (userChoice == 2) {
						System.out.println("\nYou have selected to register a new student.");
						registerStudentSelection(); // This runs registerStudentSelection
					}
					else if (userChoice == 3) {
						System.out.println("\nYou have selected to amend a student's data.");
						amendStudentDataSelection(); // This runs amendStudentDataSelection
					}
					else if (userChoice == 4) {
						System.out.println("\nYou have selected to terminate a student.");
						// This runs terminateStudent depending on the result from
						// terminateStudent Selection
						terminateStudent(terminateStudentSelection()); 
					}
					else if (userChoice == 5) {
						System.out.println("Ending program");
						exit = true; // this sets the exit condition to true
					}
					
					// if the user entry is a number but isn't between 1 and 5 then
					// it prompts the user to enter again
					number = true;
					if (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4 && userChoice != 5) {
						System.out.print("Please enter either 1, 2, 3, 4 or 5: ");
						number = false;
					}
				// if the user's entry is not a number it prompts the user to enter
				// again
				} catch (NumberFormatException e) {
					System.out.print("Please enter either 1, 2, 3, 4 or 5: ");
					number = false;
				}
			} while(number == false);

			refreshStudentObjects(); // runs refreshStudentObjects

		}
	}
	
	/** This method prompts the user to select between the four options. Each one of
	 *  these options will display a number of students in the school; either the 
	 *  number of undergrads, the number of postgradTs, the number of postGradRs or 
	 *  the total number of students in the school.
	 *  @throws FileNotFoundException
	 */
	private static void noOfStudentsSelection() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("There are four options to display the number of students:");
		
		System.out.println("1. Undergraduates"
				+ "\n   This will return the number of undergraduate students currently in the system"
				+ "\n\n2. Post Graduates Taught"
				+ "\n   This will return the number of post graduate taught students currently in the system"
				+ "\n\n3. Post Graduates Research"
				+ "\n   This will return the number of post graduate research students currently in the system"
				+ "\n\n4. All Students"
				+ "\n   This will return the total number of students currently in the system");
		
		// This selection is the same as proceed but more like the previous runOptions
		// method, the only difference is that there is 1 less option and each option
		// does something different.
		String userInput;
		boolean number = false;
		do {
			userInput = in.nextLine();
			try {
				int userChoice = Integer.parseInt(userInput);
				 // this outputs the result of noOfStudents("1") with text around it
				if (userChoice == 1) {
					System.out.println("There is/are currently " + noOfStudents("1") + " undergraduate student(s) recorded in the system");
				}
				// this outputs the result of noOfStudents("2") with text around it
				else if (userChoice == 2) {
					System.out.println("There is/are currently " + noOfStudents("2") + " post graduate taught student(s) recorded in the system");
				}
				// this outputs the result of noOfStudents("3") with text around it
				else if (userChoice == 3) {
					System.out.println("There is/are currently " + noOfStudents("3") + " post graduate research student(s) recorded in the system");
				}
				// this outputs the result of noOfStudents("4") with text around it
				else if (userChoice == 4) {
					System.out.println("There is/are currently " + noOfStudents("4") + " student(s) recorded in the system");
				}
				
				number = true;
				if (userChoice != 1 && userChoice != 2 && userChoice != 3 & userChoice != 4) {
					System.out.print("Please enter either 1, 2, 3 or 4: ");
					number = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Please enter either 1, 2, 3 or 4: ");
				number = false;
			}
		} while(number == false);
	}
	
	/** This method will prompt the user to choose what type of student they wish to
	 *  register into the system. This allows a user to select to input for either:
	 *  an undergrad, a postgradT or a postgradR
	 *  @throws FileNotFoundException
	 *  @throws IOException
	 */
	private static void registerStudentSelection() throws FileNotFoundException, IOException {
		System.out.println("\nThe program will now display your options: ");
		
		System.out.println("1. Register Undergraduate"
				+ "\n   This will allow the user to register an undergraduate student"
				+ "\n\n2. Register Post Graduate Taught"
				+ "\n   This will allow the user to register a post graduate taught student"
				+ "\n\n3. Register Post Graduate Research"
				+ "\n   This will allow the user to register a post graduate research student");
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the number corresponding to the choice you would like: ");
		
		// This selection is the same as proceed but more like the previous 
		// noOfStudentsSelection method, the only difference is that there is 1 less
		// option and each option does something different.
		String userInput;
		boolean number = false;
		do {
			userInput = in.nextLine();
			try {
				int userChoice = Integer.parseInt(userInput);
				// if the user chooses 1 this will register a new student with the
				// result of registerUndergrad
				if (userChoice == 1) {
					registerStudent(registerUndergrad());
				}
				// if the user chooses 1 this will register a new student with the
				// result of registerPostgradT
				else if (userChoice == 2) {
					registerStudent(registerPostgradT());
				}
				// if the user chooses 1 this will register a new student with the
				// result of registerPostgradR
				else if (userChoice == 3) {
					registerStudent(registerPostgradR());
				}
				number = true;
				if (userChoice != 1 && userChoice != 2 && userChoice != 3) {
					System.out.print("Please enter either 1, 2 or 3: ");
					number = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Please enter either 1, 2 or 3: ");
				number = false;
			}
		} while(number == false);
	}
	
	/** This method will prompt the user to select 1 of 4 options. These options
	 *  will be used to change the information about a student. The changes the user
	 *  can make is to change the name, the birth date or to add or remove modules.
	 *  @throws IOException
	 */
	private static void amendStudentDataSelection() throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("There are four options to amend student's data:");
		
		System.out.println("1. Change the student's name"
				+ "\n   This change the student's name to the user's entry"
				+ "\n\n2. Change Birthdate"
				+ "\n   This will change the student's birthdate to the user's entry"
				+ "\n\n3. Add Module"
				+ "\n   This will allow the user to add a module to the student's current modules"
				+ "\n\n4. Remove Module"
				+ "\n   This will allow the user to remove a module from the student's current modules");
		
		// This selection is the same as proceed but more like the previous runOptions
		// method, the only difference each option does something different.
		String userInput;
		boolean number = false;
		do {
			userInput = in.nextLine();
			try {
				int userChoice = Integer.parseInt(userInput);
				System.out.println("Here");
				// if userChoice is 1 then amendStudentData is run with the result
				// of terminateStudentSelection (to get studentID) and nameCheck
				if (userChoice == 1) {
					choice = 1; // sets choice to 1
					amendStudentData(terminateStudentSelection(), nameCheck());
				}
				// if userChoice is 2 then amendStudentData is run with the result
				// of terminateStudentSelection (to get studentID) and DOBCheck
				else if (userChoice == 2) {
					choice = 2; // sets choice to 2
					amendStudentData(terminateStudentSelection(), DOBCheck());
				}
				// if userChoice is 3 then amendStudentData is run with the result
				// of terminateStudentSelection (to get studentID) and 
				// checkModuleString
				else if (userChoice == 3) {
					choice = 3; // sets choice to 3
					amendStudentData(terminateStudentSelection(), checkModuleString());
				}
				// if userChoice is 4 then amendStudentData is run with the result
				// of studentID and moduleRemoveCheck with studentID
				
				// studentID is saved here to prevent the user having to enter info
				// twice
				else if (userChoice == 4) {
					choice = 4; // sets choice to 4
					String studentID = terminateStudentSelection();
					amendStudentData(studentID, moduleRemoveCheck(studentID));
				}
				
				number = true;
				if (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4) {
					System.out.print("Please enter either 1, 2, 3 or 4: ");
					number = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Please enter either 1, 2, 3 or 4: ");
				number = false;
			}
		} while(number == false);
	}

	/** This method is used to allow a user to easily select a student based on
	 *  their ID. This is achieved by displaying all the current IDs and asking the
	 *  user to enter one of the IDs they see on screen (probably the one they want 
	 *  to edit)
	 *  @return This returns the userInput as a String if it's equal to a valid ID
	 *  @throws IOException
	 */
	private static String terminateStudentSelection() throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter the student's studentID exactly how it appears on screen: ");
		
		// This is the same kind of idea as the previous choice sections however,
		// this is not based on the user entering a number, instead it checks to see
		// if the user enters an ID that actually exists. This is done by checking
		// the StudentObjects ArrayList to see if their entry exists.
		String userInput = "";
		boolean inArray = false;
		if (!StudentObjects.isEmpty()) { // if the list of students isn't empty
			System.out.println("These are the available students:\n");
			
			// This outputs all the studentID's for every student currently in the system
			for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) {
				System.out.println(StudentObjects.get(currentStudent).getStudentID());
			}
			
			// Choice loop here to check to see if the ID entered is actually in the
			// ArrayList StudentObjects
			do {
				userInput = in.nextLine();
				for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) {
					if (StudentObjects.get(currentStudent).getStudentID().equals(userInput)) {
						inArray = true; // if the ID does exists the exit condition is true
						break; // break out of the for loop (there are no duplicates)
					}
					// if the entry is not in the ArrayList the user is re-prompted
					else 
						System.out.print("Please enter exactly the studentID: ");
				}
			} while(inArray == false);
		} else { // If the array is empty then there are no students and this ends
			System.out.println("There are no students");
		}
		
		return userInput;
	}
	
	/** This method outputs all the modules a student is taking and prompts the user
	 *  to enter the number of the module they would like to remove from the list.
	 *  @param studentID : This is a String that contains a student ID
	 *  @return The user input as a String if its equal to a number and is one of
	 *          the options
	 */
	private static String moduleRemoveCheck(String studentID) {
		int moduleNum = 0; // this holds the number to be displayed next to each module
		
		// this loop runs through the StudentObjects List until it finds a student
		// with a matching ID when it does it outputs all the modules that student
		// is taking with an increasing moduleNum next to it. After that the outer
		// loop breaks and the program moves on.
		for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) {
			if (StudentObjects.get(currentStudent).getStudentID().equals(studentID)) {
				for (int currentModule = 0; currentModule < StudentObjects.get(currentStudent).getModules().size(); currentModule++) {
					moduleNum++;
					System.out.println(moduleNum + ". " + StudentObjects.get(currentStudent).getModules().get(currentModule).toString());
				}
				break;
			}
		}
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the number corresponding to the choice you would like: ");
		
		// This is another choice loop, this one is fairly simple, it only ends the
		// loop when the user input is a number between the moduleNum - 1 and 0
		String userInput;
		boolean number = false;
		do {
			userInput = in.nextLine();
			try { // if the input is a number
				int userChoice = Integer.parseInt(userInput);
				number = true;
				// if the number is out of bounds then the the user is re-prompted
				if (userChoice > moduleNum || userChoice <= 0) { 
					System.out.print("Please enter one of the valid options: ");
					number = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Please enter one of the valid options: ");
				number = false;
			}
		} while(number == false);
		
		return userInput;
	}
	
	/** This method makes sure the name the user enters is actually only the first
	 *  and last names by splitting the names at the spaces between names and saving
	 *  the result into a String array. The size of that array is checked to make
	 *  sure only 2 names have been entered
	 *  @return The user input as a String if it is equal to only two names
	 */
	private static String nameCheck() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter the student's FIRST and LAST name ONLY: ");
		
		// this loop keep running until the user's input is converted into only two
		// separate words.
		String userInput;
		boolean twoWords = false;
		do {
			userInput = in.nextLine();
			String[] namesArray = userInput.split(" ");
			twoWords = true;
			if (namesArray.length > 2 || namesArray.length < 2) {
				System.out.print("Please enter ONLY your first and last name: ");
				twoWords = false;
			}
		} while(twoWords == false);
		
		String name = userInput;
		return name;
	}
	
	/** This method checks to see if the user's Date of Birth has been entered in
	 *  the correct formatting i.e March 01 1999. This continues the ask the user 
	 *  until they have entered using the right format.
	 *  @return The user input as a String called DOB if it is in the correct formatting
	 */
	private static String DOBCheck() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter the student's date of birth (Formatted like this: March 01 1999): ");
		
		String userInput;
		
		// this loop continues to ask the user to enter their date of birth until:
		// there are only 3 parts (month, day, year), the month is not a number, the
		// day is two character String that can be parsed into a number and the year
		// is a number.
		boolean threeWords = false;
		do {
			userInput = in.nextLine();
			String[] DOBArray = userInput.split(" ");
			threeWords = true;
			// checks to see if there are only 3 parts to the data
			if (DOBArray.length > 3 || DOBArray.length < 3) {
				System.out.print("Please enter the month day and year: ");
				threeWords = false;
			}
			else {
				try { // checks to see if the month is a word
					Integer.parseInt(DOBArray[0]);
					System.out.print("Please retry, using the word for the month: ");
					threeWords = false;
				} catch (NumberFormatException e) {}
				// checks to see if the day is two characters long
				if (DOBArray[1].length() > 2 || DOBArray[1].length() < 2) {
					System.out.print("Please retry, using two digits for the day: ");
					threeWords = false;
				}
				else {
					try { // checks to see if the day can be parsed into a number
						Integer.parseInt(DOBArray[1]);
					} catch (NumberFormatException e) {
						System.out.print("Please retry, using the numerical day: ");
						threeWords = false;
					}
				}
				try { // checks to see if the year can be parsed into a number
					Integer.parseInt(DOBArray[2]);
				} catch (NumberFormatException e) {
					System.out.print("Please retry, using the numerical year: ");
					threeWords = false;
				}
			}
		} while(threeWords == false);
		
		String DOB = userInput;
		return DOB;
	}
	
	/** This method loops until the user has course that have enough credits to meet
	 *  the maxCredit requirement.
	 *  @param maxCredits : This is the number of credits the student is required to
	 *  					to have, this should be an Integer
	 *  @return A complete array of Module objects making sure that they add up to
	 *  		the correct number of credits
	 */
	private static ArrayList<Module> modulesCheck(int maxCredits) {
		ArrayList<Module> modules = new ArrayList<Module>();
		int creditCount = 0;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("\nYou now have to enter the details of the modules this student is taking this MUST EQUAL " + maxCredits + "\n("
				+ "if the credit count exceeds the max, you will have to restart this section) ");
		
		// this while loop runs while the credit value of the courses selected are
		// less than the required number
		while (creditCount < maxCredits) {
			System.out.print("\nEnter a module the student will be taking (Formatted like this: CourseCode,Name,Credits): ");

			String userInput;
			int credits = 0;

			boolean threeWords = false;
			do { // this section is very like the date of birth checker previously
				userInput = in.nextLine();
				String[] DOBArray = userInput.split(",");
				threeWords = true;
				if (DOBArray.length > 3 || DOBArray.length < 3) {
					System.out.print("Please enter the Coursecode, name and credit value: ");
					threeWords = false;
				}
				else { // the only difference is that the first two have to be words
					   // instead of just the first being a word
					try {
						Integer.parseInt(DOBArray[0]);
						System.out.print("Please retry, using the full coursecode: ");
						threeWords = false;
					} catch (NumberFormatException e) {}
					try {
						Integer.parseInt(DOBArray[1]);
						System.out.print("Please retry, using the a non-numerical name: ");
						threeWords = false;
					} catch (NumberFormatException e) {}
					try {
						credits = Integer.parseInt(DOBArray[2]);
					} catch (NumberFormatException e) {
						System.out.print("Please retry, using the numerical credit value: ");
						threeWords = false;
					}
				}
			} while(threeWords == false);

			creditCount += credits; // each time the loop runs the credit count is incremented
			Module module = new Module(userInput); // the new module is created 
			modules.add(module); // the new module is added
		}

		return modules; // when the loop is finished the modules list is returned
	}
	
	/** This is almost the same as the previous method but it only allows for one
	 *  Module entry
	 *  @return This returns the user input as a String if it equals a value courseCode
	 */
	private static String checkModuleString() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.print("\nEnter a module the student will be taking (Formatted like this: CourseCode,Name,Credits): ");

		String userInput;
		
		boolean threeWords = false;
		do {
			userInput = in.nextLine();
			String[] DOBArray = userInput.split(",");
			threeWords = true;
			if (DOBArray.length > 3 || DOBArray.length < 3) {
				System.out.print("Please enter the Coursecode, name and credit value: ");
				threeWords = false;
			}
			else {
				try {
					Integer.parseInt(DOBArray[0]);
					System.out.print("Please retry, using the full coursecode: ");
					threeWords = false;
				} catch (NumberFormatException e) {}
				try {
					Integer.parseInt(DOBArray[1]);
					System.out.print("Please retry, using the a non-numerical name: ");
					threeWords = false;
				} catch (NumberFormatException e) {}
				try {
					Integer.parseInt(DOBArray[2]);
				} catch (NumberFormatException e) {
					System.out.print("Please retry, using the numerical credit value: ");
					threeWords = false;
				}
			}
		} while(threeWords == false);
		
		return userInput;
	}
	
	/** This creates a new Undergrad object that is sent back to whatever called it
	 *  @return A new Undergrad object. 
	 *  @throws FileNotFoundException
	 */
	private static Undergrad registerUndergrad() throws FileNotFoundException {
		String name = nameCheck();
		String DOB = DOBCheck();
		ArrayList<Module> modules = modulesCheck(120); // checks to see if it has
													   // the correct number of
													   // credits
		
		return (new Undergrad(name, DOB, modules));
	}
	
	/** This creates a new PostgradT object that is sent back to whatever called it
	 *  @return A new PostgradT object. 
	 *  @throws FileNotFoundException
	 */
	private static PostgradT registerPostgradT() throws FileNotFoundException {
		String name = nameCheck();
		String DOB = DOBCheck();
		ArrayList<Module> modules = modulesCheck(180);
		
		return (new PostgradT(name, DOB, modules));
	}
	
	/** This creates a new PostgradR object that is sent back to whatever called it
	 *  @return A new PostgradR object. 
	 *  @throws FileNotFoundException
	 */
	private static PostgradR registerPostgradR() throws FileNotFoundException {
		String name = nameCheck();
		String DOB = DOBCheck();
		
		return (new PostgradR(name, DOB));
	}
	
	/** This is one of the 4 method specified in part two of the Specification. It
	 *  takes one String input that will decide what type of students it should 
	 *  return the count of.
	 *  @param studentType : This is should be a String that can be parsed into an
	 *  					 Integer between 1 - 4. 
	 *  @return Returns the total number of students of the specified type
	 *  @throws FileNotFoundException
	 */
	private static int noOfStudents(String studentType) throws FileNotFoundException { // THE STUDENT TYPE DETERMINES WHAT THIS RETURNS
		Scanner SRReader = new Scanner(StudentRecords);// Scanner for StudentRecords
		int ugCount = 0; int pgTCount = 0; int pgRCount = 0; // Counters for students
		
		// the Scanner runs through the file and increments each variable if the
		// student ID is of its respective type.
		while(SRReader.hasNextLine()) {
			String line = SRReader.nextLine();
			if ((int) line.charAt(0) >= 97 && (int) line.charAt(0) <= 108)
				ugCount++;
			else if ((int) line.charAt(0) >= 109 && (int) line.charAt(0) <= 115)
				pgTCount++;
			else if ((int) line.charAt(0) >= 116 && (int) line.charAt(0) <= 122)
				pgRCount++;
		}
		
		SRReader.close(); // Scanner closed to prevent leaks
		
		// returns depending on what the user enters
		if (studentType.equals("1"))
			return ugCount;
		else if (studentType.equals("2"))
			return pgTCount;
		else if (studentType.equals("3"))
			return pgRCount;
		else if (studentType.equals("4"))
			return ugCount + pgTCount + pgRCount;
		else 
			throw new IllegalArgumentException("Invalid Selection"); 
	}
	
	/** This is another of the 4 methods specified in part two of the Specification.
	 *  Adds a student object to the student objects list and then writes its info
	 *  to both the SmartCardRecords file and the StudentRecords file
	 *  @param student : This should be the a child object of the Students class
	 *  @throws IOException
	 */
	private static void registerStudent(Students student) throws IOException { // Adds a student to the list of students on the document
		// REQUIREMENTS TO CREATE A STUDENT:
		// 1. Student name
		// 2. Birth date
		// 3. Modules/Supervisor
		
		StudentObjects.add(student); // the student is added to StudentObjects
		
		// this is a very useful java class that I didnt know about until this project
		StringBuilder entry = new StringBuilder(); // a new String builder is made
		
		// If the student object is a PostgradR
		if (student instanceof PostgradR) {
			// fills the String builder with the relevant information
			entry.append(student.getStudentID() + "\n");
			entry.append(student.getSmartCardNum() + "\n");
			entry.append(student.getSupervisor() + "\n");
			
			// checks to see if the student actually has a supervisor
			if (student.getSupervisor().equals(null))
				System.out.println(student.getStudentID() + " does not have a supervisor");
			
			// adds the entry to the StudentRecords file
			writeToFile(StudentRecords, entry.toString());
			
			// fills the String builder with the relevant information
			entry = new StringBuilder(); // resets the StringBuilder
			entry.append(student.getSmartCardNum() + "\n");
			entry.append(student.getName() + "\n");
			entry.append(student.getBirthDate() + "\n");
			entry.append(student.getSmartCard().getDOI() + "\n");
			
			// adds the entry to the SmartCardRecords file
			writeToFile(SmartCardRecords, entry.toString());
		}
		
		// If the student object is an Undergrad or PostgradT
		else if (student instanceof Undergrad || student instanceof PostgradT) {
			// works the same way as before
			entry.append(student.getStudentID() + "\n");
			entry.append(student.getSmartCardNum() + "\n");
			
			int totalCredits = 0;
			// uses a for loop to add all the modules
			for (Module mod: student.getModules()) {
				totalCredits += mod.getcreditValue();
				entry.append(mod.toString() + "\n");
			}
			
			int maxCredits;
			
			// checks to see if the number of credits is actually correct, if not the
			// user is prompted
			if (student instanceof Undergrad) {
				maxCredits = 120;
				if (totalCredits > maxCredits || totalCredits < maxCredits)
					System.out.println(student.getStudentID() + " does not have the correct number of credits");
			}
			
			if (student instanceof PostgradT) {
				maxCredits = 180;
				if (totalCredits > maxCredits || totalCredits < maxCredits)
					System.out.println(student.getStudentID() + " does not have the correct number of credits");
			}
			
			// writes the info to the StudentRecords file
			writeToFile(StudentRecords, entry.toString());
			
			// fills the String builder with the relevant information
			entry = new StringBuilder();
			entry.append(student.getSmartCardNum() + "\n");
			entry.append(student.getName() + "\n");
			entry.append(student.getBirthDate() + "\n");
			entry.append(student.getSmartCard().getDOI() + "\n");
			
			// writes that info to the SmartCardRecords file
			writeToFile(SmartCardRecords, entry.toString());
		}
		
		// throws exception otherwise
		else
			throw new IllegalArgumentException("Student is of an invalid type");
	}
	
	/** This is another one of the 4 methods specified in part two of the Spec.
	 *  This removes a student from the files and the StudentObjects ArrayList by
	 *  checking for that student's ID then removing it
	 *  @param studentID : This should be a String that contains only the student ID
	 *  @throws IOException
	 */
	private static void terminateStudent(String studentID) throws IOException { // Removes a student from the list of students on the document
		refreshStudentObjects(); // runs refreshStudentObjects to remake the files
		
		for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) { // Removes the student from the arraylist of current students
			if (StudentObjects.get(currentStudent).getStudentID().equals(studentID)) {
				StudentObjects.remove(currentStudent);
			}
		}
		
		SmartCardRecords.delete(); // deletes the current files
		StudentRecords.delete();
		
		checkFiles(); // remakes empty versions of the files
		
		// uses registerStudent to rewrite every student in StudentObjects to the files
		for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) {
			registerStudent(StudentObjects.get(currentStudent));
		}
		
		refreshStudentObjects(); // runs refreshStudentObjects to remake the files
	}
	
	private static void amendStudentData(String studentID, String studentData) throws IOException { // Change student's data
		// USES choice TO DECIDE WHAT THE studentDATA String WILL AFFECT
		
		refreshStudentObjects();
		
		int studentIndex = 0;
		
		for (int currentStudent = 0; currentStudent < StudentObjects.size(); currentStudent++) {
			if (StudentObjects.get(currentStudent).getStudentID().equals(studentID))
				studentIndex = currentStudent;
		}
		
		// IF CHOICE IS 1 CHANGE NAME
		if (choice == 1)
			StudentObjects.get(studentIndex).setName(studentData);
		
		// IF CHOICE IS 2 CHANGE BIRTHDATE
		if (choice == 2)
			StudentObjects.get(studentIndex).setBirthDate(studentData);
		
		if (StudentObjects.get(studentIndex) instanceof Undergrad) {
			Undergrad student = (Undergrad) StudentObjects.get(studentIndex);
			
			// IF CHOICE IS 3 ADD MODULE
			if (choice == 3) 
				student.addModule(new Module(studentData));
			// IF CHOICE IS 4 REMOVE MODULE
			if (choice == 4) {
				student.removeModule(Integer.parseInt(studentData) - 1);}
		}
		
		else if (StudentObjects.get(studentIndex) instanceof PostgradT) {
			PostgradT student = (PostgradT) StudentObjects.get(studentIndex);
			
			if (choice == 3) 
				student.addModule(new Module(studentData));
			if (choice == 4)
				student.removeModule(Integer.parseInt(studentData)  - 1);
		}
		
		else if (StudentObjects.get(studentIndex) instanceof PostgradR) {
			if (choice == 3 || choice == 4)
				throw new IllegalArgumentException("This student type does not have modules");
		}
		
		else
			throw new IllegalArgumentException("Invalid student type");
		
		// TO REFRESH JUST DELETE FILES AND REMAKE WITH STUDENTOBJECTS THEN REFRESH
		SmartCardRecords.delete();
		StudentRecords.delete();
		
		checkFiles();
		
		int currentStudentObjectsSize = StudentObjects.size();
		for (int currentStudent = 0; currentStudent < currentStudentObjectsSize; currentStudent++) {
			System.out.println(StudentObjects.size());
			registerStudent(StudentObjects.get(currentStudent));
		}
		
		refreshStudentObjects();
	}
	
	/** This is a very simple method to make sure the files SmartCardRecords and
	 *  StudentRecords actually exist, if they don't blank version are made
	 *  @throws IOException
	 */
	private static void checkFiles() throws IOException {
		SmartCardRecords.createNewFile(); // Checks to see if a file named "SmartCardRecord exists", if it doesn't it is created
		StudentRecords.createNewFile(); // Checks to see if a file named "StudentRecord exists", if it doesn't it is created
	}
	
	/** This allows for a file specified by f to take an entry specified through the
	 *  String entry.
	 *  @param f : This should be the file to be written to
	 *  @param entry : This is the string that will be entered into the file
	 *  @throws IOException
	 */
	private static void writeToFile(File f, String entry) throws IOException { // Should be used when entering new students details
		FileWriter fileWriter = new FileWriter(f, true); // This creates a new object that writes to file f (true is in the
														 // parameters to enable append mode
		fileWriter.write(entry); // The entry is written to the file
		
		fileWriter.close(); // the FileWriter is closed
	}
	
	/** This backs up the contents of each file and then deletes them and remakes
	 *  them based on the the StudentObjects ArrayList
	 *  @throws FileNotFoundException
	 */
	private static void refreshStudentObjects() throws FileNotFoundException {
		// Creates Scanners for each file
		Scanner SRReader = new Scanner(StudentRecords);
		Scanner SCRReader = new Scanner(SmartCardRecords);
		
		// Creates an array list to hold Students objects and student infromation
		ArrayList<Students> replaceStudentObjects = new ArrayList<Students>();
		ArrayList<String> studentInfo = new ArrayList<String>();
		
		int studentInfoCounter = -1; // counter initialised to -1
		
		// loop runs through the length of the StudentRecords file
		while (SRReader.hasNextLine()) {
			String line = SRReader.nextLine(); // current line saved as a String
			boolean skipLine = false; // this will be used to skip the lines
			
			// if the current line is the StudentID then that line is skipped
			if (line.length() == StudentIDLength) {
				studentInfo.add(line); // the StudentInfo Array gets a new entry
				studentInfoCounter++; // the index of to add to in StudentInfo is +1
				skipLine = true; 
			}
			
			if (skipLine == true) {continue;} // the line is skipped
			// the studentInfo entry at the studentInfoCounter is appended with 
			// % + next line 
			studentInfo.set(studentInfoCounter, studentInfo.get(studentInfoCounter) + "%" + line);
		}
		
		int lineCount = 1; // lineCount is initialised as 1
		studentInfoCounter = 0; // StudentInfoCounter is reset
		
		// This runs through the SmartCardRecords file
		while (SCRReader.hasNextLine()) {
			// the current line is saved
			String line = SCRReader.nextLine();
			
			// studentInfo gets all the info from the SmartCardRecords file,
			// separated by "%"
			studentInfo.set(studentInfoCounter, studentInfo.get(studentInfoCounter) + "%" + line);
			
			// if the line equals 4 the studentCounter is increased, this is done
			// because there are only ever 4 lines per student entered
			if (lineCount % 4 == 0) {
				lineCount = 0; // lineCount is reset
				studentInfoCounter++;
			}
			
			lineCount++; // lineCount is incremented
		}
		
		// runs through every entry in StudentInfo
		for (int studentInfoSize = 0; studentInfoSize < studentInfo.size(); studentInfoSize++) {
			// splits each section of a StudentInfo entry and saves it as
			// studentInfoParts
			String[] studentInfoParts = studentInfo.get(studentInfoSize).split("%");
			
			// Creates a new smartCard object with the information in studentInfo
			SmartCard refreshedSC = new SmartCard(studentInfoParts[1], studentInfoParts[studentInfoParts.length - 3], 
			studentInfoParts[studentInfoParts.length - 2], studentInfoParts[studentInfoParts.length - 1], studentInfoParts[0]);
			
			// checks to see if the student is and undergrad (based on the studentID)
			if ((int) studentInfoParts[0].charAt(0) >= 97 && (int) studentInfoParts[0].charAt(0) <= 108) {
				ArrayList<Module> modules = new ArrayList<Module>(); // new Module List
				// fills the array with the list of modules in it finds in the studentInfoParts array
				for (int currentModule = 2; currentModule < studentInfoParts.length - 4; currentModule++) {
					String[] moduleInfo = studentInfoParts[currentModule].split(",");
					modules.add(new Module(moduleInfo[0], moduleInfo[1], moduleInfo[2]));
				}
				
				// creates a new Undergrad object with the parts saved
				Undergrad refreshedStudent = new Undergrad(studentInfoParts[studentInfoParts.length - 2], studentInfoParts[0], refreshedSC, modules);
				replaceStudentObjects.add(refreshedStudent);
			}
			
			// checks to see if the student is and undergrad (based on the studentID)
			else if ((int) studentInfoParts[0].charAt(0) >= 109 && (int) studentInfoParts[0].charAt(0) <= 115) {
				ArrayList<Module> modules = new ArrayList<Module>(); // new Module List
				// fills the array with the list of modules in it finds in the studentInfoParts array
				for (int currentModule = 2; currentModule < studentInfoParts.length - 4; currentModule++) {
					String[] moduleInfo = studentInfoParts[currentModule].split(",");
					modules.add(new Module(moduleInfo[0], moduleInfo[1], moduleInfo[2]));
				}
				
				// creates a new Undergrad object with the parts saved
				PostgradT refreshedStudent = new PostgradT(studentInfoParts[studentInfoParts.length - 2], studentInfoParts[0], refreshedSC, modules);
				replaceStudentObjects.add(refreshedStudent);
			}
			
			// checks to see if the student is and undergrad (based on the studentID)
			else if ((int) studentInfoParts[0].charAt(0) >= 116 && (int) studentInfoParts[0].charAt(0) <= 122) {
				PostgradR refreshedStudent = new PostgradR(studentInfoParts[studentInfoParts.length - 2], studentInfoParts[0], refreshedSC, studentInfoParts[2]);
				replaceStudentObjects.add(refreshedStudent);
			}
		}
		
		// closes the Scanners
		SRReader.close();
		SCRReader.close();
		
		// StudentObjects is reset
		StudentObjects = replaceStudentObjects;
	}
}
