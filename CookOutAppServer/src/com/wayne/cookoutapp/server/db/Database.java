package com.wayne.cookoutapp.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wayne.cookoutapp.server.ComboRating;

public class Database {
	private Connection conn = null;
	
	private static final String CONNECTION_STRING = "jdbc:sqlite:cookout.db";
	private static final Logger LOG = Logger.getLogger(Database.class);
	
	public Database() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			LOG.fatal("Failed to load sqlite JDBC drivers");
			throw e;
		}
		
		conn = DriverManager.getConnection(CONNECTION_STRING);
	}
	
	public Map<Integer, String> getFlavors() throws SQLException {
		Map<Integer, String> flavors = new HashMap<Integer, String>();
		Statement st = null;
		
		try {
			st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM flavors");
			
			while(rs.next()) {
				flavors.put(rs.getInt("flavor_id"), rs.getString("flavor_name"));
			}
			
			
		} finally {
			if(st != null)
				st.close();
		}
		
		return flavors;
	}
	
	public ComboRating getComboRating(int flavor1, int flavor2) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("SELECT times_rated, total_rating FROM flavor_combo_ratings" +
					" WHERE (flavor_1 = ? AND flavor_2 = ?) OR (flavor_1 = ? AND flavor_2 = ?)");
			
			pst.setInt(1, flavor1);
			pst.setInt(2, flavor2);
			pst.setInt(3, flavor2);
			pst.setInt(4, flavor1);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return new ComboRating(flavor1, flavor2, rs.getInt("times_rated"), rs.getInt("total_rating"));
			else
				return null;

		} finally {
			if(pst != null)
				pst.close();
		}
		
	}
	
	public List<ComboRating> getTopList() throws SQLException {
		Statement st = null;
		List<ComboRating> topList = new ArrayList<ComboRating>();
		
		try {
			st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT ((total_rating * 1.0)/times_rated) AS rating, flavor_1, flavor_2, times_rated, total_rating FROM flavor_combo_ratings ORDER BY rating DESC LIMIT 10");
			
			while(rs.next()) {
				topList.add(new ComboRating(rs.getInt("flavor_1"), rs.getInt("flavor_2"), rs.getInt("total_rating"), rs.getInt("times_rated")));
			}

		} finally {
			if(st != null)
				st.close();
		}
		
		return topList;
	}
	
	public void rateCombo(ComboRating cr) throws SQLException {
		PreparedStatement pst = null;
		int rowsAffected;
		try {
			pst = conn.prepareStatement("UPDATE flavor_combo_ratings SET times_rated = times_rated + 1, total_rating = total_rating + ? WHERE (flavor_1 = ? AND flavor_2 = ?) OR (flavor_1 = ? AND flavor_2 = ?)");
			
			pst.setInt(1, cr.getTotalRating());
			pst.setInt(2, cr.getFlavor1());
			pst.setInt(3, cr.getFlavor2());
			pst.setInt(4, cr.getFlavor2());
			pst.setInt(5, cr.getFlavor1());
			
			rowsAffected = pst.executeUpdate();
			
		} finally {
			if(pst != null)
				pst.close();
		}
		
		if(rowsAffected > 0)
			return;
		
		try {
			pst = conn.prepareStatement("INSERT INTO flavor_combo_ratings VALUES (?, ?, 1, ?)");
			pst.setInt(1, cr.getFlavor1());
			pst.setInt(2, cr.getFlavor2());
			pst.setInt(3, cr.getTotalRating());
			
			pst.execute();
			
		} finally {
			if(pst != null)
				pst.close();
		}
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.error("Failed to close sql connection.");
		}
	}
	
}
