package jpdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import helpers.databaseHelper;
import helpers.jsonHelper;
import objectLists.dinos;
import objectLists.emps;
import objectLists.facils;
import objectLists.lqs;
import objectLists.vwDinoInfos;
import objectLists.vwEmpInfos;

public class main_class {

	public static void main(String[] args) throws SQLException {

		Connection conn = databaseHelper.DbConnect("jpdb");

		//ShowAllTables(conn);
		
		//updateDinos(conn,"Billy","Billy-Boy",1980);
	
		// Jag får ett syntax error på denna men jag ser inte alls var det skulle vara. Jag kan använda scriptet i Mysql.
		//updateEmps(conn, "Sam", "Sammy", "Larsson", 23);
		
		//updateFacils(conn,"Hydro Electric Station", "Hydro Electric and Logistics Station", "A");
		
		//updateLqs(conn, "Staff Apartments", "Staff Apartments Delux", "2 B", "B");

		conn.close();
	}

	private static void ShowAllTables(Connection conn) {

		dinos myDinos = new dinos(conn);
		emps myEmps = new emps(conn);
		facils myFacils = new facils(conn);
		lqs myLqs = new lqs(conn);
		vwDinoInfos myDinoInfos = new vwDinoInfos(conn);
		vwEmpInfos myEmpInfos = new vwEmpInfos(conn);

		ArrayList<String> document = new ArrayList<String>();
		document.add(myDinos.toJson());
		document.add(myEmps.toJson());
		document.add(myFacils.toJson());
		document.add(myLqs.toJson());
		document.add(myDinoInfos.toJson());
		document.add(myEmpInfos.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);

		System.out.println(jsonDoc);

	}

	private static void updateDinos(Connection conn, String qName, String newName, int newBirthYear)
			throws SQLException {
		dinos myDinos = new dinos(conn);

		int antal = myDinos.updateDinos(qName, newName, newBirthYear);
		System.out.println("uppdaterat : " + antal);
	}
	
	private static void updateEmps(Connection conn, String qName, String newFirstName, String newLastName, int newAge )
			throws SQLException {
		emps myEmps = new emps(conn);

		int antal = myEmps.updateEmps(qName, newFirstName, newLastName, newAge);
		System.out.println("uppdaterat : " + antal);
	}
	
	private static void updateFacils(Connection conn, String qName, String newName, String newSite)
			throws SQLException {
		facils myFacils = new facils(conn);

		int antal = myFacils.updateFacils(qName, newName, newSite);
		System.out.println("uppdaterat : " + antal);
	}
	
	private static void updateLqs(Connection conn, String qName, String newLqName, String newLqRoomNr, String newLqSite)
			throws SQLException {
		lqs myLqs = new lqs(conn);

		int antal = myLqs.updateLqs(qName, newLqName, newLqRoomNr, newLqSite);
		System.out.println("uppdaterat : " + antal);
	}

}