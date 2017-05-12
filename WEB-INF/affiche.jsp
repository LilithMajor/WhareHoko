<%@ page import="com.Proprietaire"%>
<HTML>
<HEAD>
<TITLE>Affiche</TITLE>
</HEAD>
<BODY bgcolor="white">
<%if(request.getAttribute("parent").equals("connexion")){%>
<% Proprietaire prop = (Proprietaire) session.getAttribute("sessionUtilisateur");%>
	<h1>Votre nom est : <%=prop.getNom()%></h1></br>
	<h1>Votre login est : <%=prop.getLogin()%></h1></br>
	<h1>Votre mot de passe est : <%=prop.getMotdepasse()%></h1></br>
	<h1>Votre email est : <%=prop.getEmail()%></h1></br>
<%}else{%>
	<h1>Merci de vous être inscrit !
	<h1>Votre login est : <%= session.getAttribute("sessionUtilisateur")%></h1></br>
	<p>Vendez bien !</p>
<%}%>
</BODY> 
</HTML>