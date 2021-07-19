package br.com.rafael.naturassp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.naturassp.services.dto.VendasPorDataDTO;
import br.com.rafael.naturassp.model.Cliente;
import br.com.rafael.naturassp.model.Pedido;

/*
 * Possiveis combinações do filtro:
 * 0-Todos
 * 1-Somente status
 * 2-Somente nome
 * 3-Nome e Status
 * 4-Somente data
 * 5- data e Status
 * 6- data e nome
 * 7-data ,nome e status	
 */
public interface PedidoDao extends CrudRepository<Pedido, Integer>{

	
	public ArrayList<Pedido> findAllByCliente(Cliente cliente);
	
	public ArrayList<Pedido> findAllByClienteInOrderByDataPedidoDesc(Collection<Cliente> clientes);
	
	//combinação 4
	public ArrayList<Pedido> findAllByDataPedidoBetweenOrderByDataPedidoDesc(LocalDate inicio,LocalDate fim);
	
	public ArrayList<Pedido> findAllByStatusOrderByDataPedidoDesc(int status);
	
	// combinação 0
	public ArrayList<Pedido> findAllByOrderByDataPedidoDesc();
	// combinação 1
	public ArrayList<Pedido> findAllByStatusInOrderByDataPedidoDesc(Collection<Integer> status);
	
	//combinação 2
	public ArrayList<Pedido> findAllByClienteIn(Collection<Cliente> cliente);
	
	//combinação 3
	public ArrayList<Pedido> findAllByClienteInAndStatusInOrderByDataPedidoDesc(Collection<Cliente> cliente,Collection<Integer> status);
	
	//combinação 6
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInOrderByDataPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Cliente> cliente);
	
	//combinaçãod 5
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndStatusInOrderByDataPedidoDesc(LocalDate inicio,LocalDate fim , Collection<Integer> status);
	
	//combinação 7
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByDataPedidoDesc(LocalDate inicio,LocalDate fim, Collection<Cliente> cliente , Collection<Integer> status);
	
	

	@Query("SELECT new br.com.naturassp.services.dto.VendasPorDataDTO(sum(valorTotal),dataPedido)"
			+ " FROM Pedido ped WHERE dataPedido BETWEEN :dataIni and :dataFim"
			+ " GROUP BY dataPedido"
			+ " ORDER BY dataPedido DESC")
	public ArrayList<VendasPorDataDTO> recuperarPorData(@Param("dataIni") LocalDate dataIni,
										  @Param("dataFim") LocalDate dataFim);
	
	/*value = "select sum(valor_total) as total,data_pedido as data "
			+ " from tbl_pedido "
			+ " where data_pedido between (date_sub(now(),interval 30 day)) and now() "
			+ " group by (data_pedido) "
			+ " order by data_pedido desc limit 7 ", nativeQuery = true*/
	
}
