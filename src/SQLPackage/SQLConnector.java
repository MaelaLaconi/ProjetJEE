package SQLPackage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BeanPackage.Activite;
import BeanPackage.Lieu;
import BeanPackage.Notification;
import BeanPackage.UserBean;

public class SQLConnector {
	
		public SQLConnector() { }

		
		public UserBean getUser(String login, String password) {
			
			   UserBean user = null;
			   
			   String rqString = "Select * from User where login='"+login+"' and password='"+password+"';";
			   ResultSet res = doRequest(rqString);
			   int i = 0;
			   try {
				   while(res.next()) {
					   if(i==0) {
						   user = new UserBean();
						   user.setLogin(res.getString("login"));
						   user.setPassword(res.getString("password"));
						   user.setNom(res.getString("nom"));
						   user.setPrenom(res.getString("prenom"));
						   user.setRang(res.getString("role"));
					   }
					   else {
						   i++;
						   arret("Plus d'un utilisateur ayant le meme login ???");
					   }

				   }
				} 
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   return user;
		   }
		   
		public void createUser(String login, String password, String nom, String prenom, String naissance) {
			
			   Connection con = connect();
			   
			    try {
			    	Statement stmt = con.createStatement();
			    	String rqString = "INSERT INTO User (role,login,password,nom,prenom,date_naissance) VALUES ('basic_user','"+
							   login+"','"+password+"','"+prenom+"','"+nom+"','"+naissance+"')";
					stmt.executeUpdate(rqString);
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		
		/**
		 * cree une notification dans la db
		 * @param expediteur
		 * @param receveur
		 * @param type
		 * @param statut
		 */
		public void createNotification(String expediteur, String receveur, String type, String statut) {
			
			   Connection con = connect();
			   
			    try {			    	
			    	
			    	Statement stmt = con.createStatement();
			    	String rqString = "INSERT INTO Notification (expediteur,receveur,type,statut) VALUES ('"+
							   expediteur+"','"+receveur+"','"+type+"','"+statut+"')";
					stmt.executeUpdate(rqString);
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		
	
		
	public void createActivite(String date, String debut, String fin, String nomLieu, String login) {
			
			   Connection con = connect();
			   
			    try {			    	
			    	
			    	Statement stmt = con.createStatement();
			    	String rqString = "INSERT INTO Activite (dateActivite, debut, fin, nomLieu, login) VALUES ('"+date+
			    			"', '"+debut+"', '"+fin+"', '"+nomLieu+"', '"+login+"')";
					stmt.executeUpdate(rqString);
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		   }
	
	public void createLieu(String nom, String adresse) {
		
		   Connection con = connect();
		   
		    try {			    	
		    	
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO Lieu (nom, adresse) VALUES ('"+nom+"', '"+adresse+
		    			"')";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
		
	
	public boolean existLieu(String nom, String date, String debut, String fin, String login) {
		String rqString = "Select * from Activite where UPPER(nomLieu)='"+nom.toUpperCase()+"' and dateActivite='"+
				date+"' and debut='"+debut+"' and fin='"+fin+"' and login='"+login+"';";
		ResultSet resultat = doRequest(rqString);
		   int i = 0;
		   try {
			   while(resultat.next()) {
				   i++ ;
				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return (i!=0);
	}
	
	public boolean existLogin(String login) {
		String rqString = "Select * from User where UPPER(login)='"+login.toUpperCase()+"';";
		ResultSet resultat = doRequest(rqString);
		   int i = 0;
		   try {
			   while(resultat.next()) {
				   i++ ;
				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return (i!=0);
	}
	
	
	/**
	 * recupere toutes les activites entre aujourd'huit et il y a 10 jours
	 * @param nom
	 * @param date
	 * @param debut
	 * @param fin
	 * @param login
	 * @return
	 */
	public List<Activite> getSameDateactivity() {
		String rqString = "Select * from Activite where  dateActivite <= DATE(NOW()) and dateActivite >= DATE_SUB(DATE(NOW()), INTERVAL 10 DAY) ;";
		ResultSet resultat = doRequest(rqString);
		   int i = 0;
		   List<Activite> list = new ArrayList() ;
		   try {
			   while(resultat.next()) {
				   Activite activite = new Activite() ;
				   
				   activite.setNomLieu(resultat.getString("nomLieu"));
				   activite.setLogin(resultat.getString("login"));
				   activite.setDebut(resultat.getString("debut"));
				   activite.setFin(resultat.getString("fin"));
				   activite.setDate(resultat.getString("dateActivite"));
				   activite.setId(resultat.getInt("id"));

				   list.add(activite);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return list;
	}
	
	/**
	 * recupere toutes les activitées d'un utilisateur
	 * @param login
	 * @return
	 */
	public List<Activite> getActivite(String login) {
		String rqString = "Select * from Activite where login='"+login+"';";
		ResultSet resultat = doRequest(rqString);
		   int i = 0;
		   List<Activite> list = new ArrayList() ;
		   try {
			   while(resultat.next()) {
				   Activite activite = new Activite() ;
				   
				   activite.setNomLieu(resultat.getString("nomLieu"));
				   activite.setLogin(resultat.getString("login"));
				   activite.setDebut(resultat.getString("debut"));
				   activite.setFin(resultat.getString("fin"));
				   activite.setDate(resultat.getString("dateActivite"));
				   list.add(activite);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return list;
	}
	
	public List<UserBean> getAllUser() {
		String rqString = "Select * from User;";
		ResultSet resultat = doRequest(rqString);
		   List<UserBean> list = new ArrayList() ;
		   try {
			   while(resultat.next()) {
				   UserBean user = new UserBean() ;
				   user.setRang(resultat.getString("role"));
				   user.setLogin(resultat.getString("login"));
				   user.setNom(resultat.getString("nom"));
				   user.setPrenom(resultat.getString("prenom"));
				   user.setPassword(resultat.getString("password"));
				   user.setDate(resultat.getString("date_naissance"));
				   list.add(user);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return list;
	}
	//***********************************************************
	public List<Lieu> getAllLieux() {
		String rqString = "Select * from Lieu;";
		ResultSet resultat = doRequest(rqString);
		   List<Lieu> list = new ArrayList() ;
		   try {
			   while(resultat.next()) {
				   Lieu lieu = new Lieu() ;
				   lieu.setId(resultat.getInt("id"));
				   lieu.setAdr(resultat.getString("adresse"));
				   lieu.setNom(resultat.getString("nom"));
				 
				   list.add(lieu);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return list;
	}
	public List<Activite> getAllActivites() {
		String rqString = "Select * from Activite;";
		ResultSet resultat = doRequest(rqString);
		   List<Activite> list = new ArrayList() ;
		   try {
			   while(resultat.next()) {
				   Activite activite = new Activite() ;
				   activite.setId(resultat.getInt("id"));
				   activite.setDate(resultat.getString("dateActivite"));
				   activite.setLogin(resultat.getString("login"));
				   activite.setNomLieu(resultat.getString("nomLieu"));
				   activite.setFin(resultat.getString("fin"));
				   activite.setDebut(resultat.getString("debut"));
				   list.add(activite);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return list;
	}
	/**
	 * retourne les activitées au meme moment que la personne covidée
	 * @param login
	 * @return
	 */
	public Activite getActiviteCovid(String nomLieu, String date, String deb, String fin, int id, String login) {
		String rqString = "Select * from Activite where id="+id+" and nomLieu='"+nomLieu+"' and dateActivite ='"+
				date+"' and debut <= '"+fin+"' and fin >='"+deb+"' and login <> '"+login+"';";
		ResultSet resultat = doRequest(rqString);
		   int i = 0;
		   List<Activite> list = new ArrayList() ;
		   Activite activite = null ;

		   try {
			   while(resultat.next()) {
				   activite = new Activite() ;
				   activite.setNomLieu(resultat.getString("nomLieu"));
				   activite.setLogin(resultat.getString("login"));
				   activite.setDebut(resultat.getString("debut"));
				   activite.setFin(resultat.getString("fin"));
				   activite.setDate(resultat.getString("dateActivite"));
				   list.add(activite);

				   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		
		return activite;
	}
	
	
		public boolean notExistNotification(String expediteur, String receveur) {
			String rqString = "Select * from Notification where expediteur='"+expediteur+"' and receveur='"+receveur+"' and type='demandeAmi';";
			ResultSet resultat = doRequest(rqString);
			   int i = 0;
			   try {
				   while(resultat.next()) {
					   i++ ;
					   }
				} 
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (i==0);
		}
		   
		public int getNbNotifUser(String loginUser) {
			String rqString = "Select * from Notification where receveur='"+loginUser+"' and statut='attente' and type='demandeAmi';";
			ResultSet resultat = doRequest(rqString);
			   int i = 0;
			   try {
				   while(resultat.next()) {
					   i++ ;
					   }
				} 
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (i);
		}
		
		public void accepteNotifUser(Notification n) {
			String receveur = n.getReceveur();
			String expediteur = n.getExpediteur();
			String type = n.getType();
			String rqString = "UPDATE Notification SET statut='accepte' WHERE receveur='"+receveur+"' and expediteur ='"+expediteur+"' and type='"+type+"'";
			updateUser(rqString);
		}
		
		public void refuseNotifUser(Notification n) {
			String receveur = n.getReceveur();
			String expediteur = n.getExpediteur();
			String type = n.getType();
			String rqString = "UPDATE Notification SET statut='refuse' WHERE receveur='"+receveur+"' and expediteur ='"+expediteur+"' and type='"+type+"'";
			updateUser(rqString);
		}
		
		//a faire
		public void supprimerAmi(String n) {
			String rqString = "DELETE from Notification WHERE (receveur='"+n+"' or expediteur ='"+n+"') and type='demandeAmi'";
			updateUser(rqString);
		}
		
		public List<Notification> getNotifAttenteAmi(String login){
			List<Notification> list = new ArrayList() ;
			String rqString = "Select * from Notification where receveur='"+login+"' and statut='attente' and type='demandeAmi';";
			ResultSet res = doRequest(rqString);
			Notification notif = null ;
			   int i = 0;
			   try {
				   while(res.next()) {
					   notif = new Notification();
					   notif.setExpediteur(res.getString("expediteur"));
					   notif.setReceveur(res.getString("receveur"));
					   notif.setType(res.getString("type"));
					   notif.setStatut(res.getString("statut"));
					   list.add(notif);
				}
			   }
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (list);
		}
		
		public List<Notification> getNotifCovid(String login){
			List<Notification> list = new ArrayList() ;
			String rqString = "Select * from Notification where receveur='"+login+"' and type='positif' ;";
			ResultSet res = doRequest(rqString);
			Notification notif = null ;
			   int i = 0;
			   try {
				   while(res.next()) {
					   notif = new Notification();
					   notif.setExpediteur(res.getString("expediteur"));
					   notif.setReceveur(res.getString("receveur"));
					   notif.setType(res.getString("type"));
					   notif.setStatut(res.getString("statut"));
					   list.add(notif);
				}
			   }
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (list);
		}
		
		public List<Notification> getNotifHistorique(String login){
			List<Notification> list = new ArrayList() ;
			String rqString = "Select * from Notification where receveur='"+login+
					"' and (statut='refuse' or statut='accepte');";
			ResultSet res = doRequest(rqString);
			Notification notif = null ;
			   int i = 0;
			   try {
				   while(res.next()) {
					   notif = new Notification();
					   notif.setExpediteur(res.getString("expediteur"));
					   notif.setReceveur(res.getString("receveur"));
					   notif.setType(res.getString("type"));
					   notif.setStatut(res.getString("statut"));
					   list.add(notif);
				}
			   }
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (list);
		}
		
		public List<String> getAmis(String login){
			List<String> list = new ArrayList() ;
			String rqString2 = "Select * from Notification where receveur='"+login+"' and statut='accepte' and type='demandeAmi';";
			String rqString1 = "Select * from Notification where expediteur='"+login+"' and statut='accepte' and type='demandeAmi';";
			
			ResultSet res = doRequest(rqString2);
			   int i = 0;
			   try {
				  
				   while(res.next()) {
					   list.add(res.getString("expediteur"));
				}
				   res = doRequest(rqString1);
				   while(res.next()) {
					   String e = res.getString("receveur") ;
					   if(!list.contains(e)) {
						   list.add(e);
					   }
				}
			   }
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			
			return (list);
		}
		   
		   public  ResultSet doRequest(String sql_string) {
			   ResultSet results = null;
			   Connection con = connect();
			   try {
				   Statement stmt = con.createStatement();
				   results = stmt.executeQuery(sql_string);
				} catch (SQLException e) {
				   e.printStackTrace();
				}
			   
			   return results;
		   }
		   
		   /**
		    * permet d'executer une requete
		    * @param sql_string
		    */
		   public void updateUser(String sql_string) {
			   Connection con = connect();
			   try {
				   Statement stmt = con.createStatement();
				   stmt.executeUpdate(sql_string);
				} catch (SQLException e) {
				   e.printStackTrace();
				}
		   }
		   
		   /**
		    * montrer l'utilisteur cherché pour ajouter en ami
		    * @param userSearch
		    * @return null si le login cherché n'exsite pas, un UserBean sinon
		    */
		   public UserBean showFriend(String userSearch) {
			   UserBean user = null;
			   
			   String rqString = "Select * from User where login='"+userSearch+"';";
			   ResultSet res = doRequest(rqString);
			   int i = 0;
			   try {
				   while(res.next()) {
					   if(i==0) {
						   user = new UserBean();
						   user.setLogin(res.getString("login"));
						   user.setPassword(res.getString("password"));
						   user.setNom(res.getString("nom"));
						   user.setPrenom(res.getString("prenom"));
						   user.setRang(res.getString("role"));
					   }
					   else {
						   i++;
						   arret("Plus d'un utilisateur ayant le meme login ???");
					   }

				   }
				} 
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   return user;
			   
		   }
		   
		 
		   //FONCTION POUR LES ADMINISTRATEUR
		   
		   public void deleteUser(String login) {
	    
	            String rqString = "DELETE FROM User WHERE login = '" + login + "'; ";
				updateUser(rqString);
		   }
		   
		   public void deleteActivite(int id) {
                    String rqString = "DELETE FROM Activite WHERE id = " + id + " ; ";
                    updateUser(rqString);
		   } 
		   
		   public void deleteLieu(int id) {
               String rqString = "DELETE FROM Lieu WHERE id = " + id + " ; ";
               updateUser(rqString);
	   } 
		 
		   /**
		    * connection a la db
		    * @return null si connection echouée, un objet Connection sinon
		    */
		   public Connection connect() {
			   
			   Connection con = null;
			   
			   try {
				   Class.forName("com.mysql.cj.jdbc.Driver");
			   }
			   catch (ClassNotFoundException e) {
			         arret("Impossible de charger le pilote jdbc");
			   }

			   //affiche("connexion a la base de données");
			   
			   try {
			         String DBurl = "jdbc:mysql://localhost/Jee_database";
			         con = DriverManager.getConnection(DBurl,"projetTest","projetTest");
			         //affiche("connexion réussie");
			   } 
			   catch (SQLException e) {
			         arret("Connection à la base de données impossible");
			   }
			   
			   return con;
		   }
		   
		   
		   
		   private static void affiche(String message) {
			      System.out.println(message);
		   }

		   
		   
		   private static void arret(String message) {
			      System.err.println(message);
			      System.exit(99);
		   }
}
