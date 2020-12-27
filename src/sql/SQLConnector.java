package sql;

import java.sql.*;

import beans.Utilisateur;

public class SQLConnector {
	
		public SQLConnector() { }

		
		public Utilisateur getUser(String login, String password) {
			
			Utilisateur user = null;
			   
			   String rqString = "Select * from User where login='"+login+"' and password='"+password+"';";
			   ResultSet res = doRequest(rqString);
			   int i = 0;
			   try {
				   while(res.next()) {
					   if(i==0) {
						   user = new Utilisateur();
						   user.setLogin(res.getString("login"));
						   user.setMotDePasse(res.getString("password"));
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
			    	Statement stmt = con.createStatement();
			    	String rqString = "INSERT INTO Utilisateur (role,login,password,nom,prenom) VALUES ('basic_user','"+
							   login+"','"+password+"','"+prenom+"','"+nom+"')";
					stmt.executeUpdate(rqString);
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
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
		   
		 
		   public Connection connect() {
			   
			   Connection con = null;
			   
			   /* chargement driver*/
			   try {
				   Class.forName("com.mysql.cj.jdbc.Driver");
			   }
			   catch (ClassNotFoundException e) {
			         arret("Impossible de charger le pilote jdbc");
			   }

			   affiche("connexion a la base de données");
			   
			   try {
				     //localhost:3306 ou pas ?
			         String DBurl = "jdbc:mysql://localhost/PROJET";
			         con = DriverManager.getConnection(DBurl,"root","smgo14");
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
