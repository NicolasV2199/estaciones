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
import com.example.demo.model.Muestreo;
import com.example.demo.model.Parcela;
import com.example.demo.services.IMuestreoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/ingreso"})
public class MuestreoController {

	@Autowired IMuestreoService ms;
	
/*http://localhost:8080/muestreo/...*/
	
	/*VALORES DE PRUEBA
	{
    "tipo" : "C", 
    "area" : null,
    "estacion" : {"id":"1"},
    "parcelas": null
	}
	*/
	
	//CREAR UNA MUESTREO
	@PostMapping("/muestreo/crear")
	public Muestreo crear(@RequestBody Muestreo m) {
		return ms.crearMuestreo(m);
	}
	
	//EDITAR UNA ESTACION
	@PutMapping("/muestreo/editar/{id}")
	public Muestreo editar(@RequestBody Muestreo m, @PathVariable("id") Long id) {
		Muestreo oldMuestreo = ms.buscarMuestreoId(id);
		
		char tipo = m.getTipo();
		oldMuestreo.setTipo(tipo);
		
		float area = m.getArea();
		oldMuestreo.setArea(area);
		
		Estacion estacion = m.getEstacion();
		oldMuestreo.setEstacion(estacion);
		
		Set<Parcela> parcelas = m.getParcelas();
		oldMuestreo.setParcelas(parcelas);
		
		return ms.actualizarMuestreo(oldMuestreo);
	}
	
	//ELIMINAR UN MUESTREO
	@DeleteMapping("/muestreo/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Muestreo m = ms.buscarMuestreoId(id);
		ms.eliminarMuestreo(m);
	}
	
	//LISTANDO TODAS LAS ESTACIONES
	@GetMapping("/muestreo/listarmuestreos")
	public List<Muestreo> listarMuestreos(){
		return ms.listarTodosMuestreos();
	}
	
	@GetMapping("/muestreo/obtener/{id)")
	public Muestreo obtener(@PathVariable("id") Long id) {
		return ms.buscarMuestreoId(id);
	}

}
