package com.model;

/**
 * Clase que crea objetos para la tabla citas
 * 
 * @author Héctor Almaraz
 *
 */
public class Citas {
	private String id;
	private String fecha_hora;
	private String centro;
	private String email1;
	private String email2;

	/**
	 * Constructor de objetos cita para el admin
	 * 
	 * @param id
	 * @param fecha_hora
	 * @param centro
	 * @param email1
	 * @param email2
	 */
	public Citas(String id, String fecha_hora, String centro, String email1, String email2) {
		super();
		this.id = id;
		this.fecha_hora = fecha_hora;
		this.centro = centro;
		this.email1 = email1;
		this.email2 = email2;
	}

	/**
	 * Constructor de objetos cita para el usuario
	 * 
	 * @param fecha_hora
	 * @param centro
	 * @param email1
	 * @param email2
	 */
	public Citas(String fecha_hora, String centro, String email1, String email2) {
		this.fecha_hora = fecha_hora;
		this.centro = centro;
		this.email1 = email1;
		this.email2 = email2;
	}

	/**
	 * Devuelve el id de la cita
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Introduce el id de la cita que recibe como String
	 * 
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve la fecha_hora de la cita
	 * 
	 * @return the fecha_hora
	 */
	public String getFecha_hora() {
		return fecha_hora;
	}

	/**
	 * Introduce la fecha_hora de la cita que recibe como String
	 * 
	 * @param fecha_hora the fecha_hora to set
	 */
	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	/**
	 * Devuelve el centro de la cita
	 * 
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * Introduce el centro de la cita que recibe como String
	 * 
	 * @param centro the centro to set
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * Devuelve el email del primer usuario de la cita
	 * 
	 * @return the email1
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * Introduce el email del primer usuario de la cita que recibe como String
	 * 
	 * @param email1 the email1 to set
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	/**
	 * Devuelve el email del segundo usuario de la cita
	 * 
	 * @return the email2
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * Introduce el email del segundo usuario de la cita que recibe como String
	 * 
	 * @param email2 the email2 to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	/**
	 * Método para testear el Controller
	 * 
	 * @return the String
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(id);
		sbResultado.append(" ");
		sbResultado.append(fecha_hora);
		sbResultado.append(" ");
		sbResultado.append(centro);
		sbResultado.append(" ");
		sbResultado.append(email1);
		sbResultado.append(" ");
		sbResultado.append(email2);
		sbResultado.append("; \n");
		return sbResultado.toString();
	}
}
