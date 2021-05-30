package br.com.rafael.naturassp.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.model.Pedido;

public interface PedidoDao extends CrudRepository<Pedido, Integer>{

	public ArrayList<Pedido> findAllByCliente(Cliente cliente);
	
	public ArrayList<Pedido> findAllByDataPedidoBetween(LocalDate inicio,LocalDate fim);
	
	public ArrayList<Pedido> findAllByStatusOrderByDataPedidoDesc(int status);
	
	public ArrayList<Pedido> findAllByOrderByDataPedidoDesc();
	
}
