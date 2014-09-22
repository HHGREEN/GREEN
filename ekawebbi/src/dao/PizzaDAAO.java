package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fi.omapizzeria.admin.bean.Pizza;

public class PizzaDAAO {

static Connection yhteys = null;
	
	public static Connection avaaYhteys(){
		
		String username = "a1102090";
		String password = "dyDE9B32v";
		String url = "jdbc:mariadb://localhost/a1102090";
		
		try {
		
		//ajurin lataus
		Class.forName("org.mariadb.jdbc.Driver").newInstance();
		//avataan yhteys
		yhteys = DriverManager.getConnection(url, username, password);
		}  catch(Exception e) {
			//JOTAIN VIRHETTÄ TAPAHTUI
			System.out.println("Tietokantahaku aiheutti virheen");
			e.printStackTrace();
		}
		return yhteys;
	}
	
	public static Connection suljeYhteys(Connection yhteys){
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			System.out.println("Tietokantayhteys ei jostain syystä suostu menemään kiinni.");
			e.printStackTrace();
		}
		return yhteys;
	}
	
	public static List<Pizza> haePizzat(Connection yhteys){
		
		List<Pizza> Pizzat = new ArrayList<Pizza>();
		//suoritetaan haku
		try{
		String sql = "select id, nimi, hinta from pizza";
		Statement haku = yhteys.createStatement();
		ResultSet tulokset = haku.executeQuery(sql);
		
		//käydään hakutulokset läpi
		while(tulokset.next()) {
			int id = tulokset.getInt("id");
			String nimi = tulokset.getString("nimi");
			Double hinta = tulokset.getDouble("hinta");
			
			//Lisätään Pizza listaan
			Pizza p = new Pizza(id, nimi, hinta);
			
			Pizzat.add(p);
		}
	} catch(Exception e) {
		//JOTAIN VIRHETTÄ TAPAHTUI
		System.out.println("Tietokantahaku aiheutti virheen");
		e.printStackTrace();
		
		}
		return Pizzat;
	}
	
	
	public static void main(String[] args) {
		avaaYhteys();
		haePizzat(yhteys);
		suljeYhteys(yhteys);

	}

}
