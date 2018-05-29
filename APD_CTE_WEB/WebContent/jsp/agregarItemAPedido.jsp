<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Alta pedido</title>
</head>
<body>
	<div class="container">
		<h1>Agregar items al Pedido: ${idPedido}</h1>
		<br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Codigo de Barras</th>
					<th>Descripcion</th>
					<th>Presentación</th>
					<th>Tamaño</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articulos}" var="a"> 
			  		<tr>
			      		<td>${a.codDeBarras}</td>
			      		<td>${a.descripcion}</td>
			      		<td>${a.presentacion}</td>
			      		<td>${a.tamano}${a.unidad}</td>
			      		<td>${a.precioDeVenta}</td> 
			      		<td><input type="number" value="0" name="cant${a.id}"><td>
			      		<!-- llevo el id al servlet de borrado por get -->
			      		<td><a href="ServletAgregarItemsPedido?id=${a.id}&cant=$cant${a.id}" class="btn btn-info">Agregar</a><td>   
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>