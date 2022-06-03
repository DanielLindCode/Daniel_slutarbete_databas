package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class lqBean {
	
	private int _lg_id;
	private String _lq_name;
	private String _room_nr;
	private String _site;
		
	
	public int get_lg_id() {
		return _lg_id;
	}

	public void set_lg_id(int _lg_id) {
		this._lg_id = _lg_id;
	}

	public String get_lq_name() {
		return _lq_name;
	}

	public void set_lq_name(String _lq_name) {
		this._lq_name = _lq_name;
	}

	public String get_room_nr() {
		return _room_nr;
	}

	public void set_room_nr(String _room_nr) {
		this._room_nr = _room_nr;
	}

	public String get_site() {
		return _site;
	}

	public void set_site(String _site) {
		this._site = _site;
	}

	public String toString() {
		String pattern = "ID = %d, Name = %s, Room nr = %s, Site = %s";
		String returnString = String.format(pattern, this._lg_id, this._lq_name, this._room_nr, this._site);

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ID", Integer.toString(this._lg_id)));
		dataList.add(new keyvaluepair("Name", this._lq_name));
		dataList.add(new keyvaluepair("Room nr", this._room_nr));
		dataList.add(new keyvaluepair("Site", this._site));

		return jsonHelper.toJsonObject(dataList);
	}

}
