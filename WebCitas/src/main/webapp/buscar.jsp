<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Usuarios"%>
<%@ page import="com.ies.baroja.Controller"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html lang="es">

<head>
<%
HttpSession sesion = request.getSession();
if (sesion.getAttribute("usuario") == null) {
	response.sendRedirect("index.html");
}
%>
<title>Buscar Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/interfaz.css">
</head>

<body>
	<div id="h_index" class="p-5 bg-dark text-center">
		<h1 class="texto-borde">Encuentra a tu pareja ideal</h1>
		<h3 class="texto-borde">Bienvenido a BuscoPareja</h3>
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="menuin.jsp">Menú</a>
				</li>
				<li class="nav-item"><a class="nav-link active"
					href="perfil.jsp">Perfil</a></li>
				<li class="nav-item"><a class="nav-link" href="paisesin.jsp">Países</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="centrosin.jsp">Centros</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container mt-4">
		<div class="row">
			<form action="ServletForm" method="post">
				<img src="images/cupido.png" width="500" height="350" alt="cupido">
				<div class="mb-3">
					<label for="buscarUsuario" class="form-label">Buscar
						ususario:</label> <input type="email" class="form-control"
						name="buscarUsuario" required
						pattern="[a-z0-9._%+-]+@gmail+\.[a-z]{2,4}$"
						title="Email inválido." placeholder="micorreo@gmail.com">
				</div>
				<button type="submit" class="btn btn-primary">Buscar</button>
			</form>
		</div>
	</div>
	<div class="mt-5 p-4 bg-dark text-white text-center">
		<h2>Contacto</h2>
		<p>
			Empresa: Almaraz´s LovingAdvice<br> Dirección: Calle Loveless 9
			1231 Nowhere<br> Teléfono: (+12) 48 648 15 15. Email:
			alm@heartbreaker.es
		</p>
	</div>
</body>

</html>