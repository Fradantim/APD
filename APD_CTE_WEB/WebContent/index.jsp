<html lang="en">
<head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">

		<!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<title>Das Verrückte Lagerhaus login</title>
</head>
<body>

	<h1>Das Verrückte Lagerhaus TestMenu</h1>
	<h1>--------------------------------------------------------------------</h1>
	Nuevo Pedido
	<form method="get"
		action="<%=request.getContextPath()%>/jsp/altaPedido.jsp">
		<div class="form-group">		</div>
		<button type="submit" class="btn btn-default">Alta Pedido</button>
	</form>
	<h1>--------------------------------------------------------------------</h1>
	Agregar Items a pedido
	<form method="post"
		action="<%=request.getContextPath()%>/ServletAgregarItemsPedido">
		<div class="form-group">
			<label for="idPedido">idPedido</label> 
			<input type="text" class="form-control" id="idPedido" name="idPedido">
		</div>
		<button type="submit" class="btn btn-default">Agregar Items</button>
	</form>	
	
</body>
</html>