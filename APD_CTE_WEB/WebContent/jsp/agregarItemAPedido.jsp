<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Agregar Items a pedido</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletAgregarItemsPedido(id) {
			var cantidad=$("#cantidad_"+id).val();
			var url='ServletAgregarItemsPedido';
			//$(button).prop('disabled',true);
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });
			console.log('cantidad '+cantidad);	
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id, cantidad: cantidad}
		    	}).done(function (respuesta){
					//$(button).prop('disabled',false);
					$.unblockUI();
					$.notify({message: 'Articulo agregado correctamente!'},{type: 'success'});
				}).fail(function(){
					$.unblockUI();
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
		}
		function callPostServletAvanzarPedido() {
			var url='ServletAvanzarPedido';
			//$(button).prop('disabled',true);
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });				
			$.ajax({
		        url: url,
		        type: "post"
		    	}).done(function (data){
					//$.unblockUI();
					var responseJsonObj = JSON.parse(data);
					$.notify({message: responseJsonObj.message},{type: 'success'});
					//console.log(data);
					setTimeout(function() {
						window.location.replace(responseJsonObj.forwardTo);
					}, 3000);
				}).fail(function(){
					$.unblockUI();
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
		}
	</script>
</head>
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<p style="padding:10px;">Cliente: ${cliente.id}</p>
			<p style="padding:10px;">Agregar items al Pedido: ${pedidoAbierto.id}</p>
		
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
							<input id="buttonCerrarPedido_${idPedido}" type="button" value="Cerrar Pedido" class="btn btn-warning" onclick="callPostServletAvanzarPedido()" />
						</th>
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
					      	<td><input style="max-width: 60px;"type="number" value="0" name="cantidad_${a.id}" id="cantidad_${a.id}"></td>
					      	<td>
					      		<input id="buttonAgregarItemAPedido_${a.id}" type="button" value="Agregar Item" class="btn btn-info" onclick="callPostServletAgregarItemsPedido(${a.id});" />
					      	</td>  
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>