package com.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Centros;
import com.model.Citas;
import com.model.Paises;
import com.model.Usuarios;

/**
 * Clase que centraliza los métodos de acceso a BBDD
 * 
 * @author JAGD
 */

public class ConexionBBDD {

	Connection conexion;
	int port = 3306;
	String host = "localhost";
	String db = "web_citas";
	String user = "root";
	String password = "";

	String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, db);

	/**
	 *  Método para conectarse a la base de datos
	 * @throws SQLException
	 */
	public void conectar() throws SQLException {
		conexion = DriverManager.getConnection(url, user, password);
	}

	/**
	 * Método para desconectarse de la base de datos
	 */
	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
	}

	/**
	 * Método para ejecutar una consulta que recibe como string y devuelve un ResultSet en función del resultado
	 * @param sentencia
	 * @return
	 */
	public ResultSet ejecutarConsulta(String sentencia) {
		ResultSet rsResultado = null;
		try {
			System.out.println("Ejecutando: " + sentencia);
			PreparedStatement prepStatement = conexion.prepareStatement(sentencia);
			rsResultado = prepStatement.executeQuery();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
		return rsResultado;
	}
//----------------------------------------------------------------------Usuarios-----------------------------------------------------------------
	/**
	 * Método para insertar un usuario, recibe un objeto usuario y devuelve un int en función del resultado
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	public int insertar(Usuarios usuario) throws SQLException {
		int iRes = 0;
		String sInsert = "insert into usuarios (Nombre, Direccion, 	Ciudad, Pais, sexo, Pareja, Email, Contrasena) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			System.out.println("Ejecutando: " + sInsert);
			System.out.println("datos: " + usuario);
			PreparedStatement prepStatement = conexion.prepareStatement(sInsert);
			prepStatement.setString(1, usuario.getNombre());
			prepStatement.setString(2, usuario.getDireccion());
			prepStatement.setString(3, usuario.getCiudad());
			prepStatement.setString(4, usuario.getPais());
			prepStatement.setString(5, usuario.getSexo());
			prepStatement.setString(6, usuario.getPareja());
			prepStatement.setString(7, usuario.getEmail());
			prepStatement.setString(8, usuario.getContrasena());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}
	
	/**
	 * Método para actualizar el IsAvailable a false de un usuario, recibe un objeto usuario y devuelve un int en función del resultado
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	public int borrar(Usuarios usuario) throws SQLException {
		int iRes = 0;
		//String sDelete = "DELETE FROM web_citas.usuarios WHERE email=?;";
		String sDelete = "UPDATE web_citas.usuarios SET IsAvailable = false where email=?;";
		try {
			System.out.println("Ejecutando: " + sDelete);
			System.out.println("datos: " + usuario);
			PreparedStatement prepStatement = conexion.prepareStatement(sDelete);
			prepStatement.setString(1, usuario.getEmail());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}
//----------------------------------------------------------------------Citas-----------------------------------------------------------------
	/**
	 * Método para insertar una cita, recibe un objeto cita y devuelve un int en función del resultado
	 * @param cita
	 * @return
	 * @throws SQLException
	 */
	public int insertar(Citas cita) throws SQLException {
		int iRes = 0;
		String sInsert = "INSERT INTO web_citas.citas (fech_hora,centro,email1,email2) VALUES (?, ?, ?, ?);";
		try {
			System.out.println("Ejecutando: " + sInsert);
			System.out.println("datos: " + cita);
			PreparedStatement prepStatement = conexion.prepareStatement(sInsert);
			prepStatement.setString(1, cita.getFecha_hora());
			prepStatement.setString(2, cita.getCentro());
			prepStatement.setString(3, cita.getEmail1());
			prepStatement.setString(4, cita.getEmail2());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}

	/**
	 * Método para actualizar el IsAvailable a false de una cita, recibe un objeto cita y devuelve un int en función del resultado
	 * @param cita
	 * @return
	 * @throws SQLException
	 */
	public int borrar(Citas cita) throws SQLException {
		int iRes = 0;
		String sDelete = "UPDATE web_citas.citas SET IsAvailable = false where cita_id=?;";
		try {
			System.out.println("Ejecutando: " + sDelete);
			System.out.println("datos: " + cita);
			PreparedStatement prepStatement = conexion.prepareStatement(sDelete);
			prepStatement.setString(1, cita.getId());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}
//----------------------------------------------------------------------Paises-----------------------------------------------------------------
	/**
	 * Método para actualizar el IsAvailable a false de un pais, recibe un objeto pais y devuelve un int en función del resultado
	 * @param pais
	 * @return
	 * @throws SQLException
	 */
	public int borrar(Paises pais) throws SQLException {
		int iRes = 0;
		String sDelete = "UPDATE web_citas.paises SET IsAvailable = false where pais=?;";
		try {
			System.out.println("Ejecutando: " + sDelete);
			System.out.println("datos: " + pais);
			PreparedStatement prepStatement = conexion.prepareStatement(sDelete);
			prepStatement.setString(1, pais.getPais());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}
//----------------------------------------------------------------------Centros-----------------------------------------------------------------
	/**
	 * Método para actualizar el IsAvailable a false de un centro, recibe un objeto centro y devuelve un int en función del resultado
	 * @param centro
	 * @return
	 * @throws SQLException
	 */
	public int borrar(Centros centro) throws SQLException {
		int iRes = 0;
		String sDelete = "UPDATE web_citas.centros SET IsAvailable = false WHERE centro=?;";
		try {
			System.out.println("Ejecutando: " + sDelete);
			System.out.println("datos: " + centro);
			PreparedStatement prepStatement = conexion.prepareStatement(sDelete);
			prepStatement.setString(1, centro.getCentro());
			iRes = prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error : " + sqlex.getMessage());
			sqlex.printStackTrace();
			throw sqlex;
		}
		return iRes;
	}
}
