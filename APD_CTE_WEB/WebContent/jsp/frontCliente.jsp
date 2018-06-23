<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
	<title>Agregar Items a pedido</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			var urlFacturasInpagas='<%=request.getContextPath() %>/ServletFrontClienteFacturasInpagas';
        	$.ajax({
		        url: urlFacturasInpagas,
		        type: "get",
		        data: {id: $("#idCliente").val()}
		    	}).done(function (data){
		    		$('#facturasInpagas').load("<%=request.getContextPath() %>/jsp/tablaFacturas.jsp");
				}).fail(function(){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
        	var urlPedidosPendientes='<%=request.getContextPath() %>/ServletFrontClientePedidosPendientes';
        	$.ajax({
		        url: urlPedidosPendientes,
		        type: "get",
		        data: {id: $("#idCliente").val()}
		    	}).done(function (data){
		    		$('#pedidosPendientes').load("<%=request.getContextPath() %>/jsp/tablaPedidos.jsp");
				}).fail(function(){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
        	var urlPedidoAbierto='<%=request.getContextPath() %>/ServletFrontClientePedidoAbierto';
        	$.ajax({
		        url: urlPedidoAbierto,
		        type: "get",
		        data: {id: $("#idCliente").val()}
		    	}).done(function (data){
		    		$('#pedidoAbierto').load("<%=request.getContextPath() %>/jsp/tablaPedidoAbierto.jsp");
				}).fail(function(){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
   		}
	</script>
</head>
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container-fluid">

		<table class="table table-sm table-responsive">
			<tbody>
				<tr>
					<th><b> Usuario </b></th>
					<td>${cliente.razonSocial}</td>
					<td><b> ID Cliente</b></td>
					<td>${cliente.id}</td>

				</tr>
				<tr>
					<th><b> ${cliente.tipoDocumento} </b></th>
					<td>${cliente.documento}</td>
					<td><b> Telefono </b></td>
					<td>${cliente.telefono}</td>
				</tr>
				<tr class="${cliente.saldo>0 ? 'danger' : 'success'}">
					<th><b> Condicion Financiera: </b></th>
					<td>${cliente.condicionFinanciera}</td>
					<td><b> Saldo adeudado </b></td>
					<td>${cliente.saldo}</td>
				</tr>
			</tbody>
		</table>

		<div class="row-fluid">
			<input type="hidden" id="idCliente" value="${cliente.id}">

			<br>
			<h3>Facturas Inpagas:</h3>
			<div id="facturasInpagas"> 
				Cargando...
				<div>
					<img src="<%=request.getContextPath() %>/gifs/loading_cat.gif" />
				</div>
			</div>
			
			
			<h3>Pedido Abierto:</h3>
			<div id="pedidoAbierto"> 
				Cargando...
			</div>
			
			
			<h3>Pedidos Pendientes:</h3>
			<div id="pedidosPendientes">
				Cargando...
			</div>
			
		</div>	
	</div>
</body>
</html>