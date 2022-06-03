package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.vwDinoInfoBean;
import helpers.jsonHelper;

public class vwDinoInfos {


	private Connection _connection;
	private ArrayList<vwDinoInfoBean> _dinoInfos;

	private String qry = "select * from vw_dino_info";

	public vwDinoInfos(Connection cn) {
		this._connection = cn;
		this._dinoInfos = new ArrayList<vwDinoInfoBean>();
		getDinoInfos();
	}

	public ArrayList<vwDinoInfoBean> getDinoInfos() {

		if (this._dinoInfos.size() > 0)
			return this._dinoInfos;

		this._dinoInfos = new ArrayList<vwDinoInfoBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getDinoInfos exception for statement");
			e.printStackTrace();
		}

		return this._dinoInfos;
	}

	public String toJson() {
		String beansContent = "";
		for (vwDinoInfoBean dib : this._dinoInfos) {
			beansContent += dib.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Dino info", beansContent);
	}

	private vwDinoInfoBean buildDinoInfo(ResultSet rs) {
		vwDinoInfoBean dib = new vwDinoInfoBean();

		try {

			dib.set_id(rs.getInt("dino_id"));
			dib.set_name(rs.getString("dino_name"));
			dib.set_type(rs.getString("type_name"));
			dib.set_diet(rs.getString("diet"));
			dib.set_birth_year(rs.getInt("year_of_birth"));
			dib.set_paddockName(rs.getString("pad_name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dib;
	}

	private void buildDinoInfos(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this._dinoInfos.add(buildDinoInfo(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildDinoInfos(rs);
		} catch (SQLException e) {
			System.out.println("getDinoInfos exception for result set");
			e.printStackTrace();
		}

	}
}
