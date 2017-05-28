<%@ page import="com.Appartement"%>
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
				</tr>
				<% Appartement a = (Appartement) request.getAttribute("appartaVendre");%>
				<tr id="ligne " + <%=a.getNum()%>>
					<td><%=a.getNum()%></td>
					<td><%=a.getAdresse()%></td>
					<td><%=a.getTypeAppart()%></td>
					<td><%=a.getMontantVente()%></td>
					<td><%=a.getDatePublication()%></td>
					<td><%=a.getLoginProp()%></td>
				</tr>		
			<table>
				 <form method="post" action="declarervente">
					<fieldset>
						<label> Prix : </label> <input type="Number" id="prix" name="prix"> </input></td>
						<input type="hidden" name="id" value="<%=a.getNum()%>"></input>
						<input type="submit" value="Déclarer vente"></input>
					</fieldset>
				</form>
		</div>
	</body>
</html>