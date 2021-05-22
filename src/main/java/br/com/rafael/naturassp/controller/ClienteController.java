package br.com.rafael.naturassp.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
