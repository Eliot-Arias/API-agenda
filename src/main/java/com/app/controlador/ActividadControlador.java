package com.app.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ActividadDTO;
import com.app.dto.UsuarioDTO;
import com.app.servicios.ActividadServicio;
import com.app.servicios.UsuarioServicio;

@RequestMapping("/agenda")
@RestController
public class ActividadControlador {
	
	@Autowired
	private ActividadServicio actividadServicio;
	
	
	@GetMapping("/actividades")
	public List<ActividadDTO> obtenerActividades(@CookieValue("id") Integer usuarioId) {
        // Aquí obtén la lista de actividades desde tu servicio
        List<ActividadDTO> actividades = actividadServicio.listarActividadPorIdUsuario(usuarioId);
        return actividades;
    }
	
	
	
	
}    
