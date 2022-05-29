package com.ies.baroja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Centros;
import com.model.Paises;

/**
 * Servlet en el que intenté rescatar datos del los form del admin, con tilde o caracteres raros(fracasé)
 */
@WebServlet("/ServletBorrar")
public class ServletBorrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletBorrar() {
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
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("borrarPais") != null) {
				borrarPais(request, response);
			} else if (request.getParameter("borrarCentro") != null) {
				borrarCentro(request, response);
			} else {
				response.sendRedirect("index.html");
			}

		} catch (Exception ex) {
			System.out.println("Error en servlet");
			ex.printStackTrace();
			mostrarError(response, "Sin conexión a la base de datos");
		}
	}

	private void borrarPais(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sPais = request.getParameter("borrarPais");
		Paises pais = Controller.getPais(sPais);
		if (pais != null) {
			boolean bRes = Controller.deletePais(pais);
			if (bRes) {
				response.sendRedirect("admpaises.jsp");
			} else {
				mostrarError(response, "No se ha borrado el Pais");
			}
		} else {
			mostrarError(response, "Error ServletBorrarPais");
		}
	}

	private void borrarCentro(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String sCentro = request.getParameter("borrarCentro");
		Centros centro = Controller.getCentro(sCentro);
		if (centro != null) {
			boolean bRes = Controller.deleteCentro(centro);
			if (bRes) {
				response.sendRedirect("admcentros.jsp");
			} else {
				mostrarError(response, "No se ha borrado el centro");
			}
		} else {
			mostrarError(response, "Error ServletBorrarCentro");
		}
	}

	private static void mostrarError(HttpServletResponse response, String sMensaje) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html lang=\"es\">\r\n" + "\r\n" + "<head>\r\n"
				+ "  <title>Error</title>\r\n" + "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "  <link rel=\"stylesheet\" href=\"css/interfaz.css\">\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
				+ "  <div class=\"p-5 bg-dark text-white text-center\">\r\n" + "  </div>\r\n"
				+ "  <div class=\"container mt-5\">\r\n" + "    <div class=\"row\">\r\n"
				+ "      <h1 class=\"text-warning\">Ha ocurrido un error :(</h1>\r\n"
				+ "      <h2 class=\"text-danger\">" + sMensaje + "</h2>\r\n"
				+ "      <a href=\"index.html\"><img src=\"images/error.png\" class=\"rounded\" alt=\"error\"></a>\r\n"
				+ "    </div>\r\n" + "  </div>\r\n" + "  <div class=\"mt-5 p-4 bg-dark text-white text-center\">\r\n"
				+ "    <h2>Contacto</h2>\r\n" + "    <p>\r\n" + "      Empresa: HéctorAlmaraz´s LovingAdvice<br>\r\n"
				+ "      Dirección: Calle Loveless 9 1231 Nowhere<br>\r\n"
				+ "      Teléfono: (+12) 48 648 15 15. Email: alm@heartbreaker.es\r\n" + "    </p>\r\n" + "  </div>\r\n"
				+ "</body>\r\n" + "\r\n" + "</html>");
		out.close();
	}

}
