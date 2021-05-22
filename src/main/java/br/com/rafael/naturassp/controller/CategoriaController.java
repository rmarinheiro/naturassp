package br.com.rafael.naturassp.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.services.CategoriaServiceImpl;
import br.com.rafael.naturassp.services.ICategoriaService;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping
	public ResponseEntity<ArrayList<Categoria>> listarTodas(){
		return ResponseEntity.ok(service.recuperarTodasCategorias());
	}
	
	@GetMapping("/search")
	public ResponseEntity<ArrayList<Categoria>> recuperaPorPalavraChave(@RequestParam(name="key") String palavraChave){
		if(palavraChave != null) {
			return ResponseEntity.ok(service.recuperarPorPalavraChave(palavraChave));
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> adicionarNova( @Valid @RequestBody Categoria categoria){
		Categoria cat = service.inserirNovaCategoria(categoria);
		if(cat != null) {
			return ResponseEntity.status(201).body(cat);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<Categoria> alterarDados(@RequestBody Categoria categoria){
		Categoria cat = service.alterarCategoria(categoria);
		if(cat != null) {
			return ResponseEntity.ok(cat);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	//endpoint com autorização para buscar detalhes por categoria
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> recuperaDetalhes(@PathVariable(name = "id") int id){
		Categoria resultado = service.recuperarPorId(id);
		if(resultado != null) {
			return ResponseEntity.ok(resultado);
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping("/categoriaById")
	public ResponseEntity<ArrayList<Categoria>> recuperarTodasOrdenadasPorId(){
		return ResponseEntity.ok(service.recuperarTodasOrdenadasPorId());
		
	}

}
