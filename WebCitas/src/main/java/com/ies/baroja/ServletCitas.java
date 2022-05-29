package com.ies.baroja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Citas;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet en el que planeaba controlar fromularios de citas pero preferí centralizar todo en ServletForm
 */
@WebServlet("/ServletCitaS")
public class ServletCitas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCitas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/** 1- recogida de datos */
			Citas cita = new Citas( request.getParameter("id_cita"),request.getParameter("fecha"+" "+"hora"), request.getParameter("centro_id"),
					request.getParameter("fracaso"));
			/** 2- Insertar cita en la base de datos */
			boolean bRes = Controller.insertar(cita);
			/** 3- Mostrar resultado por pantalla */
			if (bRes) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<HTML>\n" + "<HEAD><TITLE>Página de JAGD</TITLE>" + " <meta charset=\"utf-8\">\r\n"
						+ " <meta name=\"viewport\"content=\"width=device-width, initial-scale=1\">\r\n"
						+ " <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
						+ " <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
						+ "</HEAD>\n" + "<BODY>\n" + "<div class=\"container mt-3\">\n"
						+ "<h2 class=\"text-success\">Usuario insertado correctamente<h2>\n"
						+ "<ul class=\"list-group\"> \n" + " <LI class=\"list-group-item\">nombre: "
						+ "</li>\n" + " <LI class=\"list-group-item\">procedencia: "
						+ cita.getFecha_hora() + "</li>\n" + "<LI class=\"list-group-item\">altura: "
						+ "</li>\n" + " <LI class=\"list-group-item\">peso: "
						+ "</li>\n"
						+ "</UL>\n" + "</div></BODY></HTML>");
				out.close();
			} else {
				// Mostramos que se ha producido un error
				mostrarError(response);
			}
		} catch (Exception ex) {
			System.out.println("Error en servletCita");
			ex.printStackTrace();
			mostrarError(response);
		}
	}

	private static void mostrarError(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>\n" + "<HEAD><TITLE>Página de JAGD</TITLE>" + "<meta charset=\"utf-8\">\r\n"
				+ " <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\r\n"
				+ " <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ " <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "</HEAD>\n" + "<BODY>\n" + "<div class=\"container mt-3\">\n"
				+ "<h1 class=\"text-danger\">Error al insertar el usuario<h1>\n"
				+ "<img src=\"img/error.jpg\" class=\"rounded\" alt=\"errorinterno\">" + "</div></BODY></HTML>");
		out.close();
	}

}
