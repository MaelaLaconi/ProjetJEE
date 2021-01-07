package BeanPackage;

public class Lieu {
	private int id ;
	private String nom ;
	private String adr ;
	
	public void setId(int id) {
		this.id=id ;
	}
	
	public void setNom(String nom) { this.nom = nom ;}
	
	public void setAdr(String adr) { this.adr = adr ;}
	
	public int getId() { return this.id;}
	public String getAdr() {return this.adr ;}
	public String getNom() {return this.nom ;}

}
