package com.example.demo.controller;


import java.util.List;
import java.util.Optional;
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
@RequestMapping(ConstantesController.VERSION_API+"/muestreo")
@CrossOrigin(origins = {"http://localhost:3000"})
public class MuestreoController {

	@Autowired IMuestreoService ms;
	
	//CREAR UNA MUESTREO
	@PostMapping
	public Muestreo crear(@RequestBody Muestreo m) {
		return ms.crearMuestreo(m);
	}
	
	//EDITAR UN MUESTREO
	@PutMapping("/{id}")
	public Muestreo editar(@RequestBody Muestreo m, @PathVariable("id") Long id) {
		
		if(id!=null) {
			Optional<Muestreo> old = ms.buscarMuestreoId(id);
			if(old.isPresent()) {
				Muestreo oldMuestreo = old.get();
				
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
		}
		
		return null;
	}
	
	//ELIMINAR UN MUESTREO
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		
		if(id!=null) {
			ms.eliminarMuestreo(id);
		}
	}
	
	//LISTANDO TODOS LOS MUESTREOS
	@GetMapping
	public List<Muestreo> listarMuestreos(){
		return ms.listarTodosMuestreos();
	}
	
	//LISTANDO UN MUESTREO
	@GetMapping("/{id)")
	public Muestreo obtener(@PathVariable("id") Long id) {
		
		if(id!=null) {
			Optional<Muestreo> m =  ms.buscarMuestreoId(id);
			if(m.isPresent()) {
				return m.get();
			}
		}
		return null;
	}
	
/*http://localhost:8080/muestreo/...*/
	
	/*Ejempli JSON esperado para crear un muestreo
	{
    "tipo" : "C", 
    "area" : null,
    "estacion" : {"id":"1"},
    "parcelas": null
	}
	*/

}
