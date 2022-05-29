package com.model;

/**
 * Clase que crea objetos para la tabla preferencias
 * 
 * @author Héctor Almaraz
 *
 */
public class Preferencias {
	private String email;
	private String categoria;
	private String sinconizar;

	/**
	 * Constructor de objetos preferencia
	 * 
	 * @param email
	 * @param categoria
	 * @param sinconizar
	 */
	public Preferencias(String email, String categoria, String sinconizar) {
		this.email = email;
		this.categoria = categoria;
		this.sinconizar = sinconizar;
	}

	/**
	 * Devuelve el email del usuario de las preferencias
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Introduce el email del usuario de las preferencias que recibe como String
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve la categoría de las preferencias
	 * 
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Introduce la categoría de las preferencias que recibe como String
	 * 
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve una respuesta si/no de las preferencias
	 * 
	 * @return the sinconizar
	 */
	public String getSinconizar() {
		return sinconizar;
	}

	/**
	 * Introduce una respuesta si/no de las preferencias que recibe como String
	 * 
	 * @param sinconizar the sinconizar to set
	 */
	public void setSinconizar(String sinconizar) {
		this.sinconizar = sinconizar;
	}

	/**
	 * Método para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(email);
		sbResultado.append(" ");
		sbResultado.append(categoria);
		sbResultado.append(" ");
		sbResultado.append(sinconizar);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}
}
