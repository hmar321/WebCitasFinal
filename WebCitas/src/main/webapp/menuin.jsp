<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Paises"%>
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
<title>Menú</title>
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

	<div id="h_index" class="p-5 bg-dark text-white text-center">
		<h1 class="texto-borde">BuscoPareja</h1>
		<h3 class="texto-borde">Empieza algo real</h3>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active"
					href="menuin.jsp">Menú</a></li>
				<li class="nav-item"><a class="nav-link" href="perfil.jsp">Perfil</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="paisesin.jsp">Países</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="centrosin.jsp">Centros</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="row">
			<div class="col-sm-4">
				<a href="perfil.jsp">
					<h2>Perfil</h2>
				</a> <a href="perfil.jsp"><img src="images/perfil.png" height="20%"
					alt="enlacePerfil"></a>
				<p>Consulta tus datos por ejemplo, tu nombre, ciudad, país,
					género, nombre de usuario. Solo tú y tus intereses pueden ver parte
					de tu perfil.</p>
				<h3 class="mt-4">Links</h3>
				<p>También te puede interesar</p>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link" href="paisesin.jsp">Países</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="centrosin.jsp">Centros</a>
					</li>
				</ul>
				<hr class="d-sm-none">
			</div>
			<div class="col-sm-8">
				<h2 class="mt-5">Sobre nosotros</h2>
				<!-- Carousel -->
				<div id="demo" class="carousel slide" data-bs-ride="carousel">

					<!-- Indicators/dots -->
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
							class="active"></button>
						<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
						<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
					</div>

					<!-- The slideshow/carousel -->
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="images/grupo.png" alt="Equipo" class="d-block"
								style="width: 100%">
						</div>
						<div class="carousel-item">
							<img src="images/pareja1.png" alt="Pareja1" class="d-block"
								style="width: 100%">
						</div>
						<div class="carousel-item">
							<img src="images/pareja2.png" alt="Pareja2" class="d-block"
								style="width: 100%">
						</div>
					</div>

					<!-- Left and right controls/icons -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#demo" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#demo" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
					</button>
				</div>
				<p>BuscoPareja surgió en París, la idea nació en una cena entre
					amigos, rodeado por varios divorciados que comentaban que cada vez
					era más difícil encontrar nuevas fórmulas para conocer a gente y
					encontrar pareja.</p>
			</div>
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