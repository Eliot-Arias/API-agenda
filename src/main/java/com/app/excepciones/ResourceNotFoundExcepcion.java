package com.app.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepcion extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String nombreDelRecurso;
	private String nombredelCampo;
	private Integer valorDelCampo;
	private String valores;
	private String nomUser;
	private String contrasenia;
	
	public ResourceNotFoundExcepcion(String nombreDelRecurso, String nombredelCampo, Integer valorDelCampo) {
		super(String.format("%s no encontrado con : %s : '%s' ", nombreDelRecurso, nombredelCampo, valorDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombredelCampo = nombredelCampo;
		this.valorDelCampo = valorDelCampo;
	}
	
	public ResourceNotFoundExcepcion(String nombreDelRecurso, String nombredelCampo, String nomUser) {
		super(String.format("%s erroneos : %s : '%s' ", nombreDelRecurso, nombredelCampo, nomUser));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombredelCampo = nombredelCampo;
		this.nomUser = nomUser;
	}
}