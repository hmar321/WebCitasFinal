package com.model;

/**
 * Clase que crea objetos para la tabla categorias
 * 
 * @author H�ctor Almaraz
 *
 */
public class Categorias {
	private String categoria;
	private String descripcion;

	/**
	 * Constructor de objetos categoria
	 * 
	 * @param categoria
	 * @param descripcion
	 */
	public Categorias(String categoria, String descripcion) {
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el nombre de la categor�a
	 * 
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Introduce el nombre de la categor�a que recibe como String
	 * 
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve la descripci�n de la categor�a
	 * 
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Introduce la descripci�n de la categor�a que recibe como String
	 * 
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * M�todo para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(categoria);
		sbResultado.append(" ");
		sbResultado.append(descripcion);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}
}
