package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

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
import com.example.demo.model.Muestreo;
import com.example.demo.model.Usuario;
import com.example.demo.services.IEstacionService;


@RestController
@RequestMapping("/api/estacion")
@CrossOrigin(origins = "http://localhost:3000")
public class EstacionController {

	@Autowired IEstacionService es;
	
	
	//CREAR UNA ESTACION
	@PostMapping("/crear")
	public Estacion crear(@RequestBody Estacion c) {
		return es.crearEstacion(c);
	}
	
	
	//EDITAR UNA ESTACION
	@PutMapping("/editar/{id}")
	public Estacion editar(@RequestBody Estacion c, @PathVariable("id") Long id) {
		Optional<Estacion> old;
		String aux;
		
		if(id!=null) {
			
			old = es.buscarEstacionId(id);
			
			if(old.isPresent()) {
				
				Estacion oldEstacion = old.get();
				
				aux = c.getNombre();
				oldEstacion.setNombre(aux);
				
				aux = c.getDescripcion();
				oldEstacion.setDescripcion(aux);
				
				aux = c.getRegion();
				oldEstacion.setRegion(aux);
				
				aux = c.getUnidad();
				oldEstacion.setUnidad(aux);
				
				float coordenada = c.getLatitud();
				oldEstacion.setLatitud(coordenada);
				
				coordenada = c.getLongitud();
				oldEstacion.setLongitud(coordenada);
				
				Usuario usuario = c.getUsuario();
				oldEstacion.setUsuario(usuario);
				
				Muestreo muestreo = c.getMuestreo();
				oldEstacion.setMuestreo(muestreo);
				
				return es.actualizarEstacion(oldEstacion);
			}
		}
		
		return null;
	}
	
	//ELIMINAR UNA ESTACION
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		if(id!=null) {
			es.eliminarEstacion(id);
		}
	}
	
	//LISTANDO TODAS LAS ESTACIONES
	@GetMapping("/listarestaciones")
	public List<Estacion> listarEstaciones(){
		return es.listarTodasEstaciones();
	}
	
	//LISTANDO UNA ESTACIÓN
	@GetMapping("/obtener/{id}")
	public Estacion obtenerEstaciones(@PathVariable("id") Long id){
		
		if(id!=null) {
			Optional<Estacion> c = es.buscarEstacionId(id);
			if(c.isPresent()) {
				return c.get();
			}
		}
		
		return null;
	}
	
	/*http://localhost:8080/estacion/...*/
	
/* ejemplo JSON esperado para crear una estación
	 * {
    "nombre": "La candelaria",
    "descripcion": "Estacion nueva",
    "region": "Caribe",
    "usuario" : {"id": "1"}, 
    "muestreos" : [{"id": "1"}]
	}
*/

}

