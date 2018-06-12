<html lang="en">
<head> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">

	<!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
	
	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

	<title>Das Verr�ckte Lagerhaus login</title>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<%--<script type="text/javascript" src="js/jquery.redirect.js"></script> --%>
	<script type="text/javascript" src="js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletLogin() {
			var user=$("#username").val();
			var pass=$("#password").val();
			var url='ServletLogin';
			$.blockUI({ message: '<center><img src="gifs/char_reversed.gif" /><br>Aguanta...</center>' });
			console.log(user);
			console.log(pass);
			$.ajax({
		        url: url,
		        type: "post",
		        data: {user: user, pass: pass}
		    	}).done(function (data){
					//$(button).prop('disabled',false);
					console.log("OK"+user);
					console.log("OK"+pass);
					$.unblockUI();
					//window.location.href = respuesta;
					//$.redirect(data);
				}).fail(function(){
					console.log("error");
					$.notify({message: 'Usuario o contrase�a incorrectos'},{type: 'error'});
				});
		}	
	</script>
</head>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
               <div class="panel-title text-center">
               		<h1 class="title">Das Verr�ckte Lagerhaus login</h1>
               		<hr />
               	</div>
            </div> 
			<div class="main-login main-center">
				
					
					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">Usuario</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="username" id="username"  placeholder="Ingrese su usuario"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Contrase�a</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="password" id="password"  placeholder="Ingrese su contrase�a"/>
							</div>
						</div>
					</div>

					<div class="form-group ">
						<button type="submit" class="btn btn-primary btn-lg btn-block login-button" onclick="callPostServletLogin();">Login</button>
						</div>
			
					
				</div>
			</div>
		</div>
	</body>
</html>