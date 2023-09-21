package com.app.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ActividadDTO {

	private Integer id;
	private String titulo;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date fechaFin;

	private String descripcion;
	private String color;
	private String notas;

	public ActividadDTO() {
		super();
	}

	public ActividadDTO(Integer id, String titulo, Date fechaInicio, Date fechaFin, String descripcion, String color,
			String notas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.color = color;
		this.notas = notas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

}
