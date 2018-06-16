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
				
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id, cantidad: cantidad}
		    	}).done(function (respuesta){
					//$(button).prop('disabled',false);
					$.unblockUI();
					$.notify({message: 'Articulo agregado correctamente!'},{type: 'success'});
				}).fail(function(){
					console.log("error");
					window.location.href = "<%=request.getContextPath() %>/jsp/error.jsp";
				});
		}	
	</script>
</head>
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<p style="padding:10px;">Cliente: ${idCliente}</p>
			<p style="padding:10px;">Agregar items al Pedido: ${idPedido}</p>
		
			Pedido Elegido
			<table>
				<thead>
					<tr>
						<th>Orden Id</th>
						<th>Descripcion Articulo</th>
						<th>Cantidad a Ubicar</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					   	<td>${ordenElegida.id}</td>
					   	<td>${ordenElegida.articulo.descripcion}</td>
					   	<td>${ordenElegida.articulo.cantidadUbicable}</td>
					</tr>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>