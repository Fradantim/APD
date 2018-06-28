<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html lang="en">
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


	function callPostServletBuscarFacturas() {
			var urlFacturasImpagas='<%=request.getContextPath() %>/ServletFacturasImpagas';
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Procesando...</center>' });
        	$.ajax({
        		url: urlFacturasImpagas,
		        type: "get",
		        data: {id: $("#idCliente").val()}
       	    	}).done(function (data){
       	    		$.unblockUI();
		    		$('#facturasImpagas').load("<%=request.getContextPath() %>/jsp/tablaFacturasImpagas.jsp");
				}).fail(function(){
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
	}
	
	function callPostServletRegistrarPago(id,monto,especie) {
		var urlRegistrarPago='<%=request.getContextPath() %>/ServletFacturasImpagas';
		$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Procesando...</center>' });
    	$.ajax({
    		url: urlRegistrarPago,
	        type: "post",
	        data: {id: id, monto : monto,especie:especie}
   	    	}).done(function (data){
   	    		$.unblockUI();
   	    		$.notify({message: 'Pago Registrado Correctamente'},{type: 'success'});
			}).fail(function(){
				var responseJsonObj = JSON.parse(data.responseText);
				$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
			});
	}

	function validar(){
	    var form    = document.getElementById('fomularioAValidar');
	    var nroCte  = form.Nrocliente;
	    var monto   = form.Monto;
	    var especie = form.Especie;
	    
	    if(nroCte.value != ''){
	     callPostServletBuscarFacturas();      
	    }
	    else {
	    	$.notify({message: 'Por favor ingrese un nro de cliente'},{type: 'danger'});	
	    }
	 }
	
	function validarPago(){
	    var form    = document.getElementById('fomularioAValidar');
	    var nroCte  = form.Nrocliente;
	    var monto   = form.Monto;
	    var especie = form.Especie;
	    
	    if(monto.value != 0 && especie.value != ''){
	     callPostServletRegistrarPago(nroCte.value,monto.value,especie.value);          
	    }
	    else {
	    	$.notify({message: 'Por favor ingrese monto y especie'},{type: 'danger'});	
	    }
	 }

	
	</script>
	
</head>
<jsp:include page="bannerSuperiorAdminCte.jsp"></jsp:include>
<body>

		<form > 
		<div class="form-group"> 
			<label style=margin-left:100px><b>Nro Cliente</b></label> 
			<label style=margin-left:350px><b>Monto</b></label>
			<label style=margin-left:50px><b>Especie</b></label>
		</div>
		</form>
		
		<form id="fomularioAValidar" class="form-inline">
		<div class="form-group"> 
			<input style=margin-left:100px;padding:10px; type="text" class="form-control" id="Nrocliente" name="Nrocliente" placeholder="Ingrese Nro de Cliente" />
		</div>
		<div class="form-group"> 
			<button type="button" id="buttonBuscarFactura_id"  class="btn btn-info" value="Buscar Facturas"  onclick="validar();">Buscar Facturas</button>
		</div>
		<div class="form-group"> 
			<label style=margin-left:100px>$</label>
		</div>
		<div class="form-group" > 
			<input  style=width:70px type="number" min="0.00" step=".01" class="form-control" id="Monto" name="Monto" size=10px placeholder="0.00"/>
		</div>
		<div class="form-group" > 
			<input  style=width:80px;padding:10px;margin-left:10px type="text" class="form-control" id="Especie" name="Especie" size=10px placeholder="Efectivo"/>
		</div>	
		</form>

		<div class="row-fluid">  
			<input type="hidden" id="idCliente" value="Nrocliente"/>
			<br>
			<div id="facturasImpagas"> </div>
		</div>
		
		<div class="form-group"> 
			<br>
			<h4><b>Ingresar Pago sin asociar Factura</b></h4>
			<button type="button" id="ingresarPagoGral"  class="btn btn-warning"  value="ingresarPagoGral"  onclick="validarPago();">Registrar Pago</button>
			<label >Se descontara el importe de las factura/s impagas mas antiguas</label>
		</div>

		
</body>
</html>