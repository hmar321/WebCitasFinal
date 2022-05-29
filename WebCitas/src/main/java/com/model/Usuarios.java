package com.model;

/**
 * Clase que crea objetos para la tabla usuarios
 * 
 * @author Héctor Almaraz
 *
 */
public class Usuarios {
	private String nombre;
	private String direccion;
	private String ciudad;
	private String pais;
	private String sexo;
	private String pareja;
	private String email;
	private String contrasena;

	/**
	 * Constructor de objetos usuario
	 * 
	 * @param nombre
	 * @param direccion
	 * @param ciudad
	 * @param pais
	 * @param sexo
	 * @param pareja
	 * @param email
	 * @param contrasena
	 */
	public Usuarios(String nombre, String direccion, String ciudad, String pais, String sexo, String pareja,
			String email, String contrasena) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
		this.sexo = sexo;
		this.pareja = pareja;
		this.email = email;
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve el nombre del usuario
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Introduce el nombre del usuario que recibe como String
	 * 
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la dirección del usuario
	 * 
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * Introduce la dirección del usuario que recibe como String
	 * 
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve la ciudad del usuario
	 * 
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Introduce la ciudad del usuario que recibe como String
	 * 
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Devuelve el país del usuario
	 * 
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Introduce el país del usuario que recibe como String
	 * 
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Devuelve el género del usuario
	 * 
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Introduce el género del usuario que recibe como String
	 * 
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Devuelve el género de la orientación sexual del usuario
	 * 
	 * @return the pareja
	 */
	public String getPareja() {
		return pareja;
	}

	/**
	 * Introduce el género de la orientación sexual del usuario que recibe como
	 * String
	 * 
	 * @param pareja the pareja to set
	 */
	public void setPareja(String pareja) {
		this.pareja = pareja;
	}

	/**
	 * Devuelve el email del usuario
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Introduce el email del usuario que recibe como String
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve la contraseña del usuario
	 * 
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Introduce la contraseña del usuario que recibe como String
	 * 
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Método para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(nombre);
		sbResultado.append(" ");
		sbResultado.append(direccion);
		sbResultado.append(" ");
		sbResultado.append(ciudad);
		sbResultado.append(" ");
		sbResultado.append(pais);
		sbResultado.append(" ");
		sbResultado.append(sexo);
		sbResultado.append(" ");
		sbResultado.append(pareja);
		sbResultado.append(" ");
		sbResultado.append(email);
		sbResultado.append(" ");
		sbResultado.append(contrasena);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}

}
