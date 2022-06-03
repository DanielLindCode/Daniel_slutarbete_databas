package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.lqBean;
import helpers.jsonHelper;



public class lqs {

	private Connection _connection;
	private ArrayList<lqBean> _lqs;

	private String qry = "select * from living_quarters";
	
	private String update =
			"UPDATE living_quarters SET"
			+ " lq_name = ?,"
			+ " room_nr = ?, "
			+ "site = ? "
			+ "WHERE lq_name = ?";
	
	public lqs(Connection cn) {
		this._connection = cn;
		this._lqs = new ArrayList<lqBean>();
		getLqs();
	}
	
	public ArrayList<lqBean> getLqs() {

		if (this._lqs.size() > 0)
			return this._lqs;

		this._lqs = new ArrayList<lqBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getLqs exception for statement");
			e.printStackTrace();
		}

		return this._lqs;
	}
	
	public String toJson() {
		String beansContent = "";
		for (lqBean lb : this._lqs) {
			beansContent += lb.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Living Quarters", beansContent);
	}

	private lqBean buildlq(ResultSet rs) {
		lqBean lb = new lqBean();

		try {
			
			lb.set_lg_id(rs.getInt("lq_id"));
			lb.set_lq_name(rs.getString("lq_name"));
			lb.set_room_nr(rs.getString("room_nr"));
			lb.set_site(rs.getString("site"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lb;
	}

	private void buildLqs(ResultSet rs) throws SQLException {
		while (rs.next()) { 
			this._lqs.add(buildlq(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildLqs(rs);
		} catch (SQLException e) {
			System.out.println("getLqs exception for result set");
			e.printStackTrace();
		}

	}

	public int updateLqs(String qName, String newLqName, String newLqRoomNr, String newLqSite) throws SQLException {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(update)) {
			myQry.setString(1, newLqName);
			myQry.setString(2, newLqRoomNr);
			myQry.setString(3, newLqSite);
			myQry.setString(4, qName);

			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateLqs exception for result set");
			e.printStackTrace();
		}

		return count;
	}
}
