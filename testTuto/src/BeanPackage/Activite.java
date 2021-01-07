package BeanPackage;

public class Activite {
	private String nomLieu ;
	private String login ;  //login de la personne qui participe a cette activite
	private String deb ;
	private String fin ;
	private String dateActivite ;
	private int id ;
	
	
	public void setId(int id) {
		this.id = id ;
	}
	public void setNomLieu(String nom) {
		this.nomLieu = nom ;
	}
	
	public void setLogin(String login) {
		this.login = login ;
	}
	
	public void setDebut(String debut) {
		this.deb = debut ;
	}
	
	public void setFin(String fin) {
		this.fin = fin ;
	}
	
	public void setDate(String date) {
		this.dateActivite = date ;
	}
	
	public String getNomLieu() {
		return this.nomLieu ;
	}
	
	public String getLogin() {
		return this.login ;
	}
	
	public String getDeb() {
		return this.deb ;
	}
	
	public String getFin() {
		return this.fin ;
	}
	
	public String getDate() {
		return this.dateActivite;
	}
	
	public int getId() {
		return this.id ;
	}
}
