package br.com.rafael.naturassp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.naturassp.services.dto.CompradorDTO;
import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
	
	// Metodo responsavel por retornar uma palavra chave
	public Cliente findByEmailOrTelefone(String email,String telefone);
	
	//public Cliente findByTelefone(String telefone);
	
	public Cliente findByCpf(String cpf);
	
	public ArrayList<Cliente> findByNomeStartsWith(String prefixo);
	
	public ArrayList<Cliente> findByNomeContaining(String keyword);
	
	public ArrayList<Cliente> findByOrderByNomeAsc();
	
	
	
	/*
	 * select cli.nome,cli.email,cli.telefone
		from tbl_cliente as cli inner join tbl_pedido on tbl_pedido.id_cliente = cli.id_cliente
		inner join tbl_itempedido on tbl_itempedido.id_itempedido = tbl_pedido.id_pedido
		inner join tbl_produto on tbl_itempedido.id_produto = tbl_produto.id_produto
			where tbl_produto.id_produto = 8
	 */
	@Query(value="select cli.nome,cli.email,cli.telefone "
			+ " from tbl_cliente as cli inner join tbl_pedido on tbl_pedido.id_cliente = cli.id_cliente "
			+ " inner join tbl_itempedido on tbl_itempedido.id_itempedido = tbl_pedido.id_pedido "
			+ " inner join tbl_produto on tbl_itempedido.id_produto = tbl_produto.id_produto "
			+ " where tbl_produto.id_produto = :id ", nativeQuery = true)
	public List<Object[]>recuperarCompradores(@Param("id")Integer idProduto);
	
	
	
	@Query(" Select new br.com.rafael.naturassp.model.Cliente(cli.nome,cli.email,cli.dataNasc,cli.telefone) "
			+ " from Cliente cli where month(cli.dataNasc) = :mes")
	public ArrayList<Cliente> recuperarAniversariantes(@Param("mes")Integer mes);

}
