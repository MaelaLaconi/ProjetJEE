<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ page import="BeanPackage.UserBean" %>
<%@ page import="BeanPackage.Notification" %>

<%@ page import="SQLPackage.SQLConnector" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<!DOCTYPE html>
<head>

  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

    <title>Notifications</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

</head>
<body>
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
          <% UserBean current_user = (UserBean) session.getAttribute("current_user"); %>
          	<form methode="post" action="showNotif" id="my_form">
            	<a class="nav-link" href="#" onclick="document.getElementById('my_form').submit()">Notification <% out.print(current_user.getNbNotif()); %><span class="sr-only">(current)</span></a>
          	</form>
          </li>
          <li class="nav-item active">
          	<form methode="post" action="showAmis" id="form_ami">
            	<a class="nav-link" href="#" onclick="document.getElementById('form_ami').submit()">Mes amis<span class="sr-only">(current)</span></a>
          	</form>
          </li>
          <li class="nav-item active">
          	<form methode="post" action="showActivite" id="form_activite">
            	<a class="nav-link" href="#" onclick="document.getElementById('form_activite').submit()">Activité<span class="sr-only">(current)</span></a>
          	</form>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <form methode="post" action="showAllUsers" id="editUser">
            	<a class="dropdown-item" href="#" onclick="document.getElementById('editUser').submit()">Utilisateur</a>
              </form>
              <a class="dropdown-item" href="#">Activite</a>
              <a class="dropdown-item" href="#">Lieu</a>
            </div>
          </li>
        </ul>
        
        <!-- Recherche utilistateur -->
        <form class="form-inline my-2 my-lg-0" method="post" action="recherche">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="recherche">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <div class="container">
          <h1 class="display-3">Tous les utilisateurs</h1>
          <p>Visionner tous les utilisateurs de covid19 !</p>
        </div>
      </div>


     <div class="container">
     	<div class="row">
     	  	<hr>
     	  	</br>
     	  	</br>
     	  	<div class="col-md-12">
     	  	
     	  	<form method="post" id="form_supp" action="deleteUser">
 	  	        <input type="hidden" name="idUser1" id="idUser1" value=""/>
     	  	</form>
 			<form method="post" id="form_modif" action="accepteNotif">
           		<input type="hidden" name="idUser" id="idUser" value=""/>
     	  	</form>
        		<table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable" >
           
             <%
 				SQLConnector sc = new SQLConnector();
            	List list = sc.getAllUser();           
	            for(int i = 0 ; i < list.size() ; i++){       
            %>
            <tr>
            	<td>
            		<% UserBean user = (UserBean)list.get(i);
            		out.print(user.getLogin()); %> 
            	</td>
            	<td>
            		<button type="submit" name="expe" id =<%out.print(i);%> class="btn btn-secondary" form="form_modif" onclick="setNotif1(this)">Modifier</button>
            		<script>
						function setNotif(e) {
						    document.getElementById("idUser1").value = e.id ;
						}
						
						function setNotif1(e) {
						    document.getElementById("idUser").value = e.id ;
						}
					</script>
            	</td>
            	<td>
            	    <button id =<%out.print(i);%> type="submit" name="refuse" class="btn btn-danger" form="form_supp" onclick="setNotif(this)">Supprimer</button>
            	</td>
            </tr>
            <% } %>
            
            </table>
     	  	</div>
         <hr>
     	</div>
     </div>
	<hr>
	</br>
    </main>

    <footer class="container">
      <p>&copy; Company 2017-2018</p>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>

</html>