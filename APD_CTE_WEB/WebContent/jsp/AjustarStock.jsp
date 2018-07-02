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
	<title>Ajustar Srock</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
	<%--<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.redirect.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function enableControl(){
			$("#codigoBarras").prop("disabled", false);
			$("#cantidad").prop("disabled", false);
			$("#ubicacionId").prop("disabled", false);
			$("#usuarioEncargado").prop("disabled", true);
			$("#usuarioAutorizador").prop("disabled", true);
			$("#usuarioEncargado").val("");
			$("#usuarioAutorizador").val("");
		}
		function enableRotura(){
			$("#codigoBarras").prop("disabled", false);
			$("#cantidad").prop("disabled", false);
			$("#ubicacionId").prop("disabled", false);
			$("#usuarioEncargado").prop("disabled", false);
			$("#usuarioAutorizador").prop("disabled", false);
		}
	
		function callPostServletAjustarStock() {			
			var url='<%=request.getContextPath() %>/ServletAjustarStock';
			$.blockUI({ message: '<center><img src="<%=request.getContextPath() %>/gifs/char_reversed.gif" /><br>Aguanta...</center>' });
			$.ajax({
		        url: url,
		        type: "post",
		        
		        //alert('(#usuarioEncargado).val()');
		        data: {codigoBarras: $("#codigoBarras").val(), cantidad: $("#cantidad").val(), ubicacion: $("#ubicacionId").val(),
		        	usuarioEncargado: $("#usuarioEncargado").val(), usuarioAutorizador: $("#usuarioAutorizador").val()}
		    	}).done(function (respuesta){
					//$(button).prop('disabled',false);
					$.unblockUI();
					$.notify({message: 'Ajuste realizado correctamente!'},{type: 'success'});
				}).fail(function(data){
					$.unblockUI();
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
					//console.log("error");
					//window.location.href = "<%=request.getContextPath() %>/jsp/error.jsp";
				});
		}	
	</script>
</head>
<jsp:include page="bannerSuperior.jsp"></jsp:include>
<body>
	<div style="margin-left: 30%;">
		<div class="container-fluid" style="padding: 15px;">
			<div class="row-fluid">
				<div class="col-xs-4">
					<input type="radio" name="Ajuste" value="Ajuste por Control" onclick="enableControl()">Ajuste por Control</input>
				</div>
				<div class="col-xs-4">
					<input type="radio" name="Ajuste" value="Ajuste por Rotura" onclick="enableRotura()">Ajuste por Rotura</input>
				</div>
				<div calss="col-xs-offset-4"></div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-xs-2">
					<p>Codigo de barras</p>				
				</div>
				<div class="col-xs-2">
					<p>Cantidad</p>				
				</div>
				<div class="col-xs-2">
					<p>Ubicacion</p>				
				</div>
			</div>		
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-xs-2">
					<input type="text" id="codigoBarras" size="8px;" disabled/>
				</div>
				<div class="col-xs-2">
					<input type="text" id="cantidad" size="3px;" disabled/>
				</div>
				<div class="col-xs-2">
					<input type="text" id="ubicacionId" size="8px;" disabled/>
				</div>
			</div>		
		</div>		
		<div class="container-fluid" style="padding: 15px;">
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-xs-2">
					<p>Usuario Encargado</p>				
				</div>
				<div class="col-xs-2"> 	
					<p>Usuario Autorizador</p>				
				</div>
			</div>		
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-xs-2">	
					<input  type="text" id="usuarioEncargado" size="8px;" disabled/>
				</div>
				<div class="col-xs-2">
					<input type="text" id="usuarioAutorizador" size="8px;" disabled/>
				</div>				
			</div>		
		</div>
		<div class="container-fluid" style="padding: 15px;">
		<div class="row-fluid">
			<div class="col-xs-12" style="margin-left:20%;">
				<button type="button" class="btn btn-primary" onclick="callPostServletAjustarStock()">Realizar Ajuste</button>
			</div> 
		</div>
	</div>
	</div>
	
</body>	
</html>