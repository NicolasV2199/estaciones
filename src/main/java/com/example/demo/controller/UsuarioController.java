package com.example.demo.controller;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Estacion;
import com.example.demo.model.Usuario;
import com.example.demo.services.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

	@Autowired IUsuarioService us;
	
/*http://localhost:8080/usuario/...*/
	
	/*Datos de prueba
	 {
    "primerNombre": "Andres",
    "primerApellido": "Perez",
    "email": "Caribe.com",
    "password" : "123", 
    "estaciones" : [{"id": "1"}]
	} 
	*/
	
	//CREAR UN USUARIO
	@PostMapping("/usuario/crear")
	public Usuario crear(@RequestBody Usuario u) {
		return us.crearUsuario(u);
	}
	
	//EDITAR UN USUARIO
	@PutMapping("/usuario/editar/{id}")
	public Usuario editar(@RequestBody Usuario u, @PathVariable("id") Long id) {
		Usuario oldUsuario = us.buscarUsuarioId(id);
		
		String primerNombre = u.getPrimerNombre();
		oldUsuario.setPrimerNombre(primerNombre);
		
		String primerApellido = u.getPrimerApellido();
		oldUsuario.setPrimerApellido(primerApellido);
		
		String email = u.getEmail();
		oldUsuario.setEmail(email);
		
		String password = u.getPassword();
		oldUsuario.setPassword(password);
		
		Set<Estacion> estaciones = u.getEstaciones();
		oldUsuario.setEstaciones(estaciones);
		
		return us.actualizarUsuario(oldUsuario);
	}
	
	//ELIMINAR UN USUARIO
	@DeleteMapping("/usuario/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Usuario u = us.buscarUsuarioId(id);
		us.eliminarUsuario(u);
	}
	
	//LISTANDO TODOS LOS USUARIOS
	@GetMapping("/usuario/listarusuarios")
	public List<Usuario> listarUsuarios(){
		return us.listarTodosUsuarios();
	}
	
	@GetMapping("/usuario/obtener/{id}")
	public Usuario obtenerUsuarios(@PathVariable("id") Long id){
		return us.buscarUsuarioId(id);
	}

}