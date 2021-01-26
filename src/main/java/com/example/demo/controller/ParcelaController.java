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

import com.example.demo.model.Muestreo;
import com.example.demo.model.Parcela;
import com.example.demo.services.IParcelaService;

@RestController
@RequestMapping(ConstantesController.VERSION_API+"/parcela")
@CrossOrigin(origins = "http://localhost:3000")
public class ParcelaController {

	@Autowired IParcelaService ps;
	
	//CREAR UNA PARCELA
	@PostMapping
	public Parcela crear(@RequestBody Parcela p) {
		return ps.crearParcela(p);
	}
	
	//EDITAR UNA PARCELA
	@PutMapping("/{id}")
	public Parcela editar(@RequestBody Parcela p, @PathVariable("id") Long id) {
		
		String aux;
		
		if(id!=null) {
			Optional<Parcela> old = ps.buscarParcelaId(id);
			if(old.isPresent()) {
				
				Parcela oldParcela = old.get();
				
				float coordenada = p.getLatitud();
				oldParcela.setLatitud(coordenada);
				
				coordenada = p.getLongitud();
				oldParcela.setLongitud(coordenada);
				
				float area = p.getArea();
				oldParcela.setArea(area);
				
				aux = p.getDescripcion();
				oldParcela.setDescripcion(aux);
				
				Muestreo muestreo = p.getMuestreo();
				oldParcela.setMuestreo(muestreo);
				
				return ps.actualizarParcela(oldParcela);
			}
			
		}
		
		return null;

	}
	
	//ELIMINAR UNA PARCELA
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		if(id!=null) {
			ps.eliminarParcela(id);
		}
	}
	
	//LISTANDO TODAS LAS PARCELAS
	@GetMapping
	public List<Parcela> listarParcelas(){
		return ps.listarTodasParcelas();
	}
	
	/*http://localhost:8080/api/parcela/...*/
	
	/*Ejemplo de JSON esperado para crear una parcela
	 {
	 "latitud" : "150.25.63", 
    "longitud" : "180.23.65",
    "area" : "153",
    "descripcion": "parcela nueva",
    "muestreo" : {"id": "1"}
    }
	*/

}
