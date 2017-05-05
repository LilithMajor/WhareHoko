<%@ page import="com.Utilisateur"%>
<HTML>
<HEAD>
<TITLE>Affiche</TITLE>
</HEAD>
<BODY bgcolor="white">
<%  
    Utilisateur user = (Utilisateur) session.getAttribute("sessionUtilisateur");
%>	
<h1>Votre adresse e-mail est : <%= user.getEmail()%></h1></br>
<p>On est le : <%= session.getAttribute("date")%>
</BODY> 
</HTML>