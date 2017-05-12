<%@ page import="com.Utilisateur"%>
<HTML>
<HEAD>
<TITLE>Affiche</TITLE>
</HEAD>
<BODY bgcolor="white">

<h1>Votre login est : <%= session.getAttribute("sessionUtilisateur")%></h1></br>
<p>On est le : date </p>
</BODY> 
</HTML>