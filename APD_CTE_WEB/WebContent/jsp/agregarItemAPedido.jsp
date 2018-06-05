<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Agregar Items a pedido</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callGetServletAgregarItemsPedido(id) {
			//console.log(id);
			var button='#buttonAgregarItemAPedido_'+id;
			var form='#formArticulo_'+id;
			var url='ServletAgregarItemsPedido?id='+id;
			//?id=${a.id}&cantidad=
				
			//console.log(button);
			//alert(url);
			$(button).prop('disabled',true);
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });
			$.get(url, $(form).serialize(),
				function (respuesta){
					$(button).prop('disabled',false);
					$.unblockUI();
					$.notify({
						message: 'Articulo agregado correctamente!' 
					},{
						type: 'success'
					});
				}
			)
		}	
	</script>
</head>
<body>
	<div class="container">
		<h1>Cliente: ${idCliente}</h1>
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
					<th>
						<input id="buttonCerrarPedido_${idPedido}" type="button" value="Cerrar Pedido" class="btn btn-warning" onclick="" />
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articulos}" var="a"> 
					<tr>
						<form id="formArticulo_${a.id}">
					   		<td>${a.codDeBarras}</td>
				      		<td>${a.descripcion}</td>
				      		<td>${a.presentacion}</td>
				      		<td>${a.tamano}${a.unidad}</td>
				      		<td>${a.precioDeVenta}</td> 
				      		<td><input type="number" value="0" name="cantidad_${a.id}" id="cantidad_${a.id}"></td>
				      		<td>
				      			<input id="buttonAgregarItemAPedido_${a.id}" type="button" value="Agregar Item" class="btn btn-info" onclick="callGetServletAgregarItemsPedido(${a.id});" />
				      		</td>  
			      		</form> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>