package BeanPackage;

public class Notification {
	private String receveur;
	private String expediteur;
	private String type;  //notification demande, notification suppression...
	private String statut;  //en attente, accepte, refuse

	public Notification() {}

	public String getReceveur() {
		return this.receveur;
	}
	
	public String getExpediteur() {
		return this.expediteur;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getStatut() {
		return this.statut;
	}
	
	
	public void setReceveur(String r) {
		this.receveur = r;
	}
	
	public void setExpediteur(String e) {
		this.expediteur = e;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public void setStatut(String s) {
		this.statut = s;
	}
}
