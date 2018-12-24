package CW1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class PostgradRTest {
	
	// checks to see if the supervisor returns the expected String
	@Test
	void testGetSupervisor() throws FileNotFoundException {
		PostgradR test = new PostgradR("Raven Duffy", "Mar 15 1996");
		String output = test.getSupervisor();
		assertEquals("Mr. Patterson", output);
	}

}
