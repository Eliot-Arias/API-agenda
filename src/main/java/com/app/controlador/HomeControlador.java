package com.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.ActividadDTO;
import com.app.dto.UsuarioDTO;
import com.app.servicios.ActividadServicio;
import com.app.servicios.UsuarioServicio;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeControlador {

	@Autowired
	UsuarioServicio usuarioServicio;

	@Autowired
	ActividadServicio actividadServicio;

	@ModelAttribute("usuario")
	public UsuarioDTO devolverUsuarioDTO() {
		return new UsuarioDTO();
	}

	@ModelAttribute("actividad")
	public ActividadDTO devolverActividadDTO() {
		return new ActividadDTO();
	}
	
	

	@GetMapping({ "/", "/login" })
	private String inicio() {
		// TODO Auto-generated method stub
		return "login";
	}
	
	@GetMapping("/calendario")
	public String calendario(){
		return "calendario";
	}

	
	@GetMapping("/nuevo")
	public String nuevaActividad(){
		return "nuevo";
	}


	@GetMapping("/registro")
	public String mostrarFormularioRegistro() {
		return "registro";
	}
	
	@GetMapping("/actividad/{id}/selecionar")
	ModelAndView editar(@PathVariable Integer id) {
		ActividadDTO actividadDTO = actividadServicio.buscarActividadPorId(id);
		return new ModelAndView("actualizar")
				.addObject("actividad", actividadDTO)
				.addObject("actividadId", id);	
	}
	
	@PostMapping("/actividad/{id}")
	String actualizar(@PathVariable Integer id,@ModelAttribute("contacto") ActividadDTO actividadDTO, RedirectAttributes ra) {
		actividadServicio.actualizaActividad(id, actividadDTO);
		ra.addFlashAttribute("msgExito", "El Contacto fue editado exitosamente");
		return "redirect:/actividades";
	}
	
	

	@PostMapping("/actividad")
	String registrarNuevaActividad(@ModelAttribute("actividad") ActividadDTO actividadDTO,
			@CookieValue("id") Integer usuarioId, RedirectAttributes ra) {
		actividadServicio.registrarActividad(usuarioId, actividadDTO);
		ra.addFlashAttribute("msgExito", "La actividad fue agregada exitosamente");
		return "redirect:/actividades";
	}
	
	@GetMapping("/actividad/{id}/eliminar")
	String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		actividadServicio.eliminarActividad(id);
		ra.addFlashAttribute("msgExito", "La actividad fue eliminado con exito");
		return "redirect:/actividades";
	}
	
	
	

	@GetMapping("/actividades")
	ModelAndView listarActividadesPorUsuario(@CookieValue("id") Integer usuarioId) {
		List<ActividadDTO> actividades = actividadServicio.listarActividadPorIdUsuario(usuarioId);

		return new ModelAndView("actividades").addObject("actividades", actividades);
	}

	@PostMapping("registrarUsuario")
	public String RegistrarNuevoUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		usuarioServicio.RegistrarUsuario(usuarioDTO);
		return "redirect:/registro?exito";
	}

	@PostMapping("/login/validar")
	public String validarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO, HttpServletResponse response) {
		String nomUser = usuarioDTO.getNomUser();
		String contrasenia = usuarioDTO.getContrasenia();

		UsuarioDTO usuarioValidado = usuarioServicio.validarUsuario(nomUser, contrasenia);

		if (usuarioValidado != null) {

			String apellidos = usuarioValidado.getApellidos().replaceAll("\\s", "");
			
			Cookie userIdC = new Cookie("id", String.valueOf(usuarioValidado.getId()));
			Cookie userNombresC = new Cookie("nombres", usuarioValidado.getNombres());
			Cookie userApellidosC = new Cookie("apellidos", apellidos);
			Cookie userNomUserC = new Cookie("nomUser", usuarioValidado.getNomUser());
			Cookie userCorreoC = new Cookie("correo", usuarioValidado.getCorreo());

			userIdC.setPath("/");
			userNombresC.setPath("/");
			userApellidosC.setPath("/");
			userNomUserC.setPath("/");
			userCorreoC.setPath("/");

			userIdC.setMaxAge(3600);
			userNombresC.setMaxAge(3600);
			userApellidosC.setMaxAge(3600);
			userNomUserC.setMaxAge(3600);
			userCorreoC.setMaxAge(3600);

			response.addCookie(userIdC);
			response.addCookie(userNombresC);
			response.addCookie(userApellidosC);
			response.addCookie(userNomUserC);
			response.addCookie(userCorreoC);

			return "redirect:/actividades";
		} else {
			// Usuario no válido, muestra un mensaje de error
			return "redirect:/login?error";
		}
	}
	
	
	

}
