<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>IngresarPagos</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
	
	function callPostServletPagarFactura(id) {
			var urlPagarFactura='<%=request.getContextPath() %>/ServletIngresarPago';
			var idcli = "${idcli}" ;
			var esp = "${esp}" ;
			var montop = "${montop}";
			var esp = "${esp}";
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Procesando...</center>' });
        	$.ajax({
        		url: urlPagarFactura,
		        type: "post",
		        data: {id: id, idcli: idcli,montop:montop,esp:esp},
		        success : CallServletFacturasImpagas
		        }).done(function (data){
					$.unblockUI();
					$.notify({message: 'Pago Asociado a Factura'},{type: 'success'});
				}).fail(function(){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
	}
	
	function CallServletFacturasImpagas() {
		var urlFacturasImpagas='<%=request.getContextPath() %>/ServletFacturasImpagas';
		var idcli = "${idcli}" ;
		var esp = "${esp}" ;
		var montop = "${montop}";
		var esp = "${esp}";
    	$.ajax({
    		url: urlFacturasImpagas,
	        type: "get",
	        data: {idcli: idcli, monto: montop, especie: esp}
  	    	}).done(function (data){
   	    		$.unblockUI();
	    		$('#facturasImpagas').load("<%=request.getContextPath() %>/jsp/tablaFacturasImpagas.jsp");
			}).fail(function(){
				var responseJsonObj = JSON.parse(data.responseText);
				$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
			});
    	}	

	</script>
</head>
<body>
<table id="facturasImpagas" class="table table-striped table-responsive table-hover">
	<thead> 
		<tr>
			<th>ID</th>
			<th>Fecha Generacion</th>
			<th>Bonificacion</th>
			<th>Estado</th>
			<th>Importe</th>
			<th>Total a Abonar (solo efectivo)</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${facturasImpagas}" var="f"> 
			<tr>
		   		<th>${f.id}</th>
				<th>${f.fecha}</th>
				<th>${f.bonificacion}</th>
				<th>${f.estado}</th>
				<th>${f.importe}</th>
				<th>${f.importe * ( 1 - f.bonificacion/100 )}</th>
				<td>
					<input id="buttonPagarFactura_${f.id}" type="button" value="Pagar Factura" class="btn btn-warning" onclick="callPostServletPagarFactura(${f.id});" />
				</td>  
				
			</tr>
		</c:forEach>
		<c:if test="${fn:length(facturasImpagas) == 0}">
			<tr>
		   		<th colspan="6" class="success">Ud no posee facturas pendientes de abonar</th>
			</tr>
		</c:if>
	</tbody>
</table>
</body>
</html>