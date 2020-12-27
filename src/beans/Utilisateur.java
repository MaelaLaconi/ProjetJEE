package beans;

public class Utilisateur {
	private String login ;
	private String nom;
	private String prenom;
	private String email ;
	private String motDePasse ;
	private String rang ;
	
	public void setLogin(String login) {
		this.login = login ;
	}
	
	public void setRang(String rang) {
		this.rang = rang ;
	}
	
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
	
	public String getLogin() {
		return this.login ;
	}
	
	public String getRang() {
		return this.rang ;
	}
}
