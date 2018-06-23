<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/multi-select.css">
	<title>Agregar Items a pedido</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.multi-select.js"></script>

	
</head>
<jsp:include page="bannerSuperiorFDT.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h3>Orden Elegida</h3>
			<table class="table table-striped">
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
			
			<input type="hidden" id="cantidadUbicacionesNecesarias" name="cantidadUbicacionesNecesarias" value="${cantidadUbicacionesNecesarias}"/> 
			<h3>Elegir ubicaciones</h3>
			<select id='callbacks' multiple='multiple'>
				<c:forEach items="${ubicacionesVacias}" var="u"> 
					<option value='${u.id}'>${u.calle}_${u.bloque}_${u.estante}_${u.posicion}</option>	
				</c:forEach>
			</select><%-- No se por que pero es necesario repetir estos imports... --%>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.multi-select.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
			<script type="text/javascript">
				var cantidadUbicacionesElegidas=0;
				var cantidadUbicacionesNecesarias = document.getElementById("cantidadUbicacionesNecesarias").value;
				$('#callbacks').multiSelect({
					afterSelect : function(values) {
						//alert("Select value: "+values);
						cantidadUbicacionesElegidas++;
						enableButton();
					},
					afterDeselect : function(values) {
						cantidadUbicacionesElegidas--;
						enableButton();
					}
				});
				
				function enableButton(){
					if (cantidadUbicacionesElegidas==cantidadUbicacionesNecesarias){
						document.getElementById("buttonUbicarOrden").disabled = false;
						$.notify({message: "Ubicaciones justas!"},{type: 'success'});
					}else{
						document.getElementById("buttonUbicarOrden").disabled = true;
						var cantidadRestante=cantidadUbicacionesNecesarias-cantidadUbicacionesElegidas;
						if(cantidadRestante > 0){
							$.notify({message: "Elegi "+ cantidadRestante + " ubicacion(es) mas."},{type: 'info'});
						} else{
							$.notify({message: "Elegiste demasiadas ubicaciones"},{type: 'danger'});
						}
					}
				}
				
				function callPostServletUbicarOrden(){
					var url='<%=request.getContextPath() %>/ServletUbicarOrden';
					var values=$('#callbacks').val();
					var out="";
					
					for (var i = 0; i < values.length; i++) {
					    if(i==0){
					    	out=values[i];
					    }else{
					    	out=out+";"+values[i];
					    }
					    
					}
					$.blockUI({ message: '<center><img src="<%=request.getContextPath() %>/gifs/char_reversed.gif" /><br>Aguanta...</center>' });	
					$.ajax({
				        url: url,
				        type: "post",
				        data: {ids: out}
				    	}).done(function (data){
							//$.unblockUI();
							var responseJsonObj = JSON.parse(data);
							$.notify({message: responseJsonObj.message},{type: 'success'});
							//console.log(data);
							setTimeout(function() {
								window.location.replace(responseJsonObj.forwardTo);
							}, 3000);
						}).fail(function(data){
							$.unblockUI();
							console.log(data);
							var responseJsonObj = JSON.parse(data.responseText);
							$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
						});
				} 
				
			</script>
			<input id="buttonUbicarOrden" disabled="true" type="button" value="Asignar ubicaciones" class="btn btn-info" onclick="callPostServletUbicarOrden();" />
		</div>	
	</div>
</body>
</html>
