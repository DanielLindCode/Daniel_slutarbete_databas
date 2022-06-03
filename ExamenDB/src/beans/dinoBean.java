package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class dinoBean {

	private int _dino_id;
	private int _dino_type_id;
	private String _name;
	private int _year_of_birth;

	public int get_dino_id() {
		return _dino_id;
	}

	public void set_dino_id(int _dino_id) {
		this._dino_id = _dino_id;
	}

	public int get_dino_type_id() {
		return _dino_type_id;
	}

	public void set_dino_type_id(int _dino_type_id) {
		this._dino_type_id = _dino_type_id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public int getYear_of_birth() {
		return _year_of_birth;
	}

	public void setYear_of_birth(int year_of_birth) {
		this._year_of_birth = year_of_birth;
	}

	public String toString() {
		String pattern = "Type = %d, Name = %s, Year of Birth = %d";
		String returnString = String.format(pattern, this._dino_type_id, this._name, this._year_of_birth);

		return returnString;
	}

	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Type ID", Integer.toString(this._dino_type_id)));
		dataList.add(new keyvaluepair("Name", this._name));
		dataList.add(new keyvaluepair("Year of Birth", Integer.toString(this._year_of_birth)));

		return jsonHelper.toJsonObject(dataList);
	}

}
