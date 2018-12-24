package CW1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModuleTest {
	
	// checks to see if the first constructor creates the expected String
	@Test
	void testConstructor1() {
		Module test = new Module("CSC1021", "Programming II", "20");
		String output = test.toString();
		assertEquals("CSC1021,Programming II,20", output);
	}
	
	// checks to see if the second constructor creates the expected String
	@Test
	void testConstructor2() {
		Module test = new Module("CSC1021,Programming II,20");
		String output = test.toString();
		assertEquals("CSC1021,Programming II,20", output);
	}
	
	// checks to see if the toString method creates the expected String
	@Test
	void testToString() {
		Module test = new Module("CSC1021", "Programming II", "20");
		String output = test.toString();
		assertEquals("CSC1021,Programming II,20", output);
	}

	// checks to see if the getcreditValue returns the expected Integer
	@Test
	void testGetcreditValue() {
		Module test = new Module("CSC1021", "Programming II", "20");
		int output = test.getcreditValue();
		assertEquals(20, output);
	}

}
