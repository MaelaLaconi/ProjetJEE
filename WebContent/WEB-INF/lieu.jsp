<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="BeanPackage.UserBean" %>

<!DOCTYPE html>
<head>

  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

    <title>Lieu</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

</head>
<body>
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<form methode="post" action="bean_servlet" id="formHome">
      	<a class="navbar-brand" href="#" onclick="document.getElementById('formHome').submit()">Home</a>
      </form>
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
          <%if(current_user.getRang().equals("admin")){ %>
            <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <form methode="post" action="showAllUsers" id="editUser">
            	<a class="dropdown-item" href="#" onclick="document.getElementById('editUser').submit()">Utilisateur</a>
              </form>
              <form methode="post" action="showAllActivites" id="editActivite">
              	<a class="dropdown-item" href="#" onclick="document.getElementById('editActivite').submit()">Activite</a>
              </form>
              <form methode="post" action="showAllLieux" id="editLieu">
              	<a class="dropdown-item" href="#" onclick="document.getElementById('editLieu').submit()">Lieu</a>
              </form>
            </div>
          </li>
          <%} %>
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
          <h1 class="display-3">Nouveau lieu</h1>
        </div>
      </div>


     <div class="container">
     	<div class="row">
     	  	<h1>Le lieu que vous avez entré n'est dans la liste des lieux proposés pour cette activité !</h1>
     	  	<hr>
     	  	</br>
     	  	</br>
     	  	<div class="col-md-12">
     	  		<form action="saveLieu" method="post">
     	  	
	     	  		<h3>Déclaré une activité</h3>
					</br>
					<h4> Veuillez saisir l'adresse</h4>
					<input name="adr" type="text" required="required"/>

     	  		   <button class="btn btn-primary" type="submit">Enregistrer</button>
				</form>
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