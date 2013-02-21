import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.db.Database;


public class DatabaseTest {

	Database db;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		db = new Database();
	}

	@After
	public void tearDown() throws Exception {
		db.close();
	}

	@Test
	public void testGetFlavors() throws SQLException {
		assertNotNull(db.getFlavors());
		
		assertEquals("Vanilla", db.getFlavors().get(38));
		assertEquals(39, db.getFlavors().entrySet().size());
	}

	@Test
	public void testGetComboRating() throws SQLException {
		assertEquals(true, db.getComboRating(38, 14).getTimesRated() > 0);
		assertEquals(true, db.getComboRating(14, 38).getTimesRated() > 0);
		
		assertNull(db.getComboRating(1000, 100));
	}

	@Test
	public void testClose() {
		db.close();
	}

}
