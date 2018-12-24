package CW1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.jupiter.api.Test;

class SmartCardTest {
	
	// checks to see if SmartCard.getName() returns the correct String
	@Test
	void testGetName() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		test.setSmartCard(new SmartCard(test));
		
		String output = test.getSmartCard().getName();
		assertEquals("Raven Duffy", output);
	}
	
	// checks to see if SmartCard.getSmartCardNum returns the correct String
	@Test
	void testGetSmartCardNum() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		test.setSmartCard(new SmartCard(test));
		
		String output = test.getSmartCard().getSmartCardNum();
		assertEquals("RD-2018-00", output);
	}

	// checks to see if SmartCard.getStudentID returns the correct String
	@Test
	void testGetStudentID() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		test.setSmartCard(new SmartCard(test));
		
		String output = test.getSmartCard().getStudentID();
		assertEquals("t0001", output);
	}

	// checks to see if SmartCard.getDOB returns the correct String
	@Test
	void testGetDOB() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		test.setSmartCard(new SmartCard(test));
		
		String output = test.getSmartCard().getDOB();
		assertEquals("Mar 15 1996", output);
	}

	// checks to see if SmartCard.getDOI = the current date which is the day this
	// SmartCard would be created
	@Test
	void testGetDOI() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		test.setSmartCard(new SmartCard(test));
		
		Date currentDate = new Date();
		String dateInfo = currentDate.toString();
		dateInfo = dateInfo.substring(4, 11) + dateInfo.substring(30); // full date
		
		String output = test.getSmartCard().getDOI();
		assertEquals(dateInfo, output);
	}

}
