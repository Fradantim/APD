<html lang="en">
<head> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
	<title>Alta pedido</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletAltaPedido() {
			var url='ServletAltaPedido';	
			$.ajax({
		        url: url,
		        type: "post",
		        data: {
		        	pais : $("#pais").val(),
		        	provincia : $("#provincia").val(),
		        	partido : $("#partido").val(),
		        	codigoPostal : $("#codigoPostal").val(),
		        	calle : $("#calle").val(),
		        	altura : $("#altura").val(),
		        	piso : $("#piso").val(),
		        	numero : $("#numero").val()		        	
		        	}
		    	}).done(function (data){
					var responseJsonObj = JSON.parse(data);
					window.location.replace(responseJsonObj.forwardTo);
				}).fail(function(data){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
		}	
	</script>
</head>
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container">
		<h1>Domicilio de envío:</h1>
		<br>
			<%--<form class="form-horizontal" method="post" action="<%=request.getContextPath() %>/ServletAltaPedido"> --%>
			<form class="form-horizontal"> 
			<div class="form-group">
				<label for="pais">Pais:</label> 
				<input type="text" class="form-control" id="pais" name="pais">
			</div>
			
			<div class="form-group">
				<label for="provincia">Provincia:</label> 
				<input type="text" class="form-control" id="provincia" name="provincia">
			</div>
			
			<div class="form-group">
				<label for="partido">Partido:</label> 
				<input type="text" class="form-control" id="partido" name="partido">
			</div>
			
			<div class="form-group">
				<label for="codigoPostal">Codigo Postal:</label> 
				<input type="text" class="form-control" id="codigoPostal" name="codigoPostal">
			</div>
			
			<div class="form-group">
				<label for="calle">Calle:</label> 
				<input type="text" class="form-control" id="calle" name="calle">
			</div>
			
			<div class="form-group">
				<label for="altura">Altura:</label> 
				<input type="text" class="form-control" id="altura" name="altura">
			</div>
			
			<div class="form-group">
				<label for="piso">Piso:</label> 
				<input type="text" class="form-control" id="piso" name="piso">
			</div>
			
			<div class="form-group">
				<label for="numero">Numero:</label> 
				<input type="text" class="form-control" id="numero" name="numero">
			</div>			
			
			<%--<button type="submit" class="btn btn-default">Enviar</button> --%>
			<input id="buttonAltaPedido" type="button" value="Enviar" class="btn btn-info" onclick="callPostServletAltaPedido();" />
		</form>
	</div>
</body>
</html>