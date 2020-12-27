<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TestTuto</title>
</head>
<body> 
	<p>Page depuis JSP</p>
	<p>
	 	<% 
            String attribut = (String) request.getAttribute("test");
            out.println( attribut );
            
            String parametre = request.getParameter( "auteurNom" );
            out.println( parametre );
        %>
    </p>
     <p>
            Récupération du bean :
            <%	
	    	beans.User notreBean = (beans.User) request.getAttribute("coyote");
	    	out.println( notreBean.getPrenom() );
            out.println( notreBean.getNom() );
            %>
            
            Avec balise jsp :
            <jsp:useBean id="coyote" class="beans.User"/>
            <jsp:getProperty name="coyote" property="prenom" />
            <jsp:setProperty name="coyote" property="prenom"  value="nouveauNom"/>
            Autre :
            <jsp:setProperty name="coyote" property="prenom" param="prenomCoyote"/>
            <%= notreBean.getPrenom() %>
        </p>
        <p>
        Encore jsp
        <!-- Initialisation d'un bean de type Coyote avec une action standard, pour l'exemple : -->
        <jsp:useBean id="coyote2" class="beans.User"/>
        <!-- Initialisation de sa propriété 'prénom' : -->
        <jsp:setProperty name="coyote2" property="prenom" value="Monsieur"/>
        <!-- Et affichage de sa valeur : -->
        <jsp:getProperty name="coyote2" property="prenom" />
        <!-- Deuxieme facon affichage -->
        ${ coyote2.prenom }
        ${ !empty coyote2.prenom ? coyote2.prenom : "Veuillez préciser un prénom" }
        </p>
</body>
</html>