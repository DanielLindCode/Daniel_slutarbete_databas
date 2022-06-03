package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.empBean;
import helpers.jsonHelper;

public class emps {

	private Connection _connection;
	private ArrayList<empBean> _emps;
	
	private String qry = "select * from employees";
	
	private String update =
			"UPDATE employees SET"
			+ " emp_first_name = ?,"
			+ " emp_last_name = ?,"
			+ " emp_age = ?"
			+ "WHERE emp_first_name = ? ";
	

	public emps(Connection cn) {
		this._connection = cn;
		this._emps = new ArrayList<empBean>();
		getEmps();
	}

	public ArrayList<empBean> getEmps() {
		

		if (this._emps.size() > 0)
			return this._emps;

		this._emps = new ArrayList<empBean>();

		try (PreparedStatement myQry = this._connection.prepareStatement(this.qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getEmps exception for statement");
			e.printStackTrace();
		}

		return this._emps;
	}

	public String toJson() {
		String beansContent = "";
		for (empBean eb : this._emps) {
			beansContent += eb.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Employees", beansContent);
	}

	private empBean buildEmp(ResultSet rs) {
		empBean eb = new empBean();
		
		try {

			eb.set_emp_id(rs.getInt("emp_id"));
			eb.set_emp_first_name(rs.getString("emp_first_name"));
			eb.set_emp_last_name(rs.getString("emp_last_name"));
			eb.set_emp_age(rs.getInt("emp_age"));
			eb.set_emp_contact_id(rs.getInt("emp_contact_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eb;
	}

	private void buildEmps(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this._emps.add(buildEmp(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildEmps(rs);
		} catch (SQLException e) {
			System.out.println("getEmps exception for result set");
			e.printStackTrace();
		}

	}

	public int updateEmps(String qName,
			String newFirstName, 
			String newLastName,
			int age) 
					throws SQLException {

		int count = -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(update)) {
			myQry.setString(1, newFirstName);
			myQry.setString(2, newLastName);
			myQry.setInt(3, age);
			myQry.setString(4, qName);

			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateEmps exception for result set");
			e.printStackTrace();
		}

		return count;
	}
}
