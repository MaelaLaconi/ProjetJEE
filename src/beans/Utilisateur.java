package beans;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String email ;
	private String motDePasse ;
	
	
	public void setNom( String nom ) {
		this.nom = nom;
	}

	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}
	
	public void setEmail( String email ) {
		this.email = email;
	}

	public void setMotDePasse( String mdp ) {
		this.motDePasse = mdp;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getMotDePasse() {
		return this.motDePasse;
	}
}
