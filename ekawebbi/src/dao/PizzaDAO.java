package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fi.omapizzeria.admin.bean.Pizza;


public class PizzaDAO {
	
	Connection yhteys = null;
	
	public Connection avaaYhteys(){
		
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
	
	public Connection suljeYhteys(Connection yhteys){
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			System.out.println("Tietokantayhteys ei jostain syystä suostu menemään kiinni.");
			e.printStackTrace();
		}
		return yhteys;
	}
	
	public List<Pizza> haePizzat(){
		avaaYhteys();
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
		suljeYhteys(yhteys);
		return Pizzat;
	}

	public void lisaaPizza(Pizza pizza) {
		
		avaaYhteys();
		try {
			
			//alustetaan sql-lause
			String sql = "insert into pizza(nimi, hinta) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			
			//täytetään puuttuvat tiedot
			lause.setString(1, pizza.getNimi());
			lause.setDouble(2, pizza.getHinta());
			
			//suoritetaan lause
			lause.executeUpdate();
			System.out.println("LISÄTTIIN HENKILÖ TIETOKANTAAN: "+pizza);
		} catch(Exception e) {
			//JOTAIN VIRHETTÄ TAPAHTUI
		}finally {
			suljeYhteys(yhteys);
		}
		
	}

	public void poistaPizza(String kolmas) {
		avaaYhteys();
		System.out.println(kolmas);
		
		
		 int id = 0 ;
		 
         try {
                 id = Integer.parseInt(kolmas);
         } catch (NumberFormatException e) {
                 e.printStackTrace();
         }
		
		try {
			
			//alustetaan sql-lause
			String sql = "delete from pizza where id="+id+";";
			PreparedStatement lause = yhteys.prepareStatement(sql);

			//täytetään puuttuvat tiedot
		
			
			//suoritetaan lause
			lause.executeUpdate();
			System.out.println("POISTETTIIN PIZZA TIETOKANNASTA!");
		} catch(Exception e) {
			//JOTAIN VIRHETTÄ TAPAHTUI
		}finally {
			suljeYhteys(yhteys);
		}
		
	}
}
