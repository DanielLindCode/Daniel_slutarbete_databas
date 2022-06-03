package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.dinoBean;
import helpers.jsonHelper;

public class dinos {

	private Connection _connection;
	private ArrayList<dinoBean> _dinos;

	private String qry = "select * from dinosaurs";

	private String updateDino =
			"UPDATE dinosaurs SET"
			+ " dino_name = ?, "
			+ "year_of_birth = ? "
			+ "WHERE dino_name = ?";

	public dinos(Connection cn) {
		this._connection = cn;
		this._dinos = new ArrayList<dinoBean>();
		getDinos();
	}

	public ArrayList<dinoBean> getDinos() {

		if (this._dinos.size() > 0)
			return this._dinos;

		this._dinos = new ArrayList<dinoBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getDinos exception for statement");
			e.printStackTrace();
		}

		return this._dinos;
	}
	

	public String toJson() {
		String beansContent = "";
		for (dinoBean db : this._dinos) {
			beansContent += db.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Dinosaurs", beansContent);
	}

	private dinoBean buildDino(ResultSet rs) {
		dinoBean db = new dinoBean();

		try {

			db.set_dino_type_id(rs.getInt("dino_type_id"));
			db.set_name(rs.getString("dino_name"));
			db.setYear_of_birth(rs.getInt("year_of_birth"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return db;
	}

	private void buildDinos(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this._dinos.add(buildDino(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildDinos(rs);
		} catch (SQLException e) {
			System.out.println("getDinos exception for result set");
			e.printStackTrace();
		}
	}

	public int updateDinos(String qName, String newName, int newBirthYear) throws SQLException {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateDino)) {
			myQry.setString(1, newName);
			myQry.setInt(2, newBirthYear);
			myQry.setString(3, qName);

			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateDinos exception for result set");
			e.printStackTrace();
		}

		return count;
	}
}
