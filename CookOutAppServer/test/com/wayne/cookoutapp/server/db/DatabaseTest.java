package com.wayne.cookoutapp.server.db;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.ComboRating;
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
		/* construct a tempory database */
		
		
		Class.forName("org.sqlite.JDBC");
		
		
		Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
		
		Statement st = conn.createStatement();
		
		st.execute("CREATE TABLE flavor_combo_ratings (flavor_1 INTEGER NOT NULL , flavor_2 INTEGER NOT NULL , times_rated INTEGER NOT NULL , total_rating INTEGER NOT NULL )");
		st.execute("CREATE TABLE flavors (flavor_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , flavor_name TEXT NOT NULL )");

		st.execute("INSERT INTO flavor_combo_ratings VALUES(1,2,1,5);");
		st.execute("INSERT INTO flavor_combo_ratings VALUES(2,3,2,9);");
		
		st.execute("INSERT INTO flavors VALUES(NULL, 'Vanilla')");
		st.execute("INSERT INTO flavors VALUES(NULL, 'Chocolate')");
		st.execute("INSERT INTO flavors VALUES(NULL, 'Peach')");
		st.execute("INSERT INTO flavors VALUES(NULL, 'Strawberry')");
		
		db = new Database(conn);
		
	}

	@After
	public void tearDown() throws Exception {
		db.close();
	}

	@Test
	public void testGetFlavors() throws SQLException {
		assertNotNull(db.getFlavors());
		
		assertEquals("Vanilla", db.getFlavors().get(1));
		assertEquals("Chocolate", db.getFlavors().get(2));
		
		assertEquals(4, db.getFlavors().entrySet().size());
	}

	@Test
	public void testGetComboRating() throws SQLException {
		assertEquals(db.getComboRating(1, 2).getTimesRated(), 1);
		assertEquals(db.getComboRating(2, 3).getTimesRated(), 2);
		
		assertNull(db.getComboRating(1000, 100));
	}
	
	@Test
	public void testRateCombo() throws SQLException {
		db.rateCombo(new ComboRating(1, 2, 1, 5));
		
		assertEquals(db.getComboRating(1, 2).getTimesRated(), 2);
		assertEquals(db.getComboRating(1, 2).getTotalRating(), 10);
		
		db.rateCombo(new ComboRating(1, 4, 1, 2));
		
		assertEquals(db.getComboRating(1, 4).getTimesRated(), 1);
		assertEquals(db.getComboRating(1, 4).getTotalRating(), 2);
	}

	@Test
	public void testGetTopList() throws SQLException {
		
		assertEquals(db.getTopList().size(), 2);
		
		assertEquals(db.getTopList().get(0).getFlavor1(), 1);
		assertEquals(db.getTopList().get(0).getFlavor2(), 2);
		
		assertEquals(db.getTopList().get(1).getFlavor1(), 2);
		assertEquals(db.getTopList().get(1).getFlavor2(), 3);
		
	}
	
	@Test
	public void testClose() {
		db.close();
	}

}
