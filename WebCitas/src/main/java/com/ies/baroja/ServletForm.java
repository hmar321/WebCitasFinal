package com.ies.baroja;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Centros;
import com.model.Citas;
import com.model.Paises;
import com.model.Usuarios;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet que controla todos los formularios y aplica métodos en funcion de la
 * procedencia de los datos
 * 
 * @author Héctor Almaraz
 */
@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("nombre") != null) {
				altaUsuario(request, response);
			} else if (request.getParameter("email") != null) {
				if (request.getParameter("email").equals("admin@gmail.com")) {
					cerrarSesion(request, response);
					loginAdmin(request, response);
				} else {
					cerrarSesion(request, response);
					loginUsuario(request, response);
				}
			} else if (request.getParameter("buscarUsuario") != null) {
				buscaUsuario(request, response);
			} else if (request.getParameter("borrarUsuario") != null) {
				borrarUsuario(request, response);
			} else if (request.getParameter("borrarPais") != null) {
				borrarPais(request, response);
			} else if (request.getParameter("borrarCentro") != null) {
				borrarCentro(request, response);
			} else if (request.getParameter("borrarCita") != null) {
				borrarCita(request, response);
			} else if (request.getParameter("cerrar") != null) {
				cerrarSesion(request, response);
				response.sendRedirect("index.html");
			} else if (request.getParameter("fecha") != null) {
				altaCita(request, response);
			} else {
				response.sendRedirect("index.html");
			}

		} catch (Exception ex) {
			System.out.println("Error en servlet");
			ex.printStackTrace();
			mostrarError(response, "Sin conexión a la base de datos");
		}
	}

//----------------------------------------------------------------------Usuarios-----------------------------------------------------------------
	/**
	 * Método para el login admin
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private static void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();
		String sEmail = request.getParameter("email");
		String sPwd = request.getParameter("contrasena");
		/*
		 * LinkedList<Usuarios> lista = Controller.getUsuarios(); //for (int i = 0; i <
		 * lista.size(); i++) { String baseEmail = lista.get(i).getEmail(); String
		 * basePwd = lista.get(i).getContrasena();
		 */
		// Buscamos el usuario en la base de datos con el metodo getUsuario del
		// Controller
		Usuarios usuario = Controller.getUsuario(sEmail);
		if (sEmail.equals(usuario.getEmail()) && sPwd.equals(usuario.getContrasena())
				&& sesion.getAttribute("usuario") == null) {

			// si coincide email y password y además no hay sesión iniciada
			sesion.setAttribute("admin", usuario);
			// redirijo a página con información de login exitoso
			response.sendRedirect("admin.jsp");
		} else {
			// lógica para login inválido
			mostrarError(response, "El usuario " + sEmail + " no tiene acceso");
		}

	}

	/**
	 * Método para el login usuario
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private static void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();
		String sEmail = request.getParameter("email");
		String sPwd = request.getParameter("contrasena");
		/*
		 * LinkedList<Usuarios> lista = Controller.getUsuarios(); //for (int i = 0; i <
		 * lista.size(); i++) { String baseEmail = lista.get(i).getEmail(); String
		 * basePwd = lista.get(i).getContrasena();
		 */
		// Buscamos el usuario en la base de datos con el metodo getUsuario del
		// Controller
		Usuarios usuario = Controller.getUsuario(sEmail);
		if (usuario != null) {
			if (sEmail.equals(usuario.getEmail()) && sPwd.equals(usuario.getContrasena())
					&& sesion.getAttribute("usuario") == null) {

				// si coincide email y password y además no hay sesión iniciada
				sesion.setAttribute("usuario", usuario);
				// redirijo a página con información de login exitoso
				response.sendRedirect("perfil.jsp");
			} else {
				// lógica para login inválido
				mostrarError(response, "Contraseña incorrecta");
			}
		} else {
			mostrarError(response, "El usuario " + sEmail + " no tiene acceso");
		}

	}

	/**
	 * Método para dar de alta al usuario
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private static void altaUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/** 1- recogida de datos */
			Usuarios usuario = new Usuarios(request.getParameter("nombre"), request.getParameter("direccion"),
					request.getParameter("ciudad"), request.getParameter("pais"), request.getParameter("sexo"),
					request.getParameter("pareja"), request.getParameter("email"), request.getParameter("contrasena"));
			/** 2- Insertar Usuario en la base de datos */
			boolean bRes = Controller.insertar(usuario);
			/** 3- Mostrar resultado por pantalla */
			if (bRes) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>\r\n" + "<html lang=\"es\">\r\n" + "\r\n" + "<head>\r\n"
						+ "  <title>Perfil</title>\r\n" + "  <meta charset=\"utf-8\">\r\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
						+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
						+ "  <link rel=\"stylesheet\" href=\"css/interfaz.css\">\r\n" + "</head>\r\n" + "\r\n"
						+ "<body>\r\n" + "\r\n"
						+ "  <div id=\"h_index\" class=\"p-5 bg-dark text-white text-center\">\r\n"
						+ "    <h1 class=\"texto-borde\">BuscoPareja</h1>\r\n"
						+ "    <h3 class=\"texto-borde\">Empieza algo real</h3>\r\n" + "  </div>\r\n" + "\r\n"
						+ "  <nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\r\n"
						+ "    <div class=\"container-fluid\">\r\n" + "    </div>\r\n" + "  </nav>\r\n" + "\r\n"
						+ "  <div class=\"container mt-5\">\r\n" + "    <div class=\"row\">\r\n" + "\r\n"
						+ "      <div class=\"col-sm-7\">\r\n"
						+ "        <h2 class=\"text-success\">Usuario añadido correctamente</h2>\r\n"
						+ "        <div class=\"card\">\r\n" + "          <div class=\"card-body\">\r\n"
						+ "            <img class=\"card-img-top\" src=\"images/perfil.png\" alt=\"Perfil\" style=\"width:300px\">\r\n"
						+ "            <h4 class=\"card-title\">" + usuario.getNombre() + "</h4>\r\n"
						+ "            <table class=\"table table-bordered\">\r\n" + "              <tbody>\r\n"
						+ "                <tr>\r\n" + "                  <th>País</th>\r\n" + "                  <td>"
						+ usuario.getPais() + "</td>\r\n" + "                </tr>\r\n" + "                <tr>\r\n"
						+ "                  <th>Ciudad</th>\r\n" + "                  <td>" + usuario.getCiudad()
						+ "</td>\r\n" + "                </tr>\r\n" + "                <tr>\r\n"
						+ "                  <th>Dirección</th>\r\n" + "                  <td>" + usuario.getDireccion()
						+ "</td>\r\n" + "                </tr>\r\n" + "                <tr>\r\n"
						+ "                  <th>Sexo</th>\r\n" + "                  <td>" + usuario.getSexo()
						+ "</td>\r\n" + "                </tr>\r\n" + "                <tr>\r\n"
						+ "                  <th>Pareja</th>\r\n" + "                  <td>" + usuario.getPareja()
						+ "</td>\r\n" + "                </tr>\r\n" + "                <tr>\r\n"
						+ "                  <th>Email</th>\r\n" + "                  <td>" + usuario.getEmail()
						+ "</td>\r\n" + "                </tr>\r\n" + "              </tbody>\r\n"
						+ "            </table>\r\n"
						+ "            <a class=\"btn btn-primary\" href=\"login.html\">Continuar</a>\r\n"
						+ "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "\r\n" + "  <div class=\"mt-5 p-4 bg-dark text-white text-center\">\r\n"
						+ "    <h2>Contacto</h2>\r\n" + "    <p>\r\n" + "      Empresa: Almaraz´s LovingAdvice<br>\r\n"
						+ "      Dirección: Calle Loveless 9 1231 Nowhere<br>\r\n"
						+ "      Teléfono: (+12) 48 648 15 15. Email: alm@heartbreaker.es\r\n" + "    </p>\r\n"
						+ "  </div>\r\n" + "\r\n" + "</body>\r\n" + "\r\n" + "</html>");
				out.close();
			} else {
				// Mostramos que se ha producido un error
				mostrarError(response, "No se ha insertado el usuario");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			mostrarError(response, "Sin conexión a la base de datos");
		}
	}

	/**
	 * Método para buscar un usuario
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void buscaUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sEmail = request.getParameter("buscarUsuario");
		Usuarios usuario = Controller.getUsuario(sEmail);
		if (usuario != null) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("busqueda", usuario);
			response.sendRedirect("datosBusqueda.jsp");
		} else {
			mostrarError(response, "Usuario " + sEmail + " no encontrado");
		}
	}

	/**
	 * Método para "borrar" un usuario
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void borrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sEmail = request.getParameter("borrarUsuario");
		Usuarios usuario = Controller.getUsuario(sEmail);
		if (usuario != null) {
			boolean bRes = Controller.deleteUsuario(usuario);
			if (bRes) {
				response.sendRedirect("admin.jsp");
			} else {
				mostrarError(response, "No se ha borrado el usuario");
			}
		} else {
			mostrarError(response, "Error ServletBorrarUsuario");
		}
	}

//----------------------------------------------------------------------Citas-----------------------------------------------------------------
	/**
	 * Método para dar de alta una cita
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void altaCita(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			boolean bRes = false;
			/** 1- recogida de datos */
			Citas cita = new Citas((request.getParameter("fecha") + " " + request.getParameter("hora")),
					request.getParameter("centro"), request.getParameter("email1"), request.getParameter("email2"));
			/** 2- Insertar Usuario en la base de datos */
			if (!(request.getParameter("email1").equals("admin@gmail.com"))
					| !(request.getParameter("email2").equals("admin@gmail.com"))) {
				bRes = Controller.insertar(cita);
			}
			/** 3- Mostrar resultado por pantalla */
			if (bRes) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>\r\n" + "<html lang=\"es\">\r\n" + "\r\n" + "<head>\r\n"
						+ "  <title>Perfil</title>\r\n" + "  <meta charset=\"utf-8\">\r\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
						+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
						+ "  <link rel=\"stylesheet\" href=\"css/interfaz.css\">\r\n" + "</head>\r\n" + "\r\n"
						+ "<body>\r\n" + "\r\n"
						+ "  <div id=\"h_index\" class=\"p-5 bg-dark text-white text-center\">\r\n"
						+ "    <h1 class=\"texto-borde\">BuscoPareja</h1>\r\n"
						+ "    <h3 class=\"texto-borde\">Empieza algo real</h3>\r\n" + "  </div>\r\n" + "\r\n"
						+ "  <nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\r\n"
						+ "    <div class=\"container-fluid\">\r\n" + "    </div>\r\n" + "  </nav>\r\n" + "\r\n"
						+ "  <div class=\"container mt-5\">\r\n" + "    <div class=\"row\">\r\n" + "\r\n"
						+ "      <div class=\"col-sm-7\">\r\n"
						+ "        <h2 class=\"text-success\">Cita añadida correctamente</h2>\r\n"
						+ "        <div class=\"card\">\r\n" + "          <div class=\"card-body\">\r\n"
						+ "            <table class=\"table table-bordered\">\r\n" + "              <tbody>\r\n"
						+ "                <tr>\r\n" + "                  <th>Fecha y Hora</th>\r\n"
						+ "                  <td>" + cita.getFecha_hora() + "</td>\r\n" + "                </tr>\r\n"
						+ "                <tr>\r\n" + "                  <th>Centro</th>\r\n"
						+ "                  <td>" + cita.getCentro() + "</td>\r\n" + "                </tr>\r\n"
						+ "                <tr>\r\n" + "                  <th>Ciudad</th>\r\n"
						+ "                  <td>" + cita.getEmail1() + "</td>\r\n" + "                </tr>\r\n"
						+ "                <tr>\r\n" + "                  <th>Dirección</th>\r\n"
						+ "                  <td>" + cita.getEmail2() + "</td>\r\n" + "                </tr>\r\n"
						+ "              </tbody>\r\n" + "            </table>\r\n"
						+ "            <a class=\"btn btn-primary\" href=\"perfil.jsp\">Continuar</a>\r\n"
						+ "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "\r\n" + "  <div class=\"mt-5 p-4 bg-dark text-white text-center\">\r\n"
						+ "    <h2>Contacto</h2>\r\n" + "    <p>\r\n" + "      Empresa: Almaraz´s LovingAdvice<br>\r\n"
						+ "      Dirección: Calle Loveless 9 1231 Nowhere<br>\r\n"
						+ "      Teléfono: (+12) 48 648 15 15. Email: alm@heartbreaker.es\r\n" + "    </p>\r\n"
						+ "  </div>\r\n" + "\r\n" + "</body>\r\n" + "\r\n" + "</html>");
				out.close();
			} else {
				// Mostramos que se ha producido un error
				mostrarError(response, "No se ha insertado la cita");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			mostrarError(response, "Sin conexión a la base de datos");
		}
	}

	/**
	 * Método para "borrar" una cita
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void borrarCita(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sCita = request.getParameter("borrarCita");
		Citas cita = Controller.getCita(sCita);
		if (cita != null) {
			boolean bRes = Controller.deleteCita(cita);
			if (bRes) {
				response.sendRedirect("admcitas.jsp");
			} else {
				mostrarError(response, "No se ha borrado la cita");
			}
		} else {
			mostrarError(response, "Error ServletBorrarCita");
		}
	}

//----------------------------------------------------------------------Paises-----------------------------------------------------------------
	/**
	 * Método para "borrar" un pais
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
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

//----------------------------------------------------------------------Centros-----------------------------------------------------------------
	/**
	 * Método para "borrar" un centro
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
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

//----------------------------------------------------------------------Otros-----------------------------------------------------------------
	/**
	 * Método para cerrar sesión
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private static void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		// response.sendRedirect("index.html");
		System.out.println("Sesión cerrada");
	}

	/**
	 * Método para mostrar un error por pantalla
	 * 
	 * @param response
	 * @param sMensaje
	 * @throws IOException
	 */
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
