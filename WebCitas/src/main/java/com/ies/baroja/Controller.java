package com.ies.baroja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.model.Centros;
import com.model.Citas;
import com.model.Paises;
import com.model.Usuarios;
import com.mysql.jdbc.PreparedStatement;
import com.bbdd.ConexionBBDD;

/**
 * Clase que comunica el servlet con la base de datos
 * 
 * @author Héctor Almaraz
 */
public class Controller {
	private static String sConsultaUsuarios = "SELECT Nombre, Direccion,Ciudad,Pais,Sexo,Pareja,Email,Contrasena FROM usuarios WHERE IsAvailable = true;";
	private static String sConsultaPaises = "SELECT pais,n_usuarios FROM paises WHERE IsAvailable = true;";
	private static String sConsultaCentros = "SELECT cp,centro,direccion,ciudad,pais,web FROM centros WHERE IsAvailable = true;";
	private static String sConsultaCitas = "SELECT cita_id,fech_hora,centro,email1,email2 FROM citas WHERE IsAvailable = true;";
//----------------------------------------------------------------------Usuarios---------------------------------------------------------------
	/**
	 * Método que devuelve un LinkedList con todos los usuarios IsAvailable=true de la base de datos
	 * @return listaUsuarios
	 */
	public static LinkedList<Usuarios> getUsuarios() {
		// Objeto con la lista de usuarios
		LinkedList<Usuarios> listaUsuarios = new LinkedList<Usuarios>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaUsuarios);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Usuarios usuario = new Usuarios(rsResultado.getString("Nombre"), rsResultado.getString("Direccion"),
							rsResultado.getString("Ciudad"), rsResultado.getString("Pais"),
							rsResultado.getString("Sexo"), rsResultado.getString("Pareja"),
							rsResultado.getString("Email"), rsResultado.getString("Contrasena"));
					// Lo insertamos en la lista
					listaUsuarios.add(usuario);

				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de usuarios=" + listaUsuarios.size());
		} catch (SQLException sqlex) {
			System.out.println("Error conexión: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaUsuarios;
	}

	/**
	 * Método que recibe un objeto usuario y llama a ConexionBBDD.insertar(usuario), devuelve un boolean en funcion del resultado
	 * @param usuario
	 * @return bRes
	 */
	public static boolean insertar(Usuarios usuario) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.insertar(usuario);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

	/**
	 * Método que recibe un objeto usuario y devuelve un LinkedList con todos los usuarios IsAvailable=true de la base de datos que coincidan en interés con el usuario
	 * @param usuario
	 * @return listaUsuarios
	 */
	public static LinkedList<Usuarios> getUsuariosComun(Usuarios usuario) {
		// Objeto con la lista de usuarios
		LinkedList<Usuarios> listaUsuarios = new LinkedList<Usuarios>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		String sConsultaUsuariosComun = "SELECT Nombre, Direccion,Ciudad,Pais,Sexo,Pareja,Email,Contrasena FROM usuarios WHERE IsAvailable = true AND sexo='"
				+ usuario.getPareja() + "' AND pareja='" + usuario.getSexo() + "' AND Pais='" + usuario.getPais() + "';";
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaUsuariosComun);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Usuarios usuarioc = new Usuarios(rsResultado.getString("Nombre"), rsResultado.getString("Direccion"),
							rsResultado.getString("Ciudad"), rsResultado.getString("Pais"),
							rsResultado.getString("Sexo"), rsResultado.getString("Pareja"),
							rsResultado.getString("Email"), rsResultado.getString("Contrasena"));
					// Lo insertamos en la lista
					listaUsuarios.add(usuarioc);

				}

			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de citas del usuario=" + listaUsuarios.size());
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaUsuarios;
	}

	/**
	 * Método que recibe un string email y busca el usuario en la base de datos, si lo encuentra monta un objeto usuario y lo devuelve
	 * @param email
	 * @return usuario
	 */
	public static Usuarios getUsuario(String email) {
		// Objeto con la lista de jugadores
		Usuarios usuario = null;
		/** 1-conecar a la BBDD */
		ConexionBBDD miConexion = new ConexionBBDD();
		String sConsultaBuscaUsuario = "SELECT nombre, direccion, ciudad, pais,sexo,pareja,email,contrasena FROM usuarios WHERE email = '"
				+ email + "';";
		try {
			miConexion.conectar();
			/** 2-lanzar consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuscaUsuario);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					usuario = new Usuarios(rsResultado.getString("nombre"), rsResultado.getString("direccion"),
							rsResultado.getString("ciudad"), rsResultado.getString("pais"),
							rsResultado.getString("sexo"), rsResultado.getString("pareja"),
							rsResultado.getString("email"), rsResultado.getString("contrasena"));
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			/** 4-cerrar conexión */
		} finally {
			miConexion.desconectar();
		}
		return usuario;
	}

	/**
	 * Método que recibe un objeto usuario y llama a ConexionBBDD.borrar(usuario), devuelve un boolean en funcion del resultado
	 * @param usuario
	 * @return bRes
	 */
	public static boolean deleteUsuario(Usuarios usuario) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.borrar(usuario);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

//----------------------------------------------------------------------Paises-----------------------------------------------------------------
	/**
	 * Método que devuelve un LinkedList con todos los paises IsAvailable=true de la base de datos
	 * @return listaPaises
	 */
	public static LinkedList<Paises> getPaises() {
		// Objeto con la lista de usuarios
		LinkedList<Paises> listaPaises = new LinkedList<Paises>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaPaises);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Paises pais = new Paises(rsResultado.getString("pais"), rsResultado.getString("n_usuarios"));
					// Lo insertamos en la lista
					listaPaises.add(pais);

				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de usuarios=" + listaPaises.size());
		} catch (SQLException sqlex) {
			System.out.println("Error conexión: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaPaises;
	}

	/**
	 * Método que recibe un string pais y busca el pais en la base de datos, si lo encuentra monta un objeto pais y lo devuelve
	 * @param pais
	 * @return pPais
	 */
	public static Paises getPais(String pais) {
		// Objeto con la lista de jugadores
		Paises pPais = null;
		/** 1-conecar a la BBDD */
		ConexionBBDD miConexion = new ConexionBBDD();
		String sConsultaBuscaPais = "SELECT pais,n_usuarios FROM paises WHERE IsAvailable = true AND pais = '" + pais
				+ "';";
		try {
			miConexion.conectar();
			/** 2-lanzar consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuscaPais);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					pPais = new Paises(rsResultado.getString("pais"), rsResultado.getString("n_usuarios"));
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			/** 4-cerrar conexión */
		} finally {
			miConexion.desconectar();
		}
		return pPais;
	}

	/**
	 * Método que recibe un objeto pais y llama a ConexionBBDD.borrar(pais), devuelve un boolean en funcion del resultado
	 * @param pais
	 * @return bRes
	 */
	public static boolean deletePais(Paises pais) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.borrar(pais);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

//----------------------------------------------------------------------Centros---------------------------------------------------------------
	/**
	 * Método que devuelve un LinkedList con todos los centros IsAvailable=true de la base de datos
	 * @return listaCentros
	 */
	public static LinkedList<Centros> getCentros() {
		// Objeto con la lista de usuarios
		LinkedList<Centros> listaCentros = new LinkedList<Centros>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaCentros);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Centros centro = new Centros(rsResultado.getString("cp"), rsResultado.getString("centro"),
							rsResultado.getString("direccion"), rsResultado.getString("ciudad"),
							rsResultado.getString("pais"), rsResultado.getString("web"));
					// Lo insertamos en la lista
					listaCentros.add(centro);

				}

			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de usuarios=" + listaCentros.size());
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaCentros;
	}

	/**
	 * Método que recibe un string centro y busca el centro en la base de datos, si lo encuentra monta un objeto centro y lo devuelve
	 * @param centro
	 * @return cCentro
	 */
	public static Centros getCentro(String centro) {
		// Objeto con la lista de jugadores
		Centros cCentro = null;
		/** 1-conecar a la BBDD */
		ConexionBBDD miConexion = new ConexionBBDD();
		System.out.println("" + centro);
		String sConsultaBuscaCentro = "SELECT cp,centro,direccion,ciudad,pais,web FROM centros WHERE IsAvailable = true AND centro = '"
				+ centro + "';";

		try {
			miConexion.conectar();
			/** 2-lanzar consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuscaCentro);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					cCentro = new Centros(rsResultado.getString("cp"), rsResultado.getString("centro"),
							rsResultado.getString("direccion"), rsResultado.getString("ciudad"),
							rsResultado.getString("pais"), rsResultado.getString("web"));
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			/** 4-cerrar conexión */
		} finally {
			miConexion.desconectar();
		}
		return cCentro;
	}

	/**
	 * Método que recibe un objeto centro y llama a ConexionBBDD.borrar(centro), devuelve un boolean en funcion del resultado
	 * @param centro
	 * @return bRes
	 */
	public static boolean deleteCentro(Centros centro) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.borrar(centro);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

//----------------------------------------------------------------------Citas-----------------------------------------------------------------
	/**
	 * Método que devuelve un LinkedList con todos las citas IsAvailable=true de la base de datos
	 * @return listaCitas
	 */
	public static LinkedList<Citas> getCitas() {
		// Objeto con la lista de usuarios
		LinkedList<Citas> listaCitas = new LinkedList<Citas>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaCitas);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Citas cita = new Citas(rsResultado.getString("cita_id"), rsResultado.getString("fech_hora"),
							rsResultado.getString("centro"), rsResultado.getString("email1"),
							rsResultado.getString("email2"));
					// Lo insertamos en la lista
					listaCitas.add(cita);

				}

			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de citas=" + listaCitas.size());
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaCitas;
	}

	/**
	 * Método que recibe un objeto usuario y busca una cita IsAvailable=TRUE del usuario en la base de datos, si lo encuentra monta un LinkedList de citas y lo devuelve
	 * @param usuario
	 * @return listaCitas
	 */
	public static LinkedList<Citas> getCitasUsuario(Usuarios usuario) {
		// Objeto con la lista de usuarios
		LinkedList<Citas> listaCitas = new LinkedList<Citas>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		String sConsultaCitaUsuario = "SELECT fech_hora, centro, email1, email2 FROM citas WHERE IsAvailable = TRUE AND email1 = '"
				+ usuario.getEmail() + "' OR email2 = '" + usuario.getEmail() + "';";
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaCitaUsuario);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					Citas cita = new Citas(rsResultado.getString("fech_hora"), rsResultado.getString("centro"),
							rsResultado.getString("email1"), rsResultado.getString("email2"));
					// Lo insertamos en la lista
					listaCitas.add(cita);

				}

			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de citas del usuario=" + listaCitas.size());
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaCitas;
	}

	/**
	 * Método que recibe un objeto cita y llama a ConexionBBDD.insertar(cita), devuelve un boolean en funcion del resultado
	 * @param cita
	 * @return bRes
	 */
	public static boolean insertar(Citas cita) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.insertar(cita);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

	/**
	 * Método que recibe un string cita_id y busca la cita en la base de datos, si la encuentra monta un objeto cita y lo devuelve
	 * @param cita_id
	 * @return cita
	 */
	public static Citas getCita(String cita_id) {
		// Objeto con la lista de jugadores
		Citas cita = null;
		/** 1-conecar a la BBDD */
		ConexionBBDD miConexion = new ConexionBBDD();
		String sConsultaBuscaCita = "SELECT cita_id,fech_hora,centro,email1,email2 FROM citas WHERE IsAvailable = true AND cita_id = '"
				+ cita_id + "';";
		try {
			miConexion.conectar();
			/** 2-lanzar consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuscaCita);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla (cada jugador)
					cita = new Citas(rsResultado.getString("cita_id"), rsResultado.getString("fech_hora"),
							rsResultado.getString("centro"), rsResultado.getString("email1"),
							rsResultado.getString("email2"));
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			/** 4-cerrar conexión */
		} finally {
			miConexion.desconectar();
		}
		return cita;
	}

	/**
	 * Método que recibe un objeto cita y llama a ConexionBBDD.borrar(cita), devuelve un boolean en funcion del resultado
	 * @param cita
	 * @return bRes
	 */
	public static boolean deleteCita(Citas cita) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.borrar(cita);
			System.out.println("Resultado controlador = " + iRes);
		} catch (SQLException sqlex) {
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}

}