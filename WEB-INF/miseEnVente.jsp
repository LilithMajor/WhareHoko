<html>
    <head>
        <meta charset="utf-8" />
        <title>Mise en Vente</title>
    </head>
    <body>
		<div id="Cadre">
			<form method="post" action="CreateAppart">
				<fieldset>
					<label>Adresse : </label> <input type="textfield" id="adresse" name="adresse" size="20" maxlength="75"/>
					<select name="type">
						<option>STUDIO</option>
						<option>T1</option>
						<option>T2</option>
						<option>T3</option>
					</select>
					<label>Prix : </label> <input type="Number" id="prix" name="prix"/>
					<input type="submit" name="Find" value="Creer">
				</fieldset>
			</form>
	</body>
</html>