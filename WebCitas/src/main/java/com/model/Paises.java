package com.model;

/**
 * Clase que crea objetos para la tabla paises
 * 
 * @author Héctor Almaraz
 *
 */
public class Paises {
	private String pais;
	private String n_usuarios;

	/**
	 * Constructor de objetos pais
	 * 
	 * @param pais
	 * @param n_usuarios
	 */
	public Paises(String pais, String n_usuarios) {
		this.pais = pais;
		this.n_usuarios = n_usuarios;
	}

	/**
	 * Devuelve el nombre del país
	 * 
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Introduce el nombre del país que recibe como String
	 * 
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Devuelve el número de usuarios del país
	 * 
	 * @return the n_usuarios
	 */
	public String getN_usuarios() {
		return n_usuarios;
	}

	/**
	 * Introduce el número de usuarios del país que recibe como String
	 * 
	 * @param n_usuarios the n_usuarios to set
	 */
	public void setN_usuarios(String n_usuarios) {
		this.n_usuarios = n_usuarios;
	}

	/**
	 * Método para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(pais);
		sbResultado.append(" ");
		sbResultado.append(n_usuarios);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}

}
