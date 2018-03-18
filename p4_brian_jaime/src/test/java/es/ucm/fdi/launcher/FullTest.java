package es.ucm.fdi.launcher;

import static org.junit.Assert.*;
import org.junit.Test;

import es.ucm.fdi.Exceptions.SimulatorException;
import es.ucm.fdi.launcher.Main;

public class FullTest {

	private static final String BASE = "src/test/resources/";

	@Test
	public void testError() throws Exception {
		try {
			Main.test(BASE + "examples/err");
			fail("Expected an exception while parsing bad ini file");
		} catch (SimulatorException e) {
			// Expected exception
		}
	}

	@Test
	public void testBasic() throws Exception {
		Main.test(BASE + "examples/basic");
	}

	@Test
	public void testAdvanced() throws Exception {
		Main.test(BASE + "examples/advanced");
	}

}