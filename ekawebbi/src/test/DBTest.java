package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {

	public static void main(String[] args) {
		
	//TIETOKANTAHAKU
		
		String username = "a1102090";
		String password = "dyDE9B32v";
		String url = "jdbc:mariadb://localhost/a1102090";

		Connection yhteys = null;
		
		try {
			//YHTEYDEN AVAUS JA HAKU
			//ajurin lataus
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			//avataan yhteys
			yhteys = DriverManager.getConnection(url, username, password);
			
			//suoritetaan haku
			String sql = "select id, nimi, hinta from pizza";
			Statement haku = yhteys.createStatement();
			ResultSet tulokset = haku.executeQuery(sql);
			
			//käydään hakutulokset läpi
			while(tulokset.next()) {
				int id = tulokset.getInt("id");
				String nimi = tulokset.getString("nimi");
				Double hinta = tulokset.getDouble("hinta");
				
				//tulostetaan yksittäinen hakutulos responseen
				System.out.println(id +" "+nimi+" "+hinta);
			}
			
		} catch(Exception e) {
			//JOTAIN VIRHETTÄ TAPAHTUI
			e.printStackTrace();
			System.out.println("VIRHE");
		} finally {
			//LOPULTA AINA SULJETAAN YHTEYS
			try {
				if (yhteys != null && !yhteys.isClosed())
					yhteys.close();
			} catch(Exception e) {
				System.out.println("Tietokantayhteys ei jostain syystä suostu menemään kiinni.");
			}
		}

	}

}
