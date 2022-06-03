package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class vwDinoInfoBean {

	private int _id;
	private String _name;
	private String _type;
	private String _diet;
	private int _birth_year;
	private String _paddockName;

	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_diet() {
		return _diet;
	}

	public void set_diet(String _diet) {
		this._diet = _diet;
	}

	public int get_birth_year() {
		return _birth_year;
	}

	public void set_birth_year(int _birth_year) {
		this._birth_year = _birth_year;
	}

	public String get_paddockName() {
		return _paddockName;
	}

	public void set_paddockName(String _paddockName) {
		this._paddockName = _paddockName;
	}

	public String toString() {
		String pattern =
				"Id = %d, "
				+ "Name = %s, "
				+ "Type = %s, "
				+ "Diet = %s, "
				+ "Year of Birth = %d, "
				+ "Assigned Paddock = %s";
		
		String returnString = String.format(pattern,
				this._id, 
				this._name, 
				this._type, 
				this._diet, 
				this._birth_year, 
				this._paddockName);

		return returnString;
	}

	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ID", Integer.toString(this._id)));
		dataList.add(new keyvaluepair("Name", this._name));
		dataList.add(new keyvaluepair("Type", this._type));
		dataList.add(new keyvaluepair("Diet", this._diet));
		dataList.add(new keyvaluepair("Birth year", Integer.toString(this._birth_year)));
		dataList.add(new keyvaluepair("Assigned Paddock", this._paddockName));

		return jsonHelper.toJsonObject(dataList);
	}

}
