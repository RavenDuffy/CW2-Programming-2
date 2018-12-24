package CW1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.jupiter.api.Test;

class SmartCardNumberTest {

	// checks to see if the toString override in SmartCardNumber returns the expected
	// value
	@Test
	void testToString() throws FileNotFoundException {
		SmartCardNumber test = new SmartCardNumber("Raven Duffy");
		String output = test.toString();
		// Note the 2018 section of the SCN will stop working in later years
		assertEquals("RD-2018-00", output);
	}

	// checks to see if SmartCardNumber.getDateOfIssue returns the expected date 
	// (the same as the current date)
	@Test
	void testGetDateOfIssue() throws FileNotFoundException {
		SmartCardNumber test = new SmartCardNumber("Raven Duffy");
		String output = test.getDateOfIssue();
		
		Date currentDate = new Date();
		String dateInfo = currentDate.toString();
		dateInfo = dateInfo.substring(4, 11) + dateInfo.substring(30); // full date
		
		assertEquals(dateInfo, output);
	}

}
