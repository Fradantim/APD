<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">	
	
	<title>Asignar Proveedor</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
	<%--<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.redirect.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletAsignarProveedor(id) {
			var idproveedor = $("#proveedores").val();
			var url='<%=request.getContextPath() %>/ServletAsignarProveedor';
			//$(button).prop('disabled',true);
			$.blockUI({ message: '<center><img src="<%=request.getContextPath() %>/gifs/char_reversed.gif" /><br>Aguanta...</center>' });
				
			$.ajax({
		        url: url,
		        type: "post",
		        data: {id: id, proveedor: idproveedor}
		    	}).done(function (respuesta){
					//$(button).prop('disabled',false);
					$.unblockUI();
					$.notify({message: 'Proveedor asignado correctamente!'},{type: 'success'});
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
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Orden Numero</th>
						<th>Descripcion</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${orden}</td>
						<td>${articulo}</td>					      	
					</tr>   					
				</tbody>
			</table>
			<div class="row-fluid" style="padding: 1%;">
				<center><select id="proveedores">
					<c:forEach items="${proveedores}" var="p"> 
				  		<option value="${p.id}"> ${p.nombre}</option>
				  	</c:forEach>
				</select></center>
			</div>
					
			<div class="form-group ">                
				<button type="submit" class="btn btn-primary btn-lg btn-block login-button" onclick="callPostServletAsignarProveedor(${orden});">Elegir</button>
			</div>				
		</div>	
	</div>
</body>
</html>