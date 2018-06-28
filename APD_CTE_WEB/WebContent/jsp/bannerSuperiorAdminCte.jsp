<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/css/general.css" rel="stylesheet">
  </head>
  <body>
	<div class="container-fluid">
		<div class="col-xs-12 div-top no-padding">
			<nav class="navbar navbar-default col-xs-12">
				<div class="container-fluid">
					<div class="row-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="<%=request.getContextPath() %>/">
								<p class="titulo-navbar">HOME</p>
							</a>
						</div>
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li class="li-option"><a href="<%=request.getContextPath() %>/ServletAdminCred" class="nav-option">Administracion Crediticia Pedidos</a></li>
								<li class="li-option"><a href="<%=request.getContextPath() %>/ServletIngresarPago" class="nav-option">Ingresar Pago</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<div class="container main-container">