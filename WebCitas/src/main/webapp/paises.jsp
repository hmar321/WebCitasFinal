<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Paises"%>
<%@ page import="com.ies.baroja.Controller"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html lang="es">

<head>
<title>Países</title>
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

	<div id="h_paises" class="p-5 bg-dark text-white text-center">
		<h1 class="texto-borde">BuscoPareja</h1>
		<h3 class="texto-borde">Empieza algo real</h3>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="index.html">Menú</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="registrarse.html">Registrarse</a></li>
				<li class="nav-item"><a class="nav-link" href="login.html">Login</a>
				</li>
				<li class="nav-item"><a class="nav-link active"
					href="paises.jsp">Países</a></li>
				<li class="nav-item"><a class="nav-link" href="centros.jsp">Centros</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="row">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>País</th>
						<th>Usuarios</th>
					</tr>
				</thead>
				<tbody>
					<%
					try {
						LinkedList<Paises> lista = Controller.getPaises();
						for (int i = 0; i < lista.size(); i++) {
							out.println("<td>" + lista.get(i).getPais() + "</td>");
							out.println("<td>" + lista.get(i).getN_usuarios() + "</td>");
							out.println("</tr>");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						out.println("<h1 class=\"text-warning\">Ha ocurrido un error :(</h1>\r\n"
						+ "      <h2 class=\"text-danger\">No se ha podido conectar con la base de datos</h2>\r\n");
					}
					%>
				</tbody>
			</table>
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