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

import com.example.demo.model.Muestreo;
import com.example.demo.model.Parcela;
import com.example.demo.services.IParcelaService;

@RestController
@RequestMapping("/parcela")
@CrossOrigin(origins = "http://localhost:3000")
public class ParcelaController {

	@Autowired IParcelaService ps;
	
/*http://localhost:8080/parcela/...*/
	
	/*VALORES DE PRUEBA
	 {
	 "latitud" : "150.25.63", 
    "longitud" : "180.23.65",
    "area" : "153",
    "descripcion": "parcela nueva",
    "muestreo" : {"id": "1"}
    }
	 * */
	
	//CREAR UNA PARCELA
	@PostMapping("/crear")
	public Parcela crear(@RequestBody Parcela p) {
		return ps.crearParcela(p);
	}
	
	//EDITAR UNA PARCELA
	@PutMapping("/editar/{id}")
	public Parcela editar(@RequestBody Parcela p, @PathVariable("id") Long id) {
		Parcela oldParcela = ps.buscarParcelaId(id);
		
		float latitud = p.getLatitud();
		oldParcela.setLatitud(latitud);
		
		float longitud = p.getLongitud();
		oldParcela.setLongitud(longitud);
		
		float area = p.getArea();
		oldParcela.setArea(area);
		
		String descripcion = p.getDescripcion();
		oldParcela.setDescripcion(descripcion);
		
		Muestreo muestreo = p.getMuestreo();
		oldParcela.setMuestreo(muestreo);
		
		return ps.actualizarParcela(oldParcela);
	}
	
	//ELIMINAR UNA PARCELA
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Parcela p = ps.buscarParcelaId(id);
		ps.eliminarParcela(p);
	}
	
	//LISTANDO TODAS LAS PARCELAS
	@GetMapping("/listarparcelas")
	public List<Parcela> listarParcelas(){
		return ps.listarTodasParcelas();
	}

}
