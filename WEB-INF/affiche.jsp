<%@ page import="com.Utilisateur"%>
<HTML>
<HEAD>
<TITLE>Affiche</TITLE>
</HEAD>
<BODY bgcolor="white">
<%if(request.getAttribute("parent").equals("connexion")){%>
	<h1>Votre login est : <%= session.getAttribute("sessionUtilisateur")%></h1></br>
<%}else{%>
	<h1>Merci de vous être inscrit !
	<h1>Votre login est : <%= session.getAttribute("sessionUtilisateur")%></h1></br>
	<p>Vendez bien !</p>
<%}%>
</BODY> 
</HTML>