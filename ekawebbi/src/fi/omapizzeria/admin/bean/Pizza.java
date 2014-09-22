package fi.omapizzeria.admin.bean;

public class Pizza {
	
 private int id;
 private String nimi;
 private Double hinta;
 
 public Pizza(int id, String nimi, Double hinta) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
	}
 
public Pizza(String nimi, Double hinta) {
	super();
	this.nimi = nimi;
	this.hinta = hinta;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNimi() {
	return nimi;
}
public void setNimi(String nimi) {
	this.nimi = nimi;
}
public Double getHinta() {
	return hinta;
}
public void setHinta(Double hinta) {
	this.hinta = hinta;
}
@Override
public String toString() {
	return "Pizza [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
}

 
}
