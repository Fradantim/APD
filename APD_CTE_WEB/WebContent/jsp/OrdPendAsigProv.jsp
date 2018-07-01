<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
    <!-- Website Font style -->    
	
		
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>Ordenes pendientes de asignacion de proveedores</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
	<%--<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.redirect.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletElegirProveedor(id, descripcion) {
			var ordenId=id;
			var articulo=descripcion;
			var url='ServletOrdPendAsigProv';
			//$(button).prop('disabled',true);
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });
				
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: ordenId, articulo: articulo}
		    	}).done(function (asigProveedores){
					//$(button).prop('disabled',false);
					$.unblockUI();
					console.log(asigProveedores);
					var responseJsonObj = JSON.parse(asigProveedores);
					window.location.replace(responseJsonObj.forwardTo);
					//$.notify({message: 'Proveedor asignado correctamente!'},{type: 'success'});
				}).fail(function(){
					console.log("error");
					window.location.href = "<%=request.getContextPath() %>/jsp/error.jsp";
				});
		}	
	</script>
</head>
<jsp:include page="bannerSuperior_santi.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Orden Numero</th>
						<th>Descripcion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ordenes}" var="o"> 
						<tr>
						   	<td>${o.id}</td>
					      	<td>${o.articulo.descripcion}</td>					      	
					      	<td>
					      		<input id="buttonOrdPendAsigProv_${o.id}" type="button" value="Elegir Proveedor" class="btn btn-info" onclick="callPostServletElegirProveedor(${o.id}, '${o.articulo.descripcion}', '${o.articulo.id});" />
					      	</td>  
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>