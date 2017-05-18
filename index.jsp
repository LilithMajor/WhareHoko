<%@ page import="com.Appartement"%>
<%@ page import="java.util.*"%>
<html>
    <head>
        <meta charset="utf-8" />
        <title>WhareHoko</title>
    </head>
    <body>
		<header>
		<%String parent = (String) request.getAttribute("parent");
		if(parent.equals("accueil")){%>
			<a href="<%=request.getContextPath()+"/connexion"%>">Se connecter</a>
			<a href="<%=request.getContextPath()+"/enregistrement"%>">S'enregister</a>
			<a href="#">Panier</a>
		<%}else{%>
			<a href="<%=request.getContextPath()+"/vendre"%>">Mettre en vente</a>
			<a href="<%=request.getContextPath()+"/deconnexion"%>">Se deconnecter</a>
			<a href="#">Panier</a></br>
			<h1>Bienvenu <%=session.getAttribute("sessionUtilisateur")%></h1>
		<%}%>
			
		</header>
		<div id="Cadre">
			<div id="Search">
				<form method="post" action="searchAppart">
					<fieldset>
						<input type="textfield" id="By_Num" name="login" value="Numero Appartement" size="20" maxlength="60" onClick="eraseInput(this);"/>
						<select name="By_Type">
							<option>STUDIO</option>
							<option>T1</option>
							<option>T2</option>
							<option>T3</option>
						</select>
						<input type="submit" name="Find" value="Search">
					</fieldset>
				</form>
			</div>
			<div id="Apparts">
				<% for(Appartement a : (ArrayList<Appartement>) request.getAttribute("apparts")){%>
					<div>
						<div name="Appart_Infos">
							<%=a.getNum()%>
							<%=a.getAdresse()%>
							<%=a.getTypeAppart()%>
							<%=a.getMontantVente()%>
							<%=a.getDatePublication()%>
							<%=a.getLoginProp()%>
							<a name="test" action="informations"> + Infos </a>
							<a action="informations"> + Infos </a>
						</div>		
					</div>
				<%}%>
			</div>
		</div>
		<script type="text/javascript">
			var eraseInput = (function() {
				var erased = false;
				return function(input) {
					input.value = !erased ? '' : input.value;
						erased = true;
					};
			})();
		</script>
	</body>
</html>
			