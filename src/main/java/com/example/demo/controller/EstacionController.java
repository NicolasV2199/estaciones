package com.example.demo.controller;


import java.util.List;


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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EstacionController {

	@Autowired IEstacionService es;
	
/*http://localhost:8080/estacion/...*/
	
	//CREAR UNA ESTACION
	@PostMapping("/estacion/crear")
	public Estacion crear(@RequestBody Estacion c) {
		return es.crearEstacion(c);
	}
	
	/*valores de prueba
	 * {
    "nombre": "La candelaria",
    "descripcion": "Estacion nueva",
    "region": "Caribe",
    "usuario" : {"id": "1"}, 
    "muestreos" : [{"id": "1"}]
	}
*/
	
	//EDITAR UNA ESTACION
	@PutMapping("/estacion/editar/{id}")
	public Estacion editar(@RequestBody Estacion c, @PathVariable("id") Long id) {
		Estacion oldEstacion = es.buscarEstacionId(id);
		
		String nombre = c.getNombre();
		oldEstacion.setNombre(nombre);
		
		String descripcion = c.getDescripcion();
		oldEstacion.setDescripcion(descripcion);
		
		String region = c.getRegion();
		oldEstacion.setRegion(region);
		
		String unidad = c.getUnidad();
		oldEstacion.setUnidad(unidad);
		
		float latitud = c.getLatitud();
		oldEstacion.setLatitud(latitud);
		
		float longitud = c.getLongitud();
		oldEstacion.setLongitud(longitud);
		
		Usuario usuario = c.getUsuario();
		oldEstacion.setUsuario(usuario);
		
		Muestreo muestreo = c.getMuestreo();
		oldEstacion.setMuestreo(muestreo);
		
		return es.actualizarEstacion(oldEstacion);
	}
	
	//ELIMINAR UNA ESTACION
	@DeleteMapping("/estacion/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Estacion c = es.buscarEstacionId(id);
		es.eliminarEstacion(c);
	}
	
	//LISTANDO TODAS LAS ESTACIONES
	@GetMapping("/estacion/listarestaciones")
	public List<Estacion> listarEstaciones(){
		return es.listarTodasEstaciones();
	}
	
	@GetMapping("/estacion/obtener/{id}")
	public Estacion obtenerEstaciones(@PathVariable("id") Long id){
		return es.buscarEstacionId(id);
	}

}

