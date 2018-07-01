<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Aprobar Pedidos Pendientes de Aprobacion Crediticia</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		$(function () {
		    $(document).on('click', '.btn', function (event) {
		        event.preventDefault();
		        $(this).closest('tr').remove();
		    });
		});	
		function callPostServletAdminCredAprobar(id) {
			var motivo=$("#motivo_"+id).val();
			var url='ServletAdminCred';
			var metodo="Aprobar"
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Procesando...</center>' });
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id, motivo: motivo,metodo:metodo}
		    	}).done(function (data){
					$.unblockUI();
					$.notify({message: 'Pedido Procesado Correctamente'},{type: 'success'});
				}).fail(function(){
					console.log("error");
					window.location.href = "<%=request.getContextPath() %>/jsp/error.jsp";
				});
		}	
		function callPostServletAdminCredRechazar(id) {
			var motivo=$("#motivo_"+id).val();
			var url='ServletAdminCred';
			var metodo="Rechazar"
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Procesando...</center>' });
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id, motivo: motivo, metodo:metodo}
		    	}).done(function (respuesta){
					$.unblockUI();
					$.notify({message: 'Pedido Rechazado Correctamente'},{type: 'success'});
				}).fail(function(){
					console.log("error");
					window.location.href = "<%=request.getContextPath() %>/jsp/error.jsp";
				});
		}	

	</script>
</head>
<jsp:include page="bannerSuperiorAdminCte.jsp"></jsp:include>
<body>
	<h4><b>Pedidos Pendientes de Aprobacion Crediticia</b></h4>
	<div class="container-fluid">
		<div class="row-fluid">
			<p style="padding:10px;"> </p>
		
			<table id=tabla class="table table-striped">
				<thead>
					<tr>
						<th>Nro Pedido</th>
						<th>Fecha de Generacion</th>
						<th>Total</th>
						<th>Nro Cliente</th>
						<th>Saldo Cliente</th>
						<th>Cond. Financiera</th>
						<th>Motivo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidospen}" var="a"> 
						<tr>
						   	<td>${a.id}</td>
						   	<td><fmt:formatDate value="${a.fechaGeneracion}" pattern="yyyy-MM-dd"/></td>
					      	<td>${a.total}</td>
							<td>${a.cliente.id}</td> 
							<td>${a.cliente.saldo}</td>
							<td>${a.cliente.condicionFinanciera}</td>
							<td><input style=max-width:150px  type="text" value=" " name="motivo" id="motivo_${a.id}"></td>
					      	<td>
					      		<input id="buttonAprobarPedido_${a.id}" type="button" value="Aprobar Pedido" class="btn btn-info" onclick="callPostServletAdminCredAprobar(${a.id});" />
					      	</td>  
			
					      	<td>
					      		<input id="buttonRechazarPedido_${a.id}" type="button" value="Rechazar Pedido" class="btn btn-warning" onclick="callPostServletAdminCredRechazar(${a.id});" />
					      	</td>  
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>