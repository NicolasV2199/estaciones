package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filters.JwtService;
import com.example.demo.model.Estacion;
import com.example.demo.model.Usuario;
import com.example.demo.services.IUsuarioService;

@RestController
@RequestMapping(ConstantesController.VERSION_API+"/usuario")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UsuarioController {

	@Autowired IUsuarioService us;
	@Autowired JwtService jwtService;
	
	
	@PostMapping("/token")
	@PreAuthorize("Authenticated")
	public ResponseEntity<String> responseToken(@AuthenticationPrincipal User activeUser){
		return new ResponseEntity<String>(jwtService.createToken(activeUser.getUsername(),new ArrayList<>()), HttpStatus.ACCEPTED);
	}
	
	//CREAR UN USUARIO
	@PostMapping
	public Usuario crear(@RequestBody Usuario u) {
		u.setPassword( new BCryptPasswordEncoder().encode(u.getPassword()) );
		return us.crearUsuario(u);
	}
	
	//EDITAR UN USUARIO
	@PutMapping("/{id}")
	@PreAuthorize("Authenticated")
	public Usuario editar(@RequestBody Usuario u, @PathVariable("id") Long id) {
		
		if(id!=null) {
			
			Optional<Usuario> old = us.buscarUsuarioId(id);
			
			if(old.isPresent()) {
				
				Usuario oldUsuario = old.get();
				
				String aux;
				
				aux = u.getPrimerNombre();
				oldUsuario.setPrimerNombre(aux);
				
				aux = u.getPrimerApellido();
				oldUsuario.setPrimerApellido(aux);
				
				aux = u.getEmail();
				oldUsuario.setEmail(aux);
				
				aux = u.getPassword();
				oldUsuario.setPassword(aux);
				
				Set<Estacion> estaciones = u.getEstaciones();
				oldUsuario.setEstaciones(estaciones);
				
				return us.actualizarUsuario(oldUsuario);
				
			}
		}
		
		return null;
		
	}
	
	//ELIMINAR UN USUARIO
	@DeleteMapping("/{id}")
	@PreAuthorize("Authenticated")
	public void eliminar(@PathVariable("id") Long id) {
		Optional<Usuario> u = us.buscarUsuarioId(id);
		if(u.isPresent()) {
			us.eliminarUsuario(id);
		}
		
	}
	
	//LISTANDO TODOS LOS USUARIOS
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Usuario> listarUsuarios(){
		return us.listarTodosUsuarios();
	}
	
	//LISTAR UN USUARIO
	@GetMapping("/{id}")
	@PreAuthorize("Authenticated")
	public Usuario obtenerUsuarios(@PathVariable("id") Long id){
		Optional<Usuario> u = us.buscarUsuarioId(id);
		if(id!=null) {
			if(u.isPresent()) {
				return u.get();
			}
		}
		return null;
	}
	
	/*http://localhost:8080/usuario/...*/
	
	/*Ejempli JSON esperado para crear Usuario
	 {
    "primerNombre": "Andres",
    "primerApellido": "Perez",
    "email": "Caribe.com",
    "password" : "123", 
    "estaciones" : [{"id": "1"}]
	} 
	*/

}