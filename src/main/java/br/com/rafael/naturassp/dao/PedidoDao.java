package br.com.rafael.naturassp.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.model.Pedido;

public interface PedidoDao extends CrudRepository<Pedido, Integer>{

	public ArrayList<Pedido> findAllByCliente(Cliente cliente);
	
}
