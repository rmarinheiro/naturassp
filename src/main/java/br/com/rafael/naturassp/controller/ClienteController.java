package br.com.rafael.naturassp.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.naturassp.services.dto.CompradorDTO;
import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.services.IClienteService;

@RestController
@RequestMapping(value = "cliente")
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> buscarPeloTelefone(@PathVariable String cpf){
		Cliente cliente = service.buscaPeloCpf(cpf);
		if(cliente != null) {
			return ResponseEntity.ok(cliente);
		}
			
		return ResponseEntity.notFound().build();
	
		
	}
	
	@GetMapping("/busca/{letra}")
	public ResponseEntity<ArrayList<Cliente>> buscarPorLetra(@PathVariable String letra){
		return ResponseEntity.ok(service.buscarPorLetra(letra));
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<Cliente>> buscarTodos(){
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/cliente/busca/{keyword}")
	public ResponseEntity<ArrayList<Cliente>> buscarPorPalavraChave(@PathVariable String keyword){
		return ResponseEntity.ok(service.buscarPorPalavrachave(keyword));
	}
	
	@GetMapping("/compras/{id}")
	public ResponseEntity<ArrayList<CompradorDTO>> recuperarCompradores(@PathVariable Integer id){
		return ResponseEntity.ok(service.buscarCompradores(id));
	}
	
	@GetMapping("/aniversariantes/{mes}")
	public ResponseEntity<ArrayList<Cliente>> recuperarAniversariantes(@PathVariable Integer mes){
		return ResponseEntity.ok(service.buscarAniversariantes(mes));
	}

}
