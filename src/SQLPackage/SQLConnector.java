package SQLPackage;
import java.sql.*;

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
		   
		public void createUser(String login, String password, String nom, String prenom) {
			
			   Connection con = connect();
			   
			    try {
			    	java.sql.Timestamp date = new java.sql.Timestamp(1999, 9, 25, 00, 00, 00, 00) ;
			    	
			    	
			    	Statement stmt = con.createStatement();
			    	String rqString = "INSERT INTO User (role,login,password,nom,prenom,date_naissance) VALUES ('basic_user','"+
							   login+"','"+password+"','"+prenom+"','"+nom+"','"+date+"')";
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
		
		public boolean notExistNotification(String expediteur, String receveur) {
			String rqString = "Select * from Notification where expediteur='"+expediteur+"' and receveur='"+receveur+"';";
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
			String rqString = "Select * from Notification where receveur='"+loginUser+"';";
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
		    * monter l'utilisteur chercher pour ajouter en ami
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

			   affiche("connexion a la base de données");
			   
			   try {
			         String DBurl = "jdbc:mysql://localhost/Jee_database";
			         con = DriverManager.getConnection(DBurl,"projetTest","projetTest");
			         affiche("connexion réussie");
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
