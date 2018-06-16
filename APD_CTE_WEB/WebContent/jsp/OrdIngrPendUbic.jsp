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
		function callPostServletOrdIngrPendUbic(id) {
			var url='ServletOrdIngrPendUbic';
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });	
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id}
		    	}).done(function (data){
					//$(button).prop('disabled',false);
					$.unblockUI();
					var responseJsonObj = JSON.parse(data);
					window.location.replace(responseJsonObj.forwardTo);
				}).fail(function(data){
					$.unblockUI();
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
					
				});
		}	
	</script>
</head>
<jsp:include page="bannerSuperiorFDT.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Codigo de Orden</th>
						<th>Descripcion Articulo</th>
						<th>Cantidad Comprada</th>
						<th>Cantidad Ubicable por Articulo</th>
						<th>Ubicaciones necesarias</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ordenes}" var="o"> 
						<tr>
						   	<td>${o.id}</td>
					      	<td>${o.articulo.descripcion}</td>
					      	<td>${o.cantidad}</td>
					      	<td>${o.articulo.cantidadUbicable}</td>
					      	<td>
					      		${o.cantidad/o.articulo.cantidadUbicable+(1-(o.cantidad/o.articulo.cantidadUbicable%1))%1}
					      	</td> <%-- Choclazo para tomar el techo de la division --%>
					      	<td>
					      		<input id="buttonUbicarOrden_${o.id}" type="button" value="Elegir ubicaciones" class="btn btn-info" onclick="callPostServletOrdIngrPendUbic(${o.id});" />
					      	</td>  
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>