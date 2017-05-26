<%@ page import="com.Appartement"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Proprietaire"%>
<html>
    <head>
        <meta charset="utf-8" />
        <title>WhareHoko</title>
    </head>
    <body>
		<div id="Cadre">
			<table id="apparts">
				<tr>
					<th>Numero</th>
					<th>Adresse</th>
					<th>Type</th>
					<th>Montant</th>
					<th>Date de publication</th>
					<th>Proprietaire</th>
					<th>Selectionner</th>
					<th>Plus d'informations</th>
				</tr>
				<% for(Appartement a : (ArrayList<Appartement>) request.getAttribute("appartsAmoi")){%>
						<tr id="ligne " + <%=a.getNum()%>>
							<td><%=a.getNum()%></td>
							<td><%=a.getAdresse()%></td>
							<td><%=a.getTypeAppart()%></td>
							<td><%=a.getMontantVente()%></td>
							<td><%=a.getDatePublication()%></td>
							<td><%=a.getLoginProp()%></td>
						</tr>		
				<%}%>
				 <form method="post" action="vendre">
					<fieldset>
						<label> Prix : </label> <input type=Number id=prix name=prix> </input></td>
						<input type=submit class="sansLabel"> Déclarer vente </input>
					</fieldset>
				</form>
			<table>
		</div>
	</body>
</html>