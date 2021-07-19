package br.com.rafael.naturassp.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.naturassp.services.dto.FiltroPedidoDTO;
import br.com.naturassp.services.dto.VendasPorDataDTO;
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
	
	/*@GetMapping()
	public ResponseEntity<ArrayList<Pedido>> recuperarTodos(){
		return ResponseEntity.ok(service.buscarTodos());
		
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> alterarStatus(@PathVariable int id , @RequestParam(name = "status") int status){
		try {
			Pedido pedido = service.mudarStatus(id, status);
			if(pedido != null) {
				return ResponseEntity.ok(pedido);
			}else {
				return ResponseEntity.badRequest().build();
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
		
	}
	
	@GetMapping("/recentes")
	public ResponseEntity<ArrayList<VendasPorDataDTO>> recuperarVendasMensal(@RequestParam("inicio") String dataIni,@RequestParam("fim") String dataFim){
		LocalDate ini = LocalDate.parse(dataIni);
		LocalDate fim = LocalDate.parse(dataFim);
		return ResponseEntity.ok(service.recuperarTotaisMensal(ini,fim));
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<ArrayList<Pedido>> recuperarTodos(@RequestBody FiltroPedidoDTO filtroPedidoDTO){
		return ResponseEntity.ok(service.filtrarPorVariosCriterios(filtroPedidoDTO));
		
	}
	
}
