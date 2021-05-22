package br.com.rafael.naturassp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.model.Pedido;
import br.com.rafael.naturassp.services.IClienteService;
import br.com.rafael.naturassp.services.IPedidoService;

@RestController
@RequestMapping(value = "/pedido")
@CrossOrigin("*")
public class PedidoController {
	
	@Autowired
	private IPedidoService service;
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Pedido> inserirNovoPedido(@Valid @RequestBody Pedido pedido){
		
		//antes de gravar um novo cliente eu salvo na base
		Cliente cli = clienteService.atualizarDados(pedido.getCliente());
		pedido.setCliente(cli);
		pedido = service.inserirNovo(pedido);
		if(pedido != null) {
			return ResponseEntity.status(201).body(pedido);
		}
		return ResponseEntity.status(400).build();
	}

}
