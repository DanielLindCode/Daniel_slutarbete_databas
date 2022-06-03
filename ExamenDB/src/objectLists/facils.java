package objectLists;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.faciBean;
import helpers.jsonHelper;

public class facils {


	private Connection _connection;
	private ArrayList<faciBean> _facils;

	private String qry = "select * from facilities";
	
	private String update =
			"UPDATE facilities SET"
			+ " faci_name = ?,"
			+ " site = ?"
			+ "WHERE faci_name = ?";

	public facils(Connection cn) {
		this._connection = cn;
		this._facils = new ArrayList<faciBean>();
		getFacils();
	}

	public ArrayList<faciBean> getFacils() {

		if (this._facils.size() > 0)
			return this._facils;

		this._facils = new ArrayList<faciBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getFacils exception for statement");
			e.printStackTrace();
		}

		return this._facils;
	}

	public String toJson() {
		String beansContent = "";
		for (faciBean fb : this._facils) {
			beansContent += fb.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Dinosaurs", beansContent);
	}

	private faciBean buildFaci(ResultSet rs) {
		faciBean fb = new faciBean();

		try {

			fb.set_faci_id(rs.getInt("faci_id"));
			fb.set_faci_name(rs.getString("faci_name"));
			fb.set_site(rs.getString("site"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fb;
	}

	private void buildFacils(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this._facils.add(buildFaci(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildFacils(rs);
		} catch (SQLException e) {
			System.out.println("getFacils exception for result set");
			e.printStackTrace();
		}

	}

	public int updateFacils(String qName, String newName, String newSite) throws SQLException {

		int count = -1;


		try (PreparedStatement myQry = this._connection.prepareStatement(update)) {
			myQry.setString(1, newName);
			myQry.setString(2, newSite);
			myQry.setString(3, qName);

			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateFacils exception for result set");
			e.printStackTrace();
		}

		return count;
	}
}
