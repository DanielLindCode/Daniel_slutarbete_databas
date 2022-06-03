package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class empBean {

	private int _emp_id;
	private String _emp_first_name;
	private String _emp_last_name;
	private int _emp_age;
	private int _emp_contact_id;

	
	public int get_emp_id() {
		return _emp_id;
	}

	public void set_emp_id(int _emp_id) {
		this._emp_id = _emp_id;
	}

	public String get_emp_first_name() {
		return _emp_first_name;
	}

	public void set_emp_first_name(String _emp_first_name) {
		this._emp_first_name = _emp_first_name;
	}

	public String get_emp_last_name() {
		return _emp_last_name;
	}

	public void set_emp_last_name(String _emp_last_name) {
		this._emp_last_name = _emp_last_name;
	}
	

	public int get_emp_age() {
		return _emp_age;
	}

	public void set_emp_age(int _emp_age) {
		this._emp_age = _emp_age;
	}

	public int get_emp_contact_id() {
		return _emp_contact_id;
	}

	public void set_emp_contact_id(int _emp_contact_id) {
		this._emp_contact_id = _emp_contact_id;
	}

	public String toString() {
		
		String pattern = "ID = %d, "
						+ "First name = %s, "
						+ "Last name = %s, "
						+ "Age = %d, "
						+ "Contact ID = %d";

		String returnString = String.format(pattern, 
				this._emp_id,
				this._emp_first_name,
				this._emp_last_name,
				this._emp_age,
				this._emp_contact_id);

		return returnString;
	}

	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ID", Integer.toString(this._emp_id)));
		dataList.add(new keyvaluepair("First name", this._emp_first_name));
		dataList.add(new keyvaluepair("Last name", this._emp_last_name));
		dataList.add(new keyvaluepair("Year of Birth", Integer.toString(this._emp_age)));
		dataList.add(new keyvaluepair("Contact ID", Integer.toString(this._emp_contact_id)));

		return jsonHelper.toJsonObject(dataList);
	}

}
