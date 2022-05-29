package com.model;

/**
 * Clase que crea objetos para la tabla centros
 * 
 * @author Héctor Almaraz
 *
 */
public class Centros {
	private String cp;
	private String centro;
	private String direccion;
	private String ciudad;
	private String pais;
	private String web;

	/**
	 * Constructor de objetos centro
	 * 
	 * @param cp
	 * @param centro
	 * @param direccion
	 * @param ciudad
	 * @param pais
	 * @param web
	 */
	public Centros(String cp, String centro, String direccion, String ciudad, String pais, String web) {
		this.cp = cp;
		this.centro = centro;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
		this.web = web;
	}

	/**
	 * Devuelve el código postal del centro
	 * 
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * Introduce el código postal del centro que recibe como String
	 * 
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * Devuelve el nombre del centro
	 * 
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * Introduce el nombre del centro que recibe como String
	 * 
	 * @param centro the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * Devuelve la dirección del centro
	 * 
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Introduce la dirección del centro que recibe como String
	 * 
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve la ciudad del centro
	 * 
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Introduce la ciudad del centro que recibe como String
	 * 
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Devuelve el país del centro
	 * 
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Introduce el país del centro que recibe como String
	 * 
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Devuelve la web donde hacer reservas en el centro
	 * 
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * Introduce la web donde hacer reservas en el centro que recibe como String
	 * 
	 * @param web the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * Método para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(cp);
		sbResultado.append(" ");
		sbResultado.append(centro);
		sbResultado.append(" ");
		sbResultado.append(direccion);
		sbResultado.append(" ");
		sbResultado.append(ciudad);
		sbResultado.append(" ");
		sbResultado.append(pais);
		sbResultado.append(" ");
		sbResultado.append(web);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}
}
