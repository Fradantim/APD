<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Procesar Pedidos Pendientes Stock Despacho</title>
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
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<p style="padding:10px;">Procesar Pedidos Pendientes de Stock</p>
		
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nro Pedido</th>
						<th>Fecha Generacion</th>
						<th>Monto</th>
						<th>Nro Cliente</th>
						<th>Saldo</th>
						<th>Condicion Financiera</th>
						<th>Evaluar Stock</th>
						<th>Procesar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidos}" var="p"> 
						<tr>
							<form id="formArticulo_${p.id}">
								<td>${p.id}</td>
						   		<td>${p.fechaGeneracion}</td>
					      		<td>${p.total}</td>
					      		<td>${p.cliente.id}</td>
					      		<td>${p.cliente.saldo}</td>
					      		<td>${p.cliente.condicionFinanciera}</td> 
					      		<td>
					      			<input id="buttonEvaluarStock_${p.id}" type="button" value="Evaluar Stock" class="btn btn-info" onclick="callGetServletEvaluarStock(${p.id});" />
					      		</td> 
					      		<td>
					      			<input id="buttonProcesarPedido_${p.id}" type="button" value="Procesar" class="btn btn-info" onclick="callGetServletProcesarPedido(${p.id});" />
					      		</td>  
				      		</form> 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>