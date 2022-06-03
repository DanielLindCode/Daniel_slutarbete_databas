package beans;
import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class faciBean {

	private int _faci_id;
	private String _faci_name;
	private String _site;

	
	public int get_faci_id() {
		return _faci_id;
	}

	public void set_faci_id(int _faci_id) {
		this._faci_id = _faci_id;
	}

	public String get_faci_name() {
		return _faci_name;
	}

	public void set_faci_name(String _faci_name) {
		this._faci_name = _faci_name;
	}

	public String get_site() {
		return _site;
	}

	public void set_site(String _site) {
		this._site = _site;
	}

	public String toString() {
		String pattern = "ID = %d, Name = %s, Site = %d";
		String returnString = String.format(pattern, this._faci_id, this._faci_name, this._site);

		return returnString;
	}

	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ID", Integer.toString(this._faci_id)));
		dataList.add(new keyvaluepair("Name", this._faci_name));
		dataList.add(new keyvaluepair("Site", this._site));

		return jsonHelper.toJsonObject(dataList);
	}

}
