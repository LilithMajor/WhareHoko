<%@ page import="com.Appartement"%>
<%@ page import="java.util.*"%>
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
					<th>Etat</th>
				</tr>
				<% ArrayList<Appartement> apparts = (ArrayList<Appartement>) request.getAttribute("panier");
				for(Appartement a : apparts){%>
						<tr id="ligne " + <%=a.getNum()%>>
						<%int vendu = a.getVendu();%>
							<td><%=a.getNum()%></td>
							<td><%=a.getAdresse()%></td>
							<td><%=a.getTypeAppart()%></td>
							<td><%=a.getMontantVente()%></td>
							<td><%=a.getDatePublication()%></td>
							<td><%=a.getLoginProp()%></td>
							<td><%if(vendu==0){
								%>En vente</td>
							<%
							}else{%>Vendu</td><%}
							%>
						</tr>		
				<%}%>
			<table>
			<a href="<%=request.getContextPath()+"/index"%>"><input type="submit" value="Retour a l'accueil" ></input></a>
		</div>
	</body>
</html>