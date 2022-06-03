package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.vwEmpInfoBean;
import helpers.jsonHelper;

public class vwEmpInfos {

	private Connection _connection;
	private ArrayList<vwEmpInfoBean> _empsInfo;
	
	private String qry = "select * from vw_emp_info";

	public vwEmpInfos(Connection cn) {
		this._connection = cn;
		this._empsInfo = new ArrayList<vwEmpInfoBean>();
		getEmps();
	}

	public ArrayList<vwEmpInfoBean> getEmps() {
		

		if (this._empsInfo.size() > 0)
			return this._empsInfo;

		this._empsInfo = new ArrayList<vwEmpInfoBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getEmpsInfo exception for statement");
			e.printStackTrace();
		}

		return this._empsInfo;
	}

	public String toJson() {
		String beansContent = "";
		for (vwEmpInfoBean eb : this._empsInfo) {
			beansContent += eb.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Employees Info", beansContent);
	}

	private vwEmpInfoBean buildEmpInfo(ResultSet rs) {
		vwEmpInfoBean eib = new vwEmpInfoBean();
		
		try {

			eib.set_id(rs.getInt("emp_id"));
			eib.set_firstName(rs.getString("emp_first_name"));
			eib.set_lastName(rs.getString("emp_last_name"));
			eib.set_hiredDate(rs.getString("emp_hired_date"));
			eib.set_phoneNr(rs.getString("phone_nr"));
			eib.set_email(rs.getString("email"));
			eib.set_lqName(rs.getString("lq_name"));
			eib.set_roomNr(rs.getString("room_nr"));
			eib.set_site(rs.getString("site"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eib;
	}

	private void buildEmpsInfos(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this._empsInfo.add(buildEmpInfo(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildEmpsInfos(rs);
		} catch (SQLException e) {
			System.out.println("getEmpsInfo exception for result set");
			e.printStackTrace();
		}
	}
}
