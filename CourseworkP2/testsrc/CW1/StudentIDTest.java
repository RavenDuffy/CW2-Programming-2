package CW1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class StudentIDTest {
	
	// checks to see if StudentID.toString() returns the correct String
	@Test
	void testToString() throws FileNotFoundException {
		// creates a list of modules
		ArrayList<Module> modules = new ArrayList<Module>();
		modules.add(new Module("CSC1021,Programming II,20"));
		modules.add(new Module("CSC1029,Fill,160"));
		
		// creates a student
		PostgradT testS = new PostgradT("Raven Duffy", "Mar 15 1996", modules);
		
		// creates a StudentID object
		StudentID test = new StudentID(testS);
		String output = test.toString();
		
		assertEquals("m0001", output);
	}

}
