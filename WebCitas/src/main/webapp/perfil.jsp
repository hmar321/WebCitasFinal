<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Usuarios"%>
<%@ page import="com.ies.baroja.Controller"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html lang="es">

<head>
<title>Perfil</title>
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

	<div class="container mt-5">
		<div class="row">

			<div class="col-sm-7">
				<h2>Perfil</h2>
				<div class="card">
					<div class="card-body">
						<img class="card-img-top" src="images/perfil.png" alt="Perfil"
							style="width: 300px">

						<%
						try {
							HttpSession sesion = request.getSession();
							if (sesion.getAttribute("usuario") == null) {
								response.sendRedirect("index.html");
							}
							Usuarios usuario = (Usuarios) sesion.getAttribute("usuario");
							out.println("<h4 class=\"card-title\">" + usuario.getNombre() + "</h4>");
							out.println("<table class=\"table table-bordered\">");
							out.println("<tbody>");
							out.println("<tr><th>País</th>");
							out.println("<td>" + usuario.getPais() + "</td></tr>");
							out.println("<tr><th>Ciudad</th>");
							out.println("<td>" + usuario.getCiudad() + "</td></tr>");
							out.println("<tr><th>Dirección</th>");
							out.println("<td>" + usuario.getDireccion() + "</td></tr>");
							out.println("<tr><th>Sexo</th>");
							out.println("<td>" + usuario.getSexo() + "</td></tr>");
							out.println("<tr><th>Pareja</th>");
							out.println("<td>" + usuario.getPareja() + "</td></tr>");
							out.println("<tr><th>Email</th>");
							out.println("<td>" + usuario.getEmail() + "</td></tr>");
						} catch (Exception ex) {
							ex.printStackTrace();
							out.println("<h1 class=\"text-warning\">Ha ocurrido un error :(</h1>\r\n"
							+ "      <h2 class=\"text-danger\">No se ha podido conectar con la base de datos</h2>\r\n");
						}
						%>
						</tbody>
						</table>
						<form action="ServletForm" method="post">
							<input type="hidden" name="cerrar" value="cerrar">
							<button type="submit" class="btn btn-primary">Cerrar
								sesión</button>
						</form>
					</div>
				</div>
			</div>

			<div class="col-sm-5">
				<h3 class="mt-4">Conoce a tu persona especial</h3>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="btn btn-dark" href="buscar.jsp">Buscar
							perfil</a></li>
					</br>
					<li class="nav-item"><a class="btn btn-dark" href="ucomun.jsp">Tus
							Posibles parejas</a></li>
					</br>
					<li class="nav-item"><a class="btn btn-dark" href="ucitas.jsp">Tus
							citas</a></li>
					</br>
					<li class="nav-item"><a class="btn btn-dark"
						href="addcita.jsp">Añadir cita</a></li>
				</ul>
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