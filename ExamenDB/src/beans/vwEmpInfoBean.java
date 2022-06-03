package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class vwEmpInfoBean {

	private int _id;
	private String _firstName;
	private String _lastName;
	private String _hiredDate;
	private String _phoneNr;
	private String _email;
	private String _lqName;
	private String _roomNr;
	private String _site;
	

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String get_hiredDate() {
		return _hiredDate;
	}

	public void set_hiredDate(String _hiredDate) {
		this._hiredDate = _hiredDate;
	}

	public String get_phoneNr() {
		return _phoneNr;
	}

	public void set_phoneNr(String _phoneNr) {
		this._phoneNr = _phoneNr;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_lqName() {
		return _lqName;
	}

	public void set_lqName(String _lqName) {
		this._lqName = _lqName;
	}

	public String get_roomNr() {
		return _roomNr;
	}

	public void set_roomNr(String _roomNr) {
		this._roomNr = _roomNr;
	}

	public String get_site() {
		return _site;
	}

	public void set_site(String _site) {
		this._site = _site;
	}

	public String toString() {
		String pattern =
				"Id = %d, "
				+ "First name = %s, "
				+ "Last name = %s, "
				+ "Hired Date = %s, "
				+ "Phone nr = %s, "
				+ "Email = %s, "
				+ "Living quarters = %s, "
				+ "Room nr = %s"
				+ "Site = %s, ";
		
		
		String returnString = String.format(pattern,
				this._id, 
				this._firstName, 
				this._lastName, 
				this._hiredDate, 
				this._phoneNr, 
				this._email,
				this._lqName, 
				this._roomNr, 
				this._site);

		return returnString;
	}

	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ID", Integer.toString(this._id)));
		dataList.add(new keyvaluepair("First name", this._firstName));
		dataList.add(new keyvaluepair("Last name", this._lastName));
		dataList.add(new keyvaluepair("Hired Date", this._hiredDate));
		dataList.add(new keyvaluepair("Phone nr", this._phoneNr));
		dataList.add(new keyvaluepair("Email", this._email));
		dataList.add(new keyvaluepair("Living quarters", this._lqName));
		dataList.add(new keyvaluepair("Room nr", this._roomNr));
		dataList.add(new keyvaluepair("Site", this._site));

		return jsonHelper.toJsonObject(dataList);
	}

}
